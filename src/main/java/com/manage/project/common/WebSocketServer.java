package com.manage.project.common;

import com.manage.project.service.UserMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@ServerEndpoint(value="/socket/{userId}")
public class WebSocketServer {
    private static final Logger log = LoggerFactory.getLogger(WebSocketServer.class);
    private StringRedisTemplate stringRedisTemplate;
    private UserMessageService userMessageService;
    @PostConstruct
    public void init(){
        log.info("websocket 加载");
    }

    private static AtomicInteger onlineCount = new AtomicInteger(0);
    private static ConcurrentHashMap<String, Session> webSocketSet = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam(value="userId") String userId){
        webSocketSet.put(userId,session);
        addOnlineCount();
        log.info(userId+"加入webSocket连接！当前在线人数为："+onlineCount);
        try{
            sendMessage(session,userId+"加入连接");
        }catch(IOException e){
            log.error("用户"+userId+"网络异常");
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(@PathParam(value="userId") String userId){
        if(webSocketSet.containsKey(userId)){
            webSocketSet.remove(userId);
            subOnlineCount();
            log.info(userId+"断开webSocket连接！当前在线人数为"+onlineCount);
        }
    }

    @OnMessage
    public void onMessage(String message){
        for(Session session:webSocketSet.values()){
            try{
                sendMessage(session,message);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @OnError
    public void onError(Session session,Throwable throwable){
        log.error("发生错误");
        throwable.printStackTrace();
    }

    public void sendMessage(Session session,String message) throws IOException{
        if(session!=null){
            session.getBasicRemote().sendText(message);
        }
    }

    public void sendInfo(String userId,String message){
        Session session = webSocketSet.get(userId);
        try{
            sendMessage(session,message);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void addOnlineCount(){
        onlineCount.incrementAndGet();
    }

    public static void subOnlineCount(){
        onlineCount.decrementAndGet();
    }

}

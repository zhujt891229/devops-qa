package com.manage.project.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

public class HttpUtil {
    private static final Logger log = LoggerFactory.getLogger(HttpUtil.class);
    public static String sendPostWithToken(String url, Map<String,Object> params, String token){
        OutputStreamWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try{
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept","*/*");
            conn.setRequestProperty("connection","Keep-Alive");
            conn.setRequestProperty("Content-Type","application/json");
            conn.setRequestProperty("x-token",token);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out=new OutputStreamWriter(conn.getOutputStream(),"UTF-8");
            out.write(new JSONObject(params).toString());
            out.flush();
            in=new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
            String line;
            while((line=in.readLine())!=null){
                result.append(line);
            }
        }catch(Exception e){
            e.printStackTrace();
            log.error("http request:{} error",url,e);
        }finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result.toString();
    }

    public static String sendPost(String url,Map<String,Object> params){
        OutputStreamWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try{
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept","*/*");
            conn.setRequestProperty("connection","Keep-Alive");
            conn.setRequestProperty("Content-Type","application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new OutputStreamWriter(conn.getOutputStream(),"UTF-8");
            out.write(new JSONObject(params).toString());
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
            String line;
            while((line=in.readLine())!=null){
                result.append(line);
            }
        }catch(Exception e){
            e.printStackTrace();
            log.error("http request:{}",url,e);
        }finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result.toString();
    }

    public static String sendGet(String url,Map<String,String> params){
        StringBuilder result = new StringBuilder();
        BufferedReader in = null;
        try{
            String urlNameString = "";
            if(url!=null&&url.contains("?")){
                urlNameString = url+"&"+getUrlParamsFromMap(params);
            }else{
                urlNameString = url+"?"+getUrlParamsFromMap(params);
            }
            URL realUrl = new URL(urlNameString);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept","*/*");
            conn.setRequestProperty("connnection","Keep-Alive");
            conn.setRequestProperty("Content-Type","application/json");
            conn.connect();
            Map<String, List<String>> map = conn.getHeaderFields();
            for(String key:map.keySet()){
                System.out.println(key+"--->"+map.get(key));
            }
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while((line=in.readLine())!=null){
                line = new String(line.getBytes(),"UTF-8");
                result.append(line);
            }
        }catch(Exception e){
            e.printStackTrace();
            log.error("http request:{} error",url,e);
        }finally{
            try{
                if(in!=null){
                    in.close();;
                }
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result.toString();
    }

    public static String getUrlParamsFromMap(Map<String,String> map){
        String content = null;
        try{
            if(null!=map){
                StringBuilder stringBuilder = new StringBuilder();
                for(Map.Entry<String,String> entry:map.entrySet()){
                    stringBuilder.append(URLEncoder.encode(entry.getKey(),"UTF-8")).append("=")
                            .append(URLEncoder.encode(entry.getValue()==null?"":entry.getValue(),"UTF-8"))
                            .append("&");
                }
                content = stringBuilder.toString();
                if(content.endsWith("&")){
                    content = StringUtils.substringBeforeLast(content,"&");
                }
                return content;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return content;
    }
}

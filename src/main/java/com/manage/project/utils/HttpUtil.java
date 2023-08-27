package com.manage.project.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Map;

public class HttpUtil {

    private static final int timeOut = 60000; // http超时时间
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
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.connect();
            Map<String, List<String>> map = conn.getHeaderFields();
            for(String key:map.keySet()){
                System.out.println(key+"--->"+map.get(key));
            }
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            String line;
            while((line=in.readLine())!=null){
                line = new String(line.getBytes(), StandardCharsets.UTF_8);
                result.append(line);
            }
        }catch(Exception e){
            e.printStackTrace();
            log.error("http request:{} error",url,e);
        }finally{
            try{
                if(in!=null){
                    in.close();
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


    /**
     * get请求，不能访问加密URL
     *
     * @param requestURL 请求地址
     * @return 响应结果
     */
    public static String doGet(String requestURL) {
        BufferedReader inReader = null;
        InputStream in = null;
        String responseBody = "";
        try {

            URL url = new URL(requestURL);
            //Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 8080));
            //HttpURLConnection conn = (HttpURLConnection) url.openConnection(proxy);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置连接超时
            conn.setConnectTimeout(timeOut);
            //设置读取超时
            conn.setReadTimeout(timeOut);
            //设置请求方法
            conn.setRequestMethod("GET");
            //设置请求头信息
            conn.setRequestProperty("Accept","*/*");
            conn.setRequestProperty("Content-Type", "application/json");
            //conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36");
            //conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            //conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36)");
            //conn.setRequestProperty("User-Agent","Chrome/90.0.4430.212");

            conn.setRequestProperty("Connection", "keep-alive");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.setUseCaches(false);//设置不要缓存
            conn.setInstanceFollowRedirects(true);
            conn.setDoOutput(true);
            conn.setDoInput(true);


            conn.setRequestProperty("Referer", "http://www.cwl.gov.cn/ygkj/wqkjgg/");

            /*
             * connect()会根据HttpURLConnection对象的配置值生成HTTP头部信息，且建立tcp连接，但是没有发送http请求
             * 所有的配置信息，都必须在connect()方法之前添加，后面的添加不进去。
             */
            conn.connect();


            /*
             * 开始发起get类型http请求，获取响应数据
             */
            //实际发送url的http请求
            if (HttpURLConnection.HTTP_OK == conn.getResponseCode()) {
                //获取正常响应流
                in = conn.getInputStream();
            } else {
                //获取异常响应流
                in = conn.getErrorStream();
            }
            //读取响应内容
            inReader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            int len;
            char[] tmp = new char[256];
            while ((len = inReader.read(tmp)) > 0) {
                sb.append(tmp, 0, len);
            }
            responseBody = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if (inReader != null) {
                    inReader.close();
                }
                if (in != null) {
                    in.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return responseBody;
    }

    /**
     * get请求，不能访问加密URL
     *
     * @param requestURL         请求地址
     * @param requestPropertyMap url中的请求头键值对
     * @return 响应结果
     */
    public static String doGet(String requestURL, Map<String, String> requestPropertyMap) {
        BufferedReader inReader = null;
        InputStream in = null;
        String responseBody = "";
        try {
            URL url = new URL(requestURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置连接超时
            conn.setConnectTimeout(timeOut);
            //设置读取超时
            conn.setReadTimeout(timeOut);
            //设置请求方法
            conn.setRequestMethod("GET");
            //设置请求头信息
            conn.setRequestProperty("accept","*/*");
            conn.setRequestProperty("Content-Type", "application/json");

            //自动遍历添加请求属性信息(例如：请求头属性等等)
            if (!requestPropertyMap.isEmpty()) {
                for (String key : requestPropertyMap.keySet()) {
                    conn.setRequestProperty(key, requestPropertyMap.get(key));
                }
            }

            /*
             * connect()会根据HttpURLConnection对象的配置值生成HTTP头部信息，且建立tcp连接，但是没有发送http请求
             * 所有的配置信息，都必须在connect()方法之前添加，后面的添加不进去。
             */
            conn.connect();
            /*
             * 开始发起get类型http请求，获取响应数据
             */
            //实际发送url的http请求
            if (HttpURLConnection.HTTP_OK == conn.getResponseCode()) {
                //获取正常响应流
                in = conn.getInputStream();
            } else {
                //获取异常响应流
                in = conn.getErrorStream();
            }
            //读取响应内容
            inReader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            int len;
            char[] tmp = new char[256];
            while ((len = inReader.read(tmp)) > 0) {
                sb.append(tmp, 0, len);
            }
            responseBody = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if (inReader != null) {
                    inReader.close();
                }
                if (in != null) {
                    in.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return responseBody;
    }

    /**
     * get请求，可以访问加密URL
     *
     * @param requestURL         请求地址
     * @param username           url账号
     * @param password           url密码
     * @return 响应结果
     * @throws IOException
     */
    public static String doGet(String requestURL, String username, String password) {
        BufferedReader inReader = null;
        InputStream in = null;
        String responseBody = "";
        try {
            URL url = new URL(requestURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置连接超时Map
            conn.setConnectTimeout(timeOut);
            //设置读取超时
            conn.setReadTimeout(timeOut);
            //设置请求方法
            conn.setRequestMethod("GET");
            //设置请求头信息
            conn.setRequestProperty("accept","*/*");
            conn.setRequestProperty("Content-Type", "application/json");

            //设置安全验证的账号密码
            Base64.Encoder encoder = Base64.getEncoder();
            //对字符串进行编码加密:base64
            String encode = encoder.encodeToString((username + ":" + password).getBytes());
            //为URLConnection 设置“授权”要求属性
            conn.setRequestProperty("Authorization", "Basic " + encode);

            /*
             * connect()会根据HttpURLConnection对象的配置值生成HTTP头部信息，且建立tcp连接，但是没有发送http请求
             * 所有的配置信息，都必须在connect()方法之前添加，后面的添加不进去。
             */
            conn.connect();
            /*
             * 开始发起get类型http请求，获取响应数据
             */
            //实际发送url的http请求
            if (HttpURLConnection.HTTP_OK == conn.getResponseCode()) {
                //获取正常响应流
                in = conn.getInputStream();
            } else {
                //获取异常响应流
                in = conn.getErrorStream();
            }
            //读取响应内容
            inReader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            int len;
            char[] tmp = new char[256];
            while ((len = inReader.read(tmp)) > 0) {
                sb.append(tmp, 0, len);
            }
            responseBody = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if (inReader != null) {
                    inReader.close();
                }
                if (in != null) {
                    in.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return responseBody;
    }
    /**
     * get请求，可以访问加密URL
     *
     * @param requestURL         请求地址
     * @param username           url账号
     * @param password           url密码
     * @param requestPropertyMap url中的请求头键值对
     * @return 响应结果
     * @throws IOException
     */
    public static String doGet(String requestURL, Map<String, String> requestPropertyMap, String username, String password) {
        BufferedReader inReader = null;
        InputStream in = null;
        String responseBody = "";
        try {
            URL url = new URL(requestURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置连接超时Map
            conn.setConnectTimeout(timeOut);
            //设置读取超时
            conn.setReadTimeout(timeOut);
            //设置请求方法
            conn.setRequestMethod("GET");
            //设置请求头信息
            conn.setRequestProperty("accept","*/*");
            conn.setRequestProperty("Content-Type", "application/json");

            //自动遍历添加请求属性信息(例如：请求头属性等等)
            if (!requestPropertyMap.isEmpty()) {
                for (String key : requestPropertyMap.keySet()) {
                    conn.setRequestProperty(key, requestPropertyMap.get(key));
                }
            }
            //设置安全验证的账号密码
            Base64.Encoder encoder = Base64.getEncoder();
            //对字符串进行编码加密:base64
            String encode = encoder.encodeToString((username + ":" + password).getBytes());
            //为URLConnection 设置“授权”要求属性
            conn.setRequestProperty("Authorization", "Basic " + encode);

            /*
             * connect()会根据HttpURLConnection对象的配置值生成HTTP头部信息，且建立tcp连接，但是没有发送http请求
             * 所有的配置信息，都必须在connect()方法之前添加，后面的添加不进去。
             */
            conn.connect();
            /*
             * 开始发起get类型http请求，获取响应数据
             */
            //实际发送url的http请求
            if (HttpURLConnection.HTTP_OK == conn.getResponseCode()) {
                //获取正常响应流
                in = conn.getInputStream();
            } else {
                //获取异常响应流
                in = conn.getErrorStream();
            }
            //读取响应内容
            inReader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            int len;
            char[] tmp = new char[256];
            while ((len = inReader.read(tmp)) > 0) {
                sb.append(tmp, 0, len);
            }
            responseBody = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if (inReader != null) {
                    inReader.close();
                }
                if (in != null) {
                    in.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return responseBody;
    }
    /**
     * post请求，不能访问加密URL
     *
     * @param requestURL 请求地址
     * @param body       请求体
     * @return 响应结果
     * @throws IOException
     */
    public static String doPost(String requestURL, String body) {
        BufferedReader inReader = null;
        InputStream in = null;
        String responseBody = "";
        OutputStream outputStream = null;
        BufferedWriter writer = null;
        try {
            //创建链接地址
            URL url = new URL(requestURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置是否允许从httpUrlConnection读取数据，默认是true
            conn.setDoInput(true);
            //设置是否向httpUrlConnection输出参数，因为这个是post请求，所以必须开启
            conn.setDoOutput(true);
            //设置连接超时
            conn.setConnectTimeout(timeOut);
            //设置读取超时
            conn.setReadTimeout(timeOut);
            //设置请求方法
            conn.setRequestMethod("POST");
            //设置请求头信息
            conn.setRequestProperty("accept","*/*");
            conn.setRequestProperty("Content-Type", "application/json");
            /*
             * connect()会根据HttpURLConnection对象的配置值生成HTTP头部信息，且建立tcp连接，但是没有发送http请求
             * 所有的请求头配置信息，都必须在connect()方法之前添加，后面的添加不进去。
             */
            conn.connect();

            /*
             *  往post连接里面写入必要的请求体-参数
             */
            //获取conn的输出流
            outputStream = conn.getOutputStream();
            //将字节流转换为字符流
            writer = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
            //往连接中写入参数，body可以是name=lmf&age=23键值对拼接形式，也可以是json字符串形式
            writer.write(body);
            //必须刷新流空间的数据
            writer.flush();

            /*
             * 开始发起post类型http请求，获取响应数据
             */
            //实际发送url的http请求
            if (HttpURLConnection.HTTP_OK == conn.getResponseCode()) {
                //获取正常响应流
                in = conn.getInputStream();
            } else {
                //获取异常响应流
                in = conn.getErrorStream();
            }
            //读取响应内容
            inReader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            int len;
            char[] tmp = new char[256];
            while ((len = inReader.read(tmp)) > 0) {
                sb.append(tmp, 0, len);
            }
            //最终响应内容字符串
            responseBody = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if (writer != null) {
                    writer.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inReader != null) {
                    inReader.close();
                }
                if (in != null) {

                    in.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return responseBody;
    }
    /**
     * post请求，可以访问加密URL
     *
     * @param requestURL 请求地址
     * @param body       请求体
     * @param username   url账号
     * @param password   url密码
     * @return 响应结果
     * @throws IOException
     */
    public static String doPost(String requestURL, String body, String username, String password) {
        BufferedReader inReader = null;
        InputStream in = null;
        String responseBody = "";
        OutputStream outputStream = null;
        BufferedWriter writer = null;
        try {
            //创建链接地址
            URL url = new URL(requestURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置是否允许从httpUrlConnection读取数据，默认是true
            conn.setDoInput(true);
            //设置是否向httpUrlConnection输出参数，因为这个是post请求，所以必须开启
            conn.setDoOutput(true);
            //设置连接超时
            conn.setConnectTimeout(timeOut);
            //设置读取超时
            conn.setReadTimeout(timeOut);
            //设置请求方法
            conn.setRequestMethod("POST");
            //设置请求头信息
            conn.setRequestProperty("accept","*/*");
            conn.setRequestProperty("Content-Type", "application/json");
            //设置安全验证的账号密码
            Base64.Encoder encoder = Base64.getEncoder();
            //对字符串进行编码加密:base64
            String encode = encoder.encodeToString((username + ":" + password).getBytes());
            //为URLConnection 设置“授权”要求属性
            conn.setRequestProperty("Authorization", "Basic " + encode);

            /*
             * connect()会根据HttpURLConnection对象的配置值生成HTTP头部信息，且建立tcp连接，但是没有发送http请求
             * 所有的请求头配置信息，都必须在connect()方法之前添加，后面的添加不进去。
             */
            conn.connect();

            /*
             *  往post连接里面写入必要的请求体-参数
             */
            //获取conn的输出流
            outputStream = conn.getOutputStream();
            //将字节流转换为字符流
            writer = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
            //往连接中写入参数，body可以是name=lmf&age=23键值对拼接形式，也可以是json字符串形式
            writer.write(body);
            //必须刷新流空间的数据
            writer.flush();

            /*
             * 开始发起post类型http请求，获取响应数据
             */
            //实际发送url的http请求
            if (HttpURLConnection.HTTP_OK == conn.getResponseCode()) {
                //获取正常响应流
                in = conn.getInputStream();
            } else {
                //获取异常响应流
                in = conn.getErrorStream();
            }
            //读取响应内容
            inReader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            int len;
            char[] tmp = new char[256];
            while ((len = inReader.read(tmp)) > 0) {
                sb.append(tmp, 0, len);
            }
            //最终响应内容字符串
            responseBody = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if (writer != null) {
                    writer.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inReader != null) {
                    inReader.close();
                }
                if (in != null) {
                    in.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return responseBody;
    }


}

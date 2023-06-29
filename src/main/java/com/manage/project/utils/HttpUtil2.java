package com.manage.project.utils;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

public class HttpUtil2 {
    private static final int timeOut = 60000; // http超时时间


    /**
     * get请求，不能访问加密URL
     *
     * @param requestURL 请求地址
     * @return 响应结果
     * @throws IOException
     */
    public static String doGet(String requestURL) throws IOException {
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
            conn.setRequestProperty("Content-Type", "application/json");
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
            if (inReader != null) {
                inReader.close();
            }
            if (in != null) {
                in.close();
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
     * @throws IOException
     */
    public static String doGet(String requestURL, Map<String, String> requestPropertyMap) throws IOException {
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
            if (inReader != null) {
                inReader.close();
            }
            if (in != null) {
                in.close();
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
    public static String doGet(String requestURL, String username, String password) throws IOException {
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
            if (inReader != null) {
                inReader.close();
            }
            if (in != null) {
                in.close();
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
    public static String doGet(String requestURL, Map<String, String> requestPropertyMap, String username, String password) throws IOException {
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
            if (inReader != null) {
                inReader.close();
            }
            if (in != null) {
                in.close();
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
    public static String doPost(String requestURL, String body) throws IOException {
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
    public static String doPost(String requestURL, String body, String username, String password) throws IOException {
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
        }
        return responseBody;
    }
}

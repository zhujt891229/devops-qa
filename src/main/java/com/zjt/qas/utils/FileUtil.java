package com.zjt.qas.utils;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

/**
 * 解析文件工具类
 */
public class FileUtil {

    /**
     * 通过reader解析文件
     * @param file
     * @return
     */
    public static StringBuffer readerMethod(File file) {
        StringBuffer sb=null;
        FileInputStream fis = null;
        InputStreamReader reader = null;
        try {
            fis = new FileInputStream(file);
            reader = new InputStreamReader(fis, StandardCharsets.UTF_8);
            int ch = 0;
            sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            try{
                if(fis!=null){
                    fis.close();
                }
                if(reader!=null){
                    reader.close();
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb;
    }

    /**
     * 通过objectmapper解析json文件
     * @param file
     * @throws IOException
     */
    public static void jacksonMethod(File file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.readValue(file, Map.class));
    }

    /**
     * nio解析文件
     * @param file
     * @throws IOException
     */
    public static void nioMethod(File file) throws IOException {
        String jsonString = new String(Files.readAllBytes(Paths.get(file.getPath())));
        System.out.println(JSONObject.parseObject(jsonString));
    }






}

package com.manage.project.aspect;

public class AspectUtil {
    public static boolean isSelfObject(Object[] args){
        if(args[0].getClass().isPrimitive()){
            return false;
        }
        if(args[0].getClass().equals(String.class)||
                args[0].getClass().equals(Integer.class)||
                args[0].getClass().equals(Byte.class)||
                args[0].getClass().equals(Short.class)||
                args[0].getClass().equals(Long.class)||
                args[0].getClass().equals(Boolean.class)||
                args[0].getClass().equals(Character.class)||
                args[0].getClass().equals(Float.class)||
                args[0].getClass().equals(Double.class)){
            return false;
        }
        return true;
    }
}

package com.shijiuAPI.config;

import java.util.Map;

public class UserThreadLocal {

    private UserThreadLocal(){}
    //线程变量隔离
    private static final ThreadLocal<Map<String,Object>> LOCAL = new ThreadLocal<>();

    public static void put(Map map){
        LOCAL.set(map);
    }

    public static Map get(){
        return LOCAL.get();
    }

    public static void remove(){
        LOCAL.remove();
    }
}
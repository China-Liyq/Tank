package com.chinaliyq.util;

import java.io.IOException;
import java.util.Properties;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/12 - 13:05
 * @Version: 1.0
 **/
public class PropertyMgr {
    private static Properties properties = new Properties();

    static {
        try {
            properties.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object getValue(String key){
        if(properties == null)return null;
        return properties.getProperty(key);
    }

}

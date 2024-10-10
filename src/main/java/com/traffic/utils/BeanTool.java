package com.traffic.utils;

import cn.hutool.json.JSONObject;

import java.lang.reflect.Field;

public class BeanTool {

    public static volatile BeanTool beanTool;

    /**
     * 私有构造
     */
    private BeanTool(){};

    /**
     * 双检锁返回对象
     * @return beanTool
     */
    public static BeanTool getBeanTool(){
        if (beanTool == null){
            synchronized (BeanTool.class){
                if (beanTool == null){
                    beanTool = new BeanTool();
                }
            }
        }
        return beanTool;
    }

    /**
     * 反射将对象转换为JSON对象
     * @param object 原始对象
     * @return JSON对象
     * @throws IllegalAccessException 非法访问异常
     */
    public JSONObject beanToJsonObj(Object object) throws IllegalAccessException {
        JSONObject jsonObject = new JSONObject();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            jsonObject.set(field.getName(), field.get(object));
        }
        return jsonObject;
    }
}

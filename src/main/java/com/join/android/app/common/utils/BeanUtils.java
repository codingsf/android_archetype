package com.join.android.app.common.utils;


import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * User: mawanjin@join-cn.com
 * Date: 14-9-9
 * Time: 下午10:48
 */
public class BeanUtils {
    static ReflectionUtils reflectionUtils = new ReflectionUtils();

    public static void copyProperties(Object dist, Object orig) {

        Field[] fields_orig = orig.getClass().getDeclaredFields();
        Field[] fields_dist = dist.getClass().getDeclaredFields();

        try {

            for (Field field : fields_orig) {
                System.out.println(field.getName());

                String typeNameOfOrig = field.getType().getName();
                String fieldNameOrig = field.getName();
                Method getmethod = reflectionUtils.getDeclaredMethod(orig, addGetString(field.getName()), null);

                Object obj = getmethod.invoke(orig, null);
                for (Field field2 : fields_dist) {
                    Class distType = field2.getType();
                    String typeNameOfdist = field2.getType().getName();
                    String fieldNameDist = field2.getName();
                    if (StringUtils.equals(fieldNameOrig, fieldNameDist)) {
//                        Class clazz = Class.forName(typeNameOfdist);
                        if (StringUtils.containsIgnoreCase(typeNameOfOrig, "string") && StringUtils.containsIgnoreCase(typeNameOfdist, "string")) {
                            Method setmethod = reflectionUtils.getDeclaredMethod(dist, addSetString(field.getName()), distType);
                            setmethod.invoke(dist, obj);

                        }
                        if (StringUtils.containsIgnoreCase(typeNameOfOrig, "long") && StringUtils.containsIgnoreCase(typeNameOfdist, "long")) {
                            Method setmethod = reflectionUtils.getDeclaredMethod(dist, addSetString(field.getName()), distType);
                            setmethod.invoke(dist, obj);

                        }

                        if (StringUtils.containsIgnoreCase(typeNameOfOrig, "int") && StringUtils.containsIgnoreCase(typeNameOfdist, "int")) {

                            Method setmethod = reflectionUtils.getDeclaredMethod(dist, addSetString(field.getName()), distType);
                            setmethod.invoke(dist, obj);
                        }

                        if (StringUtils.containsIgnoreCase(typeNameOfOrig, "float") && StringUtils.containsIgnoreCase(typeNameOfdist, "float")) {

                            Method setmethod = reflectionUtils.getDeclaredMethod(dist, addSetString(field.getName()), distType);
                            setmethod.invoke(dist, obj);
                        }

                        if (StringUtils.containsIgnoreCase(typeNameOfOrig, "Integer") && StringUtils.containsIgnoreCase(typeNameOfdist, "Integer")) {

                            Method setmethod = reflectionUtils.getDeclaredMethod(dist, addSetString(field.getName()), distType);
                            setmethod.invoke(dist, obj);
                        }

                        if (StringUtils.containsIgnoreCase(typeNameOfOrig, "boolean") && StringUtils.containsIgnoreCase(typeNameOfdist, "boolean")) {

                            Method setmethod = reflectionUtils.getDeclaredMethod(dist, addSetString(field.getName()), distType);
                            setmethod.invoke(dist, obj);
                        }

                        if (StringUtils.containsIgnoreCase(typeNameOfOrig, "String") && StringUtils.containsIgnoreCase(typeNameOfdist, "date")) {
                            Method setmethod = reflectionUtils.getDeclaredMethod(dist, addSetString(field.getName()), distType);
                            Date date = new Date(Long.parseLong(String.valueOf(obj)));
                            setmethod.invoke(dist, date);
                        }

                    }
                }

            }
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public static String addGetString(String fieldName) {
        StringBuffer sb = new StringBuffer();
        if(fieldName.startsWith("is")){
            return fieldName;
        }
        sb.append("get");
        sb.append(fieldName.substring(0, 1).toUpperCase());
        sb.append(fieldName.substring(1));
        return sb.toString();
    }

    public static String addSetString(String fieldName) {
        if(fieldName.startsWith("is")){
            return  fieldName.replace("is","set");
        }
        StringBuffer sb = new StringBuffer();
        sb.append("set");
        sb.append(fieldName.substring(0, 1).toUpperCase());
        sb.append(fieldName.substring(1));
        return sb.toString();
    }
}

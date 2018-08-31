package com.yx.util;/*
 * @program: Redis-Register
 *
 * @description:read file
 *
 * @author:yaoxu
 *
 * @create: 2018-08-20 20:53
 **/

import org.apache.log4j.Logger;

import java.util.List;
import java.util.ResourceBundle;

public class ResourceBundleUtil {
    private static final Logger logger = Logger.getLogger(ResourceBundleUtil.class);
    public static ResourceBundle resourceBundle = null;
    public static String name;
    public static String age;
//    static {
//        resourceBundle = ResourceBundle.getBundle("properties") ;
//
//    }
//    public static String getKey(String key) {
//        return resourceBundle.getString(key) ;
//    }

    //    public FileUtil(){
//        ResourceBundle resourceBundle = FileUtil.getProVal("properties") ;
//        name = resourceBundle.getString(name) ;
//        logger.info("name="+name);
//        age = resourceBundle.getString(age) ;
//        logger.info("age="+age);
//    }
    public static ResourceBundle getProVal(String fileName) {
        ResourceBundle resource = null;
        try {
            resource = ResourceBundle.getBundle(fileName);
        } catch (Exception e) {
            logger.error("读取配置文件失败！配置文件传入路径：" + fileName);
            logger.error(e.getMessage());
        }

        return resource;
    }

    //    public static FileUtil fileUtil (){
//        if(fileUtil ==  null){
//            fileUtil = new FileUtil();
//        }
//        return fileUtil;
//    }
    public static void main(String[] args) {
        ResourceBundle resourceBundle = ResourceBundleUtil.getProVal("properties.properties1") ;
        String name = resourceBundle.getString("name") ;
        logger.info("name="+name);
    }
}

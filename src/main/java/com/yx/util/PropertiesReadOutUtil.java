package com.yx.util;

import org.apache.log4j.Logger;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
/**
 * @program: Redis-Register
 * @description: Properties  配置文件处理工具类
 * @author: yaoxu
 * @create: 2018-08-21 11:06
 **/
public class PropertiesReadOutUtil {
    public static final Logger logger = Logger.getLogger(PropertiesReadOutUtil.class);
    //静态块中不能有非静态属性，所以加static
    private String propertiesName;
    private static Properties properties = null;
    public PropertiesReadOutUtil() {
    }
    public PropertiesReadOutUtil(String fileName) {
        this.propertiesName = fileName;
    }
    static {
        properties = new Properties();

        String path = System.getProperty("user.dir");
        logger.info("user.dir path=" + path);
        File filePath = new File(path);
        logger.info("filepath="+filePath);
//        String propertiesPath = filePath.getParent()+ "/properties.properties";
        String propertiesPath = path +"/properties.properties" ;
        logger.info("propertiesPath=" + propertiesPath);
        try {
            properties.load(new FileInputStream(new File(propertiesPath)));
//            properties.load(SourceLoader.class.getClassLoader().getSystemResourceAsStream(propertiesPath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //静态方法可以被类名直接调用
    public static String getValue(String key) {
        return properties.getProperty(key);
    }
    public static void main(String[] args) {
        PropertiesReadOutUtil propertiesReadOutUtil = new PropertiesReadOutUtil();
        logger.info("name=" + propertiesReadOutUtil.getValue("name"));
        logger.info("age=" + propertiesReadOutUtil.getValue("age"));
        logger.info("key=" + propertiesReadOutUtil.getValue(""));

    }
}

package com.yx.DemoUtil;

/**
 * @program: Redis-Register
 * @description: 读取外部log4j的配置文件
 * @author: yaoxu
 * @create: 2018-08-21 13:31
 **/

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
public class ReadLog4JOut {
    public static void main(String[] args) {
        //读取外部的配置文件 log4j.properties

        PropertyConfigurator.configure( "E:\\6、CTP-project\\log4j.properties" );
        Logger logger  =  Logger.getLogger(ReadLog4JOut.class);
        logger.debug( " debug " );
        logger.error( " error " );
    }

}

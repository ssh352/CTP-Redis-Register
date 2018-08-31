package com.yx.exceptions;/*
 * @program: redis-register
 *
 * @description: 异常类
 *
 * @author:yaoxu
 *
 * @create: 2018-08-28 19:48
 **/


import org.apache.log4j.Logger;

public class RegisterException extends Exception{
    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(RegisterException.class) ;
    public RegisterException(){
        super();
    }
    public RegisterException(String message){
        super(message);
        logger.error("errorMsg:["+message+"]");
    }

}

package com.yx.registerMain;

import org.xml.sax.SAXException;

import java.io.IOException;

/**
 * @program: redis-register
 * @description: 注册 consumer 和 provider 信息！
 * @author: yaoxu
 * @create: 2018-08-27 18:28
 **/
public class StartAllRegister {
    public static void main(String[] args) throws Exception {
        ConsumerRegister.StartConsumerRegister();
        ProviderRegister.StartProviderRegister();
    }
}

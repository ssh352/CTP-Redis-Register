package com.yx.registerMain;
 /*
 * @program: CTP-Batch-Register
 * @description:用于向redis中注册消费方的信息   JavaAPI的接入方式
 * @author:yaoxu
 * @create: 2018-08-20 18:07
 **/

import com.nantian.dtp.registry.IRegistryClient;
import com.nantian.dtp.registry.RegistryConnection;
import com.yx.bean.Consumer;
import com.yx.common.RedisConsts;
import com.yx.exceptions.RegisterException;
import com.yx.inter.XmlInterface;
import com.yx.util.PropertiesReadOutUtil;
import com.yx.util.PropertiesUtil;
import com.yx.util.XmlDomUtil;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import java.io.*;
import java.util.List;

public class ConsumerRegister {
    public static final Logger logger = org.apache.log4j.Logger.getLogger(ConsumerRegister.class);

    public static void StartConsumerRegister() throws IOException, SAXException, RegisterException {
        logger.info("===============Consumer Info 开始注册！===================");
        String host = PropertiesReadOutUtil.getValue(RedisConsts.HOST);
        String proPath = System.getProperty("user.dir");
        String xmlPath = proPath+ "/consumer-register-info.xml";
        logger.info("consumer-register-info.xml文件的路径，xmlPath=" + xmlPath);

//        String host = PropertiesUtil.getValue("redis-host") ;
        logger.info("redis host is:" + host);
        XmlInterface xmlInterface = new XmlDomUtil();
        RegistryConnection registryConnection = new RegistryConnection(host);
        IRegistryClient iRegistryClient = registryConnection.createClient();
        com.nantian.dtp.registry.dto.Consumer c = new com.nantian.dtp.registry.dto.Consumer();
//        String xmlPath = "src/main/resources/consumer-register-info.xml";
        List<Consumer> list = xmlInterface.xmlDocument(xmlPath);

        for (Consumer consumer : list) {
            if (consumer.getConsumerName() == null) {
                logger.error("Consumer消费方系统名称为null！");
                throw new RegisterException("consumer消费方系统名称为null！");
            }
            c.setName(consumer.getConsumerName());
            if (consumer.getCAgentName() == null) {
                logger.error("CAgent的名称为null！");
                throw new RegisterException("CAgent的名称为null！");
            }
            c.setAgentName(consumer.getCAgentName());
            if (consumer.getAddress() == null) {
                logger.error("Consumer注册的MQ地址为null！");
                throw new RegisterException("Consumer注册的MQ地址为空！");
            } else {
                if (consumer.getAddress1() != null) {
                    c.setAddress(consumer.getAddress() + "&" + consumer.getAddress1());
                } else {
                    c.setAddress(consumer.getAddress());
                }
            }
            logger.info("注册消费方系统名称：" + consumer.getConsumerName() + ", " + "注册消费方访问的CAgent名称："
                    + consumer.getCAgentName() + ", " + "注册MQ地址：" + consumer.getAddress() + "&" + consumer.getAddress1());
            iRegistryClient.setResource(c);
        }
        logger.info("==================Consumer 注册完成！========================");
    }

    public static void main(String[] args) throws RegisterException {
        try {
            StartConsumerRegister();
        } catch (IOException e) {
            logger.debug(e.getMessage());
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
            logger.error(e.getException());
        }
    }
}

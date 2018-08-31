package com.yx.util;/*
 * @program: Redis-Register
 *
 * @description:读xml中的服务提供方和消费方的注册信息
 *
 * @author:yaoxu
 *
 * @create: 2018-08-20 19:58
 **/

import com.yx.bean.Consumer;
import com.yx.bean.Provider;
import com.yx.inter.XmlInterface;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class XmlDomUtil implements XmlInterface {
    public static final Logger logger = Logger.getLogger(XmlDomUtil.class);
    private static List<Consumer> consumers = null;
    private static List<Provider> providers = null;
    private static DocumentBuilderFactory documentBuilderFactory = null;
    private static DocumentBuilder documentBuilder = null;
    private static Document document = null;
    /*  private ConsumerRegister consumerRegister;
      private int countConsumers;
      private int countProviders;
      public int getCountConsumers() {
          return consumerRegister.getCountConsumer();
      }
      public void setCountConsumers(int countConsumers) {
          this.consumerRegister.setCountConsumer(countConsumers);
      }
      public int getCountProviders() {
          return consumerRegister.getCountProvider();
      }
      public void setCountProviders(int countProviders) {
          this.consumerRegister.setCountProvider(countProviders);
      }
      public XmlDomUtil(int countConsumers) {
          countConsumers = this.countConsumers;
      }
      public XmlDomUtil() {
      }
  */
    static {
        try {
            documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
    public List<Consumer> xmlDocument(String xmlPath) throws IOException, SAXException {
        logger.info("Start To Read Xml File！");
        int countC = 0;
       /* String proPath = System.getProperty("user.dir");
        File filePath = new File(proPath);
        String xmlPath = filePath.getParent() + "/consumer-register-info.xml";
        logger.info("redis-register-info.xml文件的路径，xmlPath=" + xmlPath);*/
        //将给定uri的内容解析为一个xml文档，并返回Document对象
//         document = documentBuilder.parse(new File(xmlPath));//使用外部路径使用
        document = documentBuilder.parse(xmlPath);
        document.getDocumentElement().normalize();
        //按照文档顺序返回包含在文档中且具有给定标记名称的所有Element的nodelist
        NodeList consumerList = document.getElementsByTagName("Consumer");
        consumers = new ArrayList<Consumer>();
        logger.info("consumer注册信息共配置【" + consumerList.getLength() + "】个！");
        //遍历Consumer
        for (int i = 0; i < consumerList.getLength(); i++) {
            Consumer consumer = new Consumer();
            //获取第i个consumer节点
            Node systemNode = consumerList.item(i);
            //获取i个consumer的所有属性
            NamedNodeMap namedNodeAttr = systemNode.getAttributes();
            //获取 属性值
            String sysSide = namedNodeAttr.getNamedItem("name").getTextContent();
            String systemDescirption = namedNodeAttr.getNamedItem("descirption").getTextContent();
            String flag = namedNodeAttr.getNamedItem("flag").getTextContent();
            logger.info("系统接入方：[" + sysSide + "], " + "系统描述：[" + systemDescirption + "], flag:[" + flag+"]");
            if ("off".equals(flag)) {
                continue;
            } else {
                //获取consumer节点的子节点
                NodeList consumerChildNode = systemNode.getChildNodes();
                ArrayList<String> contents = new ArrayList<String>();
                for (int j = 0; j < consumerChildNode.getLength(); j++) {
                    Node cNode = consumerChildNode.item(j);
                    NamedNodeMap namedNodeMap = cNode.getAttributes();
                    if (cNode.getNodeType() == Node.ELEMENT_NODE) {
                        String value = cNode.getFirstChild().getTextContent();
                        contents.add(value);
                    }
                }
                consumer.setConsumerName(contents.get(0));
                consumer.setCAgentName(contents.get(1));
//                consumer.setProtocol(contents.get(2));
                consumer.setAddress(contents.get(2));
                consumer.setAddress1(contents.get(3));
                consumers.add(consumer);
                logger.info("Consumer消费方的contents=" + contents);
                countC++;
            }
        }
        logger.info("共注册Consumer=[" + countC + "]个！");
        return consumers;
    }
    public List<Provider> xmlDocumentProvider(String xmlPath) throws IOException, SAXException {
        int countP = 0;
        logger.info("Start To Read File！Loading Provider info!");
       /* String proPath = System.getProperty("user.dir");
        File filePath = new File(proPath);
        String xmlPath = filePath.getParent() + "/redis-register-info.xml";
        logger.info("redis-register-info.xml文件的路径，xmlPath=" + xmlPath);*/
        //将给定uri的内容解析为一个xml文档，并返回Document对象
//         document = documentBuilder.parse(new File(xmlPath));//使用外部路径使用
        document = documentBuilder.parse(xmlPath);
        document.getDocumentElement().normalize();
        //按照文档顺序返回包含在文档中且具有给定标记名称的所有Element的nodelist
        NodeList providerList = document.getElementsByTagName("Provider");
//        String type = document.getChildNodes();
        providers = new ArrayList<Provider>();
        logger.info("共配置 provider=" + providerList.getLength() + "  个！");
        //遍历Provider
        for (int i = 0; i < providerList.getLength(); i++) {
            Provider provider = new Provider();
            //获取第i个provider
            Node systemNode = providerList.item(i);
            //获取i个provider的所有属性
            NamedNodeMap namedNodeAttr = systemNode.getAttributes();
            //获取 属性值
            String sysSide = namedNodeAttr.getNamedItem("name").getTextContent();
            String systemDescirption = namedNodeAttr.getNamedItem("descirption").getTextContent();
            String flag = namedNodeAttr.getNamedItem("flag").getTextContent();
            logger.info("系统接入方：[" + sysSide + "], " + "系统描述：[" + systemDescirption + "], flag:[" + flag+"]");
            if ("off".equals(flag)) {
                continue;
            } else {
                countP++;
                //获取provider节点的子节点
                NodeList providerChildNode = systemNode.getChildNodes();
                ArrayList<String> contents = new ArrayList<String>();
                for (int j = 0; j < providerChildNode.getLength(); j++) {
                    Node cNode = providerChildNode.item(j);
                    NamedNodeMap namedNodeMap = cNode.getAttributes();
                    if (cNode.getNodeType() == Node.ELEMENT_NODE) {
                        String value = cNode.getFirstChild().getTextContent();
                        contents.add(value);
                    }
                }
                if (contents.size() == 5) {
                    logger.debug("【提供方接入方式为JavaAPI/tuxedo/tibco/socket适配器】");
                    provider.setProviderName(contents.get(0));
                    provider.setPagentName(contents.get(1));
                    provider.setProtocol(contents.get(2));
                    provider.setAddress(contents.get(3));
                    provider.setAddress1(contents.get(4));
                    providers.add(provider);
                } else if (contents.size() == 4) {
                    logger.debug("【提供方接入方式为Socket！(tcp-short)】");
                    provider.setProviderName(contents.get(0));
                    provider.setPagentName(contents.get(1));
                    provider.setProtocol(contents.get(2));
                    provider.setAddress(contents.get(3));
                    providers.add(provider);
                } else if (contents.size() == 3) {
                    logger.debug("【提供方接入方式为SpringCloud！】");
                    provider.setProviderName(contents.get(0));
                    provider.setPagentName(contents.get(1));
                    provider.setAddress(contents.get(2));
                    providers.add(provider);
                }
                /*provider.setProviderName(contents.get(0));
                provider.setPagentName(contents.get(1));
                if (contents.get(2) == null) {
                    logger.debug("SpringCloud方式接入不需要配置协议信息！");
                } else {
                    provider.setProtocol(contents.get(2));
                }
                provider.setAddress(contents.get(3));
                if (contents.get(4) == null) {
                    logger.debug("注册信息不需要配置address1！");
                } else {
                    provider.setAddress1(contents.get(4));
                }*/
                logger.info("Provider提供方的contents=" + contents);
            }
        }
        logger.info("共注册Provider=[" + countP + "]个！");
        return providers;
    }
}

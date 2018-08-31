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

public  class XmlDomUtilBak implements XmlInterface {
    public static final Logger logger = Logger.getLogger(XmlDomUtilBak.class);
    private static List<Consumer> consumers = null;
    private static List<Provider> providers = null ;
    private static DocumentBuilderFactory documentBuilderFactory = null;
    private static DocumentBuilder documentBuilder = null;
    private static Document document = null;
    static{
        try {
            documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public List<Consumer> xmlDocument(String xmlPath) throws IOException, SAXException {
        logger.info("read xml file start！");
       /* String proPath = System.getProperty("user.dir");
        File filePath = new File(proPath);
        String xmlPath = filePath.getParent() + "/redis-register-info.xml";
        logger.info("redis-register-info.xml文件的路径，xmlPath=" + xmlPath);*/
        //将给定uri的内容解析为一个xml文档，并返回Document对象
//         document = documentBuilder.parse(new File(xmlPath));//使用外部路径使用
        document = documentBuilder.parse(xmlPath) ;
        document.getDocumentElement().normalize();
        //按照文档顺序返回包含在文档中且具有给定标记名称的所有Element的nodelist
        NodeList consumerList = document.getElementsByTagName("Consumer");
        NodeList providerList = document.getElementsByTagName("Provider");
        consumers = new ArrayList<Consumer>();
        logger.info("共注册consumer=" + consumerList.getLength() + "系统");
        logger.info("共注册provider=" + providerList.getLength() + "系统");
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

            logger.info("系统接入方：" + sysSide + ", " + "系统描述：" + systemDescirption + " flag:" + flag);
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
                consumer.setProtocol(contents.get(2));
                consumer.setAddress(contents.get(3));
                consumer.setAddress1(contents.get(4));
                consumers.add(consumer);
                logger.info("contents=" + contents);
            }

        }
        return consumers;
    }

    public List<Provider> xmlDocumentProvider(String xmlPath) throws IOException, SAXException {
        logger.info("read xml file start！loading Provider info!");
       /* String proPath = System.getProperty("user.dir");
        File filePath = new File(proPath);
        String xmlPath = filePath.getParent() + "/redis-register-info.xml";
        logger.info("redis-register-info.xml文件的路径，xmlPath=" + xmlPath);*/
        //将给定uri的内容解析为一个xml文档，并返回Document对象
//         document = documentBuilder.parse(new File(xmlPath));//使用外部路径使用
        document = documentBuilder.parse(xmlPath) ;
        document.getDocumentElement().normalize();
        //按照文档顺序返回包含在文档中且具有给定标记名称的所有Element的nodelist
        NodeList providerList = document.getElementsByTagName("Provider");
        consumers = new ArrayList<Consumer>();
        logger.info("共注册provider=" + providerList.getLength() + "系统");
        //遍历Consumer
        for (int i = 0; i <providerList.getLength(); i++) {
            Provider provider = new Provider();
            //获取第i个provider
            Node systemNode = providerList.item(i);

            //获取i个provider的所有属性
            NamedNodeMap namedNodeAttr = systemNode.getAttributes();
            //获取 属性值
            String sysSide = namedNodeAttr.getNamedItem("name").getTextContent();
            String systemDescirption = namedNodeAttr.getNamedItem("descirption").getTextContent();
            String flag = namedNodeAttr.getNamedItem("flag").getTextContent();

            logger.info("系统接入方：" + sysSide + ", " + "系统描述：" + systemDescirption + " flag:" + flag);
            if ("off".equals(flag)) {
                continue;
            } else {
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
                provider.setProviderName(contents.get(0));
                provider.setPagentName(contents.get(1));
                provider.setProtocol(contents.get(2));
                provider.setAddress(contents.get(3));
                provider.setAddress1(contents.get(4));
                providers.add(provider);
                logger.info("contents=" + contents);
            }

        }
        return providers;
    }
}
/*                NodeList nodeList = systemNode.getChildNodes();
                for (int j=0 ; j < nodeList.getLength() ; j++){
                    Node typeNode = nodeList.item(j) ;
                    if (typeNode.getNodeType() == Node.ELEMENT_NODE){
                        NamedNodeMap namedNodeMapAttr = typeNode.getAttributes() ;
                        String name = namedNodeMapAttr.item(0).getNodeValue() ;
                        String flag = namedNodeMapAttr.item(1).getNodeValue() ;
                        logger.info("注册系统ID："+name+" ,"+"注册开关状态："+flag);
                        if("off".equals(flag)){
                            logger.info(name+"的注册状态为off，跳过！");
                            continue;
                        }
                        Map<String,String> infoMap = new HashMap<String, String>() ;
                        NodeList infoList = typeNode.getChildNodes() ;
                        for (int k=0;i<infoList.getLength() ;k++){
                            Node infoNode = infoList.item(k) ;
                            NamedNodeMap infoNameNodeMap =infoNode.getAttributes() ;
                            if (infoNode.getNodeType()==Node.ELEMENT_NODE){
                                String mapkey = infoNode.getTextContent() ;

                                String mapValue = infoNode.getNodeValue() ;
                                infoMap.put(mapkey,mapValue) ;
                                logger.info("mapKey="+mapkey+",value="+mapValue);

                            }
                        }
                    }
                }*/



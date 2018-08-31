package com.yx.registerMain;
import com.nantian.dtp.registry.IRegistryClient;
import com.nantian.dtp.registry.RegistryConnection;
import com.yx.bean.Provider;
import com.yx.common.RedisConsts;
import com.yx.exceptions.RegisterException;
import com.yx.inter.XmlInterface;
import com.yx.util.PropertiesReadOutUtil;
import com.yx.util.PropertiesUtil;
import com.yx.util.XmlDomUtil;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.util.List;
/**
 * @program: CTP-Batch-Register
 * @description: Provider注册
 * @author: yaoxu
 * @create: 2018-08-25 21:54
 **/
public class ProviderRegister {
    public static final Logger logger = Logger.getLogger(ProviderRegister.class);
    public static void StartProviderRegister() throws IOException, SAXException, RegisterException {
        logger.info("==================Provider Info 开始注册！==========================");
        //用于读取外部文件
        String proPath = System.getProperty("user.dir");//获取当前工程路径
        String xmlPath = proPath + "/provider-register-info.xml";
        logger.info("consumer-register-info.xml文件的路径，xmlPath=" + xmlPath);
        //end
        XmlInterface xmlInterface = new XmlDomUtil();
        String host = PropertiesReadOutUtil.getValue(RedisConsts.HOST);//读取外部配置文件
//        String host = PropertiesUtil.getValue("redis-host") ;
        logger.info("redis host is:" + host);
        RegistryConnection registryConnection = new RegistryConnection(host);
        IRegistryClient iRegistryClient = registryConnection.createClient();
        com.nantian.dtp.registry.dto.Provider p = new com.nantian.dtp.registry.dto.Provider();
//        String xmlPath = "src/main/resources/provider-register-info.xml";//使用外部文件的时候注释掉该文件路径
        List<Provider> list = xmlInterface.xmlDocumentProvider(xmlPath);
        for (Provider provider : list) {
            if (provider.getProviderName() == null) {
                logger.error("注册信息提供方名称为null，请检查配置文件内容是否填写正确！");
                throw new RegisterException("注册信息提供方名称为空!请检查配置文件内容是否填写正确！");
            }
            p.setName(provider.getProviderName());
            if (provider.getPagentName() == null) {
                logger.error("注册信息PAgent名称为空！");
                throw new RegisterException("注册信息PAgent名称为空！[" + provider.getProviderName() + "]");
            }
            p.setAgentName(provider.getPagentName());
            if (provider.getProtocol() == null) {
                logger.warn("提供方使用SpringCloud方式接入时不需要注册MQ协议!");
            } else {
                p.setProtocol(provider.getProtocol());
            }
            if (provider.getAddress() == null) {
                logger.error("注册信息中MQ的得治为空！");
                throw new RegisterException("注册信息中MQ的地址为空！");
            } else {
                String address = provider.getAddress();
                p.setAddress(address);
                if (provider.getAddress1() == null) {
                } else {
                    p.setAddress(provider.getAddress() + "&" + provider.getAddress1());
                }
            }
            iRegistryClient.setResource(p);
            logger.info("【provider:" + provider+"】");
        }
        logger.info("========================Provider 注册完成！==============================");
    }
    public static void main(String[] args) throws IOException, SAXException, RegisterException {
        StartProviderRegister();
    }
}

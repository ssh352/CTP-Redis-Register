package com.yx.inter;

import com.yx.bean.Consumer;
import com.yx.bean.Provider;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.List;

/**
 * @program: redis-register
 * @description: xml  parse interface
 * @author: yaoxu
 * @create: 2018-08-25 21:59
 **/
public interface XmlInterface {
    public List<Consumer> xmlDocument(String xmlPath) throws IOException, SAXException;
    public List<Provider> xmlDocumentProvider(String xmlPath) throws IOException, SAXException;
//    public void xmlDom4j(String fileName);
//    public void xmljdom(String fileName);
//    public void xmlSax(String fileName);
}

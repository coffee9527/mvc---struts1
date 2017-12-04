package struts1.config;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import struts1.common.action.Action;

/**
 * 使用DOM方式解析xml配置文件
 * @author coffee
 *
 */
public class ParseConfig {
	private static Map<String, XmlBean> beanMap;
	
	public static void parse(String xmlPath) {
		String localPath = ParseConfig.class.getClassLoader().getResource("").getPath();
		
		beanMap = new HashMap<String, XmlBean>();
		
		 // Ⅰ获得DocumentBuilderFactory  
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
		
        try {  
            // ❷Ⅱ获得DocumentBuilder  
            DocumentBuilder builder = factory.newDocumentBuilder(); 
            // ❸Ⅲ--获得文档对象--  
            Document doc = builder.parse(localPath+xmlPath);  
            // ❹Ⅳ获得根元素  
            Element element = doc.getDocumentElement();  
            
            NodeList nodeList = element.getElementsByTagName("request");
            for(int i=0;i<nodeList.getLength();i++) {
            	XmlBean xmlBean = new XmlBean();
            	
            	//request元素
            	Node request = nodeList.item(i);
            	NamedNodeMap attributes = request.getAttributes();
            	Node namedItem = attributes.getNamedItem("path");
            	String path = namedItem.getNodeValue();
            	xmlBean.setPath(path);
            	
            	NodeList nodeList2 = request.getChildNodes();
            	
            	for(int j=0;j<nodeList2.getLength();j++) {
            		 if(nodeList2.item(j).getNodeType() != Node.TEXT_NODE){
            			//form-bean元素
            			 Node node = nodeList2.item(j);
            			 
            			 if("form-bean".equals(node.getNodeName())) {
            				 NamedNodeMap formBeanAttrs = node.getAttributes();
                          	Node formBeanClass = formBeanAttrs.getNamedItem("class");
                          	String formBeanPath = formBeanClass.getNodeValue();
                          	xmlBean.setFormBeanPath(formBeanPath);
            			 }
            			 if("action-bean".equals(node.getNodeName())) {
            				//action-bean元素
                          	NamedNodeMap actionBeanAttrs = node.getAttributes();
                          	Node actionBeanClass = actionBeanAttrs.getNamedItem("class");
                          	String actionBeanPath = actionBeanClass.getNodeValue();
                          	xmlBean.setActionBeanPath(actionBeanPath);
                          	
                          	//forward元素
                          	NodeList forwards = node.getChildNodes();
                          	for(int k=0;k<forwards.getLength();k++) {
                          		Node item = forwards.item(k);
                          		if("forward".equals(item.getNodeName())) {
                          			Node forward = item;
                          			
                          			NodeList forwardChildren = forward.getChildNodes();
                          			for(int m=0;m<forwardChildren.getLength();m++) {
                          				Node item2 = forwardChildren.item(m);
                          				if("success".equals(item2.getNodeName())) {
                          					//success元素
                                         	Node success = item2;
                                         	String successUrl = success.getAttributes().getNamedItem("value").getNodeValue();
                                         	xmlBean.getUrlMap().put(Action.success, successUrl);
                          				}
                          				if("failure".equals(item2.getNodeName())) {
                          					//failure元素
                                         	Node failure = item2;
                                         	String failureUrl = failure.getAttributes().getNamedItem("value").getNodeValue();
                                         	xmlBean.getUrlMap().put(Action.failure, failureUrl);
                          				}
                          			}
                                 	
                                 	
                          		}
                          		
                          	}
                         	
            			 }	               	
                     	
            		 }
            	}
            	beanMap.put(path, xmlBean);
            }
        } catch (ParserConfigurationException e) {  
            e.printStackTrace();  
        } catch (SAXException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
	}
	
	public static Map<String, XmlBean> getConfigMap() {
		return beanMap;
	}
}

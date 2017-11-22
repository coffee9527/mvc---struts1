package example2.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 加载formbean和action的配置
 * @author coffee
 *
 */
public class ConfigMap {
	private static final String FORMBEAN_CONFIGPATH = "/example2/config/formbean-config.properties";
	private static final String ACTION_CONFIGPATH = "/example2/config/action-config.properties";
	private static Properties formBeanPro = new Properties();
	private static Properties actionBeanPro = new Properties();
	static{
		InputStream in = null;
		try {
			in =  ConfigMap.class.getResourceAsStream(FORMBEAN_CONFIGPATH);
			formBeanPro.load(in);
			in = ConfigMap.class.getResourceAsStream(ACTION_CONFIGPATH);
			actionBeanPro.load(in);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static String getFormBeanPath(String pathName) {
		return formBeanPro.getProperty(pathName);
	}
	
	public static String getActionPath(String pathName) {
		return actionBeanPro.getProperty(pathName);
	}
}

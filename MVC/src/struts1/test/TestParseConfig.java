package struts1.test;

import java.util.Map;

import org.junit.Test;

import struts1.config.ParseConfig;
import struts1.config.XmlBean;

public class TestParseConfig {
	@Test
	public void parseConfigTest() {
		Map<String, XmlBean> beanMap = ParseConfig.parse("D://eclipse_mars//MVC//src//struts1//config//struts1-config.xml");
		System.out.println(beanMap.toString());
	}
}

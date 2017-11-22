package struts1.config;

import java.util.HashMap;
import java.util.Map;

public class XmlBean {
	private String path;
	private String formBeanPath;
	private String actionBeanPath;
	private Map<String, String> urlMap = new HashMap<String, String>();
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getFormBeanPath() {
		return formBeanPath;
	}
	public void setFormBeanPath(String formBeanPath) {
		this.formBeanPath = formBeanPath;
	}
	public String getActionBeanPath() {
		return actionBeanPath;
	}
	public void setActionBeanPath(String actionBeanPath) {
		this.actionBeanPath = actionBeanPath;
	}
	public Map<String, String> getUrlMap() {
		return urlMap;
	}
	public void setUrlMap(Map<String, String> urlMap) {
		this.urlMap = urlMap;
	}
	
	
}

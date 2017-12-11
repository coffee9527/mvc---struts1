package annotation.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import annotation.common.action.Action;
import annotation.myannotation.MyAction;

public class ParseConfig {
	private static List<String> classPathList = new ArrayList<String>();
	private static Map<String, XmlBean> map = new HashMap<String, XmlBean>();
	private static ParseConfig parseConfig;
	
	private ParseConfig() {
		super();
	}
	
	public static ParseConfig getInstance() {
		if(parseConfig == null) {
			synchronized(ParseConfig.class) {
				if(parseConfig == null) {
					parseConfig = new ParseConfig();
				}
			}
		}
		return parseConfig;
	}
	
	//方法入口
	public void parse(String propPath) {
		if(propPath == null) {
			throw new RuntimeException();
		}
		parseProp(propPath);
	}
	/**
	 * 解析properties文件
	 * @param propPath
	 * @return 文件路径
	 */
	private void parseProp(String propPath) {
//		String suffix = ".properties";
//		if(propPath.endsWith(suffix)) {
//			propPath = propPath.substring(0, propPath.length()-1-suffix.length());
//		}
		
		//ResourceBundle resource = ResourceBundle.getBundle(propPath);
		//parseFile(resource.getString("base-package"));  
		Properties formBeanPro = new Properties();
		InputStream resourceAsStream = ParseConfig.class.getResourceAsStream(propPath);
		try {
			formBeanPro.load(resourceAsStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		parseFile(formBeanPro.getProperty("base-package"));
	}
	
	/**
	 * 根据文件路径找到相应的注解
	 * @param propPath
	 * @return
	 */
	private void parseFile(String path) {
		//把以"."连接的路径替换成以"/"连接的路径
		path = path.replace(".", "/");
		//得到项目路径
		String path2 = ParseConfig.class.getClassLoader().getResource(".").getPath();
		System.out.println(path2);

		
		//相对路径,
		//TODO 有待改进
		path = path2 + path;
		File filePath = new File(path);
		getFile(filePath);
	}
	
	private void getFile(File file) {
		if(file.isFile()) {
			System.out.println(file.getName());
			return;
		}
		File[] listFiles = file.listFiles();
		for(File f : listFiles) {
			if(f.isFile()) {
				if(f.getName().endsWith(".class")) {
					String absolutePath = file.getPath()+"\\"+f.getName().replace(".class", "");
					String classes = "classes";
					String classPath = absolutePath.substring(absolutePath.indexOf(classes)+classes.length()+1);
					classPath = classPath.replace("\\", ".");
					classPathList.add(classPath);
					System.out.println(classPath);
				}
			}else if(f.isDirectory()) {
				getFile(f);
			}
		}
		
		getAnnotation();
	}
	
	private void getAnnotation() {
		for(String classPath : classPathList) {
			try {
				Class<?> forName = Class.forName(classPath);
				if(forName.isAnnotationPresent(MyAction.class)) {
					parseMyAction(forName, classPath);
				}
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	private void parseMyAction(Class<?> forName, String classPath) {
		MyAction annotation = forName.getAnnotation(MyAction.class);
		String url = annotation.value();
		if("".equals(url)) {
			String packagePath = forName.getName();
			String[] packages = packagePath.split("\\.");
			String name = packages[packages.length-1];
			String a = name.substring(0, 1).toLowerCase();
			String b = name.substring(1, name.length());
			url = "/" + a + b;
		}
		XmlBean xmlBean = new XmlBean();
		xmlBean.setActionBeanPath(classPath);
		if(Exception.class != annotation.bean()) {
			xmlBean.setFormBeanPath(annotation.bean().getName());
		}
		xmlBean.setPath(annotation.value());
		Map<String, String> urlMap = xmlBean.getUrlMap();
		urlMap.put(Action.success, annotation.successUrl());
		urlMap.put(Action.failure, annotation.failureUrl());
		
		map.put(url, xmlBean);
	}
	public Map<String, XmlBean> getMap() {
		return map;
	}
	
	
}

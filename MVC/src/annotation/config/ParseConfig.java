package annotation.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import annotation.myannotation.MyAction;

public class ParseConfig {
private static List<String> classPathList = new ArrayList<String>();
	
	//方法入口
	public static void parse(String propPath) {
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
	private static void parseProp(String propPath) {
		String suffix = ".properties";
		if(propPath.endsWith(suffix)) {
			propPath = propPath.substring(0, propPath.length()-1-suffix.length());
		}
		ResourceBundle resource = ResourceBundle.getBundle(propPath);
		parseFile(resource.getString("base-package"));  
	}
	
	/**
	 * 根据文件路径找到相应的注解
	 * @param propPath
	 * @return
	 */
	private static void parseFile(String path) {
		File filePath = new File(path);
		getFile(filePath);
	}
	
	private static void getFile(File file) {
		if(file.isFile()) {
			System.out.println(file.getName());
			return;
		}
		File[] listFiles = file.listFiles();
		for(File f : listFiles) {
			if(f.isFile()) {
				if(f.getName().endsWith(".java")) {
					classPathList.add(file.getAbsolutePath()+""+f.getName().replace(".java", ""));
				}
			}else if(f.isDirectory()) {
				getFile(f);
			}
		}
		
		getAnnotation();
	}
	
	private static void getAnnotation() {
		for(String classPath : classPathList) {
			try {
				Class<?> forName = Class.forName(classPath);
				if(forName.isAnnotationPresent(MyAction.class)) {
					parseMyAction(forName);
				}
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	private static void parseMyAction(Class<?> forName) {
		MyAction annotation = forName.getAnnotation(MyAction.class);
		String url = annotation.value();
		if("".equals(url)) {
			
			String name = forName.getName();
			String a = name.substring(0, 1).toLowerCase();
			String b = name.substring(1, name.length()-1);
			url = a + b;
		}
		Class<?> beanForm = annotation.bean();
		if(Exception.class != beanForm) {
			
		}
		String success = annotation.successUrl();
		String failure = annotation.failureUrl();

	}
}

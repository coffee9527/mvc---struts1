package annotation.test;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;

import org.junit.Test;

import annotation.common.action.Action;

public class AnnotationTest {
	
	@Test
	public void fileList() {
		final String filterName = "";
		try {
			//File path = new File("MVC/src/annotation");
			Class<? extends AnnotationTest> class1 = this.getClass();
			ClassLoader classLoader = class1.getClassLoader();
			URL resource = classLoader.getResource(".");
			String path2 = resource.getPath();
			String libpath2 = URLDecoder.decode(path2, "UTF-8");  
			System.out.println(libpath2);
			File path = new File(libpath2);
//			String[] list;
//			if(filterName == null || "".equals(filterName)) 
//				list = path.list();
//			else
//				list = path.list(new FilenameFilter() {
//					String fn = filterName;
//					@Override
//					public boolean accept(File dir, String name) {
//						String f = new File(name).getName();
//						return f.indexOf(fn) != -1;
//					}
//				});
//			for(int i=0;i<list.length;i++) {
//				System.out.println(list[i]+":");
//				if(list[i].contains(".")) {
//					if(list[i].endsWith(".java")) {
//						System.out.print(list[i] + " ");
//					}
//				} else {
//					
//				}
//			}
			
				getFile(path);

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getFile(File file) {
		if(file.isFile()) {
			System.out.println(file.getName());
			return;
		}
		File[] listFiles = file.listFiles();
		for(File f : listFiles) {
			if(f.isFile()) {
				System.out.println(f.getName());
			}else if(f.isDirectory()) {
				getFile(f);
			}
		}

	}
	
	/**
	 * 通过类对象获取类路径
	 */
	@Test
	public void classPathTest() {
		//类路径，用"."来连接
		String name = Action.class.getName();
		//类名称
		String simpleName = Action.class.getSimpleName();
		System.out.println(name);
		System.out.println(simpleName);
	}
	
	/**
	 * 项目路径
	 */
	public void projectPathTest() {
		String relativelyPath=System.getProperty("user.dir"); 
	}
}

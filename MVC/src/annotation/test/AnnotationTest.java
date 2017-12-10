package annotation.test;

import java.io.File;
import java.io.FilenameFilter;

import org.junit.Test;

public class AnnotationTest {
	
	@Test
	public void fileList() {
		final String filterName = "";
		try {
			File path = new File("MVC/src/annotation");
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
}

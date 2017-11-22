package example2.common.servlet;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import example2.common.action.Action;
import example2.common.formbean.FormBean;
import example2.config.ConfigMap;
import struts1.config.XmlBean;

@WebServlet(urlPatterns="*.do")//打包时此处不应配置，在使用时再根据情况配置拦截路径
public class ActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L; 


    public ActionServlet() {
        super();
    }
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) {
//---------------获取路径和formbean的对应配置---------
		//获取路径
		String pathName = getPathName(request.getServletPath());
		String formbeanPath = ConfigMap.getFormBeanPath(pathName);
		String actionPath = ConfigMap.getActionPath(pathName);
		String url = "";
		try {
				Class formBeanClazz = Class.forName(formbeanPath);
				FormBean formBean  = (FormBean)formBeanClazz.newInstance();
				Field[] fields = formBeanClazz.getDeclaredFields();
				for(Field field : fields) {
					field.setAccessible(true);
					String param = request.getParameter(field.getName());
					field.set(formBean, param);
					field.setAccessible(false);
				}
				
				Class actionClass = Class.forName(actionPath);
				Action action  = (Action)actionClass.newInstance();
				url = action.execute(request, response, formBean);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
			requestDispatcher.forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private String getPathName(String url) {
		return url.split("\\.")[0];
	}

}

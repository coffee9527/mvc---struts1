package annotation.common.servlet;

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

import annotation.common.action.Action;
import annotation.common.formbean.FormBean;
import annotation.config.ParseConfig;
import annotation.config.XmlBean;


@WebServlet(value="*.cn",initParams={@WebInitParam(name="configPath",value="/annotation/config/mvc-config.properties")})//应在配置文件中配置
public class ActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String configPath;  
	private static Map<String, XmlBean> configMap;

    public ActionServlet() {
        super();
    }
    
    /**
     * 初始化参数
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
          //获取初始值username
    	configPath = config.getInitParameter("configPath");
    	ParseConfig instance = ParseConfig.getInstance();
    	instance.parse(configPath);
    	configMap = instance.getMap();

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
		XmlBean xmlBean = configMap.get(pathName);
		String formbeanPath = xmlBean.getFormBeanPath();
		String actionPath = xmlBean.getActionBeanPath();
		String url = "";
		try {
			String result;
			if(formbeanPath != null) {
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
				result = action.execute(request, response, formBean);
			}else {
				Class actionClass = Class.forName(actionPath);
				Action action  = (Action)actionClass.newInstance();
				result = action.execute(request, response, null);
			}
				
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(xmlBean.getUrlMap().get(result));
			requestDispatcher.forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private String getPathName(String url) {
		return url.split("\\.")[0];
	}

}

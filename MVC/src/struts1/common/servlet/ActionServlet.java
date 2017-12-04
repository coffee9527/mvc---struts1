package struts1.common.servlet;

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

import example2.config.ConfigMap;
import struts1.common.action.Action;
import struts1.common.formbean.FormBean;
import struts1.config.ParseConfig;
import struts1.config.XmlBean;


@WebServlet(value="*.com",initParams={@WebInitParam(name="configPath",value="struts1/config/struts1-config.xml")})//应在配置文件中配置
public class ActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * servelt是单例的，为避免线程安全问题，不使用成员变量
	 */
//	private static String configPath;  
//	private static Map<String, XmlBean> configMap;

    public ActionServlet() {
        super();
    }
    
    /**
     * 初始化参数
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
          //获取初始值配置文件
    	ParseConfig.parse(config.getInitParameter("configPath"));

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
		XmlBean xmlBean = ParseConfig.getConfigMap().get(pathName);
		String formbeanPath = xmlBean.getFormBeanPath();
		String actionPath = xmlBean.getActionBeanPath();
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
				String result = action.execute(request, response, formBean);
			
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

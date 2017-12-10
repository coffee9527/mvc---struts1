package annotation.common.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import struts1.common.formbean.FormBean;


/**
 * Action接口用来执行具体的逻辑处理，并返回处理后的调转路径
 * @author coffee
 *
 */
public interface Action {
	String success = "success";
	String failure = "failure";
	
	/**
	 * 
	 * @param request	
	 * @param response	
	 * @param formBean	封装jsp属性的bean对象
	 * @return 处理完成后调转路径
	 */
	String execute(HttpServletRequest request, HttpServletResponse response, FormBean formBean);
}

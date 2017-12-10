package annotation.register;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import annotation.myannotation.MyAction;
import struts1.common.action.Action;
import struts1.common.formbean.FormBean;

/**
 * 注册页面跳转页面
 * @author coffee
 *
 */
@MyAction(successUrl="/WEB-INF/jsp/register3/register.jsp")
public class ToRegisterAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, FormBean formBean) {
		return success;
	}

}

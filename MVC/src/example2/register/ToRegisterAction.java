package example2.register;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import example2.common.action.Action;
import example2.common.formbean.FormBean;

public class ToRegisterAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, FormBean formBean) {
		return "/WEB-INF/jsp/register2/register.jsp";
	}

}

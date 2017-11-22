package struts1.register;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import struts1.common.action.Action;
import struts1.common.formbean.FormBean;


public class ToRegisterAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, FormBean formBean) {
		return success;
	}

}

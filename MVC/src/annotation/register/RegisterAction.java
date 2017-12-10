package annotation.register;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import annotation.myannotation.MyAction;
import struts1.common.action.Action;
import struts1.common.formbean.FormBean;

@MyAction(successUrl="")
public class RegisterAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response,
			FormBean formBean) {
		return null;
	}

}

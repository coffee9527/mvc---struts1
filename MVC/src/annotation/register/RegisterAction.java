package annotation.register;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import annotation.common.action.Action;
import annotation.common.formbean.FormBean;
import annotation.myannotation.MyAction;

@MyAction(successUrl="")
public class RegisterAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response,
			FormBean formBean) {
		return null;
	}

}

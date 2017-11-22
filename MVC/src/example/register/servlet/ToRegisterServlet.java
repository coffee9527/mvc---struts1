package example.register.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * 跳转用户注册页面的servlet
 * @author Administrator
 *
 */
@WebServlet("/toRegister")
public class ToRegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String successUrl = "/WEB-INF/jsp/register/register.jsp"; 

	public ToRegisterServlet() {
        super();
        System.out.println("------------------初始化ToRegisterServlet对象");
    }
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("----------------执行ToRegisterServlet的get方法");
    	RequestDispatcher requestDispatcher = request.getRequestDispatcher(successUrl);
		requestDispatcher.forward(request, response);
	}
    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("----------------执行ToRegisterServlet的post方法");
		doGet(request, response);
	}

}

package example.login.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import example.common.formbean.Student;
import example.common.formbean.SudentDateBase;

/**
 * servlet只负责调用处理请求的相应对象并返回结果页面路径，不负责具体处理
 * @author coffee
 *
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //登陆成功跳转路径
	private static final String successUrl = "/WEB-INF/jsp/login/loginsuccess.jsp";
	//登陆失败页面
	private static final String failureUrl = "/WEB-INF/jsp/login/loginsuccess.jsp";
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//-----------------------------获取页面数据-----------------------------------------
		/**
		 * 此处相当于使用了硬编码，可以把页面的属性封装成一个bean对象的属性，配置bean的路径(requestPath - beanPath)，再通过反射获取对象，这样就可以获取对象属性
		 * 注意：此处的bean和与数据库中表映射相对应的bean不同，它是是对jsp页面中form元素的name属性的的映射
		 */
		String name = request.getParameter("name");
		String password = request.getParameter("password");
//-----------------------------业务逻辑-----------------------------------------
		/**
		 * 业务逻辑应该在业务层处理，不应该写在servlet中，为此我们可以创建一个专门处理对应请求的action类(requestPath - actionPath)
		 * 同时，action类还要返回处理后跳转的路径
		 */
		//获取所有注册的学生
		List<Student> studentList = SudentDateBase.selectAll();
		RequestDispatcher requestDispatcher = null;
		for(Student student : studentList) {
			if(student.getName().equals(name) && student.getPassword().equals(password)) {
				//用户名和密码正确，登陆成功，跳转成功页面
				request.setAttribute("name", name);
				requestDispatcher = request.getRequestDispatcher(successUrl);	
				requestDispatcher.forward(request, response);
				return;
			}
		}
		//设置错误信息
		request.setAttribute("message", "用户名或密码错误");
//-----------------------------返回结果页面-----------------------------------------
		//没跳转成功就跳转失败
		requestDispatcher = request.getRequestDispatcher(failureUrl);	
		requestDispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

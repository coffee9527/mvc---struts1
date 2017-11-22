package example.register.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coffee.mvc.util.ConversionUtil;

import example.common.formbean.Student;
import example.common.formbean.SudentDateBase;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //成功页面路径
	private static final String successUrl = "/WEB-INF/jsp/register/registersuccess.jsp";
	//失败页面
	private static final String failureUrl = "/WEB-INF/jsp/register/registerfailure.jsp";
    public RegisterServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//----------------------------获取页面参数----------------------------
		/**
		 * 此处相当于使用了硬编码，可以把页面的属性封装成一个bean对象的属性，配置bean的路径(requestPath - beanPath)，再通过反射获取对象，这样就可以获取对象属性
		 * 注意：此处的bean和与数据库中表映射相对应的bean不同，它是是对jsp页面中form元素的name属性的的映射
		 */
		//获取页面参数
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String age = request.getParameter("age");
		String birthday = request.getParameter("birthday");
//-----------------------------业务逻辑-----------------------------------------
		/**
		 * 业务逻辑应该在业务层处理，不应该写在servlet中，为此我们可以创建一个专门处理对应请求的action类(requestPath - actionPath)
		 * 同时，action类还要返回处理后跳转的路径
		 */
		//如果用户名或密码为空则注册失败
		if(name == null || "".equals(name)) {
			request.setAttribute("message", "姓名不能为空");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(failureUrl);
			requestDispatcher.forward(request, response);
			return;
		}
		if(password == null || "".equals(password)) {
			request.setAttribute("message", "密码不能为空");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(failureUrl);
			requestDispatcher.forward(request, response);
			return;
		}
		
		//创建学生对象并初始化
		Student student = new Student(name, password, Integer.valueOf(age),ConversionUtil.string2Date(birthday));
		//保存到学生数据库中
		SudentDateBase.save(student);
		//跳转成功页面
		request.setAttribute("name", name);
//-----------------------------返回结果页面-----------------------------------------
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(successUrl);
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

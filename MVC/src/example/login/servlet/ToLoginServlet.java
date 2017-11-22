package example.login.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/toLogin")
public class ToLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //登陆路径
	private static final String loginUrl = "/WEB-INF/jsp/login/login.jsp";

    public ToLoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 在处理没有数据传递的请求时，也要有一个action来处理要返回的页面路径，即action仅仅返回loginUrl就可以了
		 */
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(loginUrl);
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

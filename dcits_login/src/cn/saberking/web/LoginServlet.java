package cn.saberking.web;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.saberking.pojo.User;
import cn.saberking.service.UserService;
import cn.saberking.service.impl.UserServiceImpl;

//@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService = new UserServiceImpl();

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 第一步，接收参数
		request.setCharacterEncoding("utf-8");
		// 用户名
		String username = request.getParameter("username");
		// 密码
		String password = request.getParameter("password");
		// 第二步，处理业务
		User loginUser = userService.login(username, password);
		// 第三步，输出
		// 方式一：直接指定ContentType
//		response.setContentType("text/html;charset=utf-8");
		// 方式二：分别指定
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		if (loginUser != null) {
			// 方式一：传统方式
			// Date date = new Date();
			// SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			// String loginTime = sd.format(date);
			// 方式二：java8新特性
			// 获取本地时间
			LocalDateTime now = LocalDateTime.now();
			// 以系统本地样式格式化日期
			DateTimeFormatter dateTime = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
			// 以自定义样式格式化日期，和和SimpleDateFormat相似
			// DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
			request.setAttribute("msg", "登陆成功！");
			request.getSession().setAttribute("user", loginUser);
			request.setAttribute("username", username);
			request.setAttribute("loginTime", dateTime.format(now));
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			request.setAttribute("errMsg", "登陆失败，用户名或密码错误！");
//			response.sendRedirect("login.jsp");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}

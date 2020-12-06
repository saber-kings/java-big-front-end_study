package cn.saberking.web;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.saberking.pojo.User;
import cn.saberking.service.UserService;
import cn.saberking.service.impl.UserServiceImpl;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService = new UserServiceImpl();

	public LogoutServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取当前登录的用户对象
		User user = null;
		Object sessionUser = request.getSession().getAttribute("user");
		if (sessionUser instanceof User) {
			user = (User) sessionUser;
		}
		boolean isLogout = userService.logout(user);
		if (isLogout) {
			// 传统方式删除session
			request.getSession().invalidate();
			response.sendRedirect("login.jsp");
		} else {
			// 获取本地时间
			LocalDateTime now = LocalDateTime.now();
			// 以系统本地样式格式化日期
			DateTimeFormatter dateTime = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
			request.setAttribute("msg", "登出失败！");
			request.setAttribute("username", user.getUsername());
			request.setAttribute("loginTime", dateTime.format(now));
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}

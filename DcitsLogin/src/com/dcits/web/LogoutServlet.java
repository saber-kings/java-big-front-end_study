package com.dcits.web;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dcits.pojo.User;
import com.dcits.service.UserService;
import com.dcits.service.impl.UserServiceImpl;

/**
 * 登出请求
 * @author saber-kings
 *
 */
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
			// 删除session
			request.getSession().invalidate();
			//重定向到登录页
			response.sendRedirect("login.jsp");
		} else {
			// 获取本地时间
			LocalDateTime now = LocalDateTime.now();
			// 以系统本地样式格式化日期
			DateTimeFormatter dateTime = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
			//向前端传递登陆失败信息
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

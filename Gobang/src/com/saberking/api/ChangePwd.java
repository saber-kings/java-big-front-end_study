package com.saberking.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saberking.service.UserService;

import lombok.Cleanup;

/**
 * 用户修改密码
 * 
 * @author luanz
 *
 */
@WebServlet("/changePwd")
public class ChangePwd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService = new UserService();

	public ChangePwd() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 第一步，接收参数
		String uid = request.getParameter("uid");
		String newPwd = request.getParameter("pwd");
		// 第二步，处理业务
		String s = userService.changePwd(uid, newPwd);
		// 第三步，输出
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		@Cleanup
		PrintWriter out = response.getWriter();

		out.println(s);
		out.flush();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}

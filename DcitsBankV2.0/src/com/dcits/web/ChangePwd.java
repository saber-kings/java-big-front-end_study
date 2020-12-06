package com.dcits.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dcits.service.AccountService;

/**
 * 修改密码请求
 *
 */
@WebServlet("/changePwd")
public class ChangePwd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AccountService accountService = new AccountService();
	public ChangePwd() {
		super();
	}

	 @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 第一步，接收参数
		String aid = request.getParameter("aid");
		String newPwd = request.getParameter("password");
		// 第二步，处理业务
		String s = accountService.changePwd(aid, newPwd);
		// 第三步，输出
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println(s);
		out.flush();
		out.close();
	}

	 @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}

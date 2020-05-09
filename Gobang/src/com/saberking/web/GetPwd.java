package com.saberking.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saberking.service.AdminService;

import lombok.Cleanup;

/**
 * 根据管理员ID获取其密码密码
 * 
 * @author luanz
 *
 */
@WebServlet("/admin/getPwd")
public class GetPwd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminService adminService = new AdminService();

	public GetPwd() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 第一步，接收参数
		String aid = request.getParameter("aid");
		// 第二步，处理业务
		String s = adminService.getPwd(aid);
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

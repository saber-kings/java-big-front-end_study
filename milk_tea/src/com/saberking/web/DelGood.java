package com.saberking.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saberking.service.GoodService;

import lombok.Cleanup;

@WebServlet("/delGood")
public class DelGood extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private GoodService goodService = new GoodService();

	public DelGood() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 第一步，接收参数
		String uid = request.getParameter("uid");
		// 第二步，处理业务
		String s = goodService.delete(uid);
		// 第三步，输出
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		// @Cleanup: 自动将一个可关闭流在最后调用 close() 关闭
		@Cleanup PrintWriter out = response.getWriter();

		out.println(s);
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}

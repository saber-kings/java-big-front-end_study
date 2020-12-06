package com.dcits.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dcits.service.MenuService;

/**
 * 查询菜单请求
 *
 */
@WebServlet("/getMenus")
public class GetMenus extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MenuService menuService = new MenuService();

	public GetMenus() {
		super();
	}

	 @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 第一步，接收参数
		// 第二步，处理业务
		String s = menuService.getMenus();
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

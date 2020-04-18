package com.saberking.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.saberking.pojo.Custom;
import com.saberking.service.CustomService;
import com.saberking.utils.RequestToBean;

import lombok.Cleanup;

@WebServlet("/getCustoms")
public class GetCustoms extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CustomService customService = new CustomService();

	public GetCustoms() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 第一步，接收参数
		// 第二步，处理业务
		String s = customService.listAll();
		// 第三步，输出
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		@Cleanup PrintWriter out = response.getWriter();

		out.println(s);
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 第一步，接收参数
		// 方法一：用 axios 的 POST 提交 JSON 格式的数据，所以使用 fastjson 转换成 javabean 对象
		Custom custom = JSON.parseObject(RequestToBean.getRequestPostStr(request), Custom.class);
		// 第二步，处理业务
		String s = customService.search(custom);
		// 第三步，输出
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		@Cleanup PrintWriter out = response.getWriter();

		out.println(s);
		out.flush();
	}

}

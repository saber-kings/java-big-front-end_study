package com.saberking.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.saberking.pojo.Publish;
import com.saberking.service.PublishService;
import com.saberking.utils.RequestToBean;

import lombok.Cleanup;

/**
 * 公告查询
 * 
 * @author luanz
 *
 */
@WebServlet("/getPublishs")
public class GetPublishs extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PublishService publishService = new PublishService();

	public GetPublishs() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 第一步，接收参数
		String page = request.getParameter("page");
		String size = request.getParameter("size");
		// 第二步，处理业务
		String s = publishService.pageInfo(page, size);
		// 第三步，输出
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		@Cleanup PrintWriter out = response.getWriter();

		out.println(s);
		out.flush();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 第一步，接收参数
		// 方法一：用 axios 的 POST 提交 JSON 格式的数据，所以使用 fastjson 转换成 javabean 对象
		Publish publish = JSON.parseObject(RequestToBean.getRequestPostStr(request), Publish.class);
		// 第二步，处理业务
		String s = publishService.search(publish);
		// 第三步，输出
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		@Cleanup PrintWriter out = response.getWriter();

		out.println(s);
		out.flush();
	}

}

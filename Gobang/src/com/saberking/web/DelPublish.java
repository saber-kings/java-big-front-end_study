package com.saberking.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.saberking.service.PublishService;
import com.saberking.utils.RequestToBean;

import lombok.Cleanup;

/**
 * 删除公告
 * 
 * @author luanz
 *
 */
@WebServlet("/delPublish")
public class DelPublish extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PublishService publishService = new PublishService();

	public DelPublish() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 第一步，接收参数
		String pid = request.getParameter("pid");
		// 第二步，处理业务
		String s = publishService.delete(pid);
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
		// 第一步，接收参数
		// 方法一：用 axios 的 POST 提交 JSON 格式的数据，所以使用 fastjson 转换成 javabean 对象
		List<String> pids = JSON.parseArray(RequestToBean.getRequestPostStr(request), String.class);
		// 第二步，处理业务
		String s = publishService.batchDel(pids);
		// 第三步，输出
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		@Cleanup
		PrintWriter out = response.getWriter();

		out.println(s);
		out.flush();
	}

}

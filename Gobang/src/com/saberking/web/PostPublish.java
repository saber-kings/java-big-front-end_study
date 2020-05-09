package com.saberking.web;

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
 * 管理员发布/修改公告
 * 
 * @author luanz
 *
 */
@WebServlet("/postPublish")
public class PostPublish extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PublishService publishService = new PublishService();

	public PostPublish() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 第一步，接收参数
		Publish publish = JSON.parseObject(RequestToBean.getRequestPostStr(request), Publish.class);
		// 第二步，处理业务
		String s;
		if (publish.getId() == 0) {
			// 发布
			s = publishService.publish(publish);
		} else {
			// 修改
			s = publishService.update(publish);
		}
		// 第三步，输出
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		@Cleanup
		PrintWriter out = response.getWriter();

		out.println(s);
		out.flush();
	}

}

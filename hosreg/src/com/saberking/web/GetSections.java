package com.saberking.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.saberking.service.SectionService;

import lombok.Cleanup;

@WebServlet("/getSections")
public class GetSections extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private SectionService sectionService = new SectionService();

	public GetSections() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 第一步，接收参数
		String id=request.getParameter("id");
		// 第二步，处理业务
		String s = null;
		if (StringUtils.isNotBlank(id)) {
			s = sectionService.getById(id);
		} else {
			s = sectionService.getAll();
		}
		// 第三步，输出
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		// 如果使用lombok的 @Cleanup: 将自动在最后调用 close() 关闭可关闭流
		@Cleanup
		PrintWriter out = response.getWriter();

		out.println(s);
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}

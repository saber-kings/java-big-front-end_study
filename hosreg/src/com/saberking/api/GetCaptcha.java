package com.saberking.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Cleanup;

@WebServlet("/getCaptcha")
public class GetCaptcha extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetCaptcha() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 第一步，接收参数
//		String phone = request.getParameter("phone");
		// 第二步，处理业务
		// 使用自己写的方法生成6位随机数
//		String myCode = SendMsgCode.createRandomVcode();
		// 使用 commons-lang 工具类，生成6位随机数
//		String code = RandomStringUtils.randomNumeric(6);
		// 调用 sdk 发送短信验证码
//		SendMsgCode.sendCode(phone, code);
		// 第三步，输出
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		@Cleanup PrintWriter out = response.getWriter();

		out.println("{\"code\":\"456789\"}");
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}

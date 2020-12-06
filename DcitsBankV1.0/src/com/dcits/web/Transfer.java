package com.dcits.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dcits.service.AccountService;

/**
 * 转账请求
 *
 */
@WebServlet("/transfer")
public class Transfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AccountService accountService = new AccountService();
       
    public Transfer() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 第一步，接收参数
		String from = request.getParameter("from");
		String to = request.getParameter("to");
		BigDecimal money = new BigDecimal(request.getParameter("money"));
		// 第二步，处理业务
		String s = accountService.transfer(from, to, money);
		// 第三步，输出
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		// 如果使用lombok的 @Cleanup: 将自动在最后调用 close() 关闭可关闭流
		PrintWriter out = response.getWriter();

		out.println(s);
		out.flush();
		out.close();
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}

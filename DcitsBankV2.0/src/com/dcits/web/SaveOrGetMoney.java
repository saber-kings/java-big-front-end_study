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
 * 存取款请求
 *
 */
@WebServlet("/saveOrGetMoney")
public class SaveOrGetMoney extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AccountService accountService = new AccountService();
       
    public SaveOrGetMoney() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 第一步，接收参数
		String id = request.getParameter("id");
		BigDecimal balance = new BigDecimal(request.getParameter("balance"));
		// 第二步，处理业务
		String s = accountService.saveOrGetMoney(id, balance);
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

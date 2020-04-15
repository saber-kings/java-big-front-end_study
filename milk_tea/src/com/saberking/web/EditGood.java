package com.saberking.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.saberking.pojo.Good;
import com.saberking.service.GoodService;
import com.saberking.utils.RequestToBean;

/**
 * Servlet implementation class EditGood
 */
@WebServlet("/editGood")
public class EditGood extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private GoodService goodService = new GoodService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditGood() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 第一步，接收参数
		// 方法一：用 axios 的 POST 提交 JSON 格式的数据，所以使用 fastjson 转换成 javabean 对象
		Good good = JSON.parseObject(RequestToBean.getRequestPostStr(request), Good.class);
		// 方法二：用 mui 的 POST 方式提交正常接收
//		Good good = RequestToBean.getBeanToRequest(request, Good.class);
		// 第二步，处理业务
		String s = goodService.update(good);
		// 第三步，输出
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println(s);
		out.flush();
		out.close();
	}

}

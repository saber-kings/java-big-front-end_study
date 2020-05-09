package com.saberking.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.saberking.pojo.Product;
import com.saberking.service.ProductService;
import com.saberking.utils.RequestToBean;

import lombok.Cleanup;

@WebServlet("/schedule")
public class Schedule extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProductService productService = new ProductService();

	public Schedule() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 第一步，接收参数，以下可能用到阿里巴巴的 fastjson 组件，必须用到工具类：RequestToBean
		// 方法一：用 axios 的 POST 提交 JSON 格式的数据，所以使用 fastjson 转换成 javabean 对象
		Product product = JSON.parseObject(RequestToBean.getRequestPostStr(request), Product.class);
		// 第二步，处理业务
		String s = productService.save(product);
		// 第三步，输出
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		// 如果使用lombok的 @Cleanup: 将自动在最后调用 close() 关闭可关闭流
		@Cleanup
		PrintWriter out = response.getWriter();

		out.println(s);
		out.flush();
	}

}

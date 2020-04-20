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

import lombok.Cleanup;

@WebServlet("/putGood")
public class PutGood extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private GoodService goodService = new GoodService();

	public PutGood() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 第一步，接收参数
		// 方法一：用 axios 的 POST 提交 JSON 格式的数据，所以使用 fastjson 转换成 javabean 对象
		Good good = JSON.parseObject(RequestToBean.getRequestPostStr(request), Good.class);
		// 方法二：用 mui 的 POST 方式提交正常接收，这里使用了 BeanUtils 组件将表单信息封装成类，
		// 但是这种方式在使用时必须规范 set 和 get方法的命名规范，比如 gName -> setgName，
		// 而在使用了 Lombok 的 @Data 注解后是 setGName，
		// 就导致无法封装（报错：抛出类型无法找到异常：org.apache.commons.collections.FastHashMap），
		// 解决方法：要么使用就还是用方法一，要么将 commons-collections-xxx.jar 添加进项目（注意不能是4），
		// 但是就算加了也只是不报类型找不到异常的错了，但是前台发送的数据还是无法封装给对象，所有 set 和 get 不规范的字段都无法赋值
		// 总结：保险期间最好还是用第一种吧，使用 json 格式传数据
//		Good good = RequestToBean.getBeanToRequest(request, Good.class);
		// 打印添加的商品信息
//		System.out.println(good);
		// 第二步，处理业务
		String s = goodService.add(good);
		// 第三步，输出
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		@Cleanup
		PrintWriter out = response.getWriter();

		out.println(s);
		out.flush();
	}

}

package com.dcits.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.dcits.pojo.PersonInfo;
import com.dcits.service.AccountService;
import com.dcits.service.PersonInfoService;
import com.dcits.utils.RequestToBean;

/**
 * 查询或修改个人信息请求
 *
 */
@WebServlet("/getOrUpInfo")
public class GetOrUpInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PersonInfoService infoService = new PersonInfoService();

	private AccountService accountService = new AccountService();

	public GetOrUpInfo() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 第一步，接收参数
		String aid = request.getParameter("aid");
		// 第二步，处理业务
		String s = infoService.getByAid(aid);
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 第一步，接收参数，以下可能用到阿里巴巴的 fastjson 组件，必须用到工具类：RequestToBean
		// 用 axios 的 POST 提交 JSON 格式的数据，所以使用 fastjson 转换成 javabean 对象
		PersonInfo info = JSON.parseObject(RequestToBean.getRequestPostStr(request), PersonInfo.class);
		// 第二步，处理业务
		String s = accountService.upInfo(info);
		// 第三步，输出
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		// 如果使用lombok的 @Cleanup: 将自动在最后调用 close() 关闭可关闭流
		PrintWriter out = response.getWriter();

		out.println(s);
		out.flush();
		out.close();
	}

}

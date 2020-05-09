package com.saberking.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saberking.service.RoomService;

import lombok.Cleanup;

/**
 * 房间列表查询
 * 
 * @author luanz
 *
 */
@WebServlet("/getRooms")
public class GetRooms extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private RoomService roomService = new RoomService();

	public GetRooms() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 第一步，接收参数
		// 第二步，处理业务
		String s = roomService.allRooms();
		// 第三步，输出
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		// 如果使用lombok的 @Cleanup: 将自动在最后调用 close() 关闭可关闭流
		@Cleanup
		PrintWriter out = response.getWriter();

		out.println(s);
		out.flush();
		out.close();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}

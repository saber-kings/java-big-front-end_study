package com.saberking.mydatastructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

import javax.websocket.Session;

import com.alibaba.fastjson.JSON;
import com.saberking.dao.UserDao;
import com.saberking.dao.WorkshopDao;
import com.saberking.pojo.Product;
import com.saberking.pojo.User;
import com.saberking.pojo.Workshop;

public class Singleton {
	private WorkshopDao workshopDao = new WorkshopDao();

	private UserDao userDao = new UserDao();

	// 单例模式
	private Singleton() {
		// 初始化登陆的在线用户列表
		this.userOnline = new HashMap<>();

		// 获得所有用户的 id 集合
		List<String> userIds = userDao.getAllIds();
		// 初始所有用户的消息队列
		this.groupMessages = new HashMap<>();
		if (userIds != null) {
			userIds.forEach(s -> {
				this.groupMessages.put(s, new ArrayList<String>());
			});
		}

		// 初始化所有车间
		this.workshopRoom = new HashMap<>();

		// 得到所有车间（不分1/2/3）
		List<Workshop> workshops = workshopDao.getAll();

		// 遍历所有车间将每一个车间分成1/2/3三条生产线队列，然后将所有车间加入
		// 方式一for循环
//		for (int i = 0; i < workshops.size(); i++) {
//			// 得到一个车间
//			Workshop workshop = workshops.get(i);
//
//			Map<String, Queue<Product>> map = new HashMap<>();
//			// 1
//			map.put("1", new MyQueue<Product>());
//			// 2
//			map.put("2", new MyQueue<Product>());
//			// 3
//			map.put("3", new MyQueue<Product>());
//
//			// 将当前车间加入
//			this.workshopRoom.put(workshop.getIdString(), map);
//		}

		// 方式二：lambda方式 （推荐）
		this.workshopRoom = workshops.stream().collect(Collectors.toMap(Workshop::getIdString, workshop -> {
			// 将每一个车间分为1/2/3三条生产线
			Map<String, Queue<Product>> map = new HashMap<>();
			// 1
			map.put("1", new MyQueue<Product>());
			// 2
			map.put("2", new MyQueue<Product>());
			// 3
			map.put("3", new MyQueue<Product>());
			return map;
		}));
	}

	private static Singleton singleton;

	public synchronized static Singleton getSingleton() {
		if (singleton == null) {
			singleton = new Singleton();
		}
		return singleton;
	}

	// 登陆的在线用户列表
	private Map<String, Session> userOnline;

	// 所有车间（包括所有生产线）的排产排队队列
	private Map<String, Map<String, Queue<Product>>> workshopRoom;

	// 每个用户的消息队列
	private Map<String, List<String>> groupMessages;

	public synchronized void saveUser(String uid, Session session) {
		// 加入在线用户列表
		this.userOnline.put(uid, session);
		// 打印在线用户数量
//		System.out.println(this.userOnline.size());
	}

	public synchronized List<String> getMessages(String uid) {
		// 取得该用户的所有公告
		return this.groupMessages.get(uid);
	}

	public synchronized Boolean isNotOnline(String uid) {
		Session session = this.userOnline.get(uid);
		if (session == null) {
			return true;
		} else {
			return false;
		}
	}

	public synchronized void sendMsg(String uid, String msg) {
		Session session = this.userOnline.get(uid);
		try {
			session.getBasicRemote().sendText(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized void groupSend(String id, String msg) {
		User user = userDao.loginedGetById(id);
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", user.getRealName());
		map.put("msg", msg);
		String notice = JSON.toJSON(map).toString();
		this.userOnline.forEach((k, v) -> {
			try {
				v.getAsyncRemote().sendText(notice);
				this.groupMessages.forEach((uid, messages) -> {
					// 将公告加到用户的消息队列里
					messages.add(notice);
					// 如果用户未登录且消息队列的消息超过20条就清空
					if (isNotOnline(uid) && messages.size() > 20) {
						messages.clear();
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public synchronized void removeUser(String uid) {
		this.userOnline.remove(uid);
	}

	// 当用户退出时，或者点击收到消息就清空该用户消息队列
	public synchronized boolean receive(String uid) {
		List<String> messages = this.groupMessages.get(uid);
		if (messages != null) {
			messages.clear();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 排产
	 * 
	 * @param product 要排产的产品
	 */
	public synchronized void scheduling(Product product) {
		// 产品排产的车间 id
		System.out.println("wid:" + product.getWidString());
		// 获得其排产的车间
		Map<String, Queue<Product>> map = this.workshopRoom.get(product.getWidString());
		// 根据产品排产员安排的生产线 id 获得其要排产的生产线所在的产品生产排队队列（分1/2/3三条生产线）
		Queue<Product> queue = map.get(product.getPline());
		// 打印排产前的排产产品数量
		System.out.println("添加之前：" + String.valueOf(queue.size()));
		// 将产品加入生产队列
		queue.add(product);
		// 打印排产后的排产产品数量
		System.out.println("添加之后：" + String.valueOf(queue.size()));
	}

	/**
	 * 生产
	 * 
	 * @param wid   车间 id
	 * @param pline 产品生产线（1/2/3）
	 * @return 返回生产的产品
	 */
	public synchronized Product producing(String wid, String pline) {
		System.out.println("车间wid:" + wid);
		// 获得当前车间
		Map<String, Queue<Product>> map = this.workshopRoom.get(wid);
		// 得到对应生产线的产品的生产队列
		Queue<Product> queue = map.get(pline);
		// 打印生产前的排产产品数量
		System.out.println("生产之前：" + String.valueOf(queue.size()));
		// 从排队排产的产品中取出下一个产品，即生产
		Product product = queue.poll();
		// 打印生产后的排产产品数量
		System.out.println("生产之后：" + String.valueOf(queue.size()));
		return product;
	}

	/**
	 * 获得车间生产线
	 * 
	 * @param wid 车间 id
	 * @return 返回车间的生产线
	 */
	public synchronized List<String> getPlines(String wid) {
		System.out.println("车间wid:" + wid);
		// 获得当前车间
		Map<String, Queue<Product>> map = this.workshopRoom.get(wid);
		// 得到当前车间的生产线 id
		return new ArrayList<String>(map.keySet());
	}

	/**
	 * 获得车间生产线的生产队列
	 * 
	 * @param wid   车间 id
	 * @param pline 产品生产线（1/2/3）
	 * @return 车间生产线的生产队列
	 */
	public synchronized MyQueue<Product> getProductQueue(String wid, String pline) {
		System.out.println("车间wid:" + wid);
		// 获得当前车间
		Map<String, Queue<Product>> map = this.workshopRoom.get(wid);
		// 得到对应生产线的产品的生产队列
		MyQueue<Product> queue = (MyQueue<Product>) map.get(pline);
		if (!queue.isEmpty()) {
			return queue;
		} else {
			return null;
		}
		
	}

}

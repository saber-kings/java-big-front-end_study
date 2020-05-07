package com.saberking.mydatastructure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

import javax.websocket.Session;

import com.saberking.dao.SectionDao;
import com.saberking.pojo.Patient;
import com.saberking.pojo.Section;

public class Singleton {
	private SectionDao sectionDao = new SectionDao();

	// 单例模式
	private Singleton() {
		// 初始化登陆的在线用户列表
		this.userOnline = new HashMap<>();
		// 初始化所有科室
		this.sectionRoom = new HashMap<>();

		// 得到所有科室（不分普通科/专科）
		List<Section> sections = sectionDao.getAll();
		
		// 遍历所有科室将每一个科室分成普通科和专科两个队列，然后将所有科室加入
		// 方式一for循环
//		for (int i = 0; i < sections.size(); i++) {
//			// 得到一个科室
//			Section section = sections.get(i);
//
//			Map<String, Queue<Patient>> map = new HashMap<>();
//			// 普通科
//			map.put("p", new MyQueue<Patient>());
//			// 专科
//			map.put("z", new MyQueue<Patient>());
//
//			// 将当前科室加入
//			this.sectionRoom.put(section.getId() + "", map);
//		}
		
		// 方式二：lambda方式 （推荐）
		this.sectionRoom = sections.stream().collect(Collectors.toMap( Section::getIdString ,section -> {
			// 将每一个科室分为普通科和专科
			Map<String, Queue<Patient>> map = new HashMap<>();
			// 普通科
			map.put("p", new MyQueue<Patient>());
			// 专科
			map.put("z", new MyQueue<Patient>());
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

	// 所有科室（包括普通科和专家科）的挂号排队队列
	private Map<String, Map<String, Queue<Patient>>> sectionRoom;

	public synchronized void saveUser(String uid, Session session) {
		this.userOnline.put(uid, session);
		// 打印在线用户数量
//		System.out.println(this.userOnline.size());
	}

	public synchronized Boolean isOnline(String uid) {
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

	public synchronized void groupSend(String msg) {
		this.userOnline.forEach((k, v) -> {
			try {
				v.getBasicRemote().sendText(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public synchronized void removeUser(String uid) {
		this.userOnline.remove(uid);
	}

	/**
	 * 挂号
	 * @param p 要挂号的病人
	 */
	public synchronized void order(Patient p) {
		// 病人挂号的科室 id
		System.out.println("sid:" + p.getSid());
		// 获得其挂号的科室
		Map<String, Queue<Patient>> map = this.sectionRoom.get(p.getSid() + "");
		// 根据病人类型获得其要挂号的科室类型所在的排队队列（普通科/专科）
		Queue<Patient> queue = map.get(p.getPtype());
		// 打印挂号前的挂号病人数量
		System.out.println("添加之前：" + queue.size());
		// 将病人加入挂号队列
		queue.add(p);
		// 打印挂号后的挂号病人数量
		System.out.println("添加之后：" + queue.size());
	}

	/**
	 * 叫号
	 * @param sid 科室 id
	 * @param ptype 病人类型（普通科/专科）
	 * @return 返回叫到号的病人
	 */
	public synchronized Patient call(String sid, String ptype) {
		System.out.println("叫号sid:" + sid);
		// 获得当前科室
		Map<String, Queue<Patient>> map = this.sectionRoom.get(sid);
		// 得到对应类型病人的排队队列
		Queue<Patient> queue = map.get(ptype);
		// 打印叫号前的挂号病人数量
		System.out.println("叫号之前：" + queue.size());
		// 从排队挂号的病人中取出下一个病人，即叫号
		Patient patient = queue.poll();
		// 打印叫号后的挂号病人数量
		System.out.println("叫号之后：" + queue.size());
		return patient;
	}

}

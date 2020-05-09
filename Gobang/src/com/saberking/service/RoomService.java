package com.saberking.service;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

import com.alibaba.fastjson.JSON;
import com.saberking.model.Room;
import com.saberking.mydatastructure.Singleton;

/**
 * 游戏房间服务层相关操作实现类
 * 
 * @author luanz
 *
 */
public class RoomService {

	/**
	 * 获得所有房间列表
	 * 
	 * @return 房间列表的JSON格式字符串
	 */
	public String allRooms() {
		Singleton singleton = Singleton.getSingleton();
		Map<String, Room> rooms = singleton.getRooms();

		/* 由于fastjson无法转换复杂的对象，所以只能手动拼接json字符串 */
		// 方法一（高级for循环）：
//		if (rooms.keySet().size() != 0) {
//			String s =  "[";
//			
//			for (String roomId : rooms.keySet()) {
//				Room room = rooms.get(roomId);
//				Map<String, String> map = new HashMap<>();
//				map.put("id", room.getRoomId());
//				map.put("avatar", room.getLeftPlayer().getAvatar());
//				map.put("roomName", room.getRoomName());
//				map.put("homeowner", room.getLeftPlayer().getNickName());
//				s += JSON.toJSONString(map)+",";
//			}
//			s = s.substring(0, s.length() - 1);
//		}

		// 方法二（lambda遍历）：推荐
		// 第一个为分隔符，第二个参数为字符串前缀，第三个参数为字符串后缀
		if (rooms.size() != 0) {
			StringJoiner sj = new StringJoiner(",", "[", "]");
			rooms.forEach((k, v) -> {
				Map<String, Object> map = new HashMap<>(5);
				map.put("id", v.getRoomId());
				map.put("avatar", v.getLeftPlayer().getAvatar());
				map.put("roomName", v.getRoomName());
				// 设置一个当前房间是否已满的字段
				if (v.getLeftPlayer() != null && v.getRightPlayer() != null) {
					map.put("status", 1);
				} else {
					map.put("status", 0);
				}
				map.put("homeowner", v.getLeftPlayer().getNickName());
				sj.add(JSON.toJSONString(map));
			});
			System.out.println(sj.toString());
			return sj.toString();
		} else {
			return "{\"msg\":\"isNull\"}";
		}

	}

}

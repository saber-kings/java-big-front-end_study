package com.saberking.mydatastructure;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.Session;

import com.alibaba.fastjson.JSON;
import com.saberking.model.Player;
import com.saberking.model.Room;
import com.saberking.model.UserOnline;
import com.saberking.utils.JsonUtils;
import com.saberking.utils.WebsocketUtil;

import lombok.Getter;

/**
 * 自定义单例实体类
 * 
 * @author luanz
 *
 */
public class Singleton {

	/**
	 * 单例一被创建就初始化在线用户容器和游戏房间容器
	 */
	private Singleton() {
		this.usersMap = new HashMap<>();
		this.rooms = new HashMap<>();
	}

	private static Singleton Singleton;

	/**
	 * 单例模式
	 * 
	 * @return 返回单实例
	 */
	public synchronized static Singleton getSingleton() {
		if (Singleton == null) {
			Singleton = new Singleton();
		}
		return Singleton;
	}

	/**
	 * 在线用户容器
	 */
	private Map<UserOnline, Session> usersMap;

	/**
	 * 游戏房间容器（@Getter：lombok的注解，编译时自动生成Getter方法，用于查询房间列表时调用）
	 */
	@Getter
	private Map<String, Room> rooms;

	/**
	 * 用户上线时，添加用户
	 * 
	 * @param uid     用户ID
	 * @param isAdmin 是否管理员
	 * @param session 用户的客户端会话
	 */
	public synchronized void saveUser(String uid, boolean isAdmin, Session session) {
		this.usersMap.put(UserOnline.of().setUserId(uid).setAdmin(isAdmin), session);
	}

	/**
	 * 移除用户
	 * 
	 * @param uid     用户ID
	 * @param isAdmin 是否管理员
	 */
	public synchronized void removeUser(String uid, boolean isAdmin) {
		this.usersMap.remove(UserOnline.of().setUserId(uid).setAdmin(isAdmin));
	}

	/**
	 * 根据ID判断用户是否在线，是否存在（包括普通用户和管理员）
	 * 
	 * @param id 用户ID
	 * @return 返回结果 true/false
	 */
	public synchronized Boolean isNotOnline(String uid, boolean isAdmin) {
		Session session = this.usersMap.get(UserOnline.of().setUserId(uid).setAdmin(isAdmin));
		if (session == null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 向指定用户发送消息
	 * 
	 * @param uid     用户ID
	 * @param isAdmin 是否管理员
	 * @param msg     消息
	 */
	public synchronized void sendMsg(String uid, boolean isAdmin, String msg) {
		Session session = this.usersMap.get(UserOnline.of().setUserId(uid).setAdmin(isAdmin));
		try {
			session.getBasicRemote().sendText(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 向所有前台推送消息
	 * 
	 * @param msg
	 */
	public synchronized void groupSend(String msg) {
		this.usersMap.forEach((k, v) -> {
			try {
				v.getBasicRemote().sendText(msg);
				System.out.println("发布公告：" + msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * 创建房间
	 * 
	 * @param roomId 房间号
	 * @param room   房间
	 * @return
	 */
	public synchronized String createRoom(String roomId, Room room) {
		rooms.put(roomId, room);
		System.out.println("创建房间：" + room.getRoomId());
		return room.getRoomId();
	}

	/**
	 * 加入房间
	 * 
	 * @param roomId 房间号
	 * @param player 玩家
	 * @return
	 */
	public synchronized void joinRoom(String roomId, Player player) {
		Room room = this.rooms.get(roomId);
		if (room != null) {
			Map<String, Object> map = new HashMap<>(5);
			if (room.getLeftPlayer() == null) {
				room.setLeftPlayer(player);
				System.out.println("房主：" + player + "，加入房间：" + roomId);
				sendPlayerMsg(room.getLeftPlayer(), "success");
			} else {
				if (room.getRightPlayer() != null) {
					sendPlayerMsg(room.getRightPlayer(), "full");
				} else {
					room.setRightPlayer(player);
					System.out.println("玩家：" + player + "，加入房间：" + roomId);
					putMsg(map, 0, "您可以准备开始了", room.getLeftPlayer().getId(), null, null, null);
					boolean right = sendPlayerMsg(room.getRightPlayer(), JSON.toJSONString(map));
					if (!right) {
						sendPlayerMsg(room.getRightPlayer(), "connError");
					}
					putMsg(map, 0, "对面有玩家加入，您可以准备开始了", room.getRightPlayer().getId(), null, null, null);
					boolean left = sendPlayerMsg(room.getLeftPlayer(), JSON.toJSONString(map));
					if (!left) {
						sendPlayerMsg(room.getLeftPlayer(), "connError");
					}
				}
			}

		} else {
			sendPlayerMsg(player, "isNull");
		}

	}

	/**
	 * 退出房间的方法
	 * 
	 * @param roomId 房间号
	 * @param uid    玩家ID
	 */
	public synchronized void quitRoom(String roomId, String uid) {
		Room room = this.rooms.get(roomId);
		if (room != null) {
			Map<String, Object> map = new HashMap<>(5);
			if (room.getLeftPlayer() != null) {
				if (uid.equals(room.getLeftPlayer().getId())) {
					if (room.getRightPlayer() != null) {
						room.setLeftPlayer(room.getRightPlayer());
						room.setRightPlayer(null);
						room.setTurn("b");
						System.out.println("用户：" + uid + "，已退出房间，用户：" + room.getLeftPlayer().getId() + "成为房主");
						putMsg(map, 1, "对方已退出房间，您自动成为房主", null, null, null, null);
						sendPlayerMsg(room.getLeftPlayer(), JSON.toJSONString(map));
					} else {
						this.rooms.remove(roomId);
						System.out.println("用户：" + uid + "，已退出房间");
						System.out.println("房间：" + roomId + "，已销毁");
					}
				} else if (room.getRightPlayer() != null) {
					if (uid.equals(room.getRightPlayer().getId())) {
						room.setRightPlayer(null);
						room.setTurn("b");
						System.out.println("用户：" + uid + "，已退出房间");
						putMsg(map, 1, "对方已退出房间", null, null, null, null);
						sendPlayerMsg(room.getLeftPlayer(), JSON.toJSONString(map));
						if (room.getLeftPlayer() == null) {
							this.rooms.remove(roomId);
							System.out.println("房间：" + roomId + "，已销毁");
						}
					}
				}
			} else {
				this.rooms.remove(roomId);
				System.out.println("房间：" + roomId + "，已销毁");
			}
		}
	}

//	private synchronized Player getPlayerById(String rid, String id) {
//		Room room = this.rooms.get(rid);
//		if (room != null) {
//			if (id.equals(room.getLeftPlayer().getId())) {
//				return room.getLeftPlayer();
//			}
//			if (id.equals(room.getRightPlayer().getId())) {
//				return room.getRightPlayer();
//			}
//			return null;
//		} else {
//			return null;
//		}
//	}

	/**
	 * 游戏房间相关操作的处理方法
	 * 
	 * @param session 发送操作的客户端会话
	 * @param rid     房间号
	 * @param id      玩家ID
	 * @param message 请求的消息
	 */
	public synchronized void getOpertation(Session session, String rid, String id, String message) {
		System.out.println("房间：" + rid + "，收到消息：" + message);
		Room room = this.rooms.get(rid);
		if (room != null) {
			Map<String, Object> reqMap = JsonUtils.convertJsonStrToMap(message);
			Object codeObj = reqMap.get("code");
			Map<String, Object> map = new HashMap<>(5);
			Integer code = getIntValue(room, codeObj);
			String black = "b";
			if (code != null) {
				Object x = reqMap.get("x");
				Object y = reqMap.get("y");
				switch (code) {
				// 退出房间
				case 1:
					quitRoom(rid, id);
					// 准备
					break;
				case 2:
					readyPlay(id, room, reqMap, map);
					break;
				// 前端传递过来猜棋结果
				case 3:
					guessChess(id, room, reqMap, map);
					break;
				// 白方发动先手交换
				case 4:
					firstChange(id, room, map);
					break;
				// 落子
				case 5:
					fallChess(id, message, room, reqMap, map, black, x, y);
					break;
				// 取消落子，这里主要是用来回馈悔棋结果的
				case 6:
					cancelFallChess(id, room, map, x, y);
					break;
				// 对战结束同步双方客户端结果
				case 7:
					sendRoomMsg(room, message);
					room.getLeftPlayer().setReday(0);
					room.getRightPlayer().setReday(0);
					room.setTurn("b");
					break;
				// 发送提示消息
				case 8:
					sendRoomMsg(room, message);
					break;
				// 申请悔棋
				case 9:
					regretChess(id, room, map, x, y);
					break;
				// 发送聊天消息
				case 10:
					sendRoomMsg(room, message);
				default:
					putMsg(map, 8, "没有这个操作，请重试", null, null, null, null);
					if (id.equals(room.getLeftPlayer().getId())) {
						sendPlayerMsg(room.getLeftPlayer(), JSON.toJSONString(map));
					}
					if (id.equals(room.getRightPlayer().getId())) {
						sendPlayerMsg(room.getRightPlayer(), JSON.toJSONString(map));
					}
					break;
				}
			}
		} else {
			// 日志打印，获取目标请求IP并打印
			System.out.println("房间不存在，不能操作，请求IP地址：" + WebsocketUtil.getRemoteAddress(session));
		}
	}

	/**
	 * 悔棋
	 * 
	 * @param id   用户ID
	 * @param room 房间
	 * @param map  封装响应数据
	 * @param x    横坐标
	 * @param y    纵坐标
	 */
	private void regretChess(String id, Room room, Map<String, Object> map, Object x, Object y) {
		putMsg(map, 9, "对方申请悔棋是否同意？", null, null, x, y);
		if (id.equals(room.getLeftPlayer().getId())) {
			sendPlayerMsg(room.getRightPlayer(), JSON.toJSONString(map));
		}
		if (id.equals(room.getRightPlayer().getId())) {
			sendPlayerMsg(room.getLeftPlayer(), JSON.toJSONString(map));
		}
	}

	/**
	 * 取消落子
	 * 
	 * @param id   用户ID
	 * @param room 房间
	 * @param map  封装响应数据
	 * @param x    横坐标
	 * @param y    纵坐标
	 */
	private void cancelFallChess(String id, Room room, Map<String, Object> map, Object x, Object y) {
		putMsg(map, 6, "对方同意悔棋", null, null, x, y);
		if (id.equals(room.getLeftPlayer().getId())) {
			sendPlayerMsg(room.getRightPlayer(), JSON.toJSONString(map));
		}
		if (id.equals(room.getRightPlayer().getId())) {
			sendPlayerMsg(room.getLeftPlayer(), JSON.toJSONString(map));
		}
	}

	/**
	 * 落子
	 * 
	 * @param id      用户ID
	 * @param message 消息
	 * @param room    房间
	 * @param reqMap  封装好的请求数据
	 * @param map     封装响应数据
	 * @param black   黑棋棋色
	 * @param x       横坐标
	 * @param y       纵坐标
	 */
	private void fallChess(String id, String message, Room room, Map<String, Object> reqMap, Map<String, Object> map,
			String black, Object x, Object y) {
		String turn = getStrValue(room, reqMap.get("turn"));
		if (room.getTurn().equals(turn)) {
			boolean sendRes = sendRoomMsg(room, message);
			if (sendRes) {
				if (room.getTurn().equals(black)) {
					room.setTurn("w");
				} else {
					room.setTurn("b");
				}
			} else {
				putMsg(map, 6, "落子失败，请重试", null, null, x, y);
				if (id.equals(room.getLeftPlayer().getId())) {
					sendPlayerMsg(room.getLeftPlayer(), JSON.toJSONString(map));
					putMsg(map, 6, "对面落子失败，请稍等", null, null, x, y);
					sendPlayerMsg(room.getRightPlayer(), JSON.toJSONString(map));
				}
				if (id.equals(room.getRightPlayer().getId())) {
					sendPlayerMsg(room.getRightPlayer(), JSON.toJSONString(map));
					putMsg(map, 6, "对面落子失败，请稍等", null, null, x, y);
					sendPlayerMsg(room.getLeftPlayer(), JSON.toJSONString(map));
				}
			}
		} else {
			putMsg(map, 8, "还没轮到您下棋", null, null, null, null);
			if (id.equals(room.getLeftPlayer().getId())) {
				sendPlayerMsg(room.getLeftPlayer(), JSON.toJSONString(map));
			}
			if (id.equals(room.getRightPlayer().getId())) {
				sendPlayerMsg(room.getRightPlayer(), JSON.toJSONString(map));
			}
		}
	}

	/**
	 * 先手交换
	 * 
	 * @param id   用户ID
	 * @param room 房间
	 * @param map  封装响应数据
	 */
	private void firstChange(String id, Room room, Map<String, Object> map) {
		if (id.equals(room.getLeftPlayer().getId())) {
			putMsg(map, 4, "对面发动先手交换，您执白棋先下", null, "w", null, null);
			sendPlayerMsg(room.getRightPlayer(), JSON.toJSONString(map));
			putMsg(map, 4, "先手交换成功，您执黑棋后下", null, "b", null, null);
			sendPlayerMsg(room.getLeftPlayer(), JSON.toJSONString(map));
		}
		if (id.equals(room.getRightPlayer().getId())) {
			putMsg(map, 4, "对面发动先手交换，您执白棋先下", null, "w", null, null);
			sendPlayerMsg(room.getLeftPlayer(), JSON.toJSONString(map));
			putMsg(map, 4, "先手交换成功，您执黑棋后下", null, "b", null, null);
			sendPlayerMsg(room.getRightPlayer(), JSON.toJSONString(map));
		}
	}

	/**
	 * 猜棋
	 * 
	 * @param id     用户ID
	 * @param room   房间
	 * @param reqMap 封装好的请求数据
	 * @param map    封装响应数据
	 */
	private void guessChess(String id, Room room, Map<String, Object> reqMap, Map<String, Object> map) {
		Integer guess = getIntValue(room, reqMap.get("guess"));
		if (guess != null) {
			// 这里主要是由房主猜棋果，如果猜棋正确就让其执黑棋，否则执白棋
			if (id.equals(room.getLeftPlayer().getId())) {
				setTurn(map, guess);
				sendPlayerMsg(room.getLeftPlayer(), JSON.toJSONString(map));
				guess = (guess == 0 ? 1 : 0);
				setTurn(map, guess);
				sendPlayerMsg(room.getRightPlayer(), JSON.toJSONString(map));
			}
			if (id.equals(room.getRightPlayer().getId())) {
				setTurn(map, guess);
				sendPlayerMsg(room.getRightPlayer(), JSON.toJSONString(map));
				guess = (guess == 0 ? 1 : 0);
				setTurn(map, guess);
				sendPlayerMsg(room.getLeftPlayer(), JSON.toJSONString(map));
			}
		}
	}

	/**
	 * 设置准备状态
	 * 
	 * @param id     用户ID
	 * @param room   房间
	 * @param reqMap 封装好的请求数据
	 * @param map    封装响应数据
	 */
	private void readyPlay(String id, Room room, Map<String, Object> reqMap, Map<String, Object> map) {
		Integer reday = getIntValue(room, reqMap.get("reday"));
		if (reday != null) {
			if (reday == 1) {
				putMsg(map, 2, "对方已准备", null, null, null, null);
			} else {
				putMsg(map, 2, "对方已取消准备", null, null, null, null);
			}
			map.put("reday", reday);
			if (id.equals(room.getLeftPlayer().getId())) {
				room.getLeftPlayer().setReday(reday);
				sendPlayerMsg(room.getRightPlayer(), JSON.toJSONString(map));
			}
			if (id.equals(room.getRightPlayer().getId())) {
				room.getRightPlayer().setReday(reday);
				sendPlayerMsg(room.getLeftPlayer(), JSON.toJSONString(map));
			}
		}
		if (room.getLeftPlayer().getReday() == 1 && room.getRightPlayer().getReday() == 1) {
			putMsg(map, 3, "开始猜棋", null, null, null, null);
			sendPlayerMsg(room.getLeftPlayer(), JSON.toJSONString(map));
		}
	}

	/**
	 * 封装响应消息的方法
	 * 
	 * @param map   要封装的map集合
	 * @param code  响应码，对应各种操作
	 * @param msg   响应的提示消息
	 * @param enemy 对面玩家信息
	 * @param turn  棋色
	 * @param x     横坐标即行数
	 * @param y     纵坐标即列数
	 */
	private void putMsg(Map<String, Object> map, int code, String msg, String enemy, String turn, Object x, Object y) {
		map.put("code", code);
		if (msg != null) {
			map.put("msg", msg);
		}
		if (enemy != null) {
			map.put("enemy", enemy);
		}
		if (turn != null) {
			map.put("turn", turn);
		}
		if (x != null) {
			map.put("x", x);
		}
		if (y != null) {
			map.put("y", y);
		}
	}

	/**
	 * 封装设置棋色的响应数据
	 * 
	 * @param map   封装数据的map
	 * @param guess 猜棋结果（0：表示未猜中，1：表示猜中）
	 */
	private void setTurn(Map<String, Object> map, Integer guess) {
		if (guess == 1) {
			putMsg(map, 4, "双方猜棋结束，您是黑棋，先下", null, "b", null, null);
		} else {
			putMsg(map, 4, "双方猜棋结束，您是白棋，对方先下", null, "w", null, null);
		}
	}

	/**
	 * 从前端请求数据中得到整型类型的值
	 * 
	 * @param room     房间
	 * @param intValue 要得到值得数据
	 * @return
	 */
	private Integer getIntValue(Room room, Object intValue) {
		Map<String, Object> map = new HashMap<>(2);
		if (intValue instanceof Integer) {
			return (Integer) intValue;
		} else {
			putMsg(map, 8, "数据传输异常", null, null, null, null);
			sendRoomMsg(room, JSON.toJSONString(map));
			return null;
		}
	}

	/**
	 * 从前端请求数据中得到字符类型的值
	 * 
	 * @param room     房间
	 * @param strValue 要得到值得数据
	 * @return
	 */
	private String getStrValue(Room room, Object strValue) {
		Map<String, Object> map = new HashMap<>(2);
		if (strValue instanceof String) {
			return (String) strValue;
		} else {
			putMsg(map, 8, "数据传输异常", null, null, null, null);
			sendRoomMsg(room, JSON.toJSONString(map));
			return null;
		}
	}

	/**
	 * 向房间里的所有玩家发送消息
	 * 
	 * @param room 房间
	 * @param msg  消息
	 * @return
	 */
	private boolean sendRoomMsg(Room room, String msg) {
		try {
			if (room.getLeftPlayer() != null) {
				room.getLeftPlayer().getSession().getBasicRemote().sendText(msg);
			}
			if (room.getRightPlayer() != null) {
				room.getRightPlayer().getSession().getBasicRemote().sendText(msg);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 单个玩家发送消息
	 * 
	 * @param player 玩家
	 * @param msg    消息
	 * @return
	 */
	private boolean sendPlayerMsg(Player player, String msg) {
		try {
			if (player != null) {
				player.getSession().getBasicRemote().sendText(msg);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}

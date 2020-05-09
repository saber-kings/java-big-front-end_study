package com.saberking.utils;

/**
 * @Author: 小霍
 * TODO: 编码工具类
 * @UpdateUser: Saber污妖王
 * @Project: company-frame
 * @Date: 2020/3/24
 * @Package: com.yingxue.lesson.utils
 * @Version: 0.0.1
 */
public class CodeUtil {

	private static final String ROOM_PREFIX= "R";
//    private static final String PERMISSION_TPYE = "YXP";

	/**
	 * 右补位，左对齐
	 *
	 * @param oriStr 原字符串
	 * @param len    目标字符串长度
	 * @param alexin 补位字符，以 alexin 做为补位
	 * @return 目标字符串
	 */
	public static String padRight(String oriStr, int len, String alexin) {
		String str = "";
		int strlen = oriStr.length();
		if (strlen < len) {
			for (int i = 0; i < len - strlen; i++) {
				str = str + alexin;
			}
		}
		str = str + oriStr;
		return str;
	}

	/**
	 * 获取机构编码 YXD0001
	 *
	 * @param oriStr 初始值
	 * @param len    位数
	 * @param alexin 不足 以什么补充
	 * @return java.lang.String
	 */
	public static String roomCode(String oriStr, int len, String alexin) {
		return ROOM_PREFIX + padRight(oriStr, len, alexin);
	}

}

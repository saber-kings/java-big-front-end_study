package com.saberking.utils;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.github.kevinsawicki.http.HttpRequest;
import com.saberking.model.Group;

public class Test {
	
	public static void main(String[] args) {
		String result = HttpRequest.get("http://192.168.31.139:9090/GroupChat/getAllGroups.php").body();
//		System.out.println(result);
		List<Group> groups = JSON.parseArray(result, Group.class);
		System.out.println(groups);
	}
}

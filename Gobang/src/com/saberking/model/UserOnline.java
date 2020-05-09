package com.saberking.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 在线用户实体类
 * 
 * @author luanz
 *
 */
@Accessors(chain = true)
@Data
@RequiredArgsConstructor(staticName = "of")
public class UserOnline {
	private String userId;
	private boolean isAdmin;
}

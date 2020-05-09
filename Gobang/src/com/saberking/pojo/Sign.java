package com.saberking.pojo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import lombok.Data;

/**
 * 签到表实体类
 * 
 * @author luanz
 *
 */
@Data
public class Sign implements Serializable {

	/**
	 * 序列化版本号
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int uid;
	private int totalCount;
	private int contCount;
	private Date lastTime;
	private String signedTime;
	private List<String> signedTimeList;

	public List<String> getSignedTimeList() {
		this.signedTimeList = StringUtils.isNotBlank(this.signedTime) ? Arrays.asList(this.signedTime.split(","))
				: null;
		return this.signedTimeList;
	}
}

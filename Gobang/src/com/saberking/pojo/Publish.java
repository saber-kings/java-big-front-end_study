package com.saberking.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 公告实体类
 * 
 * @author luanz
 *
 */
@Data
public class Publish implements Serializable {

	/**
	 * 序列化版本号
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String title;
	private String content;
	private Date createTime;
	private Date updateTime;
	private boolean deleted;
	private Integer page;
	private Integer size;
	private Long totals;

}

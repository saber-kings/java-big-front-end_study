package com.saberking.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;
import com.saberking.dao.PublishDao;
import com.saberking.model.PaginationModel;
import com.saberking.mydatastructure.Singleton;
import com.saberking.pojo.Publish;

/**
 * 公告服务层相关操作实现类
 * 
 * @author luanz
 *
 */
public class PublishService {
	private PublishDao publishDao = new PublishDao();

	/**
	 * 分页查询
	 * 
	 * @param page 页码
	 * @param size 每页条数
	 * @return 带分页查询相关参数的结果
	 */
	public String pageInfo(String page, String size) {
		// 实例化封装分页相关参数的封装类
		PaginationModel<Publish> result = new PaginationModel<>();
		Integer pageInt;
		Integer sizeInt;
		try {
			// 将接收的字符串参数转换成数值，如果转换失败，则返回数据传输异常的代码
			pageInt = Integer.parseInt(page);
			sizeInt = Integer.parseInt(size);
			// 调用方法初始化分页相关参数
			result.setPagination(publishDao.getTotals(), pageInt, sizeInt);
			// 去数据库中分页查询
			List<Publish> pagePublish = publishDao.getPagePublish(result);
			// 添加数据到分页查询封装类的数据域中
			result.setData(pagePublish);
			return JSON.toJSON(result).toString();
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return "{\"msg\":\"dataError\"}";
		}
	}

	public String search(Publish publish) {
		PaginationModel<Publish> result = new PaginationModel<>();
		result.setPagination(publishDao.getTotals(), publish.getPage(), publish.getSize());
		List<Publish> publishs = publishDao.dynamicQuery(publish, result);
		result.setData(publishs);
		return JSON.toJSON(result).toString();
	}

	public String publish(Publish publish) {
		publish.setCreateTime(new Date());
		Map<String, Object> map = new HashMap<String, Object>(5);
		try {
			Long res = publishDao.save(publish);
			if (res != 0L) {
				publish.setId(res.intValue());
				map.put("addP", publish);
				map.put("msg", "success");
			} else {
				return "{\"msg\":\"error\"}";
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "error");
		}
		return JSON.toJSON(map).toString();
	}

	public String delete(String pid) {
		Boolean res = publishDao.delete(pid);
		if (res) {
			return "{\"msg\":\"success\"}";
		} else {
			return "{\"msg\":\"error\"}";
		}
	}

	public String batchDel(List<String> pids) {
		List<Integer> list = null;
		try {
			list = pids.stream().map(Integer::parseInt).collect(Collectors.toList());
			Boolean res = publishDao.batchDeleted(list);
			if (res) {
				return "{\"msg\":\"success\"}";
			} else {
				return "{\"msg\":\"error\"}";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"msg\":\"dataError\"}";
		}
	}

	public String update(Publish publish) {
		publish.setUpdateTime(new Date());
		Singleton singleton = Singleton.getSingleton();
		try {
			singleton.groupSend(JSON.toJSON(publish).toString());
			Boolean res = publishDao.update(publish);
			if (res) {
				return "{\"msg\":\"success\"}";
			} else {
				return "{\"msg\":\"error\"}";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"msg\":\"error\"}";
		}
	}
}

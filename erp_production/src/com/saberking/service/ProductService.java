package com.saberking.service;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.saberking.dao.ProductDao;
import com.saberking.mydatastructure.MyQueue;
import com.saberking.mydatastructure.Singleton;
import com.saberking.pojo.Product;

public class ProductService {
	private ProductDao productDao = new ProductDao();

	public String save(Product product) {
		Boolean res = productDao.save(product);
		if (res) {
			List<Product> list = productDao.selectProduct(product);
			Singleton singleton = Singleton.getSingleton();
			singleton.scheduling(list.get(0));
			return "{\"msg\":\"success\"}";
		} else {
			return "{\"msg\":\"error\"}";
		}
	}

	public String producing(String wid, String pline) {
		Singleton singleton = Singleton.getSingleton();
		Product product = singleton.producing(wid, pline);
		if (product != null) {
			return JSON.toJSON(product).toString();
		} else {
			return "{\"msg\":\"isNull\"}";
		}
	}

	public String getProQueue(String wid, String pline) {
		Singleton singleton = Singleton.getSingleton();
		MyQueue<Product> productQueue = singleton.getProductQueue(wid, pline);
		if (productQueue != null) {
			List<Product> asList = productQueue.getList();
			System.out.println(asList);
			return JSON.toJSON(asList).toString();
		} else {
			return "{\"msg\":\"isNull\"}";
		}

	}
}

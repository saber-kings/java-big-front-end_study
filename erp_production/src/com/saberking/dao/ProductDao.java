package com.saberking.dao;

import java.util.List;

import com.saberking.pojo.Product;
import com.saberking.utils.BaseDao;

public class ProductDao {
	public Boolean save(Product product) {
		String sql = "insert into t_product(pname,need_num,mould,wid,pline) values(?,?,?,?,?)";
		int num = BaseDao.update(sql, product.getPname(), product.getNeedNum(), product.getMould(), product.getWid(), product.getPline());
		if (num > 0) {
			return true;
		} else {
			return false;
		}
	}

	public List<Product> selectProduct(Product product) {
		String sql = "select * from t_product where pname=? and need_num=? and mould=? and wid=? and pline=?";
		return BaseDao.getall(sql, Product.class, product.getPname(), product.getNeedNum(), product.getMould(), product.getWid(), product.getPline());
	}
}

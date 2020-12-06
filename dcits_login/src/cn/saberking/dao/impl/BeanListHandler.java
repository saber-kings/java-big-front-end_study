package cn.saberking.dao.impl;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.saberking.dao.ResultSetHandler;

/**
 * 返回一个JavaBean的集合
 * 
 * @author: saber-kings
 */
public class BeanListHandler<T> implements ResultSetHandler<List<T>> {
	private Class<T> clazz;

	public BeanListHandler(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	public List<T> handle(ResultSet rs) throws Exception {
		// 创建要返回的实体集合，方便后面添加实体信息
		List<T> list = new ArrayList<>();
		// 声明实体对象变量名，后面遍历用做接收实体对象
		T obj = null;
		// 遍历结果集
		while (rs.next()) {
			// 获取本次查询结果集有多少列
			int count = rs.getMetaData().getColumnCount();
			obj = clazz.newInstance();
			// for循环 遍历所有的列
			for (int i = 0; i < count; i++) {
				// 获取字段名称
				String columnName = rs.getMetaData().getColumnName(i + 1);
				// 获取该字段对应的值
				Object columnValue = rs.getObject(columnName);
				// 通过字段名获取属性，try{名称不匹配}catch{到配置文件查找对应属性名}
				Field field = null;
				try {
					field = clazz.getDeclaredField(columnName);
				} catch (Exception e) {
					throw new RuntimeException("The column name does not match the field name");
				}
				// 将私有属性非可访问设置为可访问
				field.setAccessible(true);
				// 给实体类中的属性赋值
				field.set(obj, columnValue);
			}
			list.add(obj);
		}
		return list;
	}
}

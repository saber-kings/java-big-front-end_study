package com.saberking.utils;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class DH {

	public static final String connstr = "com.mysql.cj.jdbc.Driver";
	public static final String dr = "jdbc:mysql://192.168.31.32:3306/group_chat?useSSL=false&serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf8";
	public static final String uid = "root";
	public static final String pwd = "admin123";

	@SuppressWarnings("unchecked")
	public static <T> List<T> getall(String sql, Class<T> clazz, Object... params) {
		List<T> result = null;
		Connection conn = null;
		DbUtils.loadDriver(DH.connstr);
		try {
			conn = DriverManager.getConnection(DH.dr, DH.uid, DH.pwd);
			QueryRunner qr = new QueryRunner();
			// 开启下划线->驼峰转换所用
			BeanProcessor bean = new GenerousBeanProcessor();
			RowProcessor processor = new BasicRowProcessor(bean);
			// 返回结果
			if (params != null) {
				result = (List<T>) qr.query(conn, sql,
						new BeanListHandler<Object>(getObjectByClass(clazz).getClass(), processor), params);
			} else {
				result = (List<T>) qr.query(conn, sql,
						new BeanListHandler<Object>(getObjectByClass(clazz).getClass(), processor));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			DbUtils.closeQuietly(conn);

		}

		return result;
	}

	private static <T> T getObjectByClass(Class<T> clazz) {
		T t = null;
		try {
			t = clazz.newInstance();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
		return t;
	}

	public static int update(String sql, Object... params) {
		int num = 0;
		Connection conn = null;
		DbUtils.loadDriver(DH.connstr);
		try {
			conn = DriverManager.getConnection(DH.dr, DH.uid, DH.pwd);
			QueryRunner qr = new QueryRunner();
			num = qr.update(conn, sql, params);
			// System.out.println(num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			DbUtils.closeQuietly(conn);

		}
		return num;
	}

	public static BigInteger insert(String sql, Object... params) {
		BigInteger num = new BigInteger("0");
		Connection conn = null;
		DbUtils.loadDriver(DH.connstr);
		try {
			conn = DriverManager.getConnection(DH.dr, DH.uid, DH.pwd);
			QueryRunner qr = new QueryRunner();
			num = qr.insert(conn, sql, new ScalarHandler<BigInteger>(),  params);
			// System.out.println(num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			DbUtils.closeQuietly(conn);
			
		}
		return num;
	}

	public static Connection getconn() {
		Connection conn = null;
		try {
			Class.forName(DH.connstr);
			conn = DriverManager.getConnection(DH.dr, DH.uid, DH.pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void closeall(Statement st, Connection conn) {
		try {
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeall(ResultSet rs, Statement st, Connection conn) {
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

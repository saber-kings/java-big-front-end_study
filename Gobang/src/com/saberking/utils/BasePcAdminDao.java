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

/**
 * 连接后台管理系统数据库的JDBC工具类
 * 
 * @author Saber污妖王
 *
 */
public class BasePcAdminDao {

	public static final String CONNECTION_DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://192.168.31.32:3306/gobang_admin?useSSL=false&serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf8";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "admin123";

	public static <T> List<T> getAll(String sql, Class<T> clazz, Object... params) {
		List<T> result = null;
		Connection conn = null;
		DbUtils.loadDriver(BasePcAdminDao.CONNECTION_DRIVER);
		try {
			conn = DriverManager.getConnection(BasePcAdminDao.URL, BasePcAdminDao.USERNAME, BasePcAdminDao.PASSWORD);
			QueryRunner qr = new QueryRunner();
			// 开启下划线->驼峰转换所用
			BeanProcessor bean = new GenerousBeanProcessor();
			RowProcessor processor = new BasicRowProcessor(bean);
			// 返回结果
			if (params != null) {
				// 因为我是强迫症，所以这里调用了一下我写的类型转换工具，这样就不会出警告了，
				// 况且曾经去百度过使用 @SuppressWarnings("uncheck") 这个注解，
				// 去忽略强制转换类型检查警告也不是100%安全的，所以还是手动转换一下吧
				result = CastUtils.castList(qr.query(conn, sql,
						new BeanListHandler<Object>(getObjectByClass(clazz).getClass(), processor), params), clazz);
			} else {
				result = CastUtils.castList(
						qr.query(conn, sql, new BeanListHandler<Object>(getObjectByClass(clazz).getClass(), processor)),
						clazz);
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
		DbUtils.loadDriver(BasePcAdminDao.CONNECTION_DRIVER);
		try {
			conn = DriverManager.getConnection(BasePcAdminDao.URL, BasePcAdminDao.USERNAME, BasePcAdminDao.PASSWORD);
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
		DbUtils.loadDriver(BasePcAdminDao.CONNECTION_DRIVER);
		try {
			conn = DriverManager.getConnection(BasePcAdminDao.URL, BasePcAdminDao.USERNAME, BasePcAdminDao.PASSWORD);
			QueryRunner qr = new QueryRunner();
			num = qr.insert(conn, sql, new ScalarHandler<BigInteger>(), params);
			// System.out.println(num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			DbUtils.closeQuietly(conn);

		}
		return num;
	}

	public static Connection getConn() {
		Connection conn = null;
		try {
			Class.forName(BasePcAdminDao.CONNECTION_DRIVER);
			conn = DriverManager.getConnection(BasePcAdminDao.URL, BasePcAdminDao.USERNAME, BasePcAdminDao.PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void closeAll(Statement st, Connection conn) {
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

	public static void closeAll(ResultSet rs, Statement st, Connection conn) {
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

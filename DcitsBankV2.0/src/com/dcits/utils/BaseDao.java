package com.dcits.utils;

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
 * DbUtils工具类封装通用的jdbc操作
 *
 */
public class BaseDao {

	public static final String connstr = "com.mysql.cj.jdbc.Driver";
	public static final String dr = "jdbc:mysql://localhost:3306/bank?useSSL=false&serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf8";
	public static final String uid = "root";
	public static final String pwd = "admin123";

	public static <T> List<T> getList(String sql, Class<T> clazz, Object... params) {
		List<T> result = null;
		Connection conn = null;
		DbUtils.loadDriver(BaseDao.connstr);
		try {
			conn = DriverManager.getConnection(BaseDao.dr, BaseDao.uid, BaseDao.pwd);
			QueryRunner qr = new QueryRunner();
			// 开启下划线->驼峰转换所用
			BeanProcessor bean = new GenerousBeanProcessor();
			RowProcessor processor = new BasicRowProcessor(bean);
			// 返回结果
			if (params != null) {
				// 这里调用了一下自定义的类型转换工具，这样就不会出警告了，
				// 况且使用 @SuppressWarnings("uncheck") 这个注解，
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
		DbUtils.loadDriver(BaseDao.connstr);
		try {
			conn = DriverManager.getConnection(BaseDao.dr, BaseDao.uid, BaseDao.pwd);
			QueryRunner qr = new QueryRunner();
			num = qr.update(conn, sql, params);
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
		DbUtils.loadDriver(BaseDao.connstr);
		try {
			conn = DriverManager.getConnection(BaseDao.dr, BaseDao.uid, BaseDao.pwd);
			QueryRunner qr = new QueryRunner();
			num = qr.insert(conn, sql, new ScalarHandler<BigInteger>(), params);
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
			Class.forName(BaseDao.connstr);
			conn = DriverManager.getConnection(BaseDao.dr, BaseDao.uid, BaseDao.pwd);
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

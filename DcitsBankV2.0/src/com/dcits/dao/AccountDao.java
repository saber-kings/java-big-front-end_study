package com.dcits.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.lang3.StringUtils;

import com.dcits.pojo.Account;
import com.dcits.utils.BaseDao;

/**
 * 账号Dao层数据库操作
 *
 */
public class AccountDao {
	public Account login(String account, String password) {
		String sql = "select * from t_account where account=? and password=?";
		List<Account> result = BaseDao.getList(sql, Account.class, account, password);
		if (result.size() > 0) {
			return result.get(0);
		} else {
			return null;
		}
	}

	public Account loginedGetById(String id) {
		String sql = "select * from t_account where id=?";
		List<Account> result = BaseDao.getList(sql, Account.class, id);
		return result.get(0);
	}

	public Boolean register(Account account) {
		String sql = "insert into t_account(account, phone, password, real_name) values(?, ?, ?, ?)";
		int num = BaseDao.update(sql, account.getAccount(), account.getPhone(), account.getPassword(),
				account.getRealName());
		if (num > 0) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean changePwd(String id, String newPwd) {
		String sql = "update t_account set password=? where id=?";
		int num = BaseDao.update(sql, newPwd, id);
		if (num > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 付款方法
	 * 
	 * @param conn  连接对象
	 * @param from  付款人
	 * @param money 金额
	 * @throws SQLException 付款过程中出错直接抛出异常给Service层处理
	 */
	public void outMoney(Connection conn, String from, BigDecimal money) throws SQLException {
		QueryRunner qr = new QueryRunner();
		String sql = "update t_account set balance = balance - ? where account = ?";
		qr.update(conn, sql, money, from);
	}

	/**
	 * 收款方法
	 * 
	 * @param conn  连接对象
	 * @param to    收款人
	 * @param money 金额
	 * @throws SQLException 收款过程中出错直接抛出异常给Service层处理
	 */
	public void inMoney(Connection conn, String to, BigDecimal money) throws SQLException {
		QueryRunner qr = new QueryRunner();
		String sql = "update t_account set balance = balance + ? where account = ?";
		qr.update(conn, sql, money, to);
	}

	public Boolean upBalance(String id, BigDecimal balance) {
		String sql = "update t_account set balance=? where id=?";
		int num = BaseDao.update(sql, balance, id);
		if (num > 0) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean upStatus(String id, int status) {
		String sql = "update t_account set status=? where id=?";
		int num = BaseDao.update(sql, status, id);
		if (num > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 动态修改账号表信息
	 * 
	 * @param account
	 * @return
	 */
	public Boolean update(Account account) {
		// 接收更新的数据条数
		int num = 0;
		// 这个用于存储查询的条件参数的
		List<Object> params = new ArrayList<>();
		if (account.getId() != 0) {
			StringJoiner sqlPre = new StringJoiner(",", "update t_account set", ",");
			if (StringUtils.isNotBlank(account.getPhone())) {
				sqlPre.add(" phone = ?");
				params.add(account.getPhone());
			}
			if (StringUtils.isNotBlank(account.getRealName())) {
				sqlPre.add(" real_name = ?");
				params.add(account.getRealName());
			}
			StringBuilder sql = new StringBuilder(sqlPre.toString());
			sql.deleteCharAt(sqlPre.length() - 1);
			params.add(account.getId());
			sql.append(" where id = ?");
			if (params.size() > 1) {
				num = BaseDao.update(sql.toString(), params.toArray());
			}
		}
		return num > 0 ? true : false;
	}

	public List<String> getAllIds() {
		String sql = "select id from t_account";
		List<String> result = BaseDao.getList(sql, String.class, (Object[]) null);
		return result;
	}
}

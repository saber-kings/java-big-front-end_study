package cn.saberking.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import cn.saberking.dao.JdbcTemplate;
import cn.saberking.dao.UserDao;
import cn.saberking.pojo.User;

/**
 * 用户Dao层实现类
 * @author saber-kings
 *
 */
public class UserDaoImpl extends JdbcTemplate implements UserDao {

	@Override
	public User findUser(User user) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder("select * from t_user where 1=1");
		if (StringUtils.isNotBlank(user.getUsername())) {
			params.add(user.getUsername());
			sql.append(" and username=?");
		} 
		if (StringUtils.isNotBlank(user.getPassword())) {
			params.add(user.getPassword());
			sql.append(" and password=?");
		}
//		Connection conn = null;
//		PreparedStatement psmt = null;
//		ResultSet rs = null;
//		User dbUser = null;
//		try {
//			// 获取数据库连接对象
//			conn = JdbcUtils.getConn();
//			// 获取预编译语句对象
//			psmt = conn.prepareStatement(sql.toString());
//			// 给预编译语句赋值
//			for (int i = 0; i < params.size(); i++) {
//				psmt.setObject(i + 1, params.get(i));
//			}
//			// 执行SQL语句获取结果集
//			rs = psmt.executeQuery();
//			// 处理结果集
//			if (rs.next()) {
//				dbUser = new User();
//				dbUser.setId(rs.getInt("id"));
//				dbUser.setUsername(rs.getString("username"));
//				dbUser.setPassword(rs.getString("password"));
//				dbUser.setLoginStatus(rs.getInt("loginStatus"));
//				dbUser.setLoginTime(rs.getDate("loginTime"));
//				dbUser.setRemark(rs.getString("remark"));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			// 关闭数据库连接
//			JdbcUtils.close(conn, psmt, rs);
//		}
		return executeQuery(sql.toString(), new BeanHandler<User>(User.class), params.toArray());
	}

	@Override
	public int updateSatus(User user) {
		String sql = "update t_user set loginStatus = ?, loginTime = ? where id = ?";
		return executeUpdate(sql, user.getLoginStatus(), user.getLoginTime(), user.getId());
	}

}

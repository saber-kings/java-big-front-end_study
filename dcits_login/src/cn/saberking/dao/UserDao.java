package cn.saberking.dao;

import cn.saberking.pojo.User;

/**
 * 用户Dao层接口
 * 
 * @author: saber-kings
 */
public interface UserDao {
	/**
	 * 根据参数动态查询用户的方法
	 * 
	 * @param user 用户实体
	 * @return 用户信息
	 */
	User findUser(User user);

	/**
	 * 根据参数更新用户状态
	 * 
	 * @param user 用户实体
	 * @return 结果
	 */
	int updateSatus(User user);
}

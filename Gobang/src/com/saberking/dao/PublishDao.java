package com.saberking.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.saberking.model.PaginationModel;
import com.saberking.pojo.Publish;
import com.saberking.utils.BaseDao;

/**
 * 公告数据访问层实现类
 * 
 * @author luanz
 *
 */
public class PublishDao {

	public List<Publish> getAll() {
		String sql = "select * from t_publish where deleted = 0";
		return BaseDao.getAll(sql, Publish.class, (Object[]) null);
	}

	public Integer getTotals() {
		String sql = "select count(id) as totals from t_publish where deleted = 0";
		Publish publish = BaseDao.getAll(sql, Publish.class, (Object[]) null).get(0);
		return publish.getTotals().intValue();
	}

	public List<Publish> getPagePublish(PaginationModel<Publish> paginationModel) {
		String sql = "select * from t_publish where deleted = 0 order by id desc limit ?, ?";
		return BaseDao.getAll(sql, Publish.class, paginationModel.getStart(), paginationModel.getSize());
	}

	public List<Publish> dynamicQuery(Publish publish, PaginationModel<Publish> paginationModel) {
		StringBuilder sql = new StringBuilder("select * from t_publish where 1=1");
		// 这个用于存储查询的条件参数的
		List<Object> params = new ArrayList<>();
		if (publish.getId() != 0) {
			sql.append(" and id = ?");
			params.add(publish.getId());
		}
		if (StringUtils.isNotBlank(publish.getTitle())) {
			sql.append(" and title like ?");
			params.add("%" + publish.getTitle().trim() + "%");
		}
		if (StringUtils.isNotBlank(publish.getContent())) {
			sql.append(" and content like ?");
			params.add("%" + publish.getContent().trim() + "%");
		}
		if (publish.isDeleted()) {
			sql.append(" and deleted = ?");
			params.add(publish.isDeleted());
		} else {
			sql.append(" and deleted = 0");
		}
		sql.append(" order by id desc limit ?, ?");
		params.add(paginationModel.getStart());
		params.add(paginationModel.getSize());
		return BaseDao.getAll(sql.toString(), Publish.class, params.toArray());
	}

	public Publish getById(String pid) {
		String sql = "select * from t_publish where id=? and deleted = 0";
		List<Publish> result = BaseDao.getAll(sql, Publish.class, pid);
		return result.get(0);
	}

	public long save(Publish publish) {
		String sql = "insert into t_publish(title,content,create_time) values(?,?,?)";
		long num = BaseDao.insert(sql, publish.getTitle(), publish.getContent(), publish.getCreateTime()).longValue();
		if (num > 0L) {
			return num;
		} else {
			return 0L;
		}
	}

	public Boolean update(Publish publish) {
		String sql = "update t_publish set title=?,content=?,update_time=? where id=? and deleted = 0";
		int num = BaseDao.update(sql, publish.getTitle(), publish.getContent(), publish.getUpdateTime(),
				publish.getId());
		if (num > 0) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean delete(String pid) {
		String sql = "update t_publish set deleted=1 where id=?";
		int num = BaseDao.update(sql, pid);
		if (num > 0) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean batchDeleted(List<Integer> pids) {
		StringBuilder sql = new StringBuilder("delete from t_publish where id in(");
		int size = pids.size();
		if (size > 1) {
			for (int i = 0; i < size - 1; i++) {
				sql.append("?,");
			}
			sql.append("?");
		} else if (size == 1) {
			sql.append("?");
		}
		sql.append(")");
		int num = BaseDao.update(sql.toString(), pids);
		if (num > 0) {
			return true;
		} else {
			return false;
		}
	}

}

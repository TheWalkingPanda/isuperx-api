package com.isuperx.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.isuperx.dao.interfaces.IUserDAO;
import com.isuperx.domain.User;

@Repository
public class UserDAOImpl implements IUserDAO {
	
	@Autowired
	private SqlMapClientTemplate sqlMapClientTemplate;

	public long saveUser(User user) {
		Object result = sqlMapClientTemplate.insert("saveUser", user);
		return Long.parseLong(result.toString());
	}

	/**
	 * 返回影响的记录行数
	 */
	public int delUser(long userId) {
		return sqlMapClientTemplate.update("delUser", userId);
	}

	public int updateUser(User user) {
		return sqlMapClientTemplate.update("updateUser", user);
	}

	public User getUserById(long userId) {
		return (User) sqlMapClientTemplate.queryForObject("getUserById", userId);
	}

	@SuppressWarnings("unchecked")
	public List<User> getUsersByParam(User paramUser) {
		return sqlMapClientTemplate.queryForList("getUsersByParam", paramUser);
	}
	
}

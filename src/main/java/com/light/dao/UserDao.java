package com.light.dao;


import com.light.model.User1;

public interface UserDao {
	public int insert(User1 user);
	
	public int deleteById(Integer id);
	
	public int update(User1 user);
	
	public User1 getById(Integer id);
	
	public void getCompany();
}

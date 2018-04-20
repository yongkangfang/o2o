package com.light.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.light.dao.UserDao;
import com.light.model.User1;

@Repository
public class UserDaoImpl implements UserDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int insert(User1 user) {
		// TODO Auto-generated method stub
		
		String sql = "INSERT INTO USER1(ID, USERNAME, PASSWORD, BIRTHDAY) VALUES(?, ?, ?, ?)";
		return this.jdbcTemplate.update(sql, user.getId(), user.getUsername(), user.getPassword(), user.getBirthday()) 
		;		 
	}

	@Override
	public int deleteById(Integer id) {
		// TODO Auto-generated method stub
		String sql = "delete from user1 where id = ?";
		return this.jdbcTemplate.update(sql, id);
	}

	@Override
	public int update(User1 user) {
		// TODO Auto-generated method stub
		String sql = "update user1 set password = ? where id = ?";
		return this.jdbcTemplate.update(
				sql, 
				user.getPassword(),
				user.getId()
				);
	}

	@Override
	public User1 getById(Integer id) {
		// TODO Auto-generated method stub
		String sql = "select * from user1 where id = ?";
		return this.jdbcTemplate.queryForObject(sql, new RowMapper<User1>(){


			@Override
			public User1 mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
                User1 user = new User1();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setBirthday(rs.getDate("birthday"));
                return user;
			}
		}, id);
	}

	@Override
	public void getCompany() {
		// TODO Auto-generated method stub
//		String sql = "select * from company";
//		jdbcTemplate.query(sql, rse)
	}

}

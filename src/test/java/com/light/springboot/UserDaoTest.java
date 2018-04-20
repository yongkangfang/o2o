package com.light.springboot;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.light.dao.UserDao;
import com.light.model.User1;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {
	@Autowired
	private UserDao userDao;
	
	@Test
	public void testInsert(){
		User1 user = new User1();
		user.setId(1);
		user.setUsername("fyk");
		user.setPassword("fyk123");
		user.setBirthday(new Date());
		
		int result = this.userDao.insert(user);
		System.out.println(result);
	}
	
	@Test
	public void testGetById(){
		User1 user = userDao.getById(1);
		System.out.println(user.getUsername());
	}
	
	@Test
	public void testUpdate()
	{
		User1 user = new User1();
		user.setId(3);
		user.setUsername("zhangsan123");
		this.userDao.update(user);
	}
	
    @Test
    public void testDeleteById() {
        int result = this.userDao.deleteById(1);
        System.out.println(result);
    }
	
    @Test
    public void getCompany() {
        int result = this.userDao.deleteById(1);
        System.out.println(result);
    }
	
}

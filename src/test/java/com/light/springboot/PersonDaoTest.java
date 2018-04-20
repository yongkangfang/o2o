package com.light.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.light.dao.PersonDao;
import com.light.model.Person;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonDaoTest {
	@Autowired
	private PersonDao personDao;
	
    @Test
    public void testInsert() {
        Person person = new Person();
        person.setId(1);
        person.setName("管理员");
        person.setAge(25);
        this.personDao.save(person);
        System.out.println("Save Successful!");
    }
    
    @Test
    public void testFindOne() {
        Person person = this.personDao.findOne(1);
        System.out.println(person.getName());
    }
    
    @Test
    public void testUpdate() {
        Person person = new Person();
        person.setId(1);
        person.setName("蒋昌柱");
        person.setAge(25);
        this.personDao.save(person);
        System.out.println("Update Successful!");
    }
    
    @Test
    public void testDelete() {
    	
    }
}

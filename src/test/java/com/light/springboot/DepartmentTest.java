package com.light.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;

import com.light.dao.DepartmentMapper;
import com.light.model.Department;

import io.swagger.annotations.Api;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartmentTest {
	
	@Autowired
	private DepartmentMapper departmengtMapper;
	
	@Test
	public void testInsert()
	{
		Department department = new Department();
		department.setId(1);
		department.setName("研发部");
		department.setDescr("研发产品");
		this.departmengtMapper.insert(department);
	}
	
	@Test
	public void testGetById()
	{
		Department department = this.departmengtMapper.getById(1);
		System.out.println(department.getName());
	}
	
	@Test
	public void testUpdate()
	{
		Department department = new Department();
		department.setId(1);
		department.setDescr("开发高级产品");
		this.departmengtMapper.update(department);
	}
	
	@Test
	public void deleteById()
	{
		this.departmengtMapper.deleteById(1);;
	}
}

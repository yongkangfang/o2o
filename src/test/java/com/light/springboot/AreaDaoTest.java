package com.light.springboot;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.light.dao.AreaDao;
import com.light.entity.Area;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AreaDaoTest {
	@Autowired
	private AreaDao areaDao;
	
	@Test
	public void testQueryArea(){
		List<Area> areaList = areaDao.queryArea();
//		assertEquals(2, areaList.size());
		System.out.println(areaList.size());
	}
}

package com.light.springboot;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.light.dao.ShopDao;
import com.light.dto.ImageHolder;
import com.light.dto.ShopExecution;
import com.light.entity.Area;
import com.light.entity.PersonInfo;
import com.light.entity.Shop;
import com.light.entity.ShopCategory;
import com.light.enums.ShopStateEnum;
import com.light.service.ShopService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ShopServiceTest {
	@Autowired
	private ShopService shopService;
	
	@Test
	public void testAddShop()
	{
		Shop shop = new Shop();
		PersonInfo owner = new PersonInfo();
		ShopCategory shopCategory = new ShopCategory();
		Area area = new Area();
		owner.setUserId(1L);
		area.setAreaId(2);
		shopCategory.setShopCategoryId(1L);
		shop.setShopId(3L);
		shop.setOwner(owner);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setShopName("要测试的店铺1");
		shop.setShopDesc("test1");
		shop.setShopAddr("test1");
		shop.setPhone("test1");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		shop.setAdvice("审核中");
		File shopImg = new File("D:/xiaohuangren.jpg");
		InputStream is = null;
		try {
			is = new FileInputStream(shopImg);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImageHolder imageHolder = new ImageHolder(shopImg.getName(), is);
		ShopExecution se = shopService.addShop(shop, imageHolder);
		assertEquals(ShopStateEnum.CHECK.getState(), se.getState());
	}
}

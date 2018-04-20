package com.light.springboot;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.light.dao.ShopDao;
import com.light.entity.Area;
import com.light.entity.PersonInfo;
import com.light.entity.Shop;
import com.light.entity.ShopCategory;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopDaoTest {
	@Autowired
	private ShopDao shopDao;
	
	@Test
	public void testInsertShop()
	{
		Shop shop = new Shop();
		PersonInfo owner = new PersonInfo();
		ShopCategory shopCategory = new ShopCategory();
		Area area = new Area();
		owner.setUserId(1L);
		area.setAreaId(2);
		shopCategory.setShopCategoryId(1L);
		shop.setShopId(2L);
		shop.setOwner(owner);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setShopName("要测试的店铺");
		shop.setShopDesc("test");
		shop.setShopAddr("test");
		shop.setPhone("test");
		shop.setShopImg("test");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(1);
		shop.setAdvice("审核中");
		int effectNum = shopDao.insertShop(shop);
		assertEquals(1, effectNum);
	}
	
	@Test
	public void testUpdateShop()
	{
		Shop shop = new Shop();
		shop.setShopId(2L);
		shop.setShopDesc("测试描述");
		shop.setShopAddr("测试地址");
		shop.setLastEditTime(new Date());
		int effectNum = shopDao.updateShop(shop);
		assertEquals(1, effectNum);
	}
}

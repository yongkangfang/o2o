package com.light.util;

public class PathUtil {
	
	private static String seperator = System.getProperty("file.seperator");
	
	public static String getImgBasePath() {
		String os = System.getProperty("os.name");
		String basePath = "";
		if(os.toLowerCase().startsWith("win")) {
			basePath = "D:/project/image/";
		}else
		{
			basePath = "/home/xiangzai/image";
		}
//		basePath = basePath.replace("/", seperator);
		return basePath;
	}
	
	public static String getShopImagePath(long shopId) {
		String imagePath = "D:/" + shopId +"/";
		System.out.println(imagePath);
//		return imagePath.replaceAll("/", seperator);
		return imagePath;
	}
}

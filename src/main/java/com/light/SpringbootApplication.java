package com.light;

import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

import com.light.util.EncodingConvertor;

@EnableCaching
@SpringBootApplication
public class SpringbootApplication extends SpringBootServletInitializer  {
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringbootApplication.class);
    }
	
	public static void main(String[] args) {
//		SpringApplication.run(SpringbootApplication.class, args);
//		 ConfigurableApplicationContext context = SpringApplication.run(SpringbootApplication.class, args);
//	     context.getBean(DataSourceProperties.class).show();
	     
//	     ConfigurableApplicationContext context = SpringApplication.run(SpringbootApplication.class, args);
//	     Map<String, EncodingConvertor> map = context.getBeansOfType(EncodingConvertor.class);
//	     System.out.println(map);
		
	     ConfigurableApplicationContext context = SpringApplication.run(SpringbootApplication.class, args);
	     System.out.println(System.getProperty("file.encoding"));
	     Map<String, EncodingConvertor> map = context.getBeansOfType(EncodingConvertor.class);
	     System.out.println(map);
	}
}

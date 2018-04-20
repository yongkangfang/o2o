package com.light.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.light.TimeFilter.TimeFilter;
import com.light.interceptor.TimeInterceptor;
import com.light.listen.ListenerTest;
import com.light.servlet.ServletTest;


@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
	@Value("${ds.userName}")
	private String username;
	
	@Autowired
	private TimeInterceptor timeInterceptor;
	
	@Autowired
	private Environment environment;
	
	public void show()
	{
		System.out.println("ds.username = " + this.username);
		System.out.println("ds.password = " + 
				this.environment.getProperty("ds.password"));
	}
	
	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters()
	{
		FastJsonHttpMessageConverter fastJsonHttpMessageConverter = 
				new FastJsonHttpMessageConverter();
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
		fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
		HttpMessageConverter<?> converter = fastJsonHttpMessageConverter;
		
		return new HttpMessageConverters(converter);
	}
	
	@Bean
	public ServletRegistrationBean servletRegistrationBean(){
		return new ServletRegistrationBean(new ServletTest(), "/servletTest");
	}
	
	@Bean
	public FilterRegistrationBean timeFilter(){
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		
		TimeFilter timeFilter = new TimeFilter();
		registrationBean.setFilter(timeFilter);
		
		List<String> urls = new ArrayList<>();
		urls.add("/*");
		registrationBean.setUrlPatterns(urls);
		
		return registrationBean;
	}
	
	@Bean
	public ServletListenerRegistrationBean<ListenerTest> servletListenerRegistrationBean() {
	    return new ServletListenerRegistrationBean<ListenerTest>(new ListenerTest());
	}
	
	
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor);
    }
}

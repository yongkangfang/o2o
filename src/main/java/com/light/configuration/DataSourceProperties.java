package com.light.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="ds")
public class DataSourceProperties {
	private String url;
	
	private String driverClassName;
	
	private String userName;
	
	private String password;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void show()
	{
		System.out.println("ds.url = " + this.url);
		System.out.println("ds.driverClassName = " + this.driverClassName);
		System.out.println("ds.userName = " + this.userName);
		System.out.println("ds.password = " + this.password);
	}
}

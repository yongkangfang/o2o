package com.light.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EncodingConvertorConfiguration {
	@Bean
	@Conditional(UTF8Condition.class)
    public EncodingConvertor createUTF8EncodingConvertor() {
        return new UTF8EncodingConvertor();
    }
	
	@Bean
	@Conditional(GBKCondition.class)
	public EncodingConvertor createGBKEncodingConvertor() {
	        return new GBKEncodingConvertor();
	}
}

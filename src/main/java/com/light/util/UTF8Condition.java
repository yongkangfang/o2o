package com.light.util;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class UTF8Condition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		// TODO Auto-generated method stub
		String encoding = System.getProperty("file.encoding");
		if(encoding != null)
		{
			return "utf-8".equals(encoding.toLowerCase());
		}
		return false;
	}
}

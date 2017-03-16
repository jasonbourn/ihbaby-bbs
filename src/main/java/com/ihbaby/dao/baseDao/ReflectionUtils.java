package com.ihbaby.dao.baseDao;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

public class ReflectionUtils {
	
	
	public static final String CGLIB_CLASS_SEPARATOR = "$$";
	
	public static final String BRACKET_LEFT = "[";
	
	public static final String BRACKET_RIGHT = "]";
	
	private static Logger logger = LoggerFactory.getLogger(ReflectionUtils.class);
	
	public static Object getFieldValue(Object obj, String fieldName) {
		Field field = getAccessibleField(obj, fieldName);
		
		if (field == null) {
			throw new IllegalArgumentException("Could not find field ".concat("[").concat(fieldName).concat("]").concat(" on target ") + "[" + obj + "]");
		}
		
		Object result = null;
		try {
			result = field.get(obj);
		} catch (IllegalAccessException e) {
			logger.error("不可能抛出的异常{}", e.getMessage());
			throw new RuntimeException(e);
		}
		return result;
	}
	
	public static Field getAccessibleField(Object obj, String fieldName) {
		Validate.notNull(obj, "object can't be null", new Object[0]);
		Validate.notBlank(fieldName, "fieldName can't be blank", new Object[0]);
		for (Class<?> superClass = obj.getClass(); superClass != Object.class;) {
			try {
				Field field = superClass.getDeclaredField(fieldName);
				field.setAccessible(true);
				return field;
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
				
				superClass = superClass.getSuperclass();
			}
			
		}
		
		return null;
	}
}

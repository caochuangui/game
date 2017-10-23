package com.frame.commom;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liuzhengyi
 * @date 2014年12月11日 下午9:02:09
 */
public class PropertiesUtils {
	private static final Logger log = LoggerFactory
			.getLogger(PropertiesUtils.class);
	private static Properties properties = new Properties();

	static {
		try {
			properties.load(new FileInputStream("resource/sys.properties"));
		} catch (FileNotFoundException e) {
			log.error("", e);
		} catch (IOException e) {
			log.error("", e);
		}
	}

	public static <T> T getObjectByFile(Class<T> c) {
		return getObjectByFile(c, null);
	}

	public static <T> T getObjectByFile(Class<T> c, String fileName) {
		Properties newProperties = new Properties();
		T newInstance = null;
		if (fileName == null) {
			newProperties = properties;
		} else {
			try {
				newProperties.load(PropertiesUtils.class
						.getResourceAsStream(fileName));
			} catch (FileNotFoundException e) {
				log.error("", e);
			} catch (IOException e) {
				log.error("", e);
			}
		}

		try {
			newInstance = c.newInstance();
			String simpleName = newInstance.getClass().getName().toLowerCase();
			Method[] methods = newInstance.getClass().getMethods();

			for (Method method : methods) {
				String name = method.getName().toLowerCase();
				String key = simpleName + "."
						+ name.substring(3, name.length());
				if (name.startsWith("set") && newProperties.containsKey(key)) {
					Class<?> parameterType = method.getParameterTypes()[0];
					if (isInt(parameterType)) {
						method.invoke(newInstance,
								Integer.valueOf(properties.getProperty(key)));
					} else if (isBoolean(parameterType)) {
						method.invoke(newInstance,
								Boolean.valueOf(properties.getProperty(key)));
					} else if (isDouble(parameterType)) {
						method.invoke(newInstance,
								Double.valueOf(properties.getProperty(key)));
					} else if (isFloat(parameterType)) {
						method.invoke(newInstance,
								Float.valueOf(properties.getProperty(key)));
					} else if (isLong(parameterType)) {
						method.invoke(newInstance,
								Long.valueOf(properties.getProperty(key)));
					} else if (isShort(parameterType)) {
						method.invoke(newInstance,
								Short.valueOf(properties.getProperty(key)));
					} else {
						method.invoke(newInstance, properties.getProperty(key));
					}
				}
			}

		} catch (InstantiationException e) {
			log.error("", e);
		} catch (Exception e) {
			log.error("", e);
		}

		return newInstance;
	}

	private static boolean isInt(Class<?> c) {
		return c == Integer.TYPE || c == Integer.class;
	}

	private static boolean isShort(Class<?> c) {
		return c == Short.TYPE || c == Short.class;
	}

	private static boolean isLong(Class<?> c) {
		return c == Long.TYPE || c == Long.class;
	}

	private static boolean isDouble(Class<?> c) {
		return c == Double.TYPE || c == Double.class;
	}

	private static boolean isFloat(Class<?> c) {
		return c == Float.TYPE || c == Float.class;
	}

	private static boolean isBoolean(Class<?> c) {
		return c == Boolean.TYPE || c == Boolean.class;
	}

}

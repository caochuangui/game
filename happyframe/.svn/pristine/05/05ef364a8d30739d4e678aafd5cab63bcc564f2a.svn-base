package com.frame.bean;

import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

/**
 * @author liuzhengyi
 * @date 2014年11月20日 下午9:06:03
 */
public class BeanManager {
	private static final Logger log = LoggerFactory
			.getLogger(BeanManager.class);

	private static BeanManager instance;

	public static synchronized BeanManager getInstance() {
		if (instance == null) {
			instance = new BeanManager();
		}
		return instance;
	}

	private ApplicationContext cxt;

	public <T> T getBean(Class<T> c) {
		return (T) cxt.getBean(c);
	}

	public void init(ApplicationContext cxt) {
		this.cxt = cxt;
		initLoad();
	}

	public void initLoad() {
		Map<String, ILoad> beansOfType = cxt.getBeansOfType(ILoad.class);
		for (Entry<String, ILoad> entry : beansOfType.entrySet()) {
			try {
				entry.getValue().load();
			} catch (Exception e) {
				log.error("", e);
			}
		}

	}
}

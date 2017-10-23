package com.frame.net;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.frame.extention.EventJobFactory;
import com.frame.listener.ListenerActionManager;

/**
 * @author liuzhengyi
 * @date 2014年12月26日 上午11:43:54
 */
@Component
public class InitBeans implements InitializingBean, ApplicationContextAware {
	private ApplicationContext cxt;

	/**
	 * 
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		cxt = applicationContext;
	}

	/**
	 * 
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		EventJobFactory.init(cxt);
		ListenerActionManager.getIntance().init(cxt);
	}

}

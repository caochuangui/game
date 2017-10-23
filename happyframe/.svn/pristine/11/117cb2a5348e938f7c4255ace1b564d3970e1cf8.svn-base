package com.frame.listener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.frame.listener.anotation.Action;
import com.frame.listener.anotation.Listener;

/**
 * @author liuzhengyi
 * @date 2014年12月25日 下午5:28:01
 */
@Component
public class ListenerActionManager {

	Logger log = LoggerFactory.getLogger(ListenerActionManager.class);

	private static ListenerActionManager intance;

	public ListenerActionManager() {
		intance = this;
	}

	public static ListenerActionManager getIntance() {
		return intance;
	}

	private Map<Short, List<ActionObject>> actionMap = new HashMap<Short, List<ActionObject>>();

	public void init(ApplicationContext cxt) {
		Map<String, Object> beansWithAnnotation = cxt
				.getBeansWithAnnotation(Listener.class);
		Collection<Object> values = beansWithAnnotation.values();
		for (Object o : values) {
			Method[] methods = o.getClass().getMethods();
			for (Method method : methods) {
				Action annotation = method.getAnnotation(Action.class);
				if (annotation != null) {
					short type = annotation.type();
					List<ActionObject> list = actionMap.get(type);
					if (list == null) {
						list = new ArrayList<ActionObject>();
						actionMap.put(type, list);
					}
					list.add(new ActionObject(o, method));
				}
			}

		}
	}

	public void actionByType(short type, Object... objects) {
		List<ActionObject> list = actionMap.get(type);
		if (list != null) {
			for (ActionObject actionObject : list) {
				try {
					actionObject.getAction().invoke(actionObject.getOwner(),
							objects);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
					log.error("", e);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					log.error("", e);
				} catch (InvocationTargetException e) {
					e.printStackTrace();
					log.error("", e);
				}
			}
		}

	}
}

package com.frame.listener;

import java.lang.reflect.Method;

/**
 * @author liuzhengyi
 * @date 2014年12月25日 下午5:59:58
 */
public class ActionObject {
	private Object owner;
	private Method action;

	public ActionObject(Object owner, Method action) {
		this.owner = owner;
		this.action = action;
	}

	/**
	 * @return the owner
	 */
	public Object getOwner() {
		return owner;
	}

	/**
	 * @param owner
	 *            the owner to set
	 */
	public void setOwner(Object owner) {
		this.owner = owner;
	}

	/**
	 * @return the action
	 */
	public Method getAction() {
		return action;
	}

	/**
	 * @param action
	 *            the action to set
	 */
	public void setAction(Method action) {
		this.action = action;
	}

}

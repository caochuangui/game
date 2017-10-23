package com.frame.async;

/**
 * @author liuzhengyi
 * @date 2014年11月20日 下午7:11:29
 */
public abstract class AsynOperation {

	public abstract void save2DB();

	public void commit() {
		Async2DBService.getInstance().addAsyncObject(new AsynObject(this));
	}

	public abstract Integer getId();

	public void setId(Integer id) {
	}
}

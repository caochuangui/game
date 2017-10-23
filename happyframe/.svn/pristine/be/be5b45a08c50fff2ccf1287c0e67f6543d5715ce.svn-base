package com.frame.async;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liuzhengyi
 * @date 2014年11月21日 下午2:17:59
 */
public class AsynObject {
	private AsynOperation asynOperation;
	private AtomicInteger atomicInteger = new AtomicInteger();

	public AsynObject(AsynOperation asynOperation) {
		this.asynOperation = asynOperation;
	}

	/**
	 * @return the asynOperation
	 */
	public AsynOperation getAsynOperation() {
		return asynOperation;
	}

	/**
	 * @param asynOperation
	 *            the asynOperation to set
	 */
	public void setAsynOperation(AsynOperation asynOperation) {
		this.asynOperation = asynOperation;
	}

	/**
	 * @return the atomicInteger
	 */
	public AtomicInteger getAtomicInteger() {
		return atomicInteger;
	}

	public void increamInt() {
		atomicInteger.incrementAndGet();
	}

	public int getAtomicCount() {
		return atomicInteger.get();
	}

}

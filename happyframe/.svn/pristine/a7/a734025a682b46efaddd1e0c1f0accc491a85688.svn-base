package com.frame.async;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frame.OnlinePlayerManager;
import com.frame.extention.EventJobFactory;
import com.frame.extention.EventJobManager;

/**
 * 
 * 异步更新到数据库
 * 
 * @author liuzhengyi
 * @date 2014年11月20日 下午5:53:04
 */
public class Async2DBService {
	private static final Logger log = LoggerFactory
			.getLogger(Async2DBService.class);

	private static Async2DBService instance;

	public static synchronized Async2DBService getInstance() {
		if (instance == null) {
			instance = new Async2DBService();
		}
		return instance;
	}

	public static volatile boolean stop = false;

	private static ConcurrentLinkedQueue<AsynObject> queue = new ConcurrentLinkedQueue<AsynObject>();

	public static ExecutorService exects;

	// 执行的更新线程数
	private static final int NUM = 10;
	// 每周期执行更新的数据
	private static final int EXECUTE_NUM_PER_TIME = 100;
	// 每周期执行更新的数据
	private static final long SLEEP_TIME = 100;

	/**
	 * 因为是多线程操作该数据所以在添加和移除数据时要注意同步问题
	 */
	private static ConcurrentHashMap<Integer, Set<AsynOperation>> updateObjectSet = new ConcurrentHashMap<Integer, Set<AsynOperation>>();

	public Async2DBService() {
		exects = Executors.newCachedThreadPool();
		for (int i = 0; i < NUM; i++) {
			addProessThread();
		}

	}

	/**
	 * @author liuzhengyi
	 * @date 2014年11月20日 下午8:18:31
	 */
	private void addProessThread() {
		exects.execute(new Runnable() {

			@Override
			public void run() {
				int num = 0;
				while (true) {
					// 每一轮开始的时间
					long currentTimeMillis = System.currentTimeMillis();
					try {
						num = 0;
						AsynObject asyncObj = null;
						/**
						 * 当队列不为空且本次更新次数还没达到设定值时，继续更新
						 */
						while ((asyncObj = queue.poll()) != null) {
							System.out.println("======");
							boolean flag = true;
							try {
								AsynOperation asynOperation = asyncObj
										.getAsynOperation();
								// 从按玩家Id保存的更新集合中移除
								Set<AsynOperation> set = updateObjectSet
										.get(asynOperation.getId());
								if (set != null) {
									set.remove(asynOperation);
								}
								// 从集合中移除完之后更新数据
								asynOperation.save2DB();
							} catch (Exception e) {
								log.error("", e);
								flag = false;
								asyncObj.increamInt();
							}

							if (!flag && asyncObj.getAtomicCount() <= 2) {
								queue.add(asyncObj);
							} else if (!OnlinePlayerManager
									.getIntance()
									.isOnline(
											asyncObj.getAsynOperation().getId())) {
								System.out.println("player is not online");
								// 更新完数据之后判断玩家是否不在在线状态并且所有数据是否更新完，是则从保存更新集合中移除该玩家的数据并且添加移除内存数据eventjob
								Set<AsynOperation> remove = updateObjectSet
										.remove(asyncObj.getAsynOperation()
												.getId());
								if (remove != null && !remove.isEmpty()) {
									Set<AsynOperation> putIfAbsent = updateObjectSet
											.putIfAbsent(
													asyncObj.getAsynOperation()
															.getId(), remove);
									if (putIfAbsent != null) {
										putIfAbsent.addAll(remove);
										updateObjectSet.put(asyncObj
												.getAsynOperation().getId(),
												putIfAbsent);
									}
									System.out
											.println("remove is not Empty,size is "
													+ remove.size());
								} else {
									System.out.println("remove is null :"
											+ (remove == null) + ",size is :"
											+ remove == null ? -1 : remove
											.size());
									EventJobManager.getInstance().addEventJob(
											EventJobFactory
													.createExtention(asyncObj
															.getAsynOperation()
															.getId()));
								}
							}

							if (num++ > EXECUTE_NUM_PER_TIME) {
								break;
							}
						}

						if (stop) {
							/*
							 * 如果已经关闭了更新服务，而且队列中已经没有数据，那么退出。
							 */
							if (queue.isEmpty()) {
								return;
							}
						} else {
							/*
							 * 休眠一段时间，等待下次更新。(暂定休眠周期为100ms)
							 */
							try {
								long restTime = SLEEP_TIME
										- (System.currentTimeMillis() - currentTimeMillis);
								if (restTime > 0) {
									Thread.sleep(restTime);
								}
							} catch (InterruptedException e) {
								log.error("", e);
							}
						}

					} catch (Exception e) {
						log.error("", e);
					}
				}

			}
		});

	}

	/**
	 * 添加更新数据
	 * 
	 * @author liuzhengyi
	 * @date 2014年12月19日 下午3:24:56
	 * @param asynObject
	 */
	public void addAsyncObject(AsynObject asynObject) {
		if (!stop) {
			try {
				Integer id = asynObject.getAsynOperation().getId();
				if (id != null) {
					Set<AsynOperation> set = updateObjectSet.get(id);
					// 查找是否已有该对象在更新内存中了
					if (set == null) {
						set = new HashSet<AsynOperation>();
					}

					if (!set.contains(asynObject.getAsynOperation())) {
						set.add(asynObject.getAsynOperation());
						queue.add(asynObject);
					}
					Set<AsynOperation> putIfAbsent = updateObjectSet
							.putIfAbsent(id, set);
					if (putIfAbsent != null) {
						putIfAbsent.addAll(set);
					}
					System.out.println("addAsyncObject ：id is：" + id
							+ ",size is :"
							+ (putIfAbsent == null ? -1 : putIfAbsent.size()));
				} else {
					queue.add(asynObject);
				}

			} catch (Exception e) {
				log.error("", e);
			}

		}
	}

	public void stop() {
		stop(60, TimeUnit.SECONDS);
	}

	/**
	 * @author liuzhengyi
	 * @date 2014年11月27日 下午2:44:00
	 * @param i
	 * @param seconds
	 */
	private void stop(int i, TimeUnit seconds) {
		stop = true;
		try {
			log.info("stop Async2DBService begin");
			exects.shutdown();
			boolean awaitTermination = exects.awaitTermination(i, seconds);
			if (awaitTermination) {
				log.info("Asyn2DBService is stop succeful");
			} else {
				exects.shutdownNow();
				log.info("Asyn2DBService is shutdownNow");
			}
		} catch (InterruptedException e) {
			log.error("stop Asyn2DBService error;/n" + e);
		} finally {
			if (queue.size() > 0) {
				log.error("some AsynObject is not action ;size :"
						+ queue.size());
			}
			log.info("stop Async2DBService finish");
		}
	}

	/**
	 * 判断是否能移除，条件是更新数据的集合中没有改玩家的更新数据
	 * 
	 * @author liuzhengyi
	 * @date 2014年12月19日 下午3:23:39
	 * @param id
	 * @return
	 */
	public boolean isCanRemove(Integer id) {
		if (!updateObjectSet.containsKey(id)) {
			System.out.println("updateObjectSet is not containsKey : " + id);
			return true;
		} else {
			Set<AsynOperation> remove = updateObjectSet.remove(id);
			if (remove != null && !remove.isEmpty()) {
				Set<AsynOperation> putIfAbsent = updateObjectSet.putIfAbsent(
						id, remove);
				if (putIfAbsent != null) {
					putIfAbsent.addAll(remove);
				}
				System.out.println("updateObjectSet is containsKey : " + id
						+ ",size is :"
						+ (putIfAbsent == null ? -1 : putIfAbsent.size()));
				return false;
			} else {
				System.out.println("updateObjectSet is empty : " + id);
			}
		}
		return true;
	}
}

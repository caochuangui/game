package com.frame.extention;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frame.packet.RespPacket;

/**
 * 
 * 用于管理EventJob事务
 * 
 * @author liuzhengyi
 * @date 2014年11月19日 下午5:34:37
 */

public class EventJobManager extends Thread {

	private static final Logger log = LoggerFactory
			.getLogger(EventJobManager.class);

	private static final long CYCLE_TIME = 100;

	private static volatile boolean stop = false;

	private static EventJobManager instance;

	public static EventJobManager getInstance() {
		return instance;
	}

	/**
	 * 用于处理EventJob的线程池
	 */
	private static ThreadPoolExecutor extentionThreadPool;

	public EventJobManager(int corePoolSize) {
		extentionThreadPool = new ThreadPoolExecutor(corePoolSize,
				corePoolSize * 2, 30, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>());
		instance = this;
	}

	private static ConcurrentHashMap<Integer, ConcurrentLinkedQueue<AbstractEventJob>> playerIdAndEventJobList = new ConcurrentHashMap<Integer, ConcurrentLinkedQueue<AbstractEventJob>>();
	private static ConcurrentHashMap<Integer, AtomicBoolean> playerIdAndLock = new ConcurrentHashMap<Integer, AtomicBoolean>();

	/**
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		while (!stop) {
			long currentTimeMillis = System.currentTimeMillis();

			Set<Integer> keySet = playerIdAndEventJobList.keySet();
			for (int key : keySet) {
				ConcurrentLinkedQueue<AbstractEventJob> list = playerIdAndEventJobList
						.get(key);
				if (list != null && !list.isEmpty()) {
					AtomicBoolean atomicBoolean = playerIdAndLock.get(key);
					if (atomicBoolean == null) {
						atomicBoolean = new AtomicBoolean();
						playerIdAndLock.put(key, atomicBoolean);
					}
					if (atomicBoolean.get()) {
						continue;
					}
					final AbstractEventJob poll = list.poll();
					if (poll != null) {
						atomicBoolean.set(true);
						poll.setAtomicBoolean(atomicBoolean);
						extentionThreadPool.execute(new Runnable() {

							@Override
							public void run() {
								Object excete = null;
								try {
									Object o = poll.excete();
									System.out.println("EJM: " + poll.getMess() + " " + o);
									if (o instanceof RespPacket) {
										excete = (RespPacket) o;
									} else if (o instanceof AbstractEventJob) {
										AbstractEventJob job = (AbstractEventJob) o;
										addEventJob(job);
									} else if (o instanceof String) {
										excete = o;
									} else if (o instanceof StringBuffer) {
										excete = o.toString();
									}
								} catch (Exception e) {
									log.error("", e);
									e.printStackTrace();
								}
								System.out.println("EJM excete: " + excete);
								poll.getAtomicBoolean().set(false);
								if (excete != null
										&& poll.getChannel().isConnected()) {
									System.out.println("EJM write: " + excete);
									poll.getChannel().write(excete);
								}
							}
						});
					}
				}
			}

			long restTime = (currentTimeMillis + CYCLE_TIME)
					- System.currentTimeMillis();
			long sleepTime = restTime > 0 ? restTime : 0;
			if (sleepTime > 0) {
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
		log.info("stop EventJobManager finish");
	}

	public void addEventJob(AbstractEventJob abstractEventJob) {
		if (stop) {
			return;
		}
		System.out.println("addEventJob ---- " + abstractEventJob);
		int playerId = abstractEventJob.getPlayerId();
		ConcurrentLinkedQueue<AbstractEventJob> concurrentLinkedQueue = playerIdAndEventJobList
				.get(playerId);
		if (concurrentLinkedQueue == null) {
			concurrentLinkedQueue = new ConcurrentLinkedQueue<AbstractEventJob>();
			ConcurrentLinkedQueue<AbstractEventJob> putIfAbsent = playerIdAndEventJobList
					.putIfAbsent(playerId, concurrentLinkedQueue);
			if (putIfAbsent != null) {
				concurrentLinkedQueue = putIfAbsent;
			}
		}
		System.out.println("addEventJob " + abstractEventJob.getMsgId());
		concurrentLinkedQueue.add(abstractEventJob);
	}

	public void removeCache(int playerId) {
		playerIdAndEventJobList.remove(playerId);
		playerIdAndLock.remove(playerId);
	}

	public void shutdown() {
		stop = true;
	}

	public boolean isHasEventJobList(int playerId) {
		return playerIdAndEventJobList.containsKey(playerId);
	}

}

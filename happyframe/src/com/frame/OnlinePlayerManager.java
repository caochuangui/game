package com.frame;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.frame.async.Async2DBService;
import com.frame.base.GameSession;
import com.frame.extention.EventJobFactory;
import com.frame.extention.EventJobManager;
import com.frame.listener.ListenerActionManager;
import com.frame.net.netty.CheckBeatType;
import com.game.module.player.model.Player;

/**
 * @author liuzhengyi
 * @date 2014年11月18日 下午5:21:25
 */
@Component
public class OnlinePlayerManager {

	private static final Logger log = LoggerFactory
			.getLogger(OnlinePlayerManager.class);

	private static final long REMOVE_CACHE_TIME = 1 * 60 * 1000;
	private static final long MAX_BEAT_COUNT = 3;

	private static OnlinePlayerManager intance;

	public OnlinePlayerManager() {
		intance = this;
	}

	public static OnlinePlayerManager getIntance() {
		return intance;
	}

	private ConcurrentHashMap<Channel, GameSession> channel2GameSession = new ConcurrentHashMap<Channel, GameSession>();
	private ConcurrentHashMap<Integer, Channel> playerId2Channel = new ConcurrentHashMap<Integer, Channel>();
	/** 玩家离开游戏时记录时间，30分钟后移除玩家内存信息 **/
	private ConcurrentHashMap<Integer, Long> logoutTime = new ConcurrentHashMap<Integer, Long>();
	/** 记录连续发送的心跳包但没有收到回复的次数 **/
	private ConcurrentHashMap<Integer, Integer> beatCount = new ConcurrentHashMap<Integer, Integer>();

	public void addGameSession(Channel channel) {
		if (channel2GameSession.containsKey(channel)) {
			channel2GameSession.remove(channel);
		}
		GameSession gameSession = new GameSession();
		gameSession.setChannel(channel);
		channel2GameSession.put(channel, gameSession);
	}

	public boolean afterLogin(Player player, Channel channel) {
		GameSession gameSession = channel2GameSession.get(channel);
		if (gameSession == null) {
			channel.close();
			return false;
		} else {
			gameSession.setPlayer(player);
			gameSession.setUid(player.getPlayerId());
			Channel oldChannel = playerId2Channel.putIfAbsent(
					player.getPlayerId(), channel);
			if (oldChannel != null && oldChannel != channel) {
				channel2GameSession.remove(oldChannel);
				oldChannel.close();
				playerId2Channel.put(player.getPlayerId(), channel);
				return false;
			}
		}
		return true;
	}

	public void remove(Player player) {
		Channel channel = null;
		try {

			try {
				// List<RemoveCacheListener> listeners = ListenerManager
				// .getIntance().getListeners(RemoveCacheListener.class);
				// if (listeners != null && !listeners.isEmpty()) {
				// for (RemoveCacheListener removeCache : listeners) {
				// removeCache.removeCache(player.getPlayerId());
				// }
				// }
				ListenerActionManager.getIntance().actionByType((short) 1,
						player.getPlayerId());
			} catch (Exception e) {
				log.error("", e);
			}

		} catch (Exception e) {
			log.error("", e);
		} finally {
			if (channel != null && channel.isConnected()) {
				channel.close();
			}

		}
	}

	public GameSession getGameSsionByChannel(Channel channel) {
		return channel2GameSession.get(channel);
	}

	public Channel getChannelByPlayerId(int playerId) {
		return playerId2Channel.get(playerId);
	}

	public List<Integer> getAllOnlinePlayerId() {
		List<Integer> list = new ArrayList<Integer>();
		Set<Integer> keySet = playerId2Channel.keySet();
		list.addAll(keySet);
		return list;
	}

	public void clearBeatCount(Channel channel) {
		GameSession gameSession = channel2GameSession.get(channel);
		if (gameSession != null && gameSession.getUid() != 0) {
			log.debug("entry clear beat count ,beatCount is :" + beatCount
					+ ", logoutTime is :" + logoutTime);
			beatCount.remove(gameSession.getUid());
			logoutTime.remove(gameSession.getUid());
			log.debug("clear beat count ,id is :" + gameSession.getUid());
		}
	}

	public boolean isCanRemove(int playerId) {
		Long l = logoutTime.get(playerId);
		if (l == null) {
			return false;
		}
		return ((l + REMOVE_CACHE_TIME) < System.currentTimeMillis())
				&& Async2DBService.getInstance().isCanRemove(playerId);
	}

	public void addRemoveEventJob(ChannelHandlerContext ctx) {
		GameSession gameSsion = getGameSsionByChannel(ctx.getChannel());
		if (gameSsion != null
				&& OnlinePlayerManager.getIntance().getChannelByPlayerId(
						gameSsion.getUid()) != null
				&& logoutTime.get(gameSsion.getUid()) != null) {
			log.info("add remove event job ,playerId is :" + gameSsion.getUid());
			EventJobManager.getInstance().addEventJob(
					EventJobFactory.createExtention(gameSsion.getUid()));
			logoutTime.put(gameSsion.getUid(), System.currentTimeMillis());

		}
		if (gameSsion != null) {
			channel2GameSession.remove(ctx.getChannel());
			playerId2Channel.remove(gameSsion.getUid());
		}
		if (ctx.getChannel().isConnected()) {
			ctx.getChannel().close();
		}
	}

	public CheckBeatType increaseBeatCount(Channel channel) {
		int playerId = channel2GameSession.get(channel).getUid();
		if (playerId == 0) {
			return CheckBeatType.UN_LOGIN;
		}
		if (logoutTime.contains(playerId)) {
			return CheckBeatType.WAIT_REMOVEJOB;
		}
		int count = 0;
		Integer newCount = beatCount.putIfAbsent(playerId, count);
		if (newCount != null) {
			count = newCount;
		}
		count++;

		beatCount.put(playerId, count);
		if (count > MAX_BEAT_COUNT) {
			return CheckBeatType.CAN_ADD_REMOVEJOB;
		}
		return CheckBeatType.INCREAT_COUNT;
	}

	/**
	 * @author liuzhengyi
	 * @date 2014年11月25日 下午3:58:24
	 * @param playerId
	 * @return
	 */
	public boolean containKey(Integer playerId) {
		// TODO Auto-generated method stub
		return logoutTime.containsKey(playerId);
	}

	/**
	 * 判断玩家是否在线
	 * 
	 * @author liuzhengyi
	 * @date 2014年12月19日 下午2:54:01
	 * @param playerId
	 * @return
	 */
	public boolean isOnline(Integer playerId) {
		return playerId2Channel.containsKey(playerId);
	}

}

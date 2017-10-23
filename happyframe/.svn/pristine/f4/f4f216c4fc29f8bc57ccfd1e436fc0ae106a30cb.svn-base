package com.game.module.player.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.frame.bean.ILoad;
import com.frame.listener.anotation.Action;
import com.frame.listener.anotation.Listener;
import com.frame.packet.RespPacket;
import com.game.commom.listener.ListenerType;
import com.game.module.PacketId;
import com.game.module.player.dao.PlayerDao;
import com.game.module.player.model.ConfigPlayerModel;
import com.game.module.player.model.Player;

/**
 * @author liuzhengyi
 * @date 2014年11月17日 下午8:59:00
 */
@Component
@Listener
public class PlayerService implements ILoad {
	private static final Logger log = LoggerFactory
			.getLogger(PlayerService.class);

	public static final int STRENGTH = 20;
	
	private static PlayerService instance;

	public PlayerService() {
		instance = this;
	}

	public static PlayerService getInstance() {
		return instance;
	}

	@Autowired
	PlayerDao playerDao;

//	private ConfigPlayerModel configPlayerModel;
	private Map<Integer, Player> allPlayers = new ConcurrentHashMap<Integer, Player>();

	/**
	 * 
	 * @see game.commom.listener.AfterLoginListener#sys()
	 */
	@Action(type = ListenerType.AFTER_LOGIN)
	public void afterLogin(Player player) {
		System.out.println("玩家登陆成功");
		allPlayers.put(player.getPlayerId(), player);
	}

	public Player getPlayerById(int playerId) {
		return playerDao.getPlayerById(playerId);
	}

	public Player createPlayer(int playerId) {
		Player player = new Player(playerId);
		return player;
	}

	public void updatePlayer(Player player) {
		player.commit();
	}

	public RespPacket addGoldAndResp(int playerId, int addGold) {
		RespPacket respPacket = new RespPacket();
		respPacket.setPacketId(PacketId.ADD_GOLD);

		StringBuffer sb = new StringBuffer();
		Player player = addGold(playerId, addGold);
		sb.append("1;").append(player.toMsg());
		respPacket.setMess(sb.toString());;
		return respPacket;
	}

	public Player addGold(int playerId, int addGold) {
		Player player = getPlayerByPlayerId(playerId);
		player.setGold(player.getGold() + addGold);
		updatePlayer(player);
		return player;
	}

	public Player getPlayerByPlayerId(int playerId) {
		Player player = allPlayers.get(playerId);
		if (player == null) {
			player = playerDao.getPlayerById(playerId);
			if (player == null) {
				try {
					throw new Exception("the playerId is not exits");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return player;
	}

	/**
	 * 
	 * @see frame.bean.ILoad#load()
	 */
	@Override
	public void load() throws Exception {
		log.info("playerService load");
	}

	/**
	 * 
	 * @see game.commom.listener.RemoveCacheListener#removeCache(int)
	 */
	@Action(type = ListenerType.REMOVE_CACHE)
	public void removeCache(int playerId) {
		allPlayers.remove(playerId);
	}

	/**
	 * @author liuzhengyi
	 * @date 2014年12月15日 上午11:53:41
	 * @param name
	 * @return
	 */
	public RespPacket createPlayer(int playerId, String name) {
		StringBuffer sb = new StringBuffer();
		Player player = allPlayers.get(playerId);
		player.setName(name);
		player.commit();
		RespPacket respPacket = new RespPacket();
		respPacket.setPacketId(PacketId.CREATE_PLAYER);
		sb.append("1;").append(player.toMsg());
		respPacket.setMess(sb.toString());
		return respPacket;
	}
}

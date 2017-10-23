package com.game.module.farm.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.frame.OnlinePlayerManager;
import com.frame.bean.ILoad;
import com.game.commom.configDao.DaoInfo;
import com.game.module.farm.dao.FarmDao;
import com.game.module.farm.model.Farm;
import com.game.module.farm.model.FarmInfo;
import com.game.module.farm.model.FarmInit;
import com.game.module.player.dao.PlayerDao;
import com.game.module.player.model.Player;
import com.game.module.player.service.PlayerService;

/**
 * 单独的线程进行登陆处理
 * 
 * @author liuzhengyi
 * @date 2014年11月19日 上午11:06:30
 */
@Component
public class FarmService implements Runnable, ILoad {

	private static final Logger log = LoggerFactory
			.getLogger(FarmService.class);

	@Autowired
	PlayerDao playerDao;
	@Autowired
	FarmDao farmDao;
	@Autowired
	DaoInfo daoInfo;

	private static FarmService intance;
	private static boolean stop = false;

	private Map<Integer, Map<Integer, Farm>> framMap = new ConcurrentHashMap<>();
	private Map<Integer,FarmInit> farmInitMap = new HashMap<>();
	private Map<Integer,FarmInfo> farmInfoMap = new HashMap<>();
	
	public static FarmService getInstance() {
		return intance;
	}

	public FarmService() {
		intance = this;
	}

	/**
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			while (!stop) {

				List<Integer> pIdList = OnlinePlayerManager.getIntance()
						.getAllOnlinePlayerId();
				if (pIdList.isEmpty()) {
					Thread.sleep(100);
					continue;
				}

				long curTime = System.currentTimeMillis();
				for (Integer pId : pIdList) {

					Player player = playerDao.getPlayerById(pId);
					if (player == null) {
						System.err.println("ERROR: player null, pId=" + pId);
						continue;
					}

					refreshFarmStatus(player, curTime);
				}

			} // end while
		} catch (Exception e) {
			log.error("", e);
		}

	}

	public void shutdown() {
		stop = true;
	}

	/**
	 * 
	 * @see com.gzwabao.frame.bean.ILoad#load()
	 */
	@Override
	public void load() throws Exception {
		List<FarmInit> farmInitList = daoInfo.getFarmInitList();
		for(FarmInit farmInit : farmInitList){
			farmInitMap.put(farmInit.getFarmId(), farmInit);
		}
		List<FarmInfo> farmInfoList = daoInfo.getFarmInfoList();
		for(FarmInfo farmInfo : farmInfoList){
			farmInfoMap.put(farmInfo.getLevel(), farmInfo);
		}
	}

	private void refreshFarmStatus(Player player, long time) {

	}

	/**
	 * 获取所有的农田信息，没有就创建
	 * @param playerId
	 * @return
	 */
	public Set<Farm> getAllFarm(int playerId) {
		Set<Farm> set = new HashSet<>();
		if (framMap.containsKey(playerId)) {
			set.addAll(framMap.get(playerId).values());
			return set;
		}
		List<Farm> farmListByPlayerId = farmDao.getFarmListByPlayerId(playerId);
		Map<Integer, Farm> map = new HashMap<Integer, Farm>();
		if (map != null && !map.isEmpty()) {
			set.addAll(farmListByPlayerId);
			for (Farm farm : farmListByPlayerId) {
				map.put(farm.getFarmId(), farm);
			}
			framMap.put(playerId, map);
		} else {
			Player player = PlayerService.getInstance().getPlayerById(playerId);
			FarmInfo farmInfo = farmInfoMap.get(player.getLevel());
			for(int i = 1;i <= farmInfo.getLandNum();i++){
				FarmInit farmInit = farmInitMap.get(i);
				Farm createFarm = createFarm(farmInit,player);
				farmDao.addFarm(createFarm);
				map.put(createFarm.getFarmId(), createFarm);
				set.add(createFarm);
			}
			framMap.put(playerId, map);
		}
		return set;
	}

	private Farm createFarm(FarmInit farmInit, Player player) {
		Farm farm = new Farm();
		farm.setFarmId(farmInit.getFarmId());
		farm.setPlantId(farmInit.getPlantId());
		farm.setPlayerId(player.getId());
		farm.setType(farmInit.getType());
		if(farmInit.getPlantId() != 0){
			farm.setStartTime(System.currentTimeMillis());
			farm.setState(1);
		}else{
			farm.setStartTime(0);
			farm.setState(0);
		}
		return farm;
	}

	public Farm getFarm(int farmId, int farm_type, int playerId) {
		return farmDao.getFarm(farmId, farm_type, playerId);
	}

	public void addFarm(Farm farm) {
		farmDao.addFarm(farm);
	}

	public void updateFarm(Farm farm) {
		farmDao.updateFarm(farm);
	}

	public void deleteFarm(Farm farm) {
		farmDao.deleteFarm(farm);
	}

}

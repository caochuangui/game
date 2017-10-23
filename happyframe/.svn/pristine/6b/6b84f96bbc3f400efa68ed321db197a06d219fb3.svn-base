package com.game.module.farm.dao;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;

import com.game.module.farm.model.Farm;

/**
 * @author liuzhengyi
 * @date 2014年11月19日 上午11:38:02
 */
@DAO
public interface FarmDao {
	
	
	@SQL("SELECT * FROM farm where playerId =:1")
	public List<Farm> getFarmListByPlayerId(int playerId);
	
	@SQL("SELECT * FROM farm where farmId =:1 and type =:2 and playerId =:3")
	public Farm getFarm(int farmId, int type, int playerId);
	
	@SQL("INSERT INTO farm (farmId, type, playerId, plantId, startTime) values (:1.farmId, :1.type, :1.playerId, :1.plantId, :1.startTime)")
	public Integer addFarm(Farm farm);
	
	@SQL("UPDATE farm set plantId =:1.plantId and startTime =:1.startTime where farmId =:1.farmId and type =:1.type and playerId =:1.playerId")
	public void updateFarm(Farm farm);
	
	@SQL("DELETE from farm where farmId=:1.farmId and type=:1.type and playerId=:1.playerId")
	public void deleteFarm(Farm farm);

}

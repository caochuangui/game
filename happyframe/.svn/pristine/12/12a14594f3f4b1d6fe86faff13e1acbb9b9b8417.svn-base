package com.game.module.player.dao;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;

import com.game.module.player.model.Player;

/**
 * @author liuzhengyi
 * @date 2014年11月19日 上午11:38:02
 */
@DAO
public interface PlayerDao {


	@SQL("SELECT * FROM player where playerId =:1")
	public Player getPlayerById(int playerId);

	@SQL("update player set level =:1.level ,viplevel =:1.viplevel,name =:1.name,copper =:1.copper,gold =:1.gold,exp =:1.exp,bagMaxCount=:1.bagMaxCount where playerId =:1.playerId")
	public void updatePlayer(Player player);

	@SQL("insert into player (playerId,name,level,viplevel,copper,gold,exp,bagMaxCount) values(:1.playerId,:1.name,:1.level,:1.viplevel,:1.copper,:1.gold,:1.exp,:1.bagMaxCount)")
	public void addPlayer(Player player);

}

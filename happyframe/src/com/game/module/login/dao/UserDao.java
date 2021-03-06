package com.game.module.login.dao;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;

import com.game.module.login.model.User;

/**
 * @author liuzhengyi
 * @date 2014年11月19日 上午11:38:02
 */
@DAO
public interface UserDao {

	public static final String TABLE = " user ";
	public static final String FIELD = " name,pwd,playerId ";

	@SQL("SELECT " + FIELD + " FROM " + TABLE + " where name =:1 and pwd =:pwd")
	public User getPlayerById(String name, @SQLParam("pwd") String pwd);

//	@SQL("SELECT " + FIELD + " FROM " + TABLE + " where name =:name")
	@SQL("SELECT * FROM user where name =:1")
	public User getPlayerByName(String name);

	@SQL("SELECT max(playerId) FROM " + TABLE)
	public Integer getCreatePlayerId();

	@SQL("insert into" + TABLE + " ( " + FIELD
			+ ") values (:1.name,:1.pwd,:1.playerId)")
	public Integer addUser(User user);

}

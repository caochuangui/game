package com.game.module.player.model;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liuzhengyi
 * @date 2014年12月13日 下午3:03:42
 */
public class ConfigPlayerModel {

	Logger log = LoggerFactory.getLogger(ConfigPlayerModel.class);

	private int configAttack; // 初始攻击力
	private int configBlood; // 初始血气
	private int configDefend; // 初始防御
	private int configStrength; // 初始体力
	private int configAttackUp; // 攻击成长
	private int configBloodUp; // 血气成长
	private int configDefendUp; // 防御成长
	private int configPotentialUp; // 潜能成长
	private int configStrengthUp; // 体力成长
	private int configCrit; // 初始暴击
	private int configAbsoluteDefense; // 初始绝对防御
	private Map<Integer, Integer> configCardNum = new TreeMap<Integer, Integer>(); // 初始绝对防御

	/**
	 * @return the configAttack
	 */
	public int getConfigAttack() {
		return configAttack;
	}

	/**
	 * @param configAttack
	 *            the configAttack to set
	 */
	public void setConfigAttack(int configAttack) {
		this.configAttack = configAttack;
	}

	/**
	 * @return the configBlood
	 */
	public int getConfigBlood() {
		return configBlood;
	}

	/**
	 * @param configBlood
	 *            the configBlood to set
	 */
	public void setConfigBlood(int configBlood) {
		this.configBlood = configBlood;
	}

	/**
	 * @return the configDefend
	 */
	public int getConfigDefend() {
		return configDefend;
	}

	/**
	 * @param configDefend
	 *            the configDefend to set
	 */
	public void setConfigDefend(int configDefend) {
		this.configDefend = configDefend;
	}

	/**
	 * @return the configAttackUp
	 */
	public int getConfigAttackUp() {
		return configAttackUp;
	}

	/**
	 * @param configAttackUp
	 *            the configAttackUp to set
	 */
	public void setConfigAttackUp(int configAttackUp) {
		this.configAttackUp = configAttackUp;
	}

	/**
	 * @return the configBloodUp
	 */
	public int getConfigBloodUp() {
		return configBloodUp;
	}

	/**
	 * @param configBloodUp
	 *            the configBloodUp to set
	 */
	public void setConfigBloodUp(int configBloodUp) {
		this.configBloodUp = configBloodUp;
	}

	/**
	 * @return the configDefendUp
	 */
	public int getConfigDefendUp() {
		return configDefendUp;
	}

	/**
	 * @param configDefendUp
	 *            the configDefendUp to set
	 */
	public void setConfigDefendUp(int configDefendUp) {
		this.configDefendUp = configDefendUp;
	}

	/**
	 * @return the configPotentialUp
	 */
	public int getConfigPotentialUp() {
		return configPotentialUp;
	}

	/**
	 * @param configPotentialUp
	 *            the configPotentialUp to set
	 */
	public void setConfigPotentialUp(int configPotentialUp) {
		this.configPotentialUp = configPotentialUp;
	}

	/**
	 * @return the configStrengthUp
	 */
	public int getConfigStrengthUp() {
		return configStrengthUp;
	}

	/**
	 * @param configStrengthUp
	 *            the configStrengthUp to set
	 */
	public void setConfigStrengthUp(int configStrengthUp) {
		this.configStrengthUp = configStrengthUp;
	}

	/**
	 * @return the configCrit
	 */
	public int getConfigCrit() {
		return configCrit;
	}

	/**
	 * @param configCrit
	 *            the configCrit to set
	 */
	public void setConfigCrit(int configCrit) {
		this.configCrit = configCrit;
	}

	/**
	 * @return the configAbsoluteDefense
	 */
	public int getConfigAbsoluteDefense() {
		return configAbsoluteDefense;
	}

	/**
	 * @param configAbsoluteDefense
	 *            the configAbsoluteDefense to set
	 */
	public void setConfigAbsoluteDefense(int configAbsoluteDefense) {
		this.configAbsoluteDefense = configAbsoluteDefense;
	}

	/**
	 * @return the configStrength
	 */
	public int getConfigStrength() {
		return configStrength;
	}

	/**
	 * @param configStrength
	 *            the configStrength to set
	 */
	public void setConfigStrength(int configStrength) {
		this.configStrength = configStrength;
	}

	/**
	 * @author liuzhengyi
	 * @date 2014年12月15日 上午11:24:18
	 * @param level
	 * @return
	 */
	public int getCardNum(int level) {
		for (Entry<Integer, Integer> entry : configCardNum.entrySet()) {
			if (entry.getKey() <= level) {
				return entry.getValue();
			}
		}
		return 0;
	}

	/**
	 * @param configCardNum
	 *            the configCardNum to set
	 */
	public void setConfigCardNum(String cardNumJson) {
		try {} catch (Exception e) {
			log.error("", e);
		}
	}

}

package com.game.commom;

import com.frame.commom.PropertiesUtils;
import com.frame.config.SystemConfig;

/**
 * @author liuzhengyi
 * @date 2014年12月13日 上午11:47:19
 */
public class ConfigGlobal {

	private static ConfigGlobal instance;

	public ConfigGlobal() {
		load();
	}

	public static synchronized ConfigGlobal getInstance() {
		if (instance == null) {
			instance = new ConfigGlobal();
		}
		return instance;
	}

	private SystemConfig systemConfig;

	/**
	 * @author liuzhengyi
	 * @date 2014年12月13日 上午11:48:21
	 */
	private void load() {
		systemConfig = PropertiesUtils.getObjectByFile(SystemConfig.class);
	}

	/**
	 * @return the systemConfig
	 */
	public SystemConfig getSystemConfig() {
		return systemConfig;
	}

	/**
	 * @param systemConfig
	 *            the systemConfig to set
	 */
	public void setSystemConfig(SystemConfig systemConfig) {
		this.systemConfig = systemConfig;
	}

}

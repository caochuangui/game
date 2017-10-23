package com.game.commom.configDao;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;

import com.game.module.farm.model.CropInfo;
import com.game.module.farm.model.FarmInfo;
import com.game.module.farm.model.FarmInit;

@DAO
public interface DaoInfo {
	@SQL("SELECT * FROM crop")
	public List<CropInfo> getCropInfoList();

	@SQL("SELECT * FROM init")
	public List<FarmInit> getFarmInitList();

	@SQL("SELECT * FROM init")
	public List<FarmInfo> getFarmInfoList();

}

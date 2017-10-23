package com.game.module.farm.event;

import com.frame.OnlinePlayerManager;
import com.frame.base.GameSession;
import com.frame.extention.AbstractEventJob;
import com.frame.packet.anotation.Extention;
import com.game.module.MsgId;
import com.game.module.PacketId;
import com.game.module.farm.model.Farm;
import com.game.module.farm.service.FarmService;
import com.game.module.player.model.Player;
import com.game.module.player.service.PlayerService;

/**
 * @author liuzhengyi
 * @date 2014年11月17日 下午9:01:25
 */
@Extention(extentionId = PacketId.PLANT, msgId = MsgId.PLANT)
public class PlantSeedJob extends AbstractEventJob {

	/**
	 * 
	 * @see frame.extention.AbstractEventJob#excete()
	 */
	@Override
	public Object excete() {
//		REQ:	plant [land_type] [land_id] [seed_id]
//		RESP:	plant [land_type] [land_id] [seed_id] [start_time] [money]
		String msg = getMess();
		String[] msgArray = msg.split(" ");
		if (msgArray.length < 4) {
			return MsgId.PLANT + " -3 " + "输入参数错误";
		}
		int landType = 0;
		int landId = 0;
		int plantId = 0;
		try {
			landType	= Integer.parseInt(msgArray[1]);
			landId		= Integer.parseInt(msgArray[2]);
			plantId		= Integer.parseInt(msgArray[3]);
		} catch (Exception ex) {
			return MsgId.PLANT + " -13 " + "输入参数错误";
		}
		if (landType <= 0 || landId <= 0 || plantId <= 0) {
			return MsgId.PLANT + " -23 " + "输入参数错误";
		}
		
		Player player = PlayerService.getInstance().getPlayerById(getPlayerId());
		if (player == null) {
			return MsgId.PLANT + " -6 " + "未登录";
		}
		int pId = player.getPlayerId();

		FarmService farmService = FarmService.getInstance();
		Farm farm = farmService.getFarm(landId, landType, pId);
		boolean hasFarm = farm != null;
		if (hasFarm && farm.getPlantId() != 0) {
			return MsgId.PLANT + " -16 " + "该位置已经有作物";
		}
		// TODO get spend copper by plantId
		int cost = 300;
		int copper = player.getCopper();
		if (copper < cost) {
			return MsgId.PLANT + " -26 " + "金钱不足";
		}
		
		
		if (hasFarm == false) {
			farm = new Farm();
			farm.setFarmId(landId);
			farm.setType(landType);
			farm.setPlayerId(pId);
		}
		farm.setPlantId(plantId);
		farm.setStartTime(System.currentTimeMillis() / 1000);
		
		if (hasFarm) {
            farmService.updateFarm(farm);
		} else {
			farmService.addFarm(farm);
		}
		player.setCopper(copper - cost);
		PlayerService.getInstance().updatePlayer(player);

		return MsgId.PLANT + " " + landType + " "
				+ landId + " " + plantId + " "
				+ farm.getStartTime() + " " + player.getCopper();
	}

}

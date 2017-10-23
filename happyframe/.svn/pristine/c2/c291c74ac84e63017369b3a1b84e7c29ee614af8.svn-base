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
@Extention(extentionId = PacketId.CLEAN, msgId = MsgId.CLEAN)
public class CleanPlantJob extends AbstractEventJob {

	/**
	 * 
	 * @see frame.extention.AbstractEventJob#excete()
	 */
	@Override
	public Object excete() {

        // REQ:	clean [land_type] [land_id]
        // RESP:clean [land_type] [land_id]
		String msg = getMess();
		String[] msgArray = msg.split(" ");
		if (msgArray.length < 3) {
			return MsgId.CLEAN + " -3 " + "输入参数错误";
		}
		int landType = 0;
		int landId = 0;
		try {
			landType	= Integer.parseInt(msgArray[1]);
			landId		= Integer.parseInt(msgArray[2]);
		} catch (Exception ex) {
			return MsgId.CLEAN + " -13 " + "输入参数错误";
		}
		if (landType <= 0 || landId <= 0) {
			return MsgId.CLEAN + " -23 " + "输入参数错误";
		}
		
		Player player = PlayerService.getInstance().getPlayerById(getPlayerId());
		if (player == null) {
			return MsgId.CLEAN + " -6 " + "未登录";
		}
		int pId = player.getPlayerId();

		FarmService farmService = FarmService.getInstance();
		Farm farm = farmService.getFarm(landId, landType, pId);
		if (farm == null || farm.getPlantId() == 0) {
			return MsgId.CLEAN + " -16 " + "该位置没有作物";
		}

//		farm.setPlantId(0);
//		farm.setStartTime(0);
//		
//        farmService.updateFarm(farm);
		farmService.deleteFarm(farm);

		return MsgId.CLEAN + " " + landType + " " + landId;
	}

}

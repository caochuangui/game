package com.game.module.bag.event;

import com.frame.OnlinePlayerManager;
import com.frame.base.GameSession;
import com.frame.extention.AbstractEventJob;
import com.frame.packet.anotation.Extention;
import com.game.module.MsgId;
import com.game.module.PacketId;
import com.game.module.bag.model.ConfigUpdateBagModel;
import com.game.module.bag.service.BagService;
import com.game.module.player.model.Player;
import com.game.module.player.service.PlayerService;

/**
 * @author liuzhengyi
 * @date 2014年12月15日 上午11:51:03
 */
@Extention(extentionId = PacketId.UPDATE_BAG, msgId = MsgId.UPDATE_BAG)
public class UpdateBagJob extends AbstractEventJob {

	/**
	 * 
	 * @see com.gzwabao.frame.extention.AbstractEventJob#excete()
	 */
	@Override
	public Object excete() {
//		REQ:	whup
//		RESP:	whup [max_count] [gold] [money]	
		
		GameSession gs = OnlinePlayerManager.getIntance().getGameSsionByChannel(getChannel());
		
		Player player = gs.getPlayer();
		if (player == null) {
			return MsgId.UPDATE_BAG + " -6 " + "未登录";
		}
		int pId = player.getPlayerId();

		ConfigUpdateBagModel upModel = BagService.getInstance().getUpdateBagModel();
		
		int bagMaxCount = player.getBagMaxCount();
		if (bagMaxCount >= upModel.getMaxCount()) {
			return MsgId.UPDATE_BAG + " -16 " + "仓库已达最大容量";
		}
		int gold = player.getGold();
		if (gold < upModel.getUpGold()) {
			return MsgId.UPDATE_BAG + " -26 " + "金钱不足";
		}
		
		player.setBagMaxCount(bagMaxCount + upModel.getUpCount());
		player.setGold(gold - upModel.getUpGold());
		PlayerService.getInstance().updatePlayer(player);
		
		return MsgId.UPDATE_BAG + " " + pId + " "+ player.getBagMaxCount()
				+ player.getGold() + " " + player.getCopper();
	}

}

package com.game.module.test.event;

import com.frame.OnlinePlayerManager;
import com.frame.base.GameSession;
import com.frame.extention.AbstractEventJob;
import com.frame.packet.anotation.Extention;
import com.game.module.MsgId;
import com.game.module.PacketId;
import com.game.module.player.model.Player;
import com.game.module.player.service.PlayerService;

/**
 * @author liuzhengyi
 * @date 2014年12月15日 上午11:51:03
 */
@Extention(extentionId = PacketId.ADD_GOLD, msgId = MsgId.ADD_GOLD)
public class AddGoldJob extends AbstractEventJob {

	/**
	 * 
	 * @see com.gzwabao.frame.extention.AbstractEventJob#excete()
	 */
	@Override
	public Object excete() {
		String msg = getMess();
		String[] msgArray = msg.split(" ");
		
		int gold = 1000;
		if (msgArray.length >= 2) {
			try {
                gold = Integer.parseInt(msgArray[1]);
			} catch (Exception ex) {}
		}
		
		GameSession gs = OnlinePlayerManager.getIntance().getGameSsionByChannel(getChannel());
		Player player = gs.getPlayer();
		if (player == null) {
			return MsgId.ADD_GOLD + " -6 " + "未登录";
		}
		
		int cur_gold = player.getGold() + gold;
		player.setGold(cur_gold);
		PlayerService.getInstance().updatePlayer(player);
		
		return MsgId.ADD_GOLD + " " + cur_gold;
	}

}

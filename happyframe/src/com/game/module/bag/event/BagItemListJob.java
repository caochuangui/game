package com.game.module.bag.event;

import java.util.List;

import com.frame.OnlinePlayerManager;
import com.frame.base.GameSession;
import com.frame.extention.AbstractEventJob;
import com.frame.packet.anotation.Extention;
import com.game.module.MsgId;
import com.game.module.PacketId;
import com.game.module.bag.model.BagItem;
import com.game.module.bag.service.BagService;
import com.game.module.player.model.Player;
import com.game.module.player.service.PlayerService;

/**
 * @author liuzhengyi
 * @date 2014年12月15日 上午11:51:03
 */
@Extention(extentionId = PacketId.BAG_LIST, msgId = MsgId.BAG_LIST)
public class BagItemListJob extends AbstractEventJob {

	/**
	 * 
	 * @see com.gzwabao.frame.extention.AbstractEventJob#excete()
	 */
	@Override
	public Object excete() {

//		REQ:	whlist
//		RESP:	whlist [max_count] [item_count] [item_data] ...
//		item_data: uid|count
		
		GameSession gs = OnlinePlayerManager.getIntance().getGameSsionByChannel(getChannel());
		
		Player player = gs.getPlayer();
		if (player == null) {
			return MsgId.UPDATE_BAG + " -6 " + "未登录";
		}
		int pId = player.getPlayerId();
		
		// TODO
		List<BagItem> bagList = BagService.getInstance().getBagItemList(pId);
		
		StringBuffer sb = new StringBuffer(MsgId.BAG_LIST);
		sb.append(" " + player.getBagMaxCount());
		sb.append(" " + bagList.size());
		for (BagItem item : bagList) {
			sb.append(" " + item.toMsg());
		}
		
		return sb.toString();
	}

}

package com.game.module.player.event;

import org.springframework.beans.factory.annotation.Autowired;

import com.frame.OnlinePlayerManager;
import com.frame.base.GameSession;
import com.frame.extention.AbstractEventJob;
import com.frame.packet.RespPacket;
import com.frame.packet.anotation.Extention;
import com.game.module.MsgId;
import com.game.module.PacketId;
import com.game.module.player.dao.PlayerDao;
import com.game.module.player.model.Player;
import com.game.module.player.service.PlayerService;

/**
 * @author liuzhengyi
 * @date 2014年12月15日 上午11:51:03
 */
@Extention(extentionId = PacketId.CREATE_PLAYER, msgId = MsgId.CREATE_PLAYER)
public class CreatePlayerJob extends AbstractEventJob {

	/**
	 * 
	 * @see com.gzwabao.frame.extention.AbstractEventJob#excete()
	 */
	@Override
	public Object excete() {
		String msg = getMess();
		String[] msgArray = msg.split(" ");
		if (msgArray.length < 2) {
			return MsgId.CREATE_PLAYER + " -3 " + "输入参数错误";
		}
		
		int pId = getPlayerId();
		
		String name = msgArray[1];
		System.out.println("create_player job : " + pId + " " + name);
		Player player = PlayerService.getInstance().getPlayerById(pId);
		if (player == null) {
			return MsgId.CREATE_PLAYER + " -6 " + "未登录";
		}
		
		String pName = player.getName();
		if (pName != null && !"".equals(pName)) {
			return MsgId.CREATE_PLAYER + " -16 " + "昵称已经设置";
		}
		
		player.setName(name);
		PlayerService.getInstance().updatePlayer(player);
		
		return MsgId.CREATE_PLAYER + " " + pId + " " + name;
	}

}

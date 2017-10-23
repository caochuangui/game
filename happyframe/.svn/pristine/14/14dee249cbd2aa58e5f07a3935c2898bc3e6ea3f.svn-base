package com.game.module.login.event;

import org.springframework.beans.factory.annotation.Autowired;

import com.frame.extention.AbstractEventJob;
import com.frame.packet.anotation.Extention;
import com.game.module.MsgId;
import com.game.module.login.dao.UserDao;
import com.game.module.login.model.User;
import com.game.module.login.service.LoginService;

/**
 * @author liuzhengyi
 * @date 2014年12月15日 上午11:51:03
 */
@Extention(extentionId = 1002, msgId = MsgId.REGISTER)
public class RegisterEventJob extends AbstractEventJob {

	@Autowired
	UserDao userDao;
	/**
	 * 
	 * @see com.gzwabao.frame.extention.AbstractEventJob#excete()
	 */
	@Override
	public Object excete() {
//		String msg = getMess();
//
//		String[] msgArray = msg.split(" ");
//		if (msgArray.length < 3) {
//			return (MsgId.REGISTER + " -3 " + "输入参数不足");
//		}
//		
//		String name = msgArray[1];
//		String pwd	= msgArray[2];
//		
//		// TODO 手机号码判定
//		if (name.length() < 3 || name.length() > 15) {
//			return (MsgId.REGISTER + " -5 " + "用户名输入错误");
//		}
//		if (pwd.length() < 6 || pwd.length() > 12) {
//			return (MsgId.REGISTER + " -15 " + "密码长度输入错误");
//		}
//		// TODO 密码为数字或字母的判定
//		
//		User user = userDao.getPlayerByName(name);
//		if (user != null) {
//			return (MsgId.REGISTER + " -6 " + "用户名已经存在");
//		}
//
//		user = LoginService.getIntance().createUser(name, pwd);
//		int effectRow = userDao.addUser(user);
//		if (effectRow <= 0 || effectRow > 1) {
//			return (MsgId.REGISTER + " -7 " + "数据库异常");
//		}
//
//		StringBuffer sb = new StringBuffer(MsgId.REGISTER);
//        LoginService.getIntance().doLoginEffect(user, getChannel(), sb);
//		return sb;
		return null;
	}

}

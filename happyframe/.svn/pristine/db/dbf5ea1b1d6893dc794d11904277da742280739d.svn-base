package com.game;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.frame.async.Async2DBService;
import com.frame.bean.BeanManager;
import com.frame.cache.JedisUtil;
import com.frame.extention.EventJobManager;
import com.frame.net.NetServer;
import com.game.commom.ConfigGlobal;
import com.game.module.farm.service.FarmService;
import com.game.module.login.service.LoginService;

public class GameServer {
	private static final Logger logger = LoggerFactory
			.getLogger(GameServer.class);

	public static void main(String[] args) throws Exception {
		PropertyConfigurator.configure("resource/log4j.properties");
		ApplicationContext cxt = new FileSystemXmlApplicationContext("resource/application.xml");
		NetServer netServer = new NetServer();
		netServer.start(ConfigGlobal.getInstance().getSystemConfig()
				.getServerPort());
		logger.info("serverPort :"
				+ ConfigGlobal.getInstance().getSystemConfig().getServerPort());
		logger.info("start gameserver");

		// 启动登陆线程
		new Thread(LoginService.getIntance()).start();

		// 启动刷新农场线程
//		new Thread(FarmService.getIntance()).start();

		// 启动事务逻辑管理线程
		new EventJobManager(100).start();
		// 启动异步更新线程
		Async2DBService.getInstance();
		// 启动bean管理器
		BeanManager.getInstance().init(cxt);

		cmd();
	}

	public static void cmd() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (;;) {
			try {
				System.out.println("输入命令:<stop/help(了解更多指令)>");
				String cmd = br.readLine();
				if ("stop".equals(cmd)) {

					LoginService.getIntance().shutdown();

//					FarmService.getIntance().shutdown();

					EventJobManager.getInstance().shutdown();

					Async2DBService.getInstance().stop();

					JedisUtil.shutdown();

					System.exit(0);
				} else if ("reload".equals(cmd)) {
					BeanManager.getInstance().initLoad();
				} else if ("help".equals(cmd)) {
					System.out.println("stop-关闭服务机器");
					System.out.println("reload-重新加载策划数据");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

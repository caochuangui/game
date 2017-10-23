package com.cmpp;

/**
 * This thread is just for testing
 * @author Jake
 *
 */
public class CmppThread extends Thread {
    Cmpp cl=null;
    public CmppThread(Cmpp cl) {
        this.cl = cl;
    }

    /**
                端口号：106575601601
                登录网关的密码：VqU1ik；登录网关的帐号：J00455；
                网关地址：211.139.144.201；
                网关端口：7890  ；
                短信协议版本：CMPP2.0；
                企业代码：450007；业务代码：MGD0019900
                公网IP地址：221.179.101.137
                内网地址：192.168.20.89 
    */

    public void run() {
    /**
     * 5秒后向手机用户13760671291发送"Hello World!"，免费，业务代码ZXYT
     */
            try {
                Thread.sleep(5000);
                System.out.println("sendSubmitMsg to: 13760671291");
                int result=cl.sendSubmitMsg(0,1,1,0,0,"","MGD0019900", "011234", "01", "150",
                                 new String[] {"13760671291"}
                                 , "Hello World!",null);
                System.out.println("MyThread result: "+result);
            }
            catch (InterruptedException ex) {
            }
    }
    
}

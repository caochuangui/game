package com.cmpp;

import com.biglantern.cmpp.*;

public class Cmpp
    extends CmppSms {
    Cmpp cl = null;

    /**
     */
    public static void main(String args[]) {
        Cmpp cl = new Cmpp();
        cl.startServer(args);
    }

    /**
     * @param msgtype 信息类型
     * @param msgbody 信息体
     * @return 成功 序列号，失败 <0
     */
    public int sendMessage(int msgtype, byte[] msgbody){
        return super.sendMessage(msgtype, msgbody);
    }

    /**
     * @param msgid 信息标识
     * @param pktotal 相同Msg_Id的信息总条数，从1开始
     * @param pknumber 相同Msg_Id的信息序号，从1开始
     * @param registered 是否需要状态报告 0 不需要， 1 需要， 2 SMC话单
     * @param usertype 计费用户类型 0 目的， 1 源， 2 SP， 3 terminal
     * @param terminal 被计费用户号码
     * @param serviceid 业务代码
     * @param spcode  SP服务号或前缀为SP服务号的长号码
     * @param feetype 资费类别 01 免费，02 按条，03 包月，04 封顶，05 SP付费
     * @param feecode 资费，以分为单位
     * @param dest 接收方号码列表
     * @param msg 信息内容
     * @param linkid 点播业务使用的LinkID(V3.0) ， 或保留字段(V2.0)
     * @return 成功 序列号，失败 <0
     */
    public int sendSubmitMsg(long msgid, int pktotal,
                             int pknumber, int registered, int usertype,
                             String fee_termial,
                             String serviceid, String spcode,
                             String feeType, String feeCode,
                             String dest_terminal_Id[],
                             String msg, String linkid) {
        return super.sendSubmitMsg(msgid, pktotal,
                                    pknumber, registered, usertype, fee_termial,
                                    serviceid, spcode, feeType,
                                    feeCode, dest_terminal_Id, msg, linkid);
    }



    /**
     * @param time  时间YYYYMMDD（精确至日，8字节）
     * @param querytype 查询类别 0 总数查询 ， 1 按业务代码查询
     * @param serviceid 业务代码
     * @return 成功 序列号，失败 -1
     */
    public int sendQueryMsg(String time, int querytype, String serviceid) {
        return super.sendQueryMsg(time, querytype, serviceid);
    }

    /**
     * @param name  属性名称
     * @return 属性值
     */
    public static String getValue(String name) {
        return CmppSms.getValue(name);
    }

    /**
     * 系统连接成功后进行业务初始化
     */
    public void init() {
        System.out.println("init........");
        new Thread(new CmppThread(this)).start();
    }

    /**
     * 处理DELIVER数据包
     * @param seqid 序列号
     * @param msgbody 消息体
     * 填充以下部分以完成收到手机用户短信后的业务处理
     * 这是二次开发的主要部分
     */
    public void doDeliver(int seqid, byte[] msgbody) {
        System.out.println("doDeliver........" + seqid);
    }

    /**
     * 处理查询返回数据
     * @param seqid 序列号
     * @param msgbody 消息体
     */
    public void doQueryResp(int seqid, byte[] msgbody) {
        System.out.println("doQueryResp........" + seqid);
    }
}
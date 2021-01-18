package ai.pensees.sdkdemo.model;

public class RfidRecord {
    private int id;
    //设备id
    private String deviceId;
    //卡id
    private String carNo;
    //刷卡时间
    private long time;
    //刷卡状态(1-成功，2-失败)
    private int state;
}

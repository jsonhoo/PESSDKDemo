package ai.pensees.sdkdemo.model;

public class PasswordDoor {
    //开门记录
    private int id;
    //设备id
    private String deviceId;
    // 输入密码
    private String password;
    //开门时间
    private long time;
    //开门结果(1-成功，2-失败)
    private int state;
}

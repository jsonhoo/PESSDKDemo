package ai.pensees.sdkdemo.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

@Entity
public class PasswordDoor implements Serializable {

    private static final long serialVersionUID = 1L;
    //开门记录
    private long id;
    //设备id
    private String deviceId;
    // 输入密码
    private String password;
    //开门时间
    private long time;
    //开门结果(1-成功，2-失败)
    private int state;
    @Generated(hash = 1747126252)
    public PasswordDoor(long id, String deviceId, String password, long time,
            int state) {
        this.id = id;
        this.deviceId = deviceId;
        this.password = password;
        this.time = time;
        this.state = state;
    }
    @Generated(hash = 314619099)
    public PasswordDoor() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getDeviceId() {
        return this.deviceId;
    }
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public long getTime() {
        return this.time;
    }
    public void setTime(long time) {
        this.time = time;
    }
    public int getState() {
        return this.state;
    }
    public void setState(int state) {
        this.state = state;
    }
}

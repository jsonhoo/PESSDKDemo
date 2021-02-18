package ai.pensees.sdkdemo.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

@Entity
public class FaceRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private String deviceId;
    private String userNo;
    private String time;
    private int state;

    @Generated(hash = 1613253513)
    public FaceRecord(long id, String deviceId, String userNo, String time,
            int state) {
        this.id = id;
        this.deviceId = deviceId;
        this.userNo = userNo;
        this.time = time;
        this.state = state;
    }
    @Generated(hash = 1052449182)
    public FaceRecord() {
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
    public String getUserNo() {
        return this.userNo;
    }
    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public int getState() {
        return this.state;
    }
    public void setState(int state) {
        this.state = state;
    }

}

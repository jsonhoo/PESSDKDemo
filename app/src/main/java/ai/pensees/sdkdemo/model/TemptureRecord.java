package ai.pensees.sdkdemo.model;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

@Entity
public class TemptureRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private String deviceId;
    private String userNo;
    private float temp;
    private long time;

    @Generated(hash = 110755134)
    public TemptureRecord(long id, String deviceId, String userNo, float temp,
            long time) {
        this.id = id;
        this.deviceId = deviceId;
        this.userNo = userNo;
        this.temp = temp;
        this.time = time;
    }
    @Generated(hash = 1578403499)
    public TemptureRecord() {
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
    public float getTemp() {
        return this.temp;
    }
    public void setTemp(float temp) {
        this.temp = temp;
    }
    public long getTime() {
        return this.time;
    }
    public void setTime(long time) {
        this.time = time;
    }
}

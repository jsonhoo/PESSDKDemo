package ai.pensees.sdkdemo.model;

import org.greenrobot.greendao.annotation.Entity;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class RfidRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private String deviceId;
    private String carNo;
    private String time;
    private int state;
    @Generated(hash = 593348903)
    public RfidRecord(long id, String deviceId, String carNo, String time,
            int state) {
        this.id = id;
        this.deviceId = deviceId;
        this.carNo = carNo;
        this.time = time;
        this.state = state;
    }
    @Generated(hash = 193614447)
    public RfidRecord() {
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
    public String getCarNo() {
        return this.carNo;
    }
    public void setCarNo(String carNo) {
        this.carNo = carNo;
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

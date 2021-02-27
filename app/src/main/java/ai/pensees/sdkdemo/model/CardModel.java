package ai.pensees.sdkdemo.model;

import org.greenrobot.greendao.annotation.Entity;

import java.io.Serializable;

import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Unique;

@Entity
public class CardModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Unique
    private String cardNo;
    private String userId;
    private String deviceId;
    private long createTime;
    private long updateTime;

    @Generated(hash = 798932820)
    public CardModel(String cardNo, String userId, String deviceId, long createTime,
                     long updateTime) {
        this.cardNo = cardNo;
        this.userId = userId;
        this.deviceId = deviceId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    @Generated(hash = 978655671)
    public CardModel() {
    }

    public String getCardNo() {
        return this.cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

}

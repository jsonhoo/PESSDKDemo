package ai.pensees.sdkdemo.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

//用户信息
@Entity
public class UserModel {
    @Id(autoincrement = true)
    private long id;
    @Unique
    private String userNo;
    @NotNull
    private String userName;
    @NotNull
    private byte[] feature;
    @NotNull
    private String faceUrl;

    private long createTime;

    private long updateTime;
    //用户卡
    private String carNo;

    @Generated(hash = 470213102)
    public UserModel(long id, String userNo, @NotNull String userName,
            @NotNull byte[] feature, @NotNull String faceUrl, long createTime,
            long updateTime, String carNo) {
        this.id = id;
        this.userNo = userNo;
        this.userName = userName;
        this.feature = feature;
        this.faceUrl = faceUrl;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.carNo = carNo;
    }

    @Generated(hash = 782181818)
    public UserModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public byte[] getFeature() {
        return feature;
    }

    public void setFeature(byte[] feature) {
        this.feature = feature;
    }

    public String getFaceUrl() {
        return faceUrl;
    }

    public void setFaceUrl(String faceUrl) {
        this.faceUrl = faceUrl;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }
}

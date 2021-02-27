package ai.pensees.sdkdemo.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;

import java.io.Serializable;


//用户信息
@Entity
public class UserModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id(autoincrement = true)
    private long id;
    @Unique
    private String userNo;
    private String userName;
    @Unique
    private String carNo;
    private String userAddress;
    private String userPhone;

    @NotNull
    private String featureId;
    @NotNull
    private byte[] feature;
    private String photoServerUrl;
    private String photoLocalUri;

    private long createTime;
    private long updateTime;
    private boolean isAdmin;


    @Generated(hash = 304963554)
    public UserModel(long id, String userNo, String userName, String carNo,
            String userAddress, String userPhone, @NotNull String featureId,
            @NotNull byte[] feature, String photoServerUrl, String photoLocalUri,
            long createTime, long updateTime, boolean isAdmin) {
        this.id = id;
        this.userNo = userNo;
        this.userName = userName;
        this.carNo = carNo;
        this.userAddress = userAddress;
        this.userPhone = userPhone;
        this.featureId = featureId;
        this.feature = feature;
        this.photoServerUrl = photoServerUrl;
        this.photoLocalUri = photoLocalUri;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.isAdmin = isAdmin;
    }

    @Generated(hash = 782181818)
    public UserModel() {
    }


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserNo() {
        return this.userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCarNo() {
        return this.carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getUserAddress() {
        return this.userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserPhone() {
        return this.userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getFeatureId() {
        return this.featureId;
    }

    public void setFeatureId(String featureId) {
        this.featureId = featureId;
    }

    public String getPhotoServerUrl() {
        return this.photoServerUrl;
    }

    public void setPhotoServerUrl(String photoServerUrl) {
        this.photoServerUrl = photoServerUrl;
    }

    public boolean getIsAdmin() {
        return this.isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public byte[] getFeature() {
        return feature;
    }

    public void setFeature(byte[] feature) {
        this.feature = feature;
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

    public String getPhotoLocalUri() {
        return this.photoLocalUri;
    }

    public void setPhotoLocalUri(String photoLocalUri) {
        this.photoLocalUri = photoLocalUri;
    }
}

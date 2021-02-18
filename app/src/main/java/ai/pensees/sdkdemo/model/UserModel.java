package ai.pensees.sdkdemo.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
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

    private String featureId;
    private String faceUrl;

    private String createTime;
    private String updateTime;
    private boolean isAdmin;

    @Generated(hash = 1525396677)
    public UserModel(long id, String userNo, String userName, String carNo,
            String userAddress, String userPhone, String featureId, String faceUrl,
            String createTime, String updateTime, boolean isAdmin) {
        this.id = id;
        this.userNo = userNo;
        this.userName = userName;
        this.carNo = carNo;
        this.userAddress = userAddress;
        this.userPhone = userPhone;
        this.featureId = featureId;
        this.faceUrl = faceUrl;
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
    public String getFaceUrl() {
        return this.faceUrl;
    }
    public void setFaceUrl(String faceUrl) {
        this.faceUrl = faceUrl;
    }
    public String getCreateTime() {
        return this.createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public String getUpdateTime() {
        return this.updateTime;
    }
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
    public boolean getIsAdmin() {
        return this.isAdmin;
    }
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }


}

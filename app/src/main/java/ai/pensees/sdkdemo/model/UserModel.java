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
    @Unique
    private String userId;
    @NotNull
    private String featureId;
    @NotNull
    private byte[] feature;
    private String serverPhotoUrl;
    private String localPhotoUri;

    private long createTime;
    private long updateTime;
    private boolean isAdmin;
    @Generated(hash = 1156292099)
    public UserModel(String userId, @NotNull String featureId,
            @NotNull byte[] feature, String serverPhotoUrl, String localPhotoUri,
            long createTime, long updateTime, boolean isAdmin) {
        this.userId = userId;
        this.featureId = featureId;
        this.feature = feature;
        this.serverPhotoUrl = serverPhotoUrl;
        this.localPhotoUri = localPhotoUri;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.isAdmin = isAdmin;
    }
    @Generated(hash = 782181818)
    public UserModel() {
    }
    public String getUserId() {
        return this.userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getFeatureId() {
        return this.featureId;
    }
    public void setFeatureId(String featureId) {
        this.featureId = featureId;
    }
    public byte[] getFeature() {
        return this.feature;
    }
    public void setFeature(byte[] feature) {
        this.feature = feature;
    }
    public String getServerPhotoUrl() {
        return this.serverPhotoUrl;
    }
    public void setServerPhotoUrl(String serverPhotoUrl) {
        this.serverPhotoUrl = serverPhotoUrl;
    }
    public String getLocalPhotoUri() {
        return this.localPhotoUri;
    }
    public void setLocalPhotoUri(String localPhotoUri) {
        this.localPhotoUri = localPhotoUri;
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
    public boolean getIsAdmin() {
        return this.isAdmin;
    }
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}

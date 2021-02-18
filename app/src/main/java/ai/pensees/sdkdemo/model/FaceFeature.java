package ai.pensees.sdkdemo.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

import java.io.Serializable;


@Entity
public class FaceFeature implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id(autoincrement = true)
    private long id;
    @Unique
    private String featureId;
    private byte[] feature;

    @Generated(hash = 542862509)
    public FaceFeature(long id, String featureId, byte[] feature) {
        this.id = id;
        this.featureId = featureId;
        this.feature = feature;
    }
    @Generated(hash = 1671393673)
    public FaceFeature() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
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
}

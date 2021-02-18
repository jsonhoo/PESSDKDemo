package ai.pensees.sdkdemo.model;

import org.greenrobot.greendao.annotation.Entity;

import java.io.Serializable;

@Entity
public class RfidRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private String deviceId;
    private String carNo;
    private String time;
    private int state;


}

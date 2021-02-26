package ai.pensees.sdkdemo.msg;

import java.io.Serializable;

/**
 * @author liujiansheng
 * @since 2021/2/26
 */
public class MessageWrap implements Serializable {
    private int code;
    private final String message;

    public MessageWrap(int code,String message) {
        this.message = message;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }
}

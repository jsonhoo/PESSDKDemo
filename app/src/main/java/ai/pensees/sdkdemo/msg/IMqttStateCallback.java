package ai.pensees.sdkdemo.msg;

/**
 * @author liujiansheng
 * @since 2021/2/26
 */
interface IMqttStateCallback {
    /**
     * mqtt异常
     * @param code 异常code
     * @param msg  异常msg
     */
    void onError(int code,String msg);
}

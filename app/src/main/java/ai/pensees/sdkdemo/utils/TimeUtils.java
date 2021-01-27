package ai.pensees.sdkdemo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

    public static String conversionTime(String timeStamp) {
        //yyyy-MM-dd HH:mm:ss 转换的时间格式  可以自定义
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //转换
        String time = sdf.format(new Date(Long.parseLong(timeStamp)));
        return time;
    }
}

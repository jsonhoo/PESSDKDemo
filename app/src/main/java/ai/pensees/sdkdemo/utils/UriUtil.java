package ai.pensees.sdkdemo.utils;

import android.net.Uri;

/**
 * @author liujiansheng
 * @since 2021/2/27
 */
public class UriUtil {
    public static Uri toUri(String filePath) {
        return Uri.parse("file://" + filePath);
    }
}

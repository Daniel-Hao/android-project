package com.daniel.hao.finals;

import android.os.Environment;
import android.text.TextUtils;

import com.daniel.hao.app.MyApp;
import com.daniel.hao.corelib.log.AppInfo;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hdl on 2016/9/23.
 * <p>
 * 配置本地目录信息
 */
public class FinalsCachePath {


    private static String filePathBasic = null;

    public static String getFilePathBasic() {
        if (FianlsDebug.FileContants_release) {
            if (TextUtils.isEmpty(filePathBasic)) {
                filePathBasic = "/release";
            }
        } else {
            if (TextUtils.isEmpty(filePathBasic)) {
                filePathBasic = "/debug";
            }
        }
        return filePathBasic;
    }

    /**
     * 获取sd卡的路径
     */
    public static String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();// 获取外存目录
        }
        if (sdDir == null) {
            return "";
        }
        return sdDir.getPath();
    }

    /**
     * 缓存地址目录
     */
    public final static String TEMP = getSDPath() + "/"
            + AppInfo.getPackageName(MyApp.getInstance())
            + getFilePathBasic();

    /**
     * 日志文件地址
     */
    public final static String LOG_PATH = TEMP + "/log/";
    /**
     * 日志文件地址
     */
    public final static String LOG = LOG_PATH + "log " + getCurrentTime() + ".txt";

    /**
     * 下载文件保存目录
     */
    public final static String DOWNLOAD = TEMP + "/download";

    /**
     * 下载新版本
     */
    public final static String DOWNLOAD_NEW_APK = DOWNLOAD + "/popon_update.apk";

    /**
     * 网络请求缓存目录
     */
    public final static String HTTPCACHE = TEMP + "/httpCache";


    /**
     * 图片文件地址
     */
    public static final String FilePathPic = TEMP + "/img";
    /**
     * 音频文件地址
     */
    public static final String FilePathAudio = TEMP + "/audio";

    /**
     * 视频文件地址
     */
    public static final String FilePathVideo = TEMP + "/video";


    /**
     * 登录用户自己的文件存储地址
     **/
    public static String getLoginUserFilePath(String loingUserId) {
        return TEMP + "login/" + loingUserId;
    }


    public static String getCurrentTime() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd-hh");
        return sDateFormat.format(new Date(System.currentTimeMillis()));
    }
}

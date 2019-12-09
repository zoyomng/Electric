package com.dtelec.core.common.constants;


import java.io.File;

import com.dtelec.MyApplication;

public class Constants {
    public static final String PATH_CACHE = MyApplication.getAppContext().getCacheDir().getAbsolutePath() + File.separator + "cache";
    public static final File FILE_DOWNLOAD = MyApplication.getAppContext().getExternalFilesDir("download");

    //加载状态值
    public static final int STAUTS_LOADING = 0;
    public static final int STATUS_SUCCESS = 1;
    public static final int STATUS_NETWORK_ERROR = 2;
    public static final int STATUS_SERVER_ERROR = 3;

}

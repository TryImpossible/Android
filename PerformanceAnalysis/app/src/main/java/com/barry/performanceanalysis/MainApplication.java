package com.barry.performanceanalysis;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.github.moduth.blockcanary.BlockCanary;
import com.github.moduth.blockcanary.BlockCanaryContext;
import com.github.moduth.blockcanary.internal.BlockInfo;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class MainApplication extends Application {

    private final class CrashHandler implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            Log.e("Crash", e.getMessage());
        }
    }

    private final class AppBlockCanaryContext extends BlockCanaryContext {
        private static final String TAG = "AppContext";

        /**
         * Implement in your project.
         *
         * @return Qualifier which can specify this installation, like version + flavor.
         */
        @Override
        public String provideQualifier() {
            String qualifier = "";
            try {
                PackageInfo info = MainApplication.getAppContext().getPackageManager()
                        .getPackageInfo(MainApplication.getAppContext().getPackageName(), 0);
                qualifier += info.versionCode + "_" + info.versionName + "_YYB";
            } catch (PackageManager.NameNotFoundException e) {
                Log.e(TAG, "provideQualifier exception", e);
            }
            return qualifier;
        }

        /**
         * Implement in your project.
         *
         * @return user id
         */
        public String provideUid() {
            return "87224330";
        }

        /**
         * Network type
         *
         * @return {@link String} like 2G, 3G, 4G, wifi, etc.
         */
        @Override
        public String provideNetworkType() {
            return "wifi";
        }

        /**
         * Config monitor duration, after this time BlockCanary will stop, use
         * with {@code BlockCanary}'s isMonitorDurationEnd
         *
         * @return monitor last duration (in hour)
         */
        public int provideMonitorDuration() {
            return 9999;
        }

        /**
         * Config block threshold (in millis), dispatch over this duration is regarded as a BLOCK. You may set it
         * from performance of device.
         *
         * @return threshold in mills
         */
        public int provideBlockThreshold() {
            return 1000;
        }

        /**
         * Thread stack dump interval, use when block happens, BlockCanary will dump on main thread
         * stack according to current sample cycle.
         * <p>
         * Because the implementation mechanism of Looper, real dump interval would be longer than
         * the period specified here (especially when cpu is busier).
         * </p>
         *
         * @return dump interval (in millis)
         */
        public int provideDumpInterval() {
            return provideBlockThreshold();
        }

        /**
         * Path to save log, like "/blockcanary/", will save to sdcard if can.
         *
         * @return path of log files
         */
        public String providePath() {
            return "/blockcanary/";
        }

        /**
         * If need notification to notice block.
         *
         * @return true if need, else if not need.
         */
        public boolean displayNotification() {
            return true;
        }

        /**
         * Implement in your project, bundle files into a zip file.
         *
         * @param src  files before compress
         * @param dest files compressed
         * @return true if compression is successful
         */
        public boolean zip(File[] src, File dest) {
            return false;
        }

        /**
         * Implement in your project, bundled log files.
         *
         * @param zippedFile zipped file
         */
        public void upload(File zippedFile) {
            throw new UnsupportedOperationException();
        }


        /**
         * Packages that developer concern, by default it uses process name,
         * put high priority one in pre-order.
         *
         * @return null if simply concern only package with process name.
         */
        public List<String> concernPackages() {
            List<String> list = super.provideWhiteList();
            list.add("com.whitelist");
            return list;
        }

        /**
         * Filter stack without any in concern package, used with @{code concernPackages}.
         *
         * @return true if filter, false it not.
         */
        public boolean filterNonConcernStack() {
            return false;
        }

        /**
         * Provide white list, entry in white list will not be shown in ui list.
         *
         * @return return null if you don't need white-list filter.
         */
        public List<String> provideWhiteList() {
            LinkedList<String> whiteList = new LinkedList<>();
            whiteList.add("org.chromium");
            return whiteList;
        }

        /**
         * Whether to delete files whose stack is in white list, used with white-list.
         *
         * @return true if delete, false it not.
         */
        public boolean deleteFilesInWhiteList() {
            return true;
        }

        /**
         * Block interceptor, developer may provide their own actions.
         */
        public void onBlock(Context context, BlockInfo blockInfo) {

        }
    }

    private static RefWatcher refWatcher;
    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();

        Thread.setDefaultUncaughtExceptionHandler(new CrashHandler()); // 异常捕获
        refWatcher = LeakCanary.install(this); // 内存泄露检测
        BlockCanary.install(this, new AppBlockCanaryContext()).start(); // 卡顿检测
    }

    @Override
    public void registerActivityLifecycleCallbacks(ActivityLifecycleCallbacks callback) {
        super.registerActivityLifecycleCallbacks(callback);
    }

    public static RefWatcher getRefWatcher() {
        return refWatcher;
    }

    public static Context getAppContext() {
        return appContext;
    }
}


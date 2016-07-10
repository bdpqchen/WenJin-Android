package com.twt.service.wenjin;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.multidex.MultiDexApplication;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;


import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerUIUtils;
import com.squareup.picasso.Picasso;
import com.twt.service.wenjin.api.ApiClient;
import com.twt.service.wenjin.support.CrashHandler;
import com.twt.service.wenjin.support.JPushHelper;
import com.twt.service.wenjin.support.LogHelper;
import com.twt.service.wenjin.support.PrefUtils;
import com.twt.service.wenjin.support.ResourceHelper;

import java.util.Arrays;
import java.util.List;

import cn.jpush.android.api.JPushInterface;
import dagger.ObjectGraph;


/**
 * Created by M on 2015/3/19.
 */
public class WenJinApp extends MultiDexApplication {

    private static Context sContext;

    private static boolean sIsAppLunched;

    private ObjectGraph objectGraph;


    private static final String TAG = "WenJinApp";


    @Override
    public void onCreate() {
        super.onCreate();
        objectGraph = ObjectGraph.create(getModules().toArray());
        objectGraph.inject(this);

        sContext = getApplicationContext();


        ActiveAndroid.initialize(this);


        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        JPushHelper.mContext = getApplicationContext();
        JPushHelper.setNotiStyleBasic();

        DrawerImageLoader.init(new AbstractDrawerImageLoader() {
            @Override
            public void set(ImageView imageView, Uri uri, Drawable placeholder) {
//                Glide.with(imageView.getContext()).load(uri).placeholder(placeholder).into(imageView);
                Picasso.with(sContext).load(uri).placeholder(placeholder).into(imageView);
            }

            @Override
            public void cancel(ImageView imageView) {
                Picasso.with(sContext).cancelRequest(imageView);
            }

            @Override
            public Drawable placeholder(Context ctx, String tag) {
                //define different placeholders for different imageView targets
                //default tags are accessible via the DrawerImageLoader.Tags
                //custom ones can be checked via string. see the CustomUrlBasePrimaryDrawerItem LINE 111
                if (DrawerImageLoader.Tags.PROFILE.name().equals(tag)) {
                    return DrawerUIUtils.getPlaceHolder(ctx);
                } else if (DrawerImageLoader.Tags.ACCOUNT_HEADER.name().equals(tag)) {
                    return new IconicsDrawable(ctx).iconText(" ").backgroundColorRes(com.mikepenz.materialdrawer.R.color.md_white_1000).sizeDp(56);
                } else if ("customUrlItem".equals(tag)) {
                    return new IconicsDrawable(ctx).iconText(" ").backgroundColorRes(R.color.md_grey_500).sizeDp(56);
                }

                //we use the default one for
                //DrawerImageLoader.Tags.PROFILE_DRAWER_ITEM.name()

                return super.placeholder(ctx, tag);
            }
        });
    }

    private List<Object> getModules() {
        return Arrays.<Object>asList(new AppModule(this));
    }

    public ObjectGraph createScopedGraph(Object... modules) {
        return objectGraph.plus(modules);
    }

    public static Context getContext() {
        return sContext;
    }

    public static boolean isAppLunched(){
        return sIsAppLunched;
    }



    public static void setAppLunchState(Boolean argState){
        sIsAppLunched = argState;
    }

    /**
     * 获得当前进程的名字
     *
     * @param context
     * @return 进程号
     */
    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

}

package com.twt.service.wenjin.support;


import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * Created by M on 2015/4/29.
 */
public class UmengShareHelper {

    private static final String WEIXIN_APP_ID = "wx65ec2ecbdebe90bb";

    private static final String WEIXIN_APP_SECRET = "97fecfb6b53a769d0b3e367c15a7463d";

    public static void initShareSdk()
    {
        PlatformConfig.setWeixin(WEIXIN_APP_ID,WEIXIN_APP_SECRET);
        PlatformConfig.setQQZone("1104720359","SnxIFbUH9sualMYF");

    }

    public static final SHARE_MEDIA[] displaylist=new SHARE_MEDIA[]
            {
                    SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE,SHARE_MEDIA.QQ,SHARE_MEDIA.QZONE
            };



//    private static UMSocialService sUmSocialService = UMServiceFactory.getUMSocialService("com.umeng.share");
//
//    public static UMSocialService getUmSocialService() {
//        return sUmSocialService;
//    }
//
//    public static void init(Activity context) {
//        addWeiXin(context);
//        addWeiXinCircle(context);
//        sUmSocialService.openShare(context, false);
//    }
//
//    public static void addWeiXin(Context context) {
//        UMWXHandler umwxHandler = new UMWXHandler(context, WEIXIN_APP_ID, WEIXIN_APP_SECRET);
//        umwxHandler.addToSocialSDK();
//    }
//
//    public static void addWeiXinCircle(Context context) {
//        UMWXHandler umCircleHandler = new UMWXHandler(context, WEIXIN_APP_ID, WEIXIN_APP_SECRET);
//        umCircleHandler.setToCircle(true);
//        umCircleHandler.addToSocialSDK();
//    }
//
//    public static void addSina() {
//    }
//
//    public static void setContent(Context context, String content, String url) {
//        WeiXinShareContent weixinContent = new WeiXinShareContent();
//        weixinContent.setShareContent(content);
//        weixinContent.setTitle(ResourceHelper.getString(R.string.app_name));
//        weixinContent.setShareImage(new UMImage(context, R.drawable.ic_share_logo));
//        weixinContent.setTargetUrl(url);
//        sUmSocialService.setShareMedia(weixinContent);
//        CircleShareContent circleMedia = new CircleShareContent();
//        circleMedia.setShareContent(content);
//        circleMedia.setTitle(content);
//        circleMedia.setShareImage(new UMImage(context, R.drawable.ic_share_logo));
//        circleMedia.setTargetUrl(url);
//        sUmSocialService.setShareMedia(circleMedia);
//
//        sUmSocialService.setShareContent(content + url);
//    }
}

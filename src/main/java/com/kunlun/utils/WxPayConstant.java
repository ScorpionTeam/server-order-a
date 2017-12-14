package com.kunlun.utils;

import java.io.Serializable;

/**
 * 微信支付 常量
 *
 * @author by kunlun
 * @version <0.1>
 * @created on 2017/12/13.
 */
public class WxPayConstant implements Serializable {

    private static final long serialVersionUID = 5150155763881974443L;

    /**
     * 证书
     */
    public static String CERT_SECRET = System.getProperty("user.dir")
            + System.getProperty("file.separator") + "cert" + System.getProperty(
            "file.separator") + "apiclient_cert.p12";

    /**
     * 小程序ID
     */
    public final static String APP_ID = "wx5e495b0ad7d9ac5d";

    /**
     * 小程序秘钥
     */
    public final static String APP_SECRET = "6eb633a285acdb7b44932cacb6a9a5f2";

    /**
     * 商户秘钥
     */
    public final static String MCH_SECRET = "rzxlszyykpbgqcflzxsqcysyhljttclb";

    /**
     * 商户号
     */
    public final static String MCHID = "1491404382";

    /**
     * openid请求地址
     */
    public final static String OPEN_ID_URL = "https://api.weixin.qq.com/sns/jscode2session?";

    /**
     * 退款地址
     */
    public final static String WECHAT_REFUND = "https://api.mch.weixin.qq.com/secapi/pay/refund";

    /**
     * 普通支付回调通知地址
     */
    public final static String NOTIFY_URL = "https://zjhkhl.com/mall/wx/jsapi/callback/pay";

    /**
     * 拼团回调通知地址
     */
    public final static String NOTIFY_URL_GROUP = "https://zjhkhl.com/mall/wx/activity/callBack";

    /**
     * 免费试用回调通知地址
     */
    public final static String FREE_URL_GROUP = "http://zjhkhl.com/mall/wx/free/callBack";

    /**
     * 统一下单地址
     */
    public final static String WECHAT_UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    /**
     * 查询订单地址
     */
    public final static String WECHAT_ORDER_QUERY_URL = "https://api.mch.weixin.qq.com/pay/orderquery";
}

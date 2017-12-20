package com.kunlun.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author by kunlun
 * @version <0.1>
 * @created on 2017/12/13.
 */
public class WxSignUtil implements Serializable {

    private static final long serialVersionUID = 1529235120995041745L;

    /**
     * 签名排序
     *
     * @param map
     * @return
     */
    public static String sort(Map<String, Object> map) {
        StringBuffer stringBuffer = new StringBuffer();
        Collection collection = map.keySet();
        List list = new ArrayList<String>(collection);
        Collections.sort(list);
        int j = 0;
        for (int i = 0; i < list.size(); i++) {
            if (j == 0) {
                stringBuffer.append(list.get(i) + "=" + map.get(list.get(i)));
            } else {
                stringBuffer.append("&" + list.get(i) + "=" + map.get(list.get(i)));
            }
            j++;
        }
        stringBuffer.append("&key=" + WxPayConstant.MCH_SECRET);
        return stringBuffer.toString();
    }

    /**
     * 支付签名
     *
     * @param map
     * @return
     */
    public static String paySign(Map<String, Object> map) {
        return DigestUtils.md5Hex(WxSignUtil.sort(map)).toUpperCase();
    }

    /**
     * 生成支付参数
     *
     * @param timeStamp
     * @param nonceStr
     * @param prepayId
     * @return
     */
    public static Map<String, Object> payParam(Long timeStamp, String nonceStr, String prepayId) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("appId", WxPayConstant.APP_ID);
        map.put("nonceStr", nonceStr);
        map.put("package", "prepay_id=" + prepayId);
        map.put("signType", "MD5");
        map.put("timeStamp", timeStamp.toString());
        return map;
    }


    /**
     * 下单参数
     *
     * @param body       商品描述
     * @param openid     用户openid
     * @param outTradeNo 商户订单号
     * @param paymentFee 实付金额
     * @param nonceStr   随机字符串
     * @param type       下单类型
     * @return
     * @UNIFIED: 正常下单
     * @GROUP: 拼团
     * @FREE: 试用
     */
    public static String unifiedOrder(String body,
                                      String openid,
                                      String outTradeNo,
                                      int paymentFee,
                                      String nonceStr,
                                      String type) {

        Map<String, Object> map = new HashMap<>(16);
        map.put("appid", WxPayConstant.APP_ID);
        map.put("openid", openid);
        map.put("mch_id", WxPayConstant.MCHID);
        map.put("nonce_str", nonceStr);
        map.put("body", body);
        map.put("out_trade_no", outTradeNo);
        map.put("total_fee", paymentFee);
        switch (type) {
            case "UNIFIED":
                map.put("notify_url", WxPayConstant.NOTIFY_URL);
                break;
            case "GROUP":
                map.put("notify_url", WxPayConstant.NOTIFY_URL_GROUP);
                break;
            case "FREE":
                map.put("notify_url", WxPayConstant.FREE_URL_GROUP);
                break;
            default:
                map.put("notify_url", WxPayConstant.NOTIFY_URL);
                break;
        }

        map.put("trade_type", "JSAPI");
        String sign = DigestUtils.md5Hex(WxSignUtil.sort(map)).toUpperCase();
        map.put("sign", sign);
        return XmlUtil.mapConvertToXML(map);
    }

    /**
     * 退款参数
     *
     * @param orderNo
     * @param totalFee
     * @param refundFee
     * @return
     */
    public static String refundSign(
            String orderNo,
            int totalFee,
            int refundFee,
            String refundNo,
            String nonceStr) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("appid", WxPayConstant.APP_ID);
        map.put("mch_id", WxPayConstant.MCHID);
        map.put("nonce_str", nonceStr);
        map.put("out_trade_no", orderNo);
        map.put("out_refund_no", refundNo);
        map.put("total_fee", totalFee);
        map.put("refund_fee", refundFee);
        map.put("op_user_id", "100000");
        String sign = DigestUtils.md5Hex(WxSignUtil.sort(map)).toUpperCase();
        map.put("sign", sign);
        return XmlUtil.mapConvertToXML(map);
    }

    /**
     * 生成随机字符串
     *
     * @param numberFlag
     * @param length
     * @return
     */
    public static String createRandom(boolean numberFlag, int length) {
        StringBuilder retStr = new StringBuilder(length);
        String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
        int len = strTable.length();
        boolean bDone = true;
        do {
            int count = 0;
            for (int i = 0; i < length; i++) {
                double dblR = Math.random() * len;
                int intR = (int) Math.floor(dblR);
                char c = strTable.charAt(intR);
                if (('0' <= c) && (c <= '9')) {
                    count++;
                }
                retStr.append(c);
            }
            if (count >= 2) {
                bDone = false;
            }
        } while (bDone);

        return retStr.toString();
    }


}

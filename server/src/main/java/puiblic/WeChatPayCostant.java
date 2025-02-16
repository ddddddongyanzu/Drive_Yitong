package puiblic;

/**
 * @author panda
 * @date 2021/4/27 11:21
 * <p>
 * description 微信支付常量
 */
public interface WeChatPayCostant {

    /**
     * 应用id
     */
    String APP_ID = "";

    /**
     * 直连商户号
     */
    String MCH_ID = "";

    /**
     * 通知地址
     */
    String NOTIFY_URL = "";

    /**
     * 货币类型
     */
    String CURRENCY = "CNY";

    /**
     * 微信支付url
     */
    String PAY_URL = "";

    /**
     * APIv3密钥 32位（数字 + 字母_小写），可以直接使用Hutool的IdUtil.simpleUuid()生成
     */
    String API_V3_SECRET = "";

    /**
     * 商户API证书序列号
     */
    String SERIAL_NO = "";

}

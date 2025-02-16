package puiblic;

import lombok.Data;

/**
 * @author panda
 * @date 2021/5/8 14:12
 * <p>
 * description 微信支付回调 model
 */
@Data
public class WxPayCallbackModel {

    /**
     * 通知的唯一ID
     * 示例值：EV-2018022511223320873
     */
    private String id = "";
    /**
     * 通知创建的时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss.表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35+08:00表示北京时间2015年05月20日13点29分35秒。
     * 示例值：2015-05-20T13:29:35+08:00
     */
    private String create_time = "";
    /**
     * 通知的类型，支付成功通知的类型为TRANSACTION.SUCCESS
     * 示例值：TRANSACTION.SUCCESS
     */
    private String event_type = "";
    /**
     * 通知的资源数据类型，支付成功通知为encrypt-resource
     * 示例值：encrypt-resource
     */
    private String resource_type = "";
    /**
     * 回调摘要
     * 示例值：支付成功
     */
    private String summary = "";

    /**
     * 通知资源数据
     * json格式，见示例
     */
    private WxPayCallbackResourceModel resource;

    @Override
    public String toString() {
        return "WxPayCallbackModel{" +
                "id='" + id + '\'' +
                ", create_time='" + create_time + '\'' +
                ", event_type='" + event_type + '\'' +
                ", resource_type='" + resource_type + '\'' +
                ", summary='" + summary + '\'' +
                ", resource=" + resource.toString() +
                '}';
    }
}

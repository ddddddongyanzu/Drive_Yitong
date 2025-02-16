package puiblic;

import lombok.Data;

/**
 * @author panda
 * @date 2021/5/8 14:12
 * <p>
 * description 微信支付回调通知数据 model
 */
@Data
public class WxPayCallbackResourceModel {

    /**
     * 加密算法类型
     * 对开启结果数据进行加密的加密算法，目前只支持AEAD_AES_256_GCM
     * 示例值：AEAD_AES_256_GCM
     */
    private String algorithm = "";
    /**
     * 数据密文
     * Base64编码后的开启/停用结果数据密文
     * 示例值：sadsadsadsad
     */
    private String ciphertext = "";
    /**
     * 附加数据
     * 附加数据
     * 示例值：fdasfwqewlkja484w
     */
    private String associated_data = "";
    /**
     * 原始类型
     * 原始回调类型，为transaction
     * 示例值：transaction
     */
    private String original_type = "";
    /**
     * 随机串
     * 加密使用的随机串
     * 示例值：fdasflkja484w
     */
    private String nonce = "";

    @Override
    public String toString() {
        return "WxPayCallbackResourceModel{" +
                "algorithm='" + algorithm + '\'' +
                ", ciphertext='" + ciphertext + '\'' +
                ", associated_data='" + associated_data + '\'' +
                ", original_type='" + original_type + '\'' +
                ", nonce='" + nonce + '\'' +
                '}';
    }
}
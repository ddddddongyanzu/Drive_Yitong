package com.wh.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.wh.service.PortalWechatService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import puiblic.AesUtil;
import puiblic.WeChatPayCostant;
import puiblic.WxPayCallbackModel;
import puiblic.WxPayCallbackResourceModel;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * @author panda
 * @date 2021/4/27 14:11
 * <p>
 * description serviceImpl
 */
@Slf4j
@Service
public class PortalWechatServiceimpl implements PortalWechatService {

    /**
     * 微信支付回调
     *
     * @param request 请求
     * @return 结果
     */
    @Override
    public String callBack(HttpServletRequest request) {
        // TODO 必须使用request获取body（readLine读取），微信推送的消息使用@RequestBody可能一次性无法读完，造成解密失败
        String resCode = "SUCCESS";
        String resMessage = "成功";
        String streamReadString = getRequestBody(request);
        WxPayCallbackModel model = JSONObject.parseObject(streamReadString, WxPayCallbackModel.class);
        log.info("pay callback: request read={}", streamReadString);
        try {
            WxPayCallbackResourceModel resource = model.getResource();
            String associatedData = resource.getAssociated_data();
            String nonce = resource.getNonce();
            String ciphertext = resource.getCiphertext();

            byte[] aesKey = WeChatPayCostant.API_V3_SECRET.trim().toLowerCase().getBytes("UTF-8");
            byte[] associatedDataBytes = associatedData.getBytes("UTF-8");
            byte[] nonceBytes = nonce.getBytes("UTF-8");
            byte[] ciphertextBytes = Base64.decodeBase64(ciphertext);

            // 开始解密
            AesUtil aesUtil = new AesUtil(aesKey);
            String decryptedString = aesUtil.decryptToString(associatedDataBytes, nonceBytes, ciphertextBytes);
            log.info("微信支付回调 - 解密: {}", decryptedString);
            // 解密得到的json结果
            JSONObject decryptedJsonObj = JSONObject.parseObject(decryptedString);
            String code = decryptedJsonObj.getString("out_trade_no");

            // 1. 数据库通过订单号查询到的的订单
            // 2. 修改订单中支付状态，微信通知状态，支付相关其它信息

        } catch (Exception e) {
            log.error("支付回调失败: {}", e.getMessage());
//            e.printStackTrace();

            resCode = "FAIL";
            resMessage = "支付失败";
        }

        JSONObject returnJson = new JSONObject();
        returnJson.put("code", resCode);
        returnJson.put("message", resMessage);

        return returnJson.toJSONString();
    }

    // *****************************************************业务支撑*****************************************************

    /**
     * 获取请求体
     * @param request 请求
     */
    private String getRequestBody(HttpServletRequest request) {
        ServletInputStream stream = null;
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try {
            stream = request.getInputStream();
            // 获取响应
            reader = new BufferedReader(new InputStreamReader(stream));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert stream != null;
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert reader != null;
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }
}



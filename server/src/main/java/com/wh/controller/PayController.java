package com.wh.controller;

import com.alibaba.fastjson.JSON;
import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.AutoUpdateCertificatesVerifier;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import com.wh.entity.result.WXOrderResult;
import com.wh.entity.to.Amount;
import com.wh.entity.to.OrderData;
import com.wh.entity.to.PayData;
import com.wh.entity.to.payer;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.UUID;

@RequestMapping("pay")
@Controller
public class PayController {

    private CloseableHttpClient httpClient;

    private String privateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCdQKTLmaltW+ki+sTMncpG+mmDq38aNyqsKT2VEI+6sZnhrku+H4Oo6vlBBqneBEz8z0R0BJtn9RrLovKv8v0HcYDecdkWJr8NmVQSK98Te533ifMcnD8oL0Ktx6ipbMGXYEs0TlRig/GDVjv5UQ9DLoe/MbX6U9cBkrKT4sXUIS5xhdrLVZ7shkB4Ku240gjlEvpo+Ne6wTYL1y1bJcaxDk+U3+VlPqMA4AyFyojsGerAvDLQgy+mnf3o0nTno1z0SYbj/igDukgz2pEfXfgSzyQAoS2byN2W/K3eHNIT4c8VK2OsNCe6niw4RUS+S3GCsQQnWKOvslMqoojipZ7NAgMBAAECggEASjSND37NksuCcn0/jQcQwvm7/ORasDZELsWvR75u9LyGTxb+qxMYAAgR4vuQATKqWco9FTpvU1k4ccvNFfZmLLMFCvNSa7+Q/IvUPlbxh7IX0w4H6QJaU04p+vNyxbCKkjJgiqhD7dExkLaKypXTy/ITIMgGhcA2f4mIN30VuYnJh0dZ3UxsPmAm6glrIv5xr+7ilU5XoJCpCFTJdX2sSen+AyY1ucurRs4vVla4mXEuPEVk2tql/P0A14l2qipcRE3Sv7YoiHlKU8RdQ8On+O0hUKh9Q6N3OvBPdwf60S2gt7D0DLrDFeeSfVGUc8UpQZsD/vYPAvQT0FnscM/jgQKBgQDM74aLZ5e9O7zASEUJQ/pP0ztREye461nf2u2wzHi7YcixvXI4MV+nmbXhcVCO5OivwSGVPyQO0j1aO/7KzO3Jow3aMkc3qKrwjZWfs3iuiXLtBDW3KxV+WOknPjrAFcSgqQ+UBEwJlwkH/qzyPXnewvsC/VyKp+UUpDHX/V1JHQKBgQDEb39TUxFb5dQAvXqrFnIhv3kskf0vCzNFwniHctpkVTJQM9+Se1SI6q/vznISZYt2H4r6RwY3qbBW86Y77fepetYyQKHLmNBf5unUKqRoaHR7vk36SdJsWej9tvuZsJBLKyWhfBw8h5b+Esm56kgFOY7rgmh0aMaVH8wfDQNtcQKBgEbJUXLjhGEjui28iMQLi/F4p9t4P1c7S97rswBHkEhBHV5vbn2EaGLu0Akf59CDtu/GSQBRP/YynmiFopuQL25feNBuX3UVOH5AmXcBOeUnqt+9fYRYSGNM+LPKZealXjaUTsI0vcLuPXtad4hwvaC5PpSoyQdHuPPpRyIw7MDtAoGALrddjpF41WQpMSve2oVyCzigNstZbTC6tuJzC7Ny4N2qI4zSpOGTzyQRR3TPiDSCIEuMH814WxCOEX/MjwAfID1IZ5cbY3IqTER8Y6rogrQkz7EIlnesBXy0fnJgD8L/7gCK6Ia9qF+Ot0n8k6x2CFgR90yCTv0EDT8Mqm3L3OECgYBwI63cOFjvuzH+zF6nwQ5aj5QaqkZWqwSfTw0Nt24+IBtGb2XzMjjRXytH5vq7Ydgl0h5weABkWVm4uJzxe3qmcRRVaXT1IIdak0FeHTR7yz7M80qYsUHOYFvyf5fDyt3D1c9UL59bjgQktK2AWDrSZ5AMhw5nNTXQKQ7xjzOMNQ==";

    @Value("1609988549")
    private String mchId;

    @Value("1F14CD72D05A5FB0C26A244D7D3621769CBA18FC")
    private String mchSerialNo;

    @Value("zhenxingjiaxiao2021629shanghekej")
    private String apiV3Key;

    @Value("wx0157d417156253e9")
    private String appid;


    public void setup() throws IOException {
        // 加载商户私钥（privateKey：私钥字符串）
        System.out.println("-----------1-------------");
        PrivateKey merchantPrivateKey = PemUtil
                .loadPrivateKey(new ByteArrayInputStream(privateKey.getBytes("utf-8")));
        System.out.println("-----------2-------------");
        // 加载平台证书（mchId：商户号,mchSerialNo：商户证书序列号,apiV3Key：V3密钥）
        AutoUpdateCertificatesVerifier verifier = new AutoUpdateCertificatesVerifier(
                new WechatPay2Credentials(mchId, new PrivateKeySigner(mchSerialNo, merchantPrivateKey)), apiV3Key.getBytes("utf-8"));
        System.out.println("-----------3-------------");
        // 初始化httpClient
        httpClient = WechatPayHttpClientBuilder.create()
                .withMerchant(mchId, mchSerialNo, merchantPrivateKey)
                .withValidator(new WechatPay2Validator(verifier)).build();
        System.out.println("-----------4-------------");
    }

    public void after() throws IOException {
        httpClient.close();
    }

    //    int uId,  String pId, String student,int carType,double payAmount
//    下单   int uId, String pId,String student,
    @ResponseBody
    @PostMapping("/createOrder")
    public PayData CreateOrder(String openid, double payment) throws Exception {
        System.out.println("-----------chentianze-------------");
        System.out.println(openid);
        System.out.println(payment);
        System.out.println("-----------chentianzedsfsfsf-------------");
        setup();
        System.out.println("-----------chentianze1-------------");
        //请求URL
        HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/v3/pay/transactions/jsapi");
        System.out.println("-----------chentianze2-------------");
        OrderData orderData = new OrderData();
        orderData.setAmount(new Amount((int) (payment * 100), "CNY"));
        orderData.setAppid(appid);
        orderData.setDescription("驾校报名");
        orderData.setMchid(mchId);
        orderData.setNotify_url("https://www.weixin.qq.com/wxpay/pay.php");
        String out_trade_no = UUID.randomUUID().toString().replaceAll("-", "");
        orderData.setOut_trade_no(out_trade_no);
        orderData.setPayer(new payer(openid));
        System.out.println("-----------chentianze3-------------");
        // 请求body参数  转换成JSON
        String reqdata = JSON.toJSONString(orderData);
//      String reqdata = "{"
//                + "\"amount\": {"
//                + "\"total\": 1,"
//                + "\"currency\": \"CNY\""
//                + "},"
//                + "\"mchid\": \"1596317511\","
//                + "\"description\": \"Image形象店-深圳腾大-QQ公仔\","
//                + "\"notify_url\": \"https://www.weixin.qq.com/wxpay/pay.php\","
//                + "\"payer\": {"
//                + "\"openid\": \"opB2O5RjAwOlIXYxL5GAmYx9NOSE\"" + "},"
//                + "\"out_trade_no\": \"1217752501201407033233388881\","
//                + "\"goods_tag\": \"WXG\","
//                + "\"appid\": \"wx730c9ee586154929\"" + "}";
        StringEntity entity = new StringEntity(reqdata);
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        System.out.println("-----------chentianze4-------------");
        //完成签名并执行请求
        CloseableHttpResponse response = httpClient.execute(httpPost);
        System.out.println("-----------chentianze5-------------");
        try {
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                System.out.println("success,return body = " + EntityUtils.toString(response.getEntity()));

                WXOrderResult wxOrderResult = JSON.parseObject(EntityUtils.toString(response.getEntity()), WXOrderResult.class);

                String timeStamp = String.valueOf(System.currentTimeMillis());
                System.out.println("date = " + timeStamp);
                String nonceStr = UUID.randomUUID().toString().replaceAll("-", "");
                String s = appid + "\n" + timeStamp + "\n" + nonceStr + "\nprepay_id=" + wxOrderResult.getPrepay_id() + "\n";
                PayController payController = new PayController();
                System.out.println("-----------chentianze6-------------");
                System.out.println("s = " + s);
//               生成签名
                String paySign = payController.signBySHA256WithRSA(s);
                System.out.println("paySign = " + paySign);

                PayData payData = new PayData();
                payData.setNonceStr(nonceStr);
                payData.setPaySign(paySign);
                payData.setTimeStamp(timeStamp);
                payData.setPrepay_id(wxOrderResult.getPrepay_id());
                payData.setOut_trade_no(out_trade_no);
                System.out.println("商户订单号是：out_trade_no= " + out_trade_no);
                System.out.println("-----------chentianze7-------------");
                return payData;
            } else if (statusCode == 204) {
                System.out.println("-----------chentianze8-------------");
                System.out.println("success");
                return null;
            } else {
                System.out.println("-----------chentianze9-------------");
                System.out.println("failed,resp code = " + statusCode + ",return body = " + EntityUtils.toString(response.getEntity()));
                throw new IOException("request failed");
            }
        } finally {
            System.out.println("-----------chentianze10-------------");
            response.close();
            httpClient.close();
            System.out.println("-----------chentianze11-------------");
        }
    }

    @RequestMapping("/pay")
    public OrderData pay() {
        OrderData orderData = new OrderData();
        return orderData;
    }

    public String signBySHA256WithRSA(String content) {
        if (StringUtils.isBlank(privateKey)) {
            System.out.println("null----------------");
            //缺少签名私钥
            return null;
        }
        try {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
            PrivateKey priKey = KeyFactory.getInstance("RSA").generatePrivate(priPKCS8);
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(priKey);
            signature.update(content.getBytes("utf-8"));
            return Base64.encodeBase64String(signature.sign());
        } catch (Exception e) {
            //签名失败
            return null;
        }
    }

    public static void main(String[] args) {
//        String s = UUID.randomUUID().toString().replaceAll("-", "");
//        System.out.println(s);
//        System.out.println(s.length());
//        OrderData orderData = new OrderData();
//        System.out.println();
//        String date = String.valueOf(System.currentTimeMillis());
//        System.out.println("date = " + date);
//        String s="wx730c9ee586154929\n" + date + "\n5K8226ILTKCH16CQ2502SI8ZNMTM46VS\nprepay_id=wx122123317648996f445969098d38440000\n";
//        PayController payController = new PayController();
//        String s1 = payController.signBySHA256WithRSA(s);
//        System.out.println("s1 = " + s1);
//        double yuan=0.02;
//        int fen= (int) (yuan*100);
//        System.out.println("fen = " + fen);
        String url = "/root/DrivingSchools/dsms.jar!/BOOT-INF/classes!/static/b627b92e-aaf6-4c79-88a5-c173905685cf.jpg";
        url = url.replaceAll("!", "");
        System.out.println("url = " + url);

    }
}
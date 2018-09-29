package paybar;

import com.payplus.api.client.ApiClient;
import com.payplus.api.client.ApiRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.DateCodec;
import com.payplus.api.client.ApiResponse;
import com.payplus.api.enumtype.EncryptTypeEnum;
import com.payplus.api.security.AESUtil;
import com.payplus.api.util.HttpClient4Utils;

import java.text.DateFormat;
import java.util.*;

public class Test {
    private static String merchantNo = "******";//直销商户测试账户
    private static String key = "********";//测试秘钥

    public static void main(String[] args) throws Exception {

        //统一下单
        pay();
        //支付查询
        //payQuery();
        //退款
        //refund();
        //退款查询
        //refundQuery();
        //支付撤销
        //cancel();
        //结算接口
        //settle();
        //结算确认接口
        //settleConfirm();
        //结算查询
        //settleQuery();


    }

    private static void pay() throws Exception {
//统一下单
        String url = "https://hospital.jia007.com/paybar-api/paybar/hospital/pay";

        ApiRequest apiRequest = new ApiRequest(merchantNo, key);
        apiRequest.setSupportSign(false);
        apiRequest.setEncryptType(EncryptTypeEnum.AES);

        apiRequest.addParam("requestNo", String.valueOf(System.currentTimeMillis()));
        apiRequest.addParam("orderAmount", "0.01");
        //apiRequest.addParam("payTool", "MICROPAY");
        apiRequest.addParam("payTool", "WECHAT_PUBLIC");
        apiRequest.addParam("expireTime", "10");
        apiRequest.addParam("orderDate", "2018-07-30 17:43:12");
        apiRequest.addParam("serverCallbackUrl", "http://pay.weixin.cn/notify/JhNotify");
        apiRequest.addParam("productName", "测试");
        //apiRequest.addParam("productDesc", "测试");
        //apiRequest.addParam("authCode", "134599780772503768");
        apiRequest.addParam("clientIp", "192.168.0.1");
        //apiRequest.addParam("payType", "住院");
        apiRequest.addParam("payScene", "BH");
        //apiRequest.addParam("districtInfo", "一区");
        apiRequest.addParam("openid", "o0yn8wL-KDt2cFfYz-EXUrt7zV5o");
        apiRequest.addParam("appid", "wx931386123456789e");
        //apiRequest.addParam("terminalNo", "11011");

        System.out.println("apiRequest:" + apiRequest.toString());
        System.out.println("url:" + url);
        ApiResponse apiResponse = ApiClient.post(url, apiRequest);
        System.out.println(apiResponse.getState());
        System.out.println("apiResponse:" + JSON.toJSONString(apiResponse));
    }

    private static void payQuery() throws Exception {
//支付查询
        String url = "https://hospital.jia007.com/paybar-api/paybar/hospital/payQuery";

        ApiRequest apiRequest = new ApiRequest(merchantNo, key);
        apiRequest.setSupportSign(false);
        apiRequest.setEncryptType(EncryptTypeEnum.AES);

        //apiRequest.addParam("requestNo", "1531895814136");
        apiRequest.addParam("trxRequestNo", "1531896446750");
        //apiRequest.addParam("channelOrderNo", "11180713140542427639");
        //以上两个参数都传时以订单号为准

        System.out.println("apiRequest:" + apiRequest.toString());
        System.out.println("url:" + url);
        ApiResponse apiResponse = ApiClient.post(url, apiRequest);
        System.out.println(apiResponse.getState());
        System.out.println("apiResponse:" + JSON.toJSONString(apiResponse));
    }

    private static void refund() throws Exception {
//退款接口
        String url = "https://hospital.jia007.com/paybar-api/paybar/hospital/refund";

        ApiRequest apiRequest = new ApiRequest(merchantNo, key);
        apiRequest.setSupportSign(false);
        apiRequest.setEncryptType(EncryptTypeEnum.AES);

        apiRequest.addParam("requestNo", String.valueOf(System.currentTimeMillis()));
        apiRequest.addParam("trxRequestNo", "1531895814136");
        //apiRequest.addParam("orderNo", "11171031155251903380");
        apiRequest.addParam("refundAmount", "0.01");

        System.out.println("apiRequest:" + apiRequest.toString());
        System.out.println("url:" + url);
        ApiResponse apiResponse = ApiClient.post(url, apiRequest);
        System.out.println(apiResponse.getState());
        System.out.println("apiResponse:" + JSON.toJSONString(apiResponse));
    }

    private static void refundQuery() throws Exception {
//退款查询接口
        String url = "https://hospital.jia007.com/paybar-api/paybar/hospital/refundQuery";

        ApiRequest apiRequest = new ApiRequest(merchantNo, key);
        apiRequest.setSupportSign(false);
        apiRequest.setEncryptType(EncryptTypeEnum.AES);

        apiRequest.addParam("trxRequestNo", "1531895814136");
        //apiRequest.addParam("refundRequestNo", "1509437562877");
        //apiRequest.addParam("channelOrderNo", "1051100110000069");

        System.out.println("apiRequest:" + apiRequest.toString());
        System.out.println("url:" + url);
        ApiResponse apiResponse = ApiClient.post(url, apiRequest);
        System.out.println(apiResponse.getState());
        System.out.println("apiResponse:" + JSON.toJSONString(apiResponse));
    }

    private static void cancel() throws Exception {
//支付撤销
        String url = "https://hospital.jia007.com/paybar-api/paybar/hospital/cancel";

        ApiRequest apiRequest = new ApiRequest(merchantNo, key);
        apiRequest.setSupportSign(false);
        apiRequest.setEncryptType(EncryptTypeEnum.AES);

        //apiRequest.addParam("trxRequestNo", "1531896446750");
        apiRequest.addParam("channelOrderNo", "11180718144728448566");

        System.out.println("apiRequest:" + apiRequest.toString());
        System.out.println("url:" + url);
        ApiResponse apiResponse = ApiClient.post(url, apiRequest);
        System.out.println(apiResponse.getState());
        System.out.println("apiResponse:" + JSON.toJSONString(apiResponse));
    }

}

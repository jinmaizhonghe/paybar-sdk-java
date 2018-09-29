package paybar;

        import com.payplus.api.client.ApiClient;
        import com.payplus.api.client.ApiRequest;

        import com.alibaba.fastjson.JSON;
        import com.alibaba.fastjson.serializer.DateCodec;
        import com.payplus.api.client.ApiResponse;
        import com.payplus.api.enumtype.EncryptTypeEnum;
        import com.payplus.api.security.AESUtil;
        import com.payplus.api.util.CloseUtils;
        import com.payplus.api.util.HttpClient4Utils;

        import java.io.InputStream;
        import java.text.DateFormat;
        import java.util.*;

public class TestApp {
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
        //对账单下载
        //downloadFile();
        //testdownloadFile();

    }

    private static void pay() throws Exception {
//统一下单
        String url = "https://hospital.jia007.com/paybar-api/paybar/hospnet/pay";

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
        apiRequest.addParam("clientIp", "192.168.0.1");
        //apiRequest.addParam("payType", "住院");
        apiRequest.addParam("payScene", "BH");
        //apiRequest.addParam("districtInfo", "一区");
        apiRequest.addParam("openid", "o0yn8wL-KDt2cFfYz-EXUrt7zV5o");
        apiRequest.addParam("appid", "wx931386123456789e");

        System.out.println("apiRequest:" + apiRequest.toString());
        System.out.println("url:" + url);
        ApiResponse apiResponse = ApiClient.post(url, apiRequest);
        System.out.println(apiResponse.getState());
        System.out.println("apiResponse:" + JSON.toJSONString(apiResponse));
    }

    private static void payQuery() throws Exception {
//支付查询
        String url = "https://hospital.jia007.com/paybar-api/paybar/hospnet/payQuery";

        ApiRequest apiRequest = new ApiRequest(merchantNo, key);
        apiRequest.setSupportSign(false);
        apiRequest.setEncryptType(EncryptTypeEnum.AES);

        apiRequest.addParam("trxRequestNo", "1531896446750");

        System.out.println("apiRequest:" + apiRequest.toString());
        System.out.println("url:" + url);
        ApiResponse apiResponse = ApiClient.post(url, apiRequest);
        System.out.println(apiResponse.getState());
        System.out.println("apiResponse:" + JSON.toJSONString(apiResponse));
    }

    private static void refund() throws Exception {
//退款接口
        String url = "https://hospital.jia007.com/paybar-api/paybar/hospnet/refund";

        ApiRequest apiRequest = new ApiRequest(merchantNo, key);
        apiRequest.setSupportSign(false);
        apiRequest.setEncryptType(EncryptTypeEnum.AES);

        apiRequest.addParam("requestNo", String.valueOf(System.currentTimeMillis()));
        apiRequest.addParam("trxRequestNo", "1531895814136");
        apiRequest.addParam("refundAmount", "0.01");

        System.out.println("apiRequest:" + apiRequest.toString());
        System.out.println("url:" + url);
        ApiResponse apiResponse = ApiClient.post(url, apiRequest);
        System.out.println(apiResponse.getState());
        System.out.println("apiResponse:" + JSON.toJSONString(apiResponse));
    }

    private static void refundQuery() throws Exception {
//退款查询接口
        String url = "https://hospital.jia007.com/paybar-api/paybar/hospnet/refundQuery";

        ApiRequest apiRequest = new ApiRequest(merchantNo, key);
        apiRequest.setSupportSign(false);
        apiRequest.setEncryptType(EncryptTypeEnum.AES);

        apiRequest.addParam("trxRequestNo", "1531895814136");
        //apiRequest.addParam("refundRequestNo", "1509437562877");

        System.out.println("apiRequest:" + apiRequest.toString());
        System.out.println("url:" + url);
        ApiResponse apiResponse = ApiClient.post(url, apiRequest);
        System.out.println(apiResponse.getState());
        System.out.println("apiResponse:" + JSON.toJSONString(apiResponse));
    }

    private static void cancel() throws Exception {
//支付撤销
        String url = "https://hospital.jia007.com/paybar-api/paybar/hospnet/cancel";

        ApiRequest apiRequest = new ApiRequest(merchantNo, key);
        apiRequest.setSupportSign(false);
        apiRequest.setEncryptType(EncryptTypeEnum.AES);

        apiRequest.addParam("trxRequestNo", "1531896446750");

        System.out.println("apiRequest:" + apiRequest.toString());
        System.out.println("url:" + url);
        ApiResponse apiResponse = ApiClient.post(url, apiRequest);
        System.out.println(apiResponse.getState());
        System.out.println("apiResponse:" + JSON.toJSONString(apiResponse));
    }

    private static void downloadFile() throws Exception {
//对账单下载
        String url = "https://hospital.jia007.com/paybar-api/paybar/hospnet/downloadFile";

        ApiRequest apiRequest = new ApiRequest(merchantNo, key);
        apiRequest.setSupportSign(false);
        apiRequest.setEncryptType(EncryptTypeEnum.AES);

        apiRequest.addParam("billDate", "2018-09-27");
        apiRequest.addParam("payScene", "APP");
        apiRequest.addParam("bizType", "WA");

        try {
            // 文件下载路径
            String filePath = "D:\\download\\0927.txt";
            ApiClient.downloadFile(url, apiRequest,filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 下载文件返回文件流
     */
    private static void testdownloadFile() {
        //对账单下载
        String url = "https://hospital.jia007.com/paybar-api/paybar/hospnet/downloadFile";
        ApiRequest apiRequest = new ApiRequest(merchantNo, key);
        apiRequest.setSupportSign(false);
        apiRequest.setEncryptType(EncryptTypeEnum.AES);
        apiRequest.addParam("checkDate", "2018-09-11");//交易日期
        apiRequest.addParam("bizType", "TRADE");//账单业务类型:微信支付宝WA
        InputStream inputStream = null;

        try {
            // 文件下载路径
            inputStream= ApiClient.downloadFile(url, apiRequest);
            // 文件流处理


        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            CloseUtils.close(inputStream);
        }
    }

}

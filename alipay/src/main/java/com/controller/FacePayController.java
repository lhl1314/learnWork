package com.controller;

import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.demo.trade.config.Configs;
import com.alipay.demo.trade.model.ExtendParams;
import com.alipay.demo.trade.model.GoodsDetail;
import com.alipay.demo.trade.model.builder.AlipayTradePrecreateRequestBuilder;
import com.alipay.demo.trade.model.builder.AlipayTradeQueryRequestBuilder;
import com.alipay.demo.trade.model.builder.AlipayTradeRefundRequestBuilder;
import com.alipay.demo.trade.model.result.AlipayF2FPrecreateResult;
import com.alipay.demo.trade.model.result.AlipayF2FQueryResult;
import com.alipay.demo.trade.model.result.AlipayF2FRefundResult;
import com.alipay.demo.trade.service.AlipayTradeService;
import com.alipay.demo.trade.service.impl.AlipayTradeServiceImpl;
import com.alipay.demo.trade.utils.ZxingUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author lhl
 * @version 1.0
 * @date 2019/12/27 8:47
 * @description TODO
 */
@Controller
@RequestMapping(value = "/facePay")
public class FacePayController {

    /**
     * 创建当面付支付的二维码-----------这里直接创建的图片，js生成二维码不能用，不知道为啥
     * 必填项
     * 订单号：outTradeNo
     * 订单名称：subject
     * 订单金额：totalAmount
     *
     * @return
     */
    @RequestMapping(value = "/trade_precreate")
    @ResponseBody
    public void trade_precreate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Log log = LogFactory.getLog("trade_precreate");
        PrintWriter out = response.getWriter();
        if (request.getParameter("outTradeNo") != null) {
            // 一定要在创建AlipayTradeService之前设置参数
            Configs.init("zfbinfo.properties");
            AlipayTradeService tradeService = new AlipayTradeServiceImpl.ClientBuilder().build();

            // (必填) 商户网站订单系统中唯一订单号，64个字符以内，只能包含字母、数字、下划线，
            // 需保证商户系统端不能重复，建议通过数据库sequence生成，
            String outTradeNo = request.getParameter("outTradeNo");

            // (必填) 订单标题，粗略描述用户的支付目的。如“喜士多（浦东店）消费”
            String subject = request.getParameter("subject");
            subject = new String(subject.getBytes("ISO-8859-1"), "utf-8");
            // (必填) 订单总金额，单位为元，不能超过1亿元
            // 如果同时传入了【打折金额】,【不可打折金额】,【订单总金额】三者,则必须满足如下条件:【订单总金额】=【打折金额】+【不可打折金额】
            String totalAmount = request.getParameter("totalAmount");

            // (可选) 订单不可打折金额，可以配合商家平台配置折扣活动，如果酒水不参与打折，则将对应金额填写至此字段
            // 如果该值未传入,但传入了【订单总金额】,【打折金额】,则该值默认为【订单总金额】-【打折金额】
            String undiscountableAmount = request.getParameter("undiscountableAmount");

            // 卖家支付宝账号ID，用于支持一个签约账号下支持打款到不同的收款账号，(打款到sellerId对应的支付宝账号)
            // 如果该字段为空，则默认为与支付宝签约的商户的PID，也就是appid对应的PID
            String sellerId = "";

            // 订单描述，可以对交易或商品进行一个详细地描述，比如填写"购买商品2件共15.00元"
            String body = "购买商品2件共15.00元";

            // 商户操作员编号，添加此参数可以为商户操作员做销售统计
            String operatorId = "test_operator_id";

            // (必填) 商户门店编号，通过门店号和商家后台可以配置精准到门店的折扣信息，详询支付宝技术支持
            String storeId = "test_store_id";

            // 业务扩展参数，目前可添加由支付宝分配的系统商编号(通过setSysServiceProviderId方法)，详情请咨询支付宝技术支持
            ExtendParams extendParams = new ExtendParams();
            extendParams.setSysServiceProviderId("2088100200300400500");

            // 支付超时，定义为120分钟
            String timeoutExpress = "120m";

            // 商品明细列表，需填写购买商品详细信息，
            List<GoodsDetail> goodsDetailList = new ArrayList<GoodsDetail>();
            // 创建一个商品信息，参数含义分别为商品id（使用国标）、名称、单价（单位为分）、数量，如果需要添加商品类别，详见GoodsDetail
            GoodsDetail goods1 = GoodsDetail.newInstance("goods_id001", "全麦小面包", 1500, 1);
            // 创建好一个商品后添加至商品明细列表
            goodsDetailList.add(goods1);

            // 继续创建并添加第一条商品信息，用户购买的产品为“黑人牙刷”，单价为5.05元，购买了两件
            GoodsDetail goods2 = GoodsDetail.newInstance("goods_id002", "黑人牙刷", 505, 2);
            goodsDetailList.add(goods2);

            // AlipayTradePrecreateContentBuilder builder = new AlipayTradePrecreateContentBuilder()

            AlipayTradePrecreateRequestBuilder builder = new AlipayTradePrecreateRequestBuilder()
                    .setSubject(subject)
                    .setTotalAmount(totalAmount)
                    .setOutTradeNo(outTradeNo)
                    .setUndiscountableAmount(undiscountableAmount)
                    .setSellerId(sellerId)
                    .setBody(body)
                    .setOperatorId(operatorId)
                    .setStoreId(storeId)
                    .setExtendParams(extendParams)
                    .setTimeoutExpress(timeoutExpress)
                    .setNotifyUrl("http://139.196.125.230:8083/facePay/notify_url")//支付宝服务器主动通知商户服务器里指定的页面http路径,根据需要设置
                    .setGoodsDetailList(goodsDetailList);

            AlipayF2FPrecreateResult result = tradeService.tradePrecreate(builder);
            String img=null;
            switch (result.getTradeStatus()) {
                case SUCCESS:
                    log.info("支付宝预下单成功: )");

                    AlipayTradePrecreateResponse res = result.getResponse();

                    String basePath = request.getSession().getServletContext().getRealPath("/");
                    System.out.println("------------------");
                    System.out.println("images%sqr-%s.png");
                    System.out.println(File.separator);
                    System.out.println(res.getOutTradeNo());
//                    String fileName = String.format("images%sqr-%s.png", File.separator, res.getOutTradeNo());
                    String fileName = String.format(File.separator+"images%sqr-%s.png", File.separator, res.getOutTradeNo());
                    String filePath = new StringBuilder(basePath).append(fileName).toString();
                    img="<img src=\"" + fileName + "\" />";

                    System.out.println(fileName);
                    System.out.println(img);
//                    out.println("<!DOCTYPE html>\n" +
//                            "<html>\n" +
//                            "\t<head>\n" +
//                            "\t\t<meta charset=\"UTF-8\">\n" +
//                            "\t\t<title></title>\n" +
//                            "\t</head>\n" +
//                            "\t<body>\n" +
//                            "\n" +img+
//                            "\t</body>\n" +
//                            "</html>\n");
//                    out.println("filePath:" + filePath);
                    ZxingUtils.getQRCodeImge(res.getQrCode(), 256, filePath);
                    break;

                case FAILED:
                    log.error("支付宝预下单失败!!!");
                    break;

                case UNKNOWN:
                    log.error("系统异常，预下单状态未知!!!");
                    break;

                default:
                    log.error("不支持的交易状态，交易返回异常!!!");
                    break;
            }
            System.out.println(result.getResponse().getBody());
            out.println(img);
//            out.println(result.getResponse().getBody());
//            return;
        }


    }


    /**
     * 订单查询，订单号是商户自创的订单号uuid
     * 必填项：
     * 订单号：outTradeNo
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/trade_query")
    @ResponseBody
    public void trade_query(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Log log = LogFactory.getLog("trade_query");
        PrintWriter out = response.getWriter();
        if (request.getParameter("outTradeNo") != null) {
            // 一定要在创建AlipayTradeService之前设置参数
            Configs.init("zfbinfo.properties");

            AlipayTradeService tradeService = new AlipayTradeServiceImpl.ClientBuilder().build();

            // (必填) 商户订单号，通过此商户订单号查询当面付的交易状态
            String outTradeNo = request.getParameter("outTradeNo");
            AlipayTradeQueryRequestBuilder builder = new AlipayTradeQueryRequestBuilder()
                    .setOutTradeNo(outTradeNo);
            AlipayF2FQueryResult result = tradeService.queryTradeResult(builder);
            switch (result.getTradeStatus()) {
                case SUCCESS:
                    log.info("查询返回该订单支付成功: )");

                    AlipayTradeQueryResponse resp = result.getResponse();

                    log.info(resp.getTradeStatus());
                    log.info(resp.getFundBillList());
                    break;

                case FAILED:
                    log.error("查询返回该订单支付失败!!!");
                    break;

                case UNKNOWN:
                    log.error("系统异常，订单支付状态未知!!!");
                    break;

                default:
                    log.error("不支持的交易状态，交易返回异常!!!");
                    break;
            }
            out.println(result.getResponse().getBody());
            return;
        }
    }

    /**
     * 当面付退款
     * 必填项：
     * 订单号：outTradeNo
     * 退款金额：refundAmount
     * 退款原因：refundReason
     */
    @RequestMapping(value = "/trade_refund")
    @ResponseBody
    public void trade_refund(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Log log = LogFactory.getLog("trade_refund");
        PrintWriter out = response.getWriter();
        if (request.getParameter("outTradeNo") != null) {
            // 一定要在创建AlipayTradeService之前设置参数
            Configs.init("zfbinfo.properties");
            AlipayTradeService tradeService = new AlipayTradeServiceImpl.ClientBuilder().build();

            // (必填) 外部订单号，需要退款交易的商户外部订单号
            String outTradeNo = request.getParameter("outTradeNo");

            // (必填) 退款金额，该金额必须小于等于订单的支付金额，单位为元
            String refundAmount = request.getParameter("refundAmount");

            // (可选，需要支持重复退货时必填) 商户退款请求号，相同支付宝交易号下的不同退款请求号对应同一笔交易的不同退款申请，
            // 对于相同支付宝交易号下多笔相同商户退款请求号的退款交易，支付宝只会进行一次退款
            String outRequestNo = request.getParameter("outRequestNo");

            // (必填) 退款原因，可以说明用户退款原因，方便为商家后台提供统计
            String refundReason = request.getParameter("refundReason");

            // (必填) 商户门店编号，退款情况下可以为商家后台提供退款权限判定和统计等作用，详询支付宝技术支持
            String storeId = request.getParameter("storeId");

            AlipayTradeRefundRequestBuilder builder = new AlipayTradeRefundRequestBuilder()
                    .setOutTradeNo(outTradeNo)
                    .setRefundAmount(refundAmount)
                    .setRefundReason(refundReason)
                    .setOutRequestNo(outRequestNo)
                    .setStoreId(storeId);

            AlipayF2FRefundResult result = tradeService.tradeRefund(builder);
            switch (result.getTradeStatus()) {
                case SUCCESS:
                    log.info("支付宝退款成功: )");
                    break;

                case FAILED:
                    log.error("支付宝退款失败!!!");
                    break;

                case UNKNOWN:
                    log.error("系统异常，订单退款状态未知!!!");
                    break;

                default:
                    log.error("不支持的交易状态，交易返回异常!!!");
                    break;
            }
            out.println(result.getResponse().getBody());
            return;
        }
    }

    /**
     * 支付成功后的异步回调
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/notify_url")
    @ResponseBody
    public Map<String, String[]> notify_url(HttpServletRequest request) {
        System.out.println("-------------------------------");
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            System.out.println(entry.getKey() + "-------------" + entry.getValue()[0]);
        }
        return parameterMap;
    }
}

package com.gzuniversity.eduorder.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gzuniversity.eduorder.entity.TOrder;
import com.gzuniversity.eduorder.entity.TPayLog;
import com.gzuniversity.eduorder.mapper.TPayLogMapper;
import com.gzuniversity.eduorder.service.TOrderService;
import com.gzuniversity.eduorder.service.TPayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gzuniversity.eduorder.utils.HttpClient;
import com.gzuniversity.servicebase.handler.GuliException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-08-16
 */
@Service
public class TPayLogServiceImpl extends ServiceImpl<TPayLogMapper, TPayLog> implements TPayLogService {
    @Autowired
    private TOrderService tOrderService;

    @Override
    public Map createNative(String orderNo) {
/*        try {
            QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
            wrapper.eq("order_no",orderNo);
            TOrder order = tOrderService.getOne(wrapper);

            Map m = new HashMap();
            m.put("appid","wx74862e0dfcf69954");
            m.put("mch_id", "1558950191");
            m.put("nonce_str", WXPayUtil.generateNonceStr());
            m.put("body", order.getCourseTitle()); //课程标题
            m.put("out_trade_no", orderNo); //订单号
            m.put("total_fee", order.getTotalFee().multiply(new BigDecimal("100")).longValue()+"");
            m.put("spbill_create_ip", "127.0.0.1");
            m.put("notify_url", "http://guli.shop/api/order/weixinPay/weixinNotify\n");
            m.put("trade_type", "NATIVE");

            HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/unifiedorder");
            client.setXmlParam(WXPayUtil.generateSignedXml(m,"T6m9iK73b0kn9g5v426MKfHQH7X8rKwb"));
            client.setHttps(true);
            client.post();

            String xml = client.getContent();

            Map<String,String> resultMap = WXPayUtil.xmlToMap(xml);

            Map map = new HashMap();
            map.put("out_trade_no", orderNo);
            map.put("course_id", order.getCourseId());
            map.put("total_fee", order.getTotalFee());
            map.put("result_code", resultMap.get("result_code"));
            map.put("code_url", resultMap.get("code_url"));

            return map;
        }catch(Exception e) {
            throw new GuliException(20001,"生成二维码失败");
        }*/
        return null;
    }

    //查询订单支付状态
    @Override
    public Map<String, String> queryPayStatus(String orderNo) {
/*        try {
            Map m = new HashMap<>();
            m.put("appid", "wx74862e0dfcf69954");
            m.put("mch_id", "1558950191");
            m.put("out_trade_no", orderNo);
            m.put("nonce_str", WXPayUtil.generateNonceStr());

            HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/orderquery");
            client.setXmlParam(WXPayUtil.generateSignedXml(m,"T6m9iK73b0kn9g5v426MKfHQH7X8rKwb"));
            client.setHttps(true);
            client.post();

            String xml = client.getContent();
            Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);
            return resultMap;
        }catch(Exception e) {
            return null;
        }*/
        return null;
    }

    //添加支付记录和更新订单状态
    @Override
    public void updateOrdersStatus(Map<String, String> map) {
        String orderNo = map.get("out_trade_no");
        QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderNo);
        TOrder order = tOrderService.getOne(wrapper);
        if(order.getStatus().intValue() == 1) { return; }
        order.setStatus(1);//1代表已经支付
        tOrderService.updateById(order);

        TPayLog payLog = new TPayLog();
        payLog.setOrderNo(orderNo);
        payLog.setPayTime(new Date());
        payLog.setPayType(1);
        payLog.setTotalFee(order.getTotalFee());

        payLog.setTradeState(map.get("trade_state"));
        payLog.setTransactionId(map.get("transaction_id"));
        payLog.setAttr(JSONObject.toJSONString(map));

        baseMapper.insert(payLog);
    }
}

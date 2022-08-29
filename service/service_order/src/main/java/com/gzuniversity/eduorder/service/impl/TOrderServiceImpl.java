package com.gzuniversity.eduorder.service.impl;

import com.gzuniversity.commonutils.CourseWebVoOrder;
import com.gzuniversity.commonutils.UcenterMemberOrder;
import com.gzuniversity.eduorder.client.EduClient;
import com.gzuniversity.eduorder.client.UcenterClient;
import com.gzuniversity.eduorder.entity.TOrder;
import com.gzuniversity.eduorder.mapper.TOrderMapper;
import com.gzuniversity.eduorder.service.TOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gzuniversity.eduorder.utils.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-08-16
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {

    @Autowired
    public EduClient eduClient;

    @Autowired
    public UcenterClient ucenterClient;

    @Override
    public String createOrders(String courseId, String userIdByJwtToken) {
        CourseWebVoOrder courseWebVoOrder = eduClient.getCourseInfoOrder(courseId);
        UcenterMemberOrder ucenterMemberOrder = ucenterClient.getUcenterById(userIdByJwtToken);

        TOrder order = new TOrder();
        order.setOrderNo(OrderNoUtil.getOrderNo());
        order.setCourseId(courseId);
        order.setCourseTitle(courseWebVoOrder.getTitle());
        order.setCourseCover(courseWebVoOrder.getCover());
        order.setTeacherName("test");
        order.setTotalFee(courseWebVoOrder.getPrice());
        order.setMemberId(userIdByJwtToken);
        order.setMobile(ucenterMemberOrder.getMobile());
        order.setNickname(ucenterMemberOrder.getNickname());
        order.setStatus(0);
        order.setPayType(1);
        baseMapper.insert(order);
        return order.getOrderNo();
    }
}

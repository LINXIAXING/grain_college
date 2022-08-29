package com.gzuniversity.eduorder.client;

import com.gzuniversity.commonutils.UcenterMemberOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("service-ucenter")//调取的服务名
public interface UcenterClient {
    @GetMapping("/ucenterservice/ucenterMember/getUserInfoOrder/{memberId}")
    public UcenterMemberOrder getUcenterById(@PathVariable("memberId") String memberId);
}

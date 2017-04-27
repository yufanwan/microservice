package com.example;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by 42278 on 2017/4/10.
 */
@FeignClient("REVIEW")
public interface ReviewService {

    @RequestMapping(value = "/reviews",method = RequestMethod.GET)  //访问 review service的接口
    public String getReviews();

}

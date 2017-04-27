package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by 42278 on 2017/4/2.
 */
@RestController
@RefreshScope
public class ConfigClientTestController {

    //get config from config server
    @Value("${feature.x.enable}")
    String featureXEnable;



    @RequestMapping("/config")
    public String config(){
        return "Get config server featureXEnable:"+featureXEnable;
    }
}
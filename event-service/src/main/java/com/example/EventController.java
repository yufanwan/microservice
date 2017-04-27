package com.example;

import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * Created by 42278 on 2017/4/9.
 */
@RestController
public class EventController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;

    @Autowired
    private LoadBalancerClient loadBalance;

    @Autowired
    private ReviewService reviewService;

    @RequestMapping("/instance")
    public String getInfo(){
        List<String> services = client.getServices();
        StringBuffer info = new StringBuffer();
        for (String service : services) {
            List<ServiceInstance> instances = client.getInstances(service);
            for (ServiceInstance instance : instances){
                info.append(service+"[Host:"+instance.getHost()+"-Port:"+instance.getPort()+"]");
                info.append("<br/>");
            }
        }
        return info.toString();
    }


    @RequestMapping("/reviews")
    public String getReviews(){
        String result = getResultFromRemote("REVIEW");
        if (result == null || result.isEmpty()){
            result = "can not get reviews from review service";
        }
        return result;
    }

    private String getResultFromRemote(String service) {
        //------------------------------------------1  未用Ribbon实现客户端负载均衡的服务间调用-------------------------
//        List<ServiceInstance> instances = client.getInstances(service);
//        if (instances != null && instances.size()>0){
//            ServiceInstance instance = instances.get(0);
//            URI uri = instance.getUri();
//            if (uri !=null){
//                String originResult = (new RestTemplate()).getForObject(uri+"/reviews",String.class);
//                return String.format("%s [from %s:%s]",originResult,instance.getHost(),instance.getPort());
//            }
//        }
//        return "";

        //----------------------------------------- 1 ------------------------------------------------------------------
        //----------------------------------------  2 使用Ribbon实现客户端负载均衡的服务间调用--------------------------
//        ServiceInstance instance = loadBalance.choose(service);
//        if (instance == null){
//            return null;
//        }
//        URI uri = instance.getUri();
//        if (uri != null){
//            String originResult = (new RestTemplate()).getForObject(uri+"/reviews",String.class);
//            return String.format("%s [from %s:%s]",originResult,instance.getHost(),instance.getPort());
//        }
//        return "";
        //----------------------------------------- 2 ------------------------------------------------------------------
        //----------------------------------------  3 使用Feign实现客户端负载均衡的服务间调用---------------------------
        //Feign 要引入compile('org.springframework.cloud:spring-cloud-starter-feign')  并  @EnableFeignClients

        logger.info("Request review-service by Feign client.");

        try {
            return String.format("%s (by Feign client)",reviewService.getReviews());
        }catch (HystrixRuntimeException ex){
            return String.format("service is not available [%s]",ex.getLocalizedMessage());
        }

        //----------------------------------------- 3 ------------------------------------------------------------------

    }




}

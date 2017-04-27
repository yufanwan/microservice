package com.example;

import com.example.resource.HALReview;
import com.example.resource.HALReviews;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.hateoas.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.MediaTypes.HAL_JSON_VALUE;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created by 42278 on 2017/4/9.
 */
@RestController
public class ReviewController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;

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


    @GetMapping(path = "/",produces = {HAL_JSON_VALUE,APPLICATION_JSON_VALUE})
    public HttpEntity<ResourceSupport> root(){
        ResourceSupport resourceRoot = new ResourceSupport();
        resourceRoot.add(
                new Link(
                        new UriTemplate(
                                linkTo(ReviewController.class,"").slash("reviews").toString()+"{?eventId}"),"reviews" ));
        resourceRoot.add(
                new Link(
                        new UriTemplate(
                                linkTo(ReviewController.class,"").slash("reviews").toString()+"/{reviewId}"),"review" ));

        return new ResponseEntity<>(resourceRoot, HttpStatus.OK);
    }

    @RequestMapping("/reviews/{id}")
    public HttpEntity<HALReview> review(@PathVariable String id) {
        HALReview review = generateReview(id);
        review.add(
                linkTo(methodOn(ReviewController.class).review(id)).withRel("self"));
        return new ResponseEntity<>(review,HttpStatus.OK);
    }

    @RequestMapping("/reviews")
    public HttpEntity<HALReviews> reviews(@RequestParam("eventId") String eventId){
        logger.info("Request review-service - /reviews.");

        HALReviews halReviews =  new HALReviews();
        halReviews.add(
                linkTo(methodOn(ReviewController.class).reviews(eventId)).withRel("self"));

        halReviews.add("reviews",generateReviews(eventId));
        return new ResponseEntity<>(halReviews,HttpStatus.OK);
    }

    private List<HALReview> generateReviews(String eventId) {
        List<HALReview> reviews = new ArrayList<>();
        for (int i = 1; i < 4; i++){
            HALReview halReview = new HALReview(eventId, "" + i, "author " + i, "subject "+ i, "content " + i );
            halReview.add(
                    linkTo(methodOn(ReviewController.class).review(eventId)).withRel("self"));
            reviews.add(halReview);
        }
        return reviews;
    }

    private HALReview generateReview(String id) {
        return new HALReview(id, id, "author " + id, "subject "+ id, "content " + id );
    }


}

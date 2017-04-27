package com.example.controller;

import com.example.gateway.EventCompositeGateway;
import com.example.model.Event;
import com.example.resource.EventAggregated;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.UriTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.MediaTypes.HAL_JSON_VALUE;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class EventCompositeController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private EventCompositeGateway gateway;

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


    @GetMapping(path = "/",produces = {HAL_JSON_VALUE,APPLICATION_JSON_VALUE,APPLICATION_JSON_UTF8_VALUE})
    public HttpEntity<ResourceSupport> root(){
        ResourceSupport resourceRoot = new ResourceSupport();
        resourceRoot.add(
                new Link(
                        new UriTemplate(
                                linkTo(EventCompositeController.class,"").toString()+"/{eventId}"),"event" ));

        return new ResponseEntity<>(resourceRoot, HttpStatus.OK);
    }

    @GetMapping(path = "/{eventId}",produces = {HAL_JSON_VALUE,APPLICATION_JSON_VALUE,APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<EventAggregated> getEvent(@PathVariable String eventId){

        Event event = gateway.getEvent(eventId);
        List recommendations = gateway.getRecommendations(eventId);
        List reviews = gateway.getReviews(eventId);

        EventAggregated eventAggregated = new EventAggregated(event,recommendations,reviews);
        eventAggregated.add(
                new Link(
                        new UriTemplate(
                                linkTo(EventCompositeController.class,"").toString()),"self" ));

        return new ResponseEntity<>(eventAggregated, HttpStatus.OK);
    }
}

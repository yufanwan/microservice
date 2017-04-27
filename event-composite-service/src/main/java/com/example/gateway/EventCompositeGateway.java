package com.example.gateway;

import com.example.model.Event;
import com.example.model.Recommendation;
import com.example.model.Review;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by 42278 on 2017/4/11.
 */
@Component
public class EventCompositeGateway {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    RestOperations restTemplate;

    @HystrixCommand(fallbackMethod = "defaultEvent")
    public Event getEvent(String eventId) {
        return parseEvent(
                restTemplate.getForEntity(
                        loadBalancerClient.choose("EVENT").getUri()+"/events/"+eventId,String.class));
    }

    @HystrixCommand(fallbackMethod = "defaultRecommendations")
    public List getRecommendations(String eventId) {
        ResponseEntity<String> resultStr = null;
        try {
            resultStr = restTemplate.getForEntity(
                    loadBalancerClient.choose("RECOMMENDATION").getUri()+"/recommendations?eventId="+eventId,String.class);
        } catch (Exception e){}

        if (resultStr != null) {
            return parseRecommendations(resultStr);
        }
        return new ArrayList<>();
    }

    @HystrixCommand(fallbackMethod = "defaultReviews")
    public List getReviews(String eventId) {
        ResponseEntity<String> resultStr = null;
        try {
            resultStr = restTemplate.getForEntity(
                    loadBalancerClient.choose("REVIEW").getUri()+"/reviews?eventId="+eventId,String.class);
        } catch (Exception e){}

        if (resultStr != null) {
            return parseReviews(resultStr);
        }
        return new ArrayList<>();
    }

    private List parseReviews(ResponseEntity<String> resp) {
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        JsonNode node = null;
        try {
            node = objectMapper.readerFor(JsonNode.class).readValue(resp.getBody());
            JsonNode jsonNode = node.get("_embedded").get("reviews");
            return objectMapper.convertValue(jsonNode,List.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List parseRecommendations(ResponseEntity<String> resp) {
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        JsonNode node = null;
        try {
            node = objectMapper.readerFor(JsonNode.class).readValue(resp.getBody());
            JsonNode jsonNode = node.get("_embedded").get("recommendations");
            return objectMapper.convertValue(jsonNode,List.class);
        } catch (IOException e) {
           throw new RuntimeException(e);
        }

    }

    private Event parseEvent(ResponseEntity<String> resp) {
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        Event event = null;
        try {
            event = objectMapper.readerFor(Event.class).readValue(resp.getBody());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return event;
    }

    private Event defaultEvent(String eventId) {
        return new Event(eventId,"默认活动",0,"","默认活动",new Date(),new Date());
    }

    private List defaultRecommendations(String eventId) {
        List list = new ArrayList<Review>();
        list.add(new Review(eventId,"0","Fallback author","Fallback subject","Fallback content"));
        return list;
    }

    private List defaultReviews(String eventId) {
        List list = new ArrayList<Recommendation>();
        list.add(new Recommendation(eventId,"0","Fallback content"));
        return list;
    }


}

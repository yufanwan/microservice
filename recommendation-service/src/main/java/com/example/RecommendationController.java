package com.example;

import com.example.resource.HALRecommendation;
import com.example.resource.HALRecommendations;
import org.apache.log4j.Logger;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.UriTemplate;
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
public class RecommendationController {

    private final Logger logger = Logger.getLogger(getClass());

    @GetMapping(path = "/",produces = {HAL_JSON_VALUE,APPLICATION_JSON_VALUE})
    public HttpEntity<ResourceSupport> root(){
        ResourceSupport resourceRoot = new ResourceSupport();
        resourceRoot.add(
                new Link(
                        new UriTemplate(
                                linkTo(RecommendationController.class,"").slash("recommendations").toString()+"{?eventId}"),"recommendations" ));
        resourceRoot.add(
                new Link(
                        new UriTemplate(
                                linkTo(RecommendationController.class,"").slash("recommendations").toString()+"/{recommendationId}"),"recommendation" ));

        return new ResponseEntity<>(resourceRoot, HttpStatus.OK);
    }

    @RequestMapping("/recommendations/{id}")
    public HttpEntity<HALRecommendation> recommendation(@PathVariable String id) {
        HALRecommendation recommendation = generateRecommendation(id);
        recommendation.add(
                linkTo(methodOn(RecommendationController.class).recommendation(id)).withRel("self"));
        return new ResponseEntity<>(recommendation,HttpStatus.OK);
    }

    @RequestMapping("/recommendations")
    public HttpEntity<HALRecommendations> recommendations(@RequestParam("eventId") String eventId){
        logger.info("Request recommendation-service - /recommendations.");

        HALRecommendations halRecommendations =  new HALRecommendations();
        halRecommendations.add(
                linkTo(methodOn(RecommendationController.class).recommendations(eventId)).withRel("self"));

        halRecommendations.add("recommendations",generateRecommendations(eventId));
        return new ResponseEntity<>(halRecommendations,HttpStatus.OK);
    }

    private List<HALRecommendation> generateRecommendations(String eventId) {
        List<HALRecommendation> recommendations = new ArrayList<>();
        for (int i = 1; i < 4; i++){
            HALRecommendation halRecommendation = new HALRecommendation(eventId, "" + i, "content " + i );
            halRecommendation.add(
                    linkTo(methodOn(RecommendationController.class).recommendation(eventId)).withRel("self"));
            recommendations.add(halRecommendation);
        }
        return recommendations;
    }

    private HALRecommendation generateRecommendation(String id) {
        return new HALRecommendation(id, id, "content " + id );
    }


}

package com.example.controller;

import com.example.model.Enrollment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.UriTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.MediaTypes.HAL_JSON_VALUE;
import static org.springframework.hateoas.jaxrs.JaxRsLinkBuilder.linkTo;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

/**
 * Created by 42278 on 2017/4/22.
 */
@RestController
@RequestMapping("/")
public class EnrollController {

    private static final Logger LOG = LoggerFactory.getLogger(EnrollController.class);

//    private NotificationS

    @GetMapping(path = "/",produces = {HAL_JSON_VALUE,APPLICATION_JSON_VALUE,APPLICATION_JSON_UTF8_VALUE})
    public HttpEntity<ResourceSupport> root(){
        ResourceSupport resourceRoot = new ResourceSupport();
        resourceRoot.add(
                new Link(
                        new UriTemplate(
                                linkTo(EnrollController.class,"").slash("/enroll").toString()),"enroll" ));

        return new ResponseEntity<>(resourceRoot, HttpStatus.OK);
    }

    @PostMapping("/enroll")
    public String enroll(@RequestBody Enrollment enrollment) {
        LOG.info(enrollment.toString());
        return "enroll success";
    }

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().antMatchers("/enroll/*").authenticated();
        }
    }
}

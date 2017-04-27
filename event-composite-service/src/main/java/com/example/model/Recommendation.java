package com.example.model;

import org.springframework.hateoas.ResourceSupport;

/**
 * Created by 42278 on 2017/4/10.
 */
public class Recommendation extends ResourceSupport {

    private String eventId;

    private String recommendId;

    private String content;

    public Recommendation() { }

    public Recommendation(String eventId, String recommendId, String content) {
        this.eventId = eventId;
        this.recommendId = recommendId;
        this.content = content;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getRecommendId() {
        return recommendId;
    }

    public void setRecommendId(String recommendId) {
        this.recommendId = recommendId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

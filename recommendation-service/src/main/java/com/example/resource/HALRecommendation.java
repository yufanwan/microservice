package com.example.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by 42278 on 2017/4/10.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class HALRecommendation extends ResourceSupport {

    @JsonProperty("eventId")
    private String eventId;

    @JsonProperty("recommendId")
    private String recommendId;

    @JsonProperty("content")
    private String content;

    public HALRecommendation(String eventId, String recommendId, String content) {
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

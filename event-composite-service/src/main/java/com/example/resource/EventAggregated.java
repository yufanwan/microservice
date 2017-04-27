package com.example.resource;

import com.example.model.Event;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.hateoas.ResourceSupport;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 42278 on 2017/4/11.
 */
public class EventAggregated extends ResourceSupport {

    private String id;

    private String name;
    private Integer numberLimit;
    private String mainPhoto;
    private String introduction;

    private Date startAt;
    private Date endAt;

    @JsonProperty("_embedded")
    private Map<String, Object> embedded;

    public EventAggregated(Event event, List recommendations, List reviews) {
        this.id = event.getId();
        this.name = event.getName();
        this.numberLimit = event.getNumberLimit();
        this.mainPhoto = event.getMainPhoto();
        this.introduction = event.getIntroduction();
        this.startAt = event.getStartAt();
        this.endAt = event.getEndAt();
        this.embedded = new HashedMap();
        this.embedded.put("recommendations",recommendations);
        this.embedded.put("reviews",reviews);
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberLimit() {
        return numberLimit;
    }

    public void setNumberLimit(Integer numberLimit) {
        this.numberLimit = numberLimit;
    }

    public String getMainPhoto() {
        return mainPhoto;
    }

    public void setMainPhoto(String mainPhoto) {
        this.mainPhoto = mainPhoto;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    public Map<String, Object> getEmbedded() {
        return embedded;
    }

    public void setEmbedded(Map<String, Object> embedded) {
        this.embedded = embedded;
    }
}

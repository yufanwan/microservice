package com.example.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by 42278 on 2017/4/10.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class HALReview extends ResourceSupport {

    @JsonProperty("eventId")
    private String eventId;

    @JsonProperty("reviewId")
    private String reviewId;

    @JsonProperty("author")
    private String author;

    @JsonProperty("subject")
    private String subject;

    @JsonProperty("content")
    private String content;

    public HALReview(String eventId, String reviewId, String author, String subject, String content) {
        this.eventId = eventId;
        this.reviewId = reviewId;
        this.author = author;
        this.subject = subject;
        this.content = content;
    }


    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

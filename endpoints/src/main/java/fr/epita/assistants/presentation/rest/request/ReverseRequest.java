package fr.epita.assistants.presentation.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReverseRequest {
    @JsonProperty(required = false)
    private String content;

    public ReverseRequest() {
        this(null);
    }

    public ReverseRequest(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }
}


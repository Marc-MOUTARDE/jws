package fr.epita.assistants.presentation.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HelloResponse {
    @JsonProperty
    private String content;

    public HelloResponse(String content) {
        this.content = content;
    }
}

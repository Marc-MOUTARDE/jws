package fr.epita.assistants.presentation.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReverseResponse {
    @JsonProperty
    private String original;
    @JsonProperty
    private String reversed;

    public ReverseResponse(String original) {
        this.original = original;
        this.reversed = new StringBuilder(original).reverse().toString();
    }
}

package fr.epita.assistants.presentation.rest.request;

public class ReverseRequest {
    private String content;

    public ReverseRequest(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }
}


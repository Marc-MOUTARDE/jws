package fr.epita.assistants.presentation.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import fr.epita.assistants.presentation.rest.request.ReverseRequest;
import fr.epita.assistants.presentation.rest.response.ReverseResponse;

@Path("/")
public class Endpoints {
    @Path("/hello/{name}")
    @GET
    public String helloWorld(String name) {
        return "{\"content\": \"hello " + name + "\"}";
    }

    @Path("/reverse")
    @POST
    public ReverseResponse reverse(ReverseRequest request) {
        System.out.println(request.getContent());
        return new ReverseResponse(request.getContent());
    }
}


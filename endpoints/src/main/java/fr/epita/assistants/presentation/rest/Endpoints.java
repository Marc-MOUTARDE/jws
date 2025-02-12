package fr.epita.assistants.presentation.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;

import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.RestResponse.ResponseBuilder;

import fr.epita.assistants.presentation.rest.request.ReverseRequest;
import fr.epita.assistants.presentation.rest.response.ReverseResponse;
import fr.epita.assistants.presentation.rest.response.HelloResponse;

@Path("/")
public class Endpoints {
    @Path("/hello/{name}")
    @GET
    public HelloResponse helloWorld(String name) {
        return new HelloResponse("hello " + name);
    }

    @Path("/reverse")
    @POST
    public RestResponse<ReverseResponse> reverse(ReverseRequest request) {
        if (request == null || request.getContent() == null || request.getContent().length() == 0) {
            throw new BadRequestException();
        } else {
            return ResponseBuilder.ok(new ReverseResponse(request.getContent())).build();
        }
    }
}


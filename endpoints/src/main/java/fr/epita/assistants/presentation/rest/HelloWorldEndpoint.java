package fr.epita.assistants.presentation.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class HelloWorldEndpoint {
    @Path("/")
    @GET
    public String helloWorld() {
        return "Hello World!";
    }
}

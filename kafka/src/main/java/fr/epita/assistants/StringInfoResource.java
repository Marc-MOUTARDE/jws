package fr.epita.assistants;

import io.smallrye.reactive.messaging.annotations.Broadcast;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import jakarta.inject.Inject;

@Path("/send")
public class StringInfoResource {
    @Inject
    @Broadcast
    @Channel("string-info-command")
    Emitter<String> emitter;

    @POST
    public void send(String s) {
        emitter.send(s);
    }
}

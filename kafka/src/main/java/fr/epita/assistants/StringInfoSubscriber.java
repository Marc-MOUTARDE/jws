package fr.epita.assistants;

import fr.epita.assistants.utils.StringInfo;
import io.smallrye.reactive.messaging.annotations.Broadcast;
import org.eclipse.microprofile.reactive.messaging.Incoming;

public class StringInfoSubscriber {

    @Broadcast
    @Incoming("string-info-aggregate")
    public void consume(StringInfo info) {
        System.out.println(info.toString());
    }
}

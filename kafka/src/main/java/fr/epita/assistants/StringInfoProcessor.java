package fr.epita.assistants;

import fr.epita.assistants.utils.StringInfo;
import io.smallrye.reactive.messaging.annotations.Broadcast;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import jakarta.inject.Inject;

public class StringInfoProcessor {

    @Inject
    @Broadcast
    @Channel("string-info-aggregate")
    Emitter<StringInfo> emitter;

    @Incoming("string-info-command")
    public void consume(String s) {
        int vowels = 0;
        int cosonants = 0;
        boolean palindrome = true;

        String tmp = s.toLowerCase();

        for (int i = 0; i < s.length(); ++i) {
            if (tmp.charAt(i) == 'a' || tmp.charAt(i) == 'e' || tmp.charAt(i) == 'i' || tmp.charAt(i) == 'o' || tmp.charAt(i) == 'u' || tmp.charAt(i) == 'y') {
                vowels++;
            } else {
                if ((tmp.charAt(i) >= '0' && tmp.charAt(i) <= '9')) {
                    continue;
                }
                if (tmp.charAt(i) != ' ')
                    cosonants++;
            }
        }

        for (int i = 0; i < s.length() / 2; ++i ) {
            if (s.charAt(i) != s.charAt(s.length() - 1 -i)) {
                palindrome = false;
                break;
            }
        }

        emitter.send(new StringInfo(s, vowels, cosonants, palindrome));
    }
}

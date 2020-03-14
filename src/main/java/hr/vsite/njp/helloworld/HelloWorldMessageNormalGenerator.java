package hr.vsite.njp.helloworld;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("NORMAL")
@Profile("NORMAL")
public class HelloWorldMessageNormalGenerator implements HelloWorldMessageGenerator {
    @Override
    public String generate(String name) {
        return "Hello " + name + "!";
    }
}

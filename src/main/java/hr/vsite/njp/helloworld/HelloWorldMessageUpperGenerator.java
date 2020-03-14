package hr.vsite.njp.helloworld;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
//@Primary
//@Qualifier("UPPER")
@Profile("UPPER")
public class HelloWorldMessageUpperGenerator implements HelloWorldMessageGenerator {
    @Override
    public String generate(String name) {
        return "Hello " + name.toUpperCase() + "!";
    }
}

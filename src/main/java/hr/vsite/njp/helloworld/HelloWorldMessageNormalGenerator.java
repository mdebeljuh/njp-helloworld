package hr.vsite.njp.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
//@Qualifier("NORMAL")
@Profile("NORMAL")
public class HelloWorldMessageNormalGenerator implements HelloWorldMessageGenerator {

    private int counter = 0;

    @Autowired
    private Writer writer;

    @Autowired
    private ApplicationConfiguration configuration;


    public HelloWorldMessageNormalGenerator() {

    }

    @PostConstruct
    public void postInit() {
        writer.print("Konstruktor");
    }

    @Override
    public String generate(String name) {
        return "Hello " + name + "!" + "{" + ++counter + "}" + configuration.getMessage();
    }
}

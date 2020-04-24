package hr.vsite.njp.helloworld.domain;

import hr.vsite.njp.helloworld.configuration.ApplicationConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("TEMPLATE")
public class HelloWorldTemplateMessageGenerator implements HelloWorldMessageGenerator {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldTemplateMessageGenerator.class);


    private final ApplicationConfiguration applicationConfiguration;
    private final NameOperation nameOperation;


    @Autowired
    public HelloWorldTemplateMessageGenerator(ApplicationConfiguration applicationConfiguration, NameOperation nameOperation) {
        this.applicationConfiguration = applicationConfiguration;
        this.nameOperation = nameOperation;
    }

    @Override
    public String generate(String name) {
        LOGGER.trace("generate entry: {}", name);
        String message = String.format(applicationConfiguration.getHelloWorldTemplate(), nameOperation.change(name));
        LOGGER.trace("generate exit: {}", message);
        return message;
    }
}

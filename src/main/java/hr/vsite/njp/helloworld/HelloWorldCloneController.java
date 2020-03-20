package hr.vsite.njp.helloworld;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldCloneController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldCloneController.class);

    private final HelloWorldMessageGenerator messageGenerator;

    @Autowired
    public HelloWorldCloneController(
            HelloWorldMessageGenerator messageGenerator
    ) {
        this.messageGenerator = messageGenerator;
    }

    @GetMapping("/helloworld2/{name}")
    public HelloWorld helloWorld(@PathVariable String name) {
        LOGGER.trace("hello world start - {}", name);
        HelloWorld helloWorld = new HelloWorld(1L, messageGenerator.generate(name));
        LOGGER.trace("hello world end - {}", helloWorld);
        return helloWorld;
    }
}

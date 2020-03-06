package hr.vsite.njp.helloworld;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldController.class);

    @GetMapping("/helloworld/{name}")
    public HelloWorld helloWorld(@PathVariable String name) {
        LOGGER.trace("hello world start - {}", name);
        HelloWorld helloWorld = new HelloWorld(1L, "Hello " + name);
        LOGGER.trace("hello world end - {}", helloWorld);
        return helloWorld;
    }
}

package hr.vsite.njp.helloworld;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloWorldController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldController.class);

//    @Autowired
//    private HelloWorldMessageGenerator messageGenerator;

    private final HelloWorldMessageGenerator messageGenerator;


//    public HelloWorldController() {
//        messageGenerator = null;
//    }

     @Autowired
    public HelloWorldController(
//            @Qualifier("UPPER")
            HelloWorldMessageGenerator messageGenerator
    ) {
        this.messageGenerator = messageGenerator;
    }

    @GetMapping("/helloworld/{name}")
    public HelloWorld helloWorld(@PathVariable String name) {
        LOGGER.trace("hello world start - {}", name);
        HelloWorld helloWorld = new HelloWorld(1L, messageGenerator.generate(name));
        LOGGER.trace("hello world end - {}", helloWorld);
        return helloWorld;
    }
}
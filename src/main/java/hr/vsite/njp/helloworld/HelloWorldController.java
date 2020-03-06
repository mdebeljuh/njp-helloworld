package hr.vsite.njp.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/helloworld/{name}")
    public HelloWorld helloWorld(@PathVariable String name) {
        return new HelloWorld(1L, "Hello " + name);
    }
}

package hr.vsite.njp.helloworld.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "my-app-conf")
public class ApplicationConfiguration {
    private String message;
    private String helloWorldTemplate = "Hello %s!";

    public String getHelloWorldTemplate() {
        return helloWorldTemplate;
    }

    public ApplicationConfiguration setHelloWorldTemplate(String helloWorldTemplate) {
        this.helloWorldTemplate = helloWorldTemplate;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ApplicationConfiguration setMessage(String message) {
        this.message = message;
        return this;
    }
}

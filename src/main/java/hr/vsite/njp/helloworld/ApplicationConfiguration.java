package hr.vsite.njp.helloworld;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="my-app-conf")
public class ApplicationConfiguration {
    private String message;

    public String getMessage() {
        return message;
    }

    public ApplicationConfiguration setMessage(String message) {
        this.message = message;
        return this;
    }
}

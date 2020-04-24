package hr.vsite.njp.helloworld.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Slf4jWriter implements Writer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Slf4jWriter.class);

    @Override
    public void print(String message) {
        LOGGER.trace(message);
    }
}

package hr.vsite.njp.helloworld.domain;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("NORMAL")
@Profile("NORMAL")
public class NormalNameOperation implements NameOperation{
    @Override
    public String change(String name) {
        return name;
    }
}

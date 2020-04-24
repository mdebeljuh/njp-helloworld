package hr.vsite.njp.helloworld.configuration;


import hr.vsite.njp.helloworld.domain.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@org.springframework.context.annotation.Configuration
public class BeanConfiguration {


    @Bean
//@Primary
//@Qualifier("UPPER")
    @Profile("UPPER")
    public NameOperation helloWorldMessageNormalGenerator() {
        return new UpperNameOperation();
    }
}

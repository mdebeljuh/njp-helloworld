package hr.vsite.njp.helloworld;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@org.springframework.context.annotation.Configuration
public class BeanConfiguration {


    @Bean
//@Primary
//@Qualifier("UPPER")
    @Profile("UPPER")
    public HelloWorldMessageGenerator helloWorldMessageNormalGenerator(Writer writer) {
        return new HelloWorldMessageUpperGenerator(writer);
    }
}

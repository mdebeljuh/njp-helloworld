package hr.vsite.njp.helloworld.configuration;


import hr.vsite.njp.helloworld.domain.NameOperation;
import hr.vsite.njp.helloworld.domain.UpperNameOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories //ovdje prebačeno jer se inače ne može testirati samo
// rest controlleri sa @WebMvcTest
public class BeanConfiguration {


    @Bean
//@Primary
//@Qualifier("UPPER")
    @Profile("UPPER")
    public NameOperation helloWorldMessageNormalGenerator() {
        return new UpperNameOperation();
    }
}

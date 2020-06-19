package hr.vsite.njp;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories //ovdje prebačeno jer se inače ne može testirati samo
// rest controlleri sa @WebMvcTest
public class JPAConfiguration {

}

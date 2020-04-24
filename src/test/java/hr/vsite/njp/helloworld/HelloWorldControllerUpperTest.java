package hr.vsite.njp.helloworld;

import hr.vsite.njp.helloworld.infrastructure.rest.HelloWorldDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(
        "UPPER"
)
class HelloWorldControllerUpperTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void helloWorld() {
        HelloWorldDTO helloWorld = testRestTemplate.getForObject("/helloworld/java", HelloWorldDTO.class);
        Assertions.assertThat(helloWorld).isNotNull();
        Assertions.assertThat(helloWorld.getMessage()).isNotNull().isEqualTo("Hello JAVA!");
//        Assertions.assertThat(helloWorld).extracting(e->e.getMessage()).isNotNull().isEqualTo("Hello JAVA!");

    }
}
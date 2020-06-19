package hr.vsite.njp.helloworld;

import hr.vsite.njp.helloworld.domain.HelloWorldMessageGenerator;
import hr.vsite.njp.helloworld.infrastructure.rest.HelloWorldController;
import hr.vsite.njp.proverbs.domain.ProverbsManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles(
        "UPPER"
)
@ExtendWith(value = SpringExtension.class)
@WebMvcTest(HelloWorldController.class)
class HelloWorldControllerUpperMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HelloWorldMessageGenerator helloWorldMessageGenerator;

//    @MockBean
//    private ProverbsManager proverbsManager;

    @BeforeEach
    void setUp() {
        Mockito.when(helloWorldMessageGenerator.generate(Mockito.anyString())).thenReturn("Hello!");
    }

    @Test
    void helloWorld() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/helloworld/{name}", "java"))
                .andDo(MockMvcResultHandlers.log()) //samo za logiranje
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful()) //http statusu 200-299
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("Hello!"));
        Mockito.verify(helloWorldMessageGenerator, Mockito.times(1)).generate("java");
        Mockito.verify(helloWorldMessageGenerator, Mockito.times(0)).generate("JAVA");
//        Assertions.assertThat(helloWorld).extracting(e->e.getMessage()).isNotNull().isEqualTo("Hello JAVA!");
    }

    @Test
    void helloWorld_empty() throws Exception {
        //"/helloworld/java" -> /helloworld/
        mockMvc.perform(MockMvcRequestBuilders.get("/helloworld/{name}", ""))
                .andDo(MockMvcResultHandlers.log()) //samo za logiranje
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
//        Assertions.assertThat(helloWorld).extracting(e->e.getMessage()).isNotNull().isEqualTo("Hello JAVA!");
    }
}
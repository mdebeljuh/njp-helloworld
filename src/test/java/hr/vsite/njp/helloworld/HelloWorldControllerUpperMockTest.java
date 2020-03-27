package hr.vsite.njp.helloworld;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles(
        "UPPER"
)
@AutoConfigureMockMvc
class HelloWorldControllerUpperMockTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void helloWorld() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/helloworld/{name}", "java"))
                .andDo(MockMvcResultHandlers.log()) //samo za logiranje
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful()) //http statusu 200-299
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("Hello JAVA!"));
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
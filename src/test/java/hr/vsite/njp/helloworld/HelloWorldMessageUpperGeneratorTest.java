package hr.vsite.njp.helloworld;

import hr.vsite.njp.helloworld.configuration.ApplicationConfiguration;
import hr.vsite.njp.helloworld.domain.HelloWorldTemplateMessageGenerator;
import hr.vsite.njp.helloworld.domain.UpperNameOperation;
import hr.vsite.njp.helloworld.domain.Writer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class HelloWorldMessageUpperGeneratorTest {

    private HelloWorldTemplateMessageGenerator generator;

    @BeforeEach
    void setUp() {
        //prepare
//        Slf4jWriter writer = new Slf4jWriter();
//        Writer writer = new WriterMock(true);
        Writer writer = Mockito.mock(Writer.class);
//        Mockito.doThrow(RuntimeException.class).when(writer).print(Mockito.anyString());
//        Mockito.doThrow(RuntimeException.class).when(writer).print("Hello JAVA!");
        UpperNameOperation nameOperation = new UpperNameOperation();
        generator = new HelloWorldTemplateMessageGenerator(new ApplicationConfiguration(), nameOperation);
    }

    @Test
    void generateUpperJAVA() {
        //execute
        String message = generator.generate("JAVA");
        //test
        Assertions.assertThat(message).isEqualTo("Hello JAVA!");
    }

    @Test
    void generateUpper_java() {
        //execute
        String message = generator.generate("java");
        //test
        Assertions.assertThat(message).isEqualTo("Hello JAVA!");
    }

    @Test
    void generateUpper_empty() {
        //execute
        String message = generator.generate("");
        //test
        Assertions.assertThat(message).isEqualTo("Hello !");
    }

    @Test
    void generateUpper_null() {
        //execute
        String message = generator.generate(null);
        //test
        Assertions.assertThat(message).isEqualTo("Hello !");
    }
}
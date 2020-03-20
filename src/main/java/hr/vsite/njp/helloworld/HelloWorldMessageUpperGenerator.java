package hr.vsite.njp.helloworld;

//@Component
//@Primary
//@Qualifier("UPPER")
//@Profile("UPPER")
public class HelloWorldMessageUpperGenerator implements HelloWorldMessageGenerator {

    private final Writer writer;

    public HelloWorldMessageUpperGenerator(Writer writer) {
        this.writer = writer;
    }

    @Override
    public String generate(String name) {
        return "Hello " + name.toUpperCase() + "!";
    }
}

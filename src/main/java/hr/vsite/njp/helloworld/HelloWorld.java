package hr.vsite.njp.helloworld;

public class HelloWorld {
    private final Long id;
    private final String message;

    public HelloWorld(Long id, String message) {
        this.id = id;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}

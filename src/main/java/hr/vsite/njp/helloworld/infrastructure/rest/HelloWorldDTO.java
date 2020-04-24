package hr.vsite.njp.helloworld.infrastructure.rest;

public class HelloWorldDTO {
    private final Long id;
    private final String message;

    public HelloWorldDTO(Long id, String message) {
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

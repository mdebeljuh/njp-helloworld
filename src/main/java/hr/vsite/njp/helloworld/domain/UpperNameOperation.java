package hr.vsite.njp.helloworld.domain;

public class UpperNameOperation implements NameOperation{
    @Override
    public String change(String name) {
        return (name != null ? name.toUpperCase() : "");
    }
}

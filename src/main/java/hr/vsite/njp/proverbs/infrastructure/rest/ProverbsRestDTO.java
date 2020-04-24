package hr.vsite.njp.proverbs.infrastructure.rest;

public class ProverbsRestDTO {
    private Long id;
    private String proverb;

    public Long getId() {
        return id;
    }

    public ProverbsRestDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getProverb() {
        return proverb;
    }

    public ProverbsRestDTO setProverb(String proverb) {
        this.proverb = proverb;
        return this;
    }
}

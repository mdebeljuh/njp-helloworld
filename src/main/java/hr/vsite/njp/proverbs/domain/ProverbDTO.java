package hr.vsite.njp.proverbs.domain;

public class ProverbDTO {
    private Long id;
    private String proverb;

    public ProverbDTO(Long id, String proverb) {
        this.id = id;
        this.proverb = proverb;
    }

    public Long getId() {
        return id;
    }

    public ProverbDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getProverb() {
        return proverb;
    }

    public ProverbDTO setProverb(String proverb) {
        this.proverb = proverb;
        return this;
    }

}

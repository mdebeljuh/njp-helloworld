package hr.vsite.njp.proverbs.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
//@Table(name="adsd")
public class Proverb {
    @Id
    @GeneratedValue
    private Long id;
    @Column(length = 2000)
    private String proverb;


    public Long getId() {
        return id;
    }

    public Proverb setId(Long id) {
        this.id = id;
        return this;
    }

    public String getProverb() {
        return proverb;
    }

    public Proverb setProverb(String proverb) {
        this.proverb = proverb;
        return this;
    }
}

package hr.vsite.njp.proverbs.domain;

import hr.vsite.njp.GenId;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
//@Table(name="adsd")
public class Proverb {
    @Id
    @GenericGenerator(name = "genId", strategy = GenId.CLASS_NAME)
    @GeneratedValue(generator = "genId")
//    @GeneratedValue
    private Long id;
    @Column(length = 2000)
    @NotNull
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

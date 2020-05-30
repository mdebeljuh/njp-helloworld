package hr.vsite.njp.proverbs.domain;

import java.util.List;
import java.util.Optional;

public interface CustomProverbsRepository {
    Long randomId();

    Optional<Proverb> randomProverb(Integer i);

    List<Proverb> findCustomBy(String text, Integer type);
}

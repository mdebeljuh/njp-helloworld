package hr.vsite.njp.proverbs.domain;

import java.util.List;
import java.util.Optional;

public interface ProverbsManager {
    List<ProverbDTO> findAll();

    List<ProverbDTO> findCustomBy(String text, Integer type);

    Optional<ProverbDTO> findOne(Long id);

    void save(ProverbDTO proverb) throws Exception;

    void delete(Long id);

    Optional<ProverbDTO> random();
    Optional<ProverbDTO> random(Integer i);
}

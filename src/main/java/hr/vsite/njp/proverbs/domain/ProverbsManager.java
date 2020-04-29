package hr.vsite.njp.proverbs.domain;

import java.util.List;
import java.util.Optional;

public interface ProverbsManager {
    List<ProverbDTO> findAll();

    Optional<ProverbDTO> findOne(Long id);

    Optional<ProverbDTO> random();
}

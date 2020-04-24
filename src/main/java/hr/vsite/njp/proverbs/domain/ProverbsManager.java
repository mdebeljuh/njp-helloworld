package hr.vsite.njp.proverbs.domain;

import java.util.List;

public interface ProverbsManager {
    List<ProverbDTO> findAll();

    ProverbDTO findOne(Long id);

    ProverbDTO random();
}

package hr.vsite.njp.proverbs.domain;

import java.util.List;

public interface ProverbsRepository {

    ProverbDTO findById(Long Id);

    List<ProverbDTO> findAll();

    int count();

}

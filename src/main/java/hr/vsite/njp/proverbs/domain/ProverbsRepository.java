package hr.vsite.njp.proverbs.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProverbsRepository extends
        JpaRepository<Proverb, Long>,
        CustomProverbsRepository {


    Optional<Proverb> findByProverbContainsOrIdGreaterThan(String text, Long id);

//    ProverbDTO findById(Long Id);
//
//    List<ProverbDTO> findAll();
//
//    int count();

}

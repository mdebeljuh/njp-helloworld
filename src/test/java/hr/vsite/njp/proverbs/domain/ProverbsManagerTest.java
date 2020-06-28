package hr.vsite.njp.proverbs.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles(
        "NORMAL"
)
class ProverbsManagerTest {

    @Autowired
    private ProverbsManager proverbsManager;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    void setUp() throws Exception {
        Proverb proverbDTO = new Proverb();
        proverbDTO.setId(1L);
        proverbDTO.setProverb("Proverb");
        entityManager.merge(proverbDTO);
        entityManager.flush();
        entityManager.clear();
//        proverbsManager.save(proverbDTO);
    }

    @Test
    @Transactional
    void findOne() {
        Optional<ProverbDTO> proverbDTO = proverbsManager.findOne(1L);
        Assertions.assertThat(proverbDTO).isPresent();
        Assertions.assertThat(proverbDTO.get().getId()).isEqualTo(1L);
        Assertions.assertThat(proverbDTO.get().getProverb()).isEqualTo("Proverb");
    }

    @Test
    @Transactional
    void findOneEmpty() {
        Optional<ProverbDTO> proverbDTO = proverbsManager.findOne(12L);
        Assertions.assertThat(proverbDTO).isEmpty();
    }

    @Test
    @Transactional
    void testSaveWithException()  {
        ProverbDTO proverbDTO = new ProverbDTO();
        proverbDTO.setId(3L);
        proverbDTO.setProverb("proverb");
        org.junit.jupiter.api.Assertions.assertThrows(Exception.class,
                ()->proverbsManager.save(proverbDTO)
        );

    }

    @Test
    @Transactional
    void testSaveWithOutException() throws Exception {
        ProverbDTO proverbDTO = new ProverbDTO();
        proverbDTO.setId(1L);
        proverbDTO.setProverb("proverb");
        proverbsManager.save(proverbDTO);

        entityManager.flush();
        entityManager.clear();

        Optional<ProverbDTO> proverbSaved = proverbsManager.findOne(1L);

        Assertions.assertThat(proverbSaved).isPresent();
        Assertions.assertThat(proverbSaved.get().getId()).isEqualTo(1L);
        Assertions.assertThat(proverbSaved.get().getProverb()).isEqualTo("also changed");

    }

}
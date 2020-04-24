package hr.vsite.njp.proverbs.infrastructure.db;

import hr.vsite.njp.proverbs.domain.ProverbDTO;
import hr.vsite.njp.proverbs.domain.ProverbsRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProverbsInMemoryRepository implements ProverbsRepository {
    private final List<String> proverbs;

    public ProverbsInMemoryRepository() {
        this.proverbs = new ArrayList<>();
        this.proverbs.add("aaaa1");
        this.proverbs.add("aaaa2");
        this.proverbs.add("aaaa3");
        this.proverbs.add("aaaa4");
        this.proverbs.add("aaaa5");
    }

    @Override
    public ProverbDTO findById(Long id) {
        String proverb = proverbs.get(id.intValue());
        return new ProverbDTO(id, proverb);
    }

    @Override
    public List<ProverbDTO> findAll() {
        List<ProverbDTO> proverbDTOS = new ArrayList<>();
        for (int i = 0; i < proverbs.size(); i++) {
            proverbDTOS.add(new ProverbDTO((long) i, proverbs.get(i)));
        }
        return proverbDTOS;
    }

    @Override
    public int count() {
        return proverbs.size();
    }
}

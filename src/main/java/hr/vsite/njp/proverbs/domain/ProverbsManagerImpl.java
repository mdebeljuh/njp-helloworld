package hr.vsite.njp.proverbs.domain;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProverbsManagerImpl implements ProverbsManager {

    private final ProverbsRepository proverbsRepository;

    public ProverbsManagerImpl(ProverbsRepository proverbsRepository) {
        this.proverbsRepository = proverbsRepository;
    }

    @Override
    public List<ProverbDTO> findAll() {
//        return proverbsRepository.findAll();
        return null;
    }

    @Override
    public Optional<ProverbDTO> findOne(Long id) {
        proverbsRepository.findByProverbContainsOrIdGreaterThan("aa", (long) 12);
        Optional<Proverb> proverb = proverbsRepository.findById(id);
        Optional<ProverbDTO> proverbDTO = proverb.map(prov -> new ProverbDTO(prov.getId(), prov.getProverb()));
        return proverbDTO;
//        return proverbsRepository.findById(id)
//                .map(p->new ProverbDTO(p.getId(), p.getProverb()))
//                .orElse(null);
    }

    @Override
    public Optional<ProverbDTO> random() {
//        int count = proverbsRepository.count();
//        int id = new Random().nextInt(count);
//        return proverbsRepository.findById((long) id);
        return null;
    }
}

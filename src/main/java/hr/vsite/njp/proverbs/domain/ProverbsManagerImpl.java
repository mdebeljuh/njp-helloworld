package hr.vsite.njp.proverbs.domain;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProverbsManagerImpl implements ProverbsManager {

    private final ProverbsRepository proverbsRepository;

    public ProverbsManagerImpl(ProverbsRepository proverbsRepository) {
        this.proverbsRepository = proverbsRepository;
    }

    @Override
    public List<ProverbDTO> findAll() {
        Iterable<Proverb> proverbs = proverbsRepository.findAll();
        Iterator<Proverb> proverbsIterator = proverbs.iterator();
        List<ProverbDTO> proverbDTOS = new LinkedList<>();

        for (Proverb proverb : proverbs) {
//        while (proverbs.hasNext()){
//            Proverb proverb = proverbs.next();
            ProverbDTO proverbDTO = new ProverbDTO(proverb.getId(), proverb.getProverb());
            proverbDTOS.add(proverbDTO);
        }
        return proverbDTOS;
//        return null;
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
    public void save(ProverbDTO proverbDto) {
        Proverb proverb = new Proverb();
        proverb.setId(proverbDto.getId());
        proverb.setProverb(proverbDto.getProverb());
        proverbsRepository.save(proverb);
    }

    @Override
    public void delete(Long id) {
        proverbsRepository.deleteById(id);
    }

    @Override
    public Optional<ProverbDTO> random() {
        Long id = proverbsRepository.randomId();
        return proverbsRepository.findById( id)
                .map(prov -> new ProverbDTO(prov.getId(), prov.getProverb()));

       // "SELECT id FROM proverb " +
       //         "OFFSET floor(random()*(select count(*) from proverb)) LIMIT 1";
    }
}

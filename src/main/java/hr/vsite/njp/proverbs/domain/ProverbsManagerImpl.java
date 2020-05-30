package hr.vsite.njp.proverbs.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProverbsManagerImpl implements ProverbsManager {
    private final static Logger LOGGER = LoggerFactory.getLogger(ProverbsManagerImpl.class);

    private final ProverbsRepository proverbsRepository;
    private final ProverbMapper proverbMapper;

    public ProverbsManagerImpl(ProverbsRepository proverbsRepository, ProverbMapper proverbMapper) {
        this.proverbsRepository = proverbsRepository;
        this.proverbMapper = proverbMapper;
    }

    @Override
    public List<ProverbDTO> findAll() {
        return proverbMapper.toProverbDTO(proverbsRepository
                .findAll());
        /*Iterable<Proverb> proverbs = proverbsRepository.findAll();
        Iterator<Proverb> proverbsIterator = proverbs.iterator();
        List<ProverbDTO> proverbDTOS = new LinkedList<>();

        for (Proverb proverb : proverbs) {
//        while (proverbs.hasNext()){
//            Proverb proverb = proverbs.next();
            ProverbDTO proverbDTO = new ProverbDTO(proverb.getId(), proverb.getProverb());
            proverbDTOS.add(proverbDTO);
        }
        return proverbDTOS;
//        return null;*/
    }

    @Override
    public Optional<ProverbDTO> findOne(Long id) {
        proverbsRepository.findByProverbContainsOrIdGreaterThan("aa", (long) 12);
        return proverbsRepository.findById(id).map(proverbMapper::toProverbDTO);

//        Optional<Proverb> proverb = proverbsRepository.findById(id);
//        Optional<ProverbDTO> proverbDTO = proverb.map(prov -> new ProverbDTO(prov.getId(), prov.getProverb()));
//        return proverbDTO;
//        return proverbsRepository.findById(id)
//                .map(p->new ProverbDTO(p.getId(), p.getProverb()))
//                .orElse(null);
    }

    @Override
    public void save(ProverbDTO proverbDto) {
//        Proverb proverb = new Proverb();
//        proverb.setId(proverbDto.getId());
//        proverb.setProverb(proverbDto.getProverb());
        proverbsRepository.save(proverbMapper.fromProverbDTO(proverbDto));
    }

    @Override
    public void delete(Long id) {
        proverbsRepository.deleteById(id);
    }

    @Override
    public Optional<ProverbDTO> random() {
        Long id = proverbsRepository.randomId();
        return proverbsRepository.findById(id).map(proverbMapper::toProverbDTO);

        // "SELECT id FROM proverb " +
        //         "OFFSET floor(random()*(select count(*) from proverb)) LIMIT 1";
    }

    @Override
    public Optional<ProverbDTO> random(Integer i) {
        return proverbsRepository.randomProverb(i).map(proverbMapper::toProverbDTO);
    }

    @Override
    public List<ProverbDTO> findCustomBy(String text, Integer type) {
        final Long start = System.currentTimeMillis();
        List<ProverbDTO> proverbDTOS = proverbMapper.toProverbDTO(proverbsRepository.findCustomBy(text, type));
        LOGGER.warn("Execution type {} time {}", type, System.currentTimeMillis() - start);
        return proverbDTOS;
    }
}

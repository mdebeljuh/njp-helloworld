package hr.vsite.njp.proverbs.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProverbsManagerImpl implements ProverbsManager {
    private final static Logger LOGGER = LoggerFactory.getLogger(ProverbsManagerImpl.class);

    private final ProverbsRepository proverbsRepository;
    private final ProverbMapper proverbMapper;
    private final EntityManager entityManager;

    public ProverbsManagerImpl(ProverbsRepository proverbsRepository, ProverbMapper proverbMapper, EntityManager entityManager) {
        this.proverbsRepository = proverbsRepository;
        this.proverbMapper = proverbMapper;
        this.entityManager = entityManager;
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
//    @Transactional(readOnly = true) // (2) isto kao da i nema aktivne transakcije
    @Transactional() // (1) aktivne transakcije
    public Optional<ProverbDTO> findOne(Long id) {
//        proverbsRepository.findByProverbContainsOrIdGreaterThan("aa", (long) 12);
//        return proverbsRepository.findById(id).map(proverbMapper::toProverbDTO);
        Optional<Proverb> proverbOptional = proverbsRepository.findById(id);
//        Proverb p = proverbOptional.get();
////        entityManager.detach(p);
//        p.setProverb("Read modified2"); // ako su aktivne transakcije i
        // ako nije napravljen detach tada ce se i ovo spremiti
        return proverbOptional.map(proverbMapper::toProverbDTO);

//        Optional<Proverb> proverb = proverbsRepository.findById(id);
//        Optional<ProverbDTO> proverbDTO = proverb.map(prov -> new ProverbDTO(prov.getId(), prov.getProverb()));
//        return proverbDTO;
//        return proverbsRepository.findById(id)
//                .map(p->new ProverbDTO(p.getId(), p.getProverb()))
//                .orElse(null);
    }

    @Override
    @Transactional(
//            propagation = Propagation.REQUIRES_NEW,
            rollbackFor = Exception.class
    )
    public void save(ProverbDTO proverbDto) throws Exception {
//        Proverb proverb = new Proverb();
//        proverb.setId(proverbDto.getId());
//        proverb.setProverb(proverbDto.getProverb());
        Proverb converted = proverbMapper.fromProverbDTO(proverbDto);
        Proverb pFromSaved =
                proverbsRepository.save(converted);

        converted.setProverb("Converted Proverb"); //nece se spremiti jer nije povezano na entity managera
        pFromSaved.setProverb("From saved proverb"); // ova promjena ce se spremiti
        if (proverbDto.getId() != null) {
            Optional<Proverb> drugi = proverbsRepository.findById(1L);
            if (drugi.isPresent()) {
                Proverb p2 = drugi.get();
                p2.setProverb("also changed"); // i ovo ce se spremiti jer entiti manager prati tu instancu
            }
        }
        saveNewInternal();

//        entityManager.flush();
        LOGGER.info("Done saving");

        //rollback
        TransactionStatus transactionStatus =
                TransactionAspectSupport.currentTransactionStatus();
        if (pFromSaved.getId()>2){
//            transactionStatus.setRollbackOnly();
            LOGGER.info("Rollback");
            throw new RuntimeException();
        }

//        throw new Exception();

//        entityManager.flush();
    }

    @Transactional(
            propagation = Propagation.REQUIRES_NEW
//            rollbackFor = Exception.class
    )
    public void saveNewInternal() throws Exception {
        Proverb proverb = new Proverb();
        proverb.setId(9999L);
        proverb.setProverb("Internal saved");
        proverbsRepository.save(proverb);
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

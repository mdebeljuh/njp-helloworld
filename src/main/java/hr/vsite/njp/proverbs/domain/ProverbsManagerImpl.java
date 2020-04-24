package hr.vsite.njp.proverbs.domain;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ProverbsManagerImpl implements ProverbsManager {

    private final ProverbsRepository proverbsRepository;

    public ProverbsManagerImpl(ProverbsRepository proverbsRepository) {
        this.proverbsRepository = proverbsRepository;
    }

    @Override
    public List<ProverbDTO> findAll() {
        return proverbsRepository.findAll();
    }

    @Override
    public ProverbDTO findOne(Long id) {
        return proverbsRepository.findById(id);
    }

    @Override
    public ProverbDTO random() {
        int count = proverbsRepository.count();
        int id = new Random().nextInt(count);
        return proverbsRepository.findById((long) id);
    }
}

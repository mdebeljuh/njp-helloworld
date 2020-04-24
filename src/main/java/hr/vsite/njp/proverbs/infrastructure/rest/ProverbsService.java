package hr.vsite.njp.proverbs.infrastructure.rest;

import hr.vsite.njp.proverbs.domain.ProverbDTO;
import hr.vsite.njp.proverbs.domain.ProverbsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProverbsService {

    private final ProverbsManager proverbsManager;

    public ProverbsService(ProverbsManager proverbsManager) {
        this.proverbsManager = proverbsManager;
    }

    @GetMapping("/proverbs/{id}")
    public ProverbsRestDTO proverbsRestDTO(@PathVariable(name="id") Long id){
        ProverbDTO proverbDTO = proverbsManager.findOne(id);
        ProverbsRestDTO restDTO = new ProverbsRestDTO();
        restDTO.setId(proverbDTO.getId());
        restDTO.setProverb(proverbDTO.getProverb());
        return restDTO;
    }
}

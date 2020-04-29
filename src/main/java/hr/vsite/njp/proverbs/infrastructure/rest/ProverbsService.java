package hr.vsite.njp.proverbs.infrastructure.rest;

import hr.vsite.njp.proverbs.domain.ProverbDTO;
import hr.vsite.njp.proverbs.domain.ProverbsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ProverbsService {

    private final ProverbsManager proverbsManager;

    public ProverbsService(ProverbsManager proverbsManager) {
        this.proverbsManager = proverbsManager;
    }

    @GetMapping("/proverbs/{id}")
    public Optional<ProverbsRestDTO> proverbsRestDTO(@PathVariable(name = "id") Long id) {
        Optional<ProverbDTO> proverbDTO = proverbsManager.findOne(id);
        return proverbDTO.map(p -> {
                    ProverbsRestDTO restDTO = new ProverbsRestDTO();
                    restDTO.setId(p.getId());
                    restDTO.setProverb(p.getProverb());
                    return restDTO;
                }
        );
    }

    /*
    /proverbs
    /proverbs/{id}
    /proverbs/random

     /proverbs/{id}  GET (select) PUT (update/insert) DELETE (delete)

     /proverbs POST (insert)

*/
}

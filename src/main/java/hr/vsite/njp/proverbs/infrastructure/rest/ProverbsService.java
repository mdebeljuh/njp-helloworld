package hr.vsite.njp.proverbs.infrastructure.rest;

import hr.vsite.njp.proverbs.domain.ProverbDTO;
import hr.vsite.njp.proverbs.domain.ProverbsManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ProverbsService {
    private final static Logger LOGGER = LoggerFactory.getLogger(ProverbsService.class);

    private final ProverbsManager proverbsManager;

    public ProverbsService(ProverbsManager proverbsManager) {
        this.proverbsManager = proverbsManager;
    }


    private ProverbsRestDTO convert(ProverbDTO proverb) {
        ProverbsRestDTO dto = new ProverbsRestDTO();
        dto.setId(proverb.getId());
        dto.setProverb(proverb.getProverb());
        return dto;
    }

    @GetMapping("/proverbs")
    public List<ProverbsRestDTO> allProverbsRestDTO() {
        return proverbsManager
                .findAll()
                .stream().map(p -> {
                    return convert(p);
                }).collect(Collectors.toList());
    }

    @GetMapping("/proverbs/custom")
    public List<ProverbsRestDTO> customProverbsRestDTO(@RequestParam String text,
                                                       @RequestParam Integer type) {
        return proverbsManager
                .findCustomBy(text, type)
                .stream().map(this::convert)
                .collect(Collectors.toList());
    }

    @GetMapping("/proverbs/{id}")
    public Optional<ProverbsRestDTO> proverbsRestDTO(@PathVariable(name = "id") Long id) {
        Optional<ProverbDTO> proverbDTO = proverbsManager.findOne(id);
        return proverbDTO.map(p -> convert(p));
    }

//    @GetMapping("/proverbs/random")
//    public Optional<ProverbsRestDTO> randomProverbsRestDTO() {
//        Optional<ProverbDTO> proverbDTO = proverbsManager.random();
//        return proverbDTO.map(this::convert);
//    }

    @GetMapping("/proverbs/random")
    public Optional<ProverbsRestDTO> randomProverbsByParamRestDTO(@RequestParam Integer type) {
        Optional<ProverbDTO> proverbDTO = proverbsManager.random(type);
        return proverbDTO.map(this::convert);
    }


    @PutMapping("/proverbs/{id}")
    public void saveProverb(@PathVariable(name = "id") Long id, @RequestBody ProverbsRestDTO proverbDTO) {
        ProverbDTO proverb = new ProverbDTO(id, proverbDTO.getProverb());
        proverbsManager.save(proverb);
    }

    @DeleteMapping("/proverbs/{id}")
    public void deleteProverb(@PathVariable(name = "id") Long id) {
        proverbsManager.delete(id);
    }

    @PostMapping("/proverbs")
    public void saveProverb(@RequestBody ProverbsRestDTO proverbDTO) {
        ProverbDTO proverb = new ProverbDTO(proverbDTO.getId(), proverbDTO.getProverb());
        proverbsManager.save(proverb);
    }

    /*
    /proverbs
    /proverbs/{id}
    /proverbs/random

     /proverbs/{id}  GET (select) PUT (update/insert) DELETE (delete)

     /proverbs POST (insert)

*/
}

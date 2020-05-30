package hr.vsite.njp.proverbs.domain;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProverbMapper {
    ProverbDTO toProverbDTO(Proverb proverb);

    List<ProverbDTO> toProverbDTO(Iterable<Proverb> proverbs);

    Proverb fromProverbDTO(ProverbDTO proverbDTO);

}

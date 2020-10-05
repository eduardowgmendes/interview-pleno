package br.com.brainweb.interview.domain.datatransferobject;


import br.com.brainweb.interview.domain.Hero;
import br.com.brainweb.interview.domain.PowerStats;
import br.com.brainweb.interview.domain.Race;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.UUID;

@Data
public class HeroDTO {

    private UUID id;
    private String name;
    private Race race;
    private PowerStats powerStats;

    public static HeroDTO create(Hero hero) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(hero, HeroDTO.class);
    }

}

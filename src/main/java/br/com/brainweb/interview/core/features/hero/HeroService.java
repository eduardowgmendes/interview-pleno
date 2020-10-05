package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.core.exception.ObjectNotFoundException;
import br.com.brainweb.interview.core.features.filter.HeroSpecification;
import br.com.brainweb.interview.domain.Hero;
import br.com.brainweb.interview.domain.datatransferobject.HeroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HeroService {

    @Autowired
    private HeroRepository repository;

    public List<HeroDTO> all(String name) {
        return repository.findAll(new HeroSpecification(new Hero(name)))
                .stream()
                .map(HeroDTO::create)
                .collect(Collectors.toList());
    }

    public HeroDTO byId(UUID uuid) {
        return repository.findById(uuid)
                .map(HeroDTO::create)
                .orElseThrow(() -> new ObjectNotFoundException("The requested entity " + uuid + " wasn't found."));
    }

    public HeroDTO insert(Hero hero) {
        Assert.isNull(hero.getId(), "It's not possible insert the Hero.");

        return HeroDTO.create(repository.save(hero));
    }

    public HeroDTO update(Hero hero, UUID uuid) {
        Assert.notNull(uuid, "It's not possible update the Hero.");

        Optional<Hero> optional = repository.findById(uuid);

        if (optional.isPresent()) {
            Hero h = optional.get();

            h.setName(hero.getName());
            h.setRace(hero.getRace());
            h.setPowerStats(hero.getPowerStats());
            h.setCreatedAt(hero.getCreatedAt());
            h.setUpdatedAt(hero.getUpdatedAt());

            repository.save(h);

            return HeroDTO.create(h);
        } else {
            return null;
        }
    }

    public void delete(UUID uuid) {
        repository.deleteById(uuid);
    }

}

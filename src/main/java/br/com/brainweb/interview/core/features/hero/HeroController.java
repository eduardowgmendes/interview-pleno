package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.core.features.filter.HeroSpecification;
import br.com.brainweb.interview.domain.Hero;
import br.com.brainweb.interview.domain.datatransferobject.HeroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/heroes")
public class HeroController {

    @Autowired
    private HeroService service;

    @GetMapping("/{name}")
    public ResponseEntity<List<HeroDTO>> getAll(@RequestParam(value = "name", required = false) String name) {
        Specification<Hero> specification = new HeroSpecification(new Hero(name));
        return ResponseEntity.ok(service.all(name));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<HeroDTO> getById(@PathVariable("uuid") UUID uuid) {
        HeroDTO hero = service.byId(uuid);
        return hero != null ?
                ResponseEntity.ok(hero)
                : ResponseEntity.notFound().build();
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<HeroDTO> update(@RequestBody Hero hero, @PathVariable("uuid") UUID uuid) {
        hero.setId(uuid);

        HeroDTO h = service.update(hero, uuid);

        return h != null ?
                ResponseEntity.ok(h)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<HeroDTO> delete(@PathVariable("uuid") UUID uuid) {
        service.delete(uuid);
        return ResponseEntity.ok().build();
    }

}

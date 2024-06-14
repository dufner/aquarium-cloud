package tacos.web.repositories;

import tacos.ComponentOFAquarium;

import java.util.Optional;

public interface ComponentOfAquariumRepository {

    Iterable<ComponentOFAquarium> findAll();
    Optional<ComponentOFAquarium> findById(String id);
    ComponentOFAquarium save (ComponentOFAquarium componentOFAquarium);
}

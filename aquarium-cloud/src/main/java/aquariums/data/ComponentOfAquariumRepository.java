package aquariums.data;

import aquariums.ComponentOFAquarium;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ComponentOfAquariumRepository
        extends CrudRepository<ComponentOFAquarium, String> {

}
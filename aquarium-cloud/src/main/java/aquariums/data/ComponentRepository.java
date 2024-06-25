package aquariums.data;

import aquariums.Component;
import org.springframework.data.repository.CrudRepository;

public interface ComponentRepository
        extends CrudRepository<Component, String> {

}
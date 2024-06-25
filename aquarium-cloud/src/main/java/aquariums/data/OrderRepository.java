package aquariums.data;


import aquariums.AquariumOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository
        extends CrudRepository<AquariumOrder, Long> {

}

package aquariums;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Aquarium {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private Date createdAt= new Date();



  @Size(min=1, message="You must choose at least 1 ingredient")
  @ManyToMany()
  private List<ComponentOFAquarium> componentsOFAquariums = new ArrayList<>();
  public void addComponentOFAquarium(ComponentOFAquarium componentOFAquariumt) {
    this.componentsOFAquariums.add(componentOFAquariumt);
  }

}

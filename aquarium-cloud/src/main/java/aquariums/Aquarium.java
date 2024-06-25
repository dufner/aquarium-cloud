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

  @NotNull
  @Size(min=5, message="Name must be at least 5 characters long")
  private String name;

  private Date createdAt= new Date();



  @Size(min=1, message="You must choose at least 1 ingredient")
  @ManyToMany()
  private List<Component> components = new ArrayList<>();
  public void addComponent(Component component) {
    this.components.add(component);
  }

}

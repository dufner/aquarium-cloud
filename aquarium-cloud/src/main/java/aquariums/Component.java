package aquariums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
public class Component {
  @Id
  private  String id;
  private  String name;
  private  Type type;



  public enum Type {
    AQUASOIL, FISH, AQUAPLANT, TANK,DECOR
  }

}

package tacos;

import lombok.Data;

@Data
public class ComponentOFAquarium {
  
  private final String id;
  private final String name;
  private final Type type;
  
  public enum Type {
    AQUASOIL, FISH, AQUAPLANT, TANK,DECOR
  }

}

package tacos.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import tacos.ComponentOFAquarium;
import tacos.ComponentOFAquarium.Type;

@Component
public class ComponentOfAquariumByIdConverter implements Converter<String, ComponentOFAquarium> {

  private Map<String, ComponentOFAquarium> ingredientMap = new HashMap<>();
  
  public ComponentOfAquariumByIdConverter() {
    ingredientMap.put("CFT",
            new ComponentOFAquarium("CFT", "coldwater freshwater tank", Type.TANK));
    ingredientMap.put("TFT",
            new ComponentOFAquarium("TFT", "tropical freshwater tank", Type.TANK));
    ingredientMap.put("GF",
            new ComponentOFAquarium("GF", "GoldFish", Type.FISH));
    ingredientMap.put("KOI",
            new ComponentOFAquarium("KOI", "KOI", Type.FISH));
    ingredientMap.put("COONTAIL",
            new ComponentOFAquarium("COONTAIL", "Coontail", Type.AQUAPLANT));
    ingredientMap.put("DW",
            new ComponentOFAquarium("DW", "Duckweeds", Type.AQUAPLANT));
    ingredientMap.put("SAND",
            new ComponentOFAquarium("SAND", "White sand", Type.AQUASOIL));
    ingredientMap.put("ROCKSSMALL",
            new ComponentOFAquarium("ROCKSSMALL", "Small rocks black", Type.AQUASOIL));
    ingredientMap.put("AD",
            new ComponentOFAquarium("AD", "Artificial Driftwood", Type.DECOR));
    ingredientMap.put("ND",
            new ComponentOFAquarium("ND", "Natural Driftwood", Type.DECOR));
  }
  
  @Override
  public ComponentOFAquarium convert(String id) {
    return ingredientMap.get(id);
  }

}

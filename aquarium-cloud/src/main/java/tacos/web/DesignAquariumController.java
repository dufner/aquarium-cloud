package tacos.web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import tacos.Aquarium;
import tacos.AquariumOrder;
import tacos.ComponentOFAquarium;
import tacos.ComponentOFAquarium.Type;

import javax.validation.Valid;
import org.springframework.validation.Errors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("aquariumOrder")
public class DesignAquariumController {

    public static Logger getLog() {
        return log;
    }

    @ModelAttribute
public void addIngredientsToModel(Model model) {
	List<ComponentOFAquarium> componentOFAquariums = Arrays.asList(
	  new ComponentOFAquarium("CFT", "coldwater freshwater tank", Type.TANK),
	  new ComponentOFAquarium("TFT", "tropical freshwater tank", Type.TANK),
	  new ComponentOFAquarium("GF", "GoldFish", Type.FISH),
	  new ComponentOFAquarium("KOI", "KOI", Type.FISH),
	  new ComponentOFAquarium("COONTAIL", "Coontail", Type.AQUAPLANT),
	  new ComponentOFAquarium("DW", "Duckweeds", Type.AQUAPLANT),
	  new ComponentOFAquarium("SAND", "White sand", Type.AQUASOIL),
	  new ComponentOFAquarium("ROCKSSMALL", "Small rocks black", Type.AQUASOIL),
	  new ComponentOFAquarium("AD", "Artificial Driftwood", Type.DECOR),
	  new ComponentOFAquarium("ND", "Natural Driftwood", Type.DECOR)
	);

	Type[] types = ComponentOFAquarium.Type.values();
	for (Type type : types) {
	  model.addAttribute(type.toString().toLowerCase(),
	      filterByType(componentOFAquariums, type));
	}
  }

  @ModelAttribute(name = "aquariumOrder")
  public AquariumOrder order() {
    return new AquariumOrder();
  }

  @ModelAttribute(name = "aquarium")
  public Aquarium taco() {
    return new Aquarium();
  }

  @GetMapping
  public String showDesignForm() {
    return "design";
  }

  @ModelAttribute(name = "message")
    public String message (){
    return "fuck!!!!";
    }
/*
  @PostMapping
  public String processTaco(Taco taco,
  			@ModelAttribute TacoOrder tacoOrder) {
    tacoOrder.addTaco(taco);
    log.info("Processing taco: {}", taco);

    return "redirect:/orders/current";
  }
 */

  @PostMapping
  public String processTaco(
          @Valid Aquarium aquarium, Errors errors,
          @ModelAttribute AquariumOrder aquariumOrder) {

    if (errors.hasErrors()) {
      return "design";
    }

    aquariumOrder.addAquarium(aquarium);
    log.info("Processing aquarium: {}", aquarium);

    return "redirect:/orders/current";
  }

  private Iterable<ComponentOFAquarium> filterByType(
          List<ComponentOFAquarium> componentOFAquariums, Type type) {
    return componentOFAquariums
              .stream()
              .filter(x -> x.getType().equals(type))
              .collect(Collectors.toList());
  }

}

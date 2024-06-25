package aquariums.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import aquariums.ComponentOFAquarium;
import aquariums.data.ComponentOfAquariumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import aquariums.Aquarium;
import aquariums.AquariumOrder;
import aquariums.ComponentOFAquarium;
import aquariums.ComponentOFAquarium.Type;
import javax.validation.Valid;
import org.springframework.validation.Errors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("aquariumOrder")
public class DesignAquariumController {

    private final ComponentOfAquariumRepository componentOfAquariumRepository;
@Autowired
    public DesignAquariumController(ComponentOfAquariumRepository componentOfAquariumRepository) {
        this.componentOfAquariumRepository = componentOfAquariumRepository;
    }


    @ModelAttribute
public void addIngredientsToModel(Model model) {
        List<ComponentOFAquarium> componentOFAquariums = new ArrayList<>();
        componentOfAquariumRepository.findAll().forEach(i -> componentOFAquariums.add(i));

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

//  @ModelAttribute(name = "message")
//    public String message (){
//    return "fuck!!!!";
//    }
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
          List<ComponentOFAquarium> componentOFAquariums, ComponentOFAquarium.Type type) {
    return componentOFAquariums
              .stream()
              .filter(x -> x.getType().equals(type))
              .collect(Collectors.toList());
  }

}

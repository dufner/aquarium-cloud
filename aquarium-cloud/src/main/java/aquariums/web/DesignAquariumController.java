package aquariums.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import aquariums.Component;
import aquariums.data.ComponentRepository;
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
import aquariums.Component.Type;
import javax.validation.Valid;
import org.springframework.validation.Errors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("aquariumOrder")
public class DesignAquariumController {

    private final ComponentRepository componentRepository;
    @Autowired
    public DesignAquariumController(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }


    @ModelAttribute
    public void addComponentsToModel(Model model) {
        List<Component> components = new ArrayList<>();
        componentRepository.findAll().forEach(i -> components.add(i));

      Type[] types = Component.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(components, type));
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

  private Iterable<Component> filterByType(
          List<Component> components, Component.Type type) {
    return components
              .stream()
              .filter(x -> x.getType().equals(type))
              .collect(Collectors.toList());
  }

}

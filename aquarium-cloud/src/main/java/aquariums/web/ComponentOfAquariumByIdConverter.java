package aquariums.web;

import aquariums.data.ComponentOfAquariumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import aquariums.ComponentOFAquarium;

@Component
public class ComponentOfAquariumByIdConverter implements Converter<String, ComponentOFAquarium> {

  private ComponentOfAquariumRepository componentOfAquariumRepository;

  @Autowired
  public ComponentOfAquariumByIdConverter (ComponentOfAquariumRepository ingredientRepo) {
    this.componentOfAquariumRepository = ingredientRepo;
  }


  @Override
  public ComponentOFAquarium convert(String id) {
    return componentOfAquariumRepository.findById(id).orElse(null);
  }
}

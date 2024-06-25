package aquariums.web;

import aquariums.data.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import aquariums.Component;

@org.springframework.stereotype.Component
public class ComponentByIdConverter implements Converter<String, Component> {

  private ComponentRepository componentRepository;

  @Autowired
  public ComponentByIdConverter(ComponentRepository ingredientRepo) {
    this.componentRepository = ingredientRepo;
  }


  @Override
  public Component convert(String id) {
    return componentRepository.findById(id).orElse(null);
  }
}

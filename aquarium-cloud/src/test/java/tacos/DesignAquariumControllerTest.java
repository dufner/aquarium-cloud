package tacos;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import tacos.ComponentOFAquarium.Type;
import tacos.web.DesignAquariumController;

@ExtendWith(SpringExtension.class) // <1>
@WebMvcTest(DesignAquariumController.class)
public class DesignAquariumControllerTest {

  @Autowired
  private MockMvc mockMvc;

  private List<ComponentOFAquarium> componentOFAquariums;

  @BeforeEach
  public void setup() {
    componentOFAquariums = Arrays.asList(
      new ComponentOFAquarium("FLTO", "Flour Tortilla", Type.WRAP),
      new ComponentOFAquarium("COTO", "Corn Tortilla", Type.WRAP),
      new ComponentOFAquarium("GRBF", "Ground Beef", Type.PROTEIN),
      new ComponentOFAquarium("CARN", "Carnitas", Type.PROTEIN),
      new ComponentOFAquarium("TMTO", "Diced Tomatoes", Type.VEGGIES),
      new ComponentOFAquarium("LETC", "Lettuce", Type.VEGGIES),
      new ComponentOFAquarium("CHED", "Cheddar", Type.CHEESE),
      new ComponentOFAquarium("JACK", "Monterrey Jack", Type.CHEESE),
      new ComponentOFAquarium("SLSA", "Salsa", Type.SAUCE),
      new ComponentOFAquarium("SRCR", "Sour Cream", Type.SAUCE)
    );

  }

  @Test
  public void testShowDesignForm() throws Exception {
    mockMvc.perform(get("/design"))
        .andExpect(status().isOk())
        .andExpect(view().name("design"))
        .andExpect(model().attribute("wrap", componentOFAquariums.subList(0, 2)))
        .andExpect(model().attribute("protein", componentOFAquariums.subList(2, 4)))
        .andExpect(model().attribute("veggies", componentOFAquariums.subList(4, 6)))
        .andExpect(model().attribute("cheese", componentOFAquariums.subList(6, 8)))
        .andExpect(model().attribute("sauce", componentOFAquariums.subList(8, 10)));
  }

  @Test
  public void processTaco() throws Exception {
    mockMvc.perform(post("/design")
        .content("name=Test+Taco&ingredients=FLTO,GRBF,CHED")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
        .andExpect(status().is3xxRedirection())
        .andExpect(header().stringValues("Location", "/orders/current"));
  }

}

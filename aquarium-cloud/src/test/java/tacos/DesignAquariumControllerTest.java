package tacos;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.List;

import aquariums.Component;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import aquariums.Component.Type;
import aquariums.web.DesignAquariumController;

@ExtendWith(SpringExtension.class) // <1>
@WebMvcTest(DesignAquariumController.class)
public class DesignAquariumControllerTest {

  @Autowired
  private MockMvc mockMvc;

  private List<Component> componentOFAquariums;

  @BeforeEach
  public void setup() {
    componentOFAquariums = Arrays.asList(
            new Component("CFT", "coldwater freshwater tank", Type.TANK),
            new Component("TFT", "tropical freshwater tank", Type.TANK),
            new Component("GF", "GoldFish", Type.FISH),
            new Component("KOI", "KOI", Type.FISH),
            new Component("COONTAIL", "Coontail", Type.AQUAPLANT),
            new Component("DW", "Duckweeds", Type.AQUAPLANT),
            new Component("SAND", "White sand", Type.AQUASOIL),
            new Component("ROCKSSMALL", "Small rocks black", Type.AQUASOIL),
            new Component("AD", "Artificial Driftwood", Type.DECOR),
            new Component("ND", "Natural Driftwood", Type.DECOR)
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

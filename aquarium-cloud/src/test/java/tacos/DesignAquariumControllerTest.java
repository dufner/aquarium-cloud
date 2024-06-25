package tacos;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.List;

import aquariums.ComponentOFAquarium;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import aquariums.ComponentOFAquarium.Type;
import aquariums.web.DesignAquariumController;

@ExtendWith(SpringExtension.class) // <1>
@WebMvcTest(DesignAquariumController.class)
public class DesignAquariumControllerTest {

  @Autowired
  private MockMvc mockMvc;

  private List<ComponentOFAquarium> componentOFAquariums;

  @BeforeEach
  public void setup() {
    componentOFAquariums = Arrays.asList(
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

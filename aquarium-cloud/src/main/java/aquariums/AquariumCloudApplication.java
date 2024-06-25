package aquariums;

import aquariums.data.ComponentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import aquariums.Component.Type;

@SpringBootApplication   // <1>
public class AquariumCloudApplication {

  public static void main(String[] args) {
    SpringApplication.run(AquariumCloudApplication.class, args); // <2>
  }

  @Bean
  public CommandLineRunner dataLoader(ComponentRepository repo) {
    return new CommandLineRunner() {
      @Override
      public void run(String... args) throws Exception {

        repo.save( new Component("CFT", "coldwater freshwater tank", Component.Type.TANK));
        repo.save(new Component("TFT", "tropical freshwater tank", Type.TANK));
        repo.save(new Component("GF", "GoldFish", Type.FISH));
        repo.save(new Component("KOI", "KOI", Type.FISH));
        repo.save(new Component("COONTAIL", "Coontail", Type.AQUAPLANT));
        repo.save(new Component("DW", "Duckweeds", Type.AQUAPLANT));
        repo.save(new Component("SAND", "White sand", Type.AQUASOIL));
        repo.save(new Component("ROCKSSMALL", "Small rocks black", Type.AQUASOIL));
        repo.save(new Component("AD", "Artificial Driftwood", Type.DECOR));
        repo.save(new Component("ND", "Natural Driftwood", Type.DECOR));
      }
    };
  }
}

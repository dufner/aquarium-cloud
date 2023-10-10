package tacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication   // <1>
public class AquariumCloudApplication {

  public static void main(String[] args) {
    SpringApplication.run(AquariumCloudApplication.class, args); // <2>
  }

}

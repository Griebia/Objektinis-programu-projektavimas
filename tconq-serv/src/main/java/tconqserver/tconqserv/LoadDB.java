package tconqserver.tconqserv;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class LoadDatabase {

  @Bean
  CommandLineRunner initDatabase(PlayerRepository repository) {
    return args -> {
      log.info("Preloading " + repository.save(new Player("Bilbo Baggins", 200, true, 120)));
      log.info("Preloading " + repository.save(new Player("Frodo Baggins", 350, false, 210)));
    };
  }
}
package tconqserver.tconqserv.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import tconqserver.tconqserv.entities.Player;
import tconqserver.tconqserv.observer.Observer;
import tconqserver.tconqserv.observer.Subject;
import tconqserver.tconqserv.repositories.PlayerRepository;


@RestController
class PlayerController {

    private static Subject sub;

    public PlayerController(){
        sub = new Subject();
    }

    public static Subject getSub() {
        return sub;
    }

    static private class PlayerNotFoundExeption extends Exception {
        public PlayerNotFoundExeption(String errorMessage) {
            super(errorMessage);
        }
    }
    
    @Autowired
    private PlayerRepository repository;

    // Aggregate root

    @GetMapping("/Players")
    public List<Player> all() {
        return repository.findAll();
    }

    @PostMapping("/Players")
    public ResponseEntity<Object> newPlayer(@RequestBody Player newPlayer) {
        Player savedPlayer = repository.save(newPlayer);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedPlayer.getId()).toUri();


        Observer observer1 = new Observer(sub, savedPlayer.getId());

        return ResponseEntity.created(location).build();
    }

    // Single item

    @GetMapping("/Player/{id}")
    public Player one(@PathVariable Long id) throws PlayerNotFoundExeption{
        Optional<Player> player = repository.findById(id);
        if(!player.isPresent()){
            throw new PlayerNotFoundExeption("id-"+id);
        }
        return player.get();
    }


    @PutMapping("/Player/{id}")
    public Player replacePlayer(@RequestBody Player newPlayer, @PathVariable Long id) {

        return repository.findById(id)
        .map(Player -> {
            Player.setName(newPlayer.getName());
            Player.setGold(newPlayer.getGold());
            Player.setPlaying(newPlayer.isPlaying());
            Player.setPoints(newPlayer.getPoints());
            return repository.save(Player);
        })
        .orElseGet(() -> {
            newPlayer.setId(id);
            return repository.save(newPlayer);
        });
    }

    @DeleteMapping("/Player/{id}")
    public void deletePlayer(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
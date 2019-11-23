package tconqserver.tconqserv.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/Players/{id}")
    public ArrayList<Player> allButMe(@PathVariable Long id) {      // returns all players except the one with this id
        List<Player> allPlayers = all();
        ArrayList<Player> allPlayersButMe = new ArrayList<Player>();
        for (Player player : allPlayers) {
            if(!player.getId().equals(id))
                allPlayersButMe.add(player);
        }
        return allPlayersButMe;
    }

    @PostMapping("/Player")
    public Player newPlayer(@RequestBody Player newPlayer) {
        Player savedPlayer = repository.save(newPlayer);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedPlayer.getId()).toUri();

        System.out.println(newPlayer);

        Observer observer1 = new Observer(sub, savedPlayer.getId());

//        return ResponseEntity.created(location).build();

        return repository.findById(savedPlayer.getId()).orElse(null);
    }

    // Single item

    @GetMapping("/Player/{id}")
    public Player onePlayer(@PathVariable Long id) throws PlayerNotFoundExeption{
        Optional<Player> player = repository.findById(id);
        if(!player.isPresent()){
            throw new PlayerNotFoundExeption("id-"+id);
        }
        return repository.findById(id).orElse(null);
    }


    @PutMapping("/Player/{id}")
    public Player replacePlayer(@RequestBody Player newPlayer) {
        return repository.findById(newPlayer.getId())
        .map(Player -> {
            Player.setName(newPlayer.getName());
            Player.setGold(newPlayer.getGold());
            Player.setPlaying(newPlayer.isPlaying());
            Player.setPoints(newPlayer.getPoints());
            Player.setNextTurn(newPlayer.getNextTurn());            
            return repository.save(Player);
        })
        .orElseGet(() -> {
            newPlayer.setId(newPlayer.getId());
            return repository.save(newPlayer);
        });
    }

    @DeleteMapping("/Player/{id}")
    public void deletePlayer(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PostMapping("/NextTurn/{id}")
    public Boolean nextTurn(@RequestBody Player player){
        getSub().notifyObserverPlayer(player, repository);
        return true;
    }

}
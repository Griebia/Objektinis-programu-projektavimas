package tconqserver.tconqserv.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
class PlayerController {
    

    private final PlayerRepository repository;

    PlayerController(PlayerRepository repository) {
        this.repository = repository;
    }

    // Aggregate root

    @GetMapping("/Players")
    List<Player> all() {
        return repository.findAll();
    }

    @PostMapping("/Players")
    Player newPlayer(@RequestBody Player newPlayer) {
        return repository.save(newPlayer);
    }

    // Single item

    @GetMapping("/Player/{id}")
    Player one(@PathVariable Long id) {

        return repository.findById(id);
    }


    @PutMapping("/Player/{id}")
    Player replacePlayer(@RequestBody Player newPlayer, @PathVariable Long id) {

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
    void deletePlayer(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
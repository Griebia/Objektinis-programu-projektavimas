package tconqserver.tconqserv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tconqserver.tconqserv.entities.SEntity;
import tconqserver.tconqserv.observer.Observer;
import tconqserver.tconqserv.observer.Subject;
import tconqserver.tconqserv.repositories.SEntityRepository;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class SEntityController {

    static private class SEntityNotFoundExeption extends Exception {
        public SEntityNotFoundExeption(String errorMessage) {
            super(errorMessage);
        }
    }

    @Autowired
    private SEntityRepository repository;

    @GetMapping("/SEntities")
    public List<SEntity> all() {
        return repository.findAll();
    }

    @PostMapping("/SEntities")
    public ResponseEntity<Object> newSEntity(@RequestBody ArrayList<SEntity> newSEntities){

        //gets all unique ids of players associated with passed entities
        ArrayList<Long> uniqueID = new ArrayList<>();
        uniqueID.add(newSEntities.get(0).getPlayer().getId());
        for (SEntity ent: newSEntities){
            if (!uniqueID.contains(ent.getPlayer().getId())){
                uniqueID.add(ent.getPlayer().getId());
            }
        }

        //deletes all entities from db that are associated with unique id's
        for (Long ID: uniqueID){
            deleteSEntity(ID);
        }

        //puts new entities to db for players
        for (SEntity newSEntity : newSEntities) {
            SEntity savedSEntity = repository.save(newSEntity);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(savedSEntity.getId()).toUri();
        }

        PlayerController.getSub().setEntity(newSEntities, newSEntities.get(0).getPlayer().getId());

        return null;
    }

    @PostMapping("/SEntity")
    public ResponseEntity<Object> newSEntity(@RequestBody SEntity newSEntity){

        SEntity savedSEntity = repository.save(newSEntity);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedSEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }


    @DeleteMapping("/SEntities/{id}")
    public void deleteSEntity(@PathVariable Long id) {

        List<SEntity> entities = all();
        for (SEntity ent: entities) {
            if (ent.getPlayer().getId().equals(id))
                repository.deleteById(ent.getId());
        }
        System.out.println("DELETE STUFF");

    }
 
}

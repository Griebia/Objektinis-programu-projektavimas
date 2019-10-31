package tconqserver.tconqserv.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import tconqserver.tconqserv.entities.SEntity;
import tconqserver.tconqserv.repositories.SEntityRepository;

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

    @GetMapping("/SEntities/{id}")
    public ArrayList<SEntity> allById(@PathVariable Long id) {   // gets all entities of player with given id
        ArrayList<SEntity> entities = (ArrayList<SEntity>)all();
        ArrayList<SEntity> filtered = new ArrayList<SEntity>() ;
        for (SEntity entity : entities) {
            if(entity.getPlayer().getId() == id)
                filtered.add(entity);
        }
        return filtered;
        //return repository.findAllButByPlayerId(id);
    }

    @PostMapping("/SEntities")
    public ResponseEntity<Object> newSEntity(@RequestBody ArrayList<SEntity> newSEntities){

        PlayerController.getSub().setEntity(newSEntities, newSEntities.get(0).getPlayer().getId(), repository);

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
            {
                
                repository.deleteById(ent.getId());
            }
        }
        

    }
 
}

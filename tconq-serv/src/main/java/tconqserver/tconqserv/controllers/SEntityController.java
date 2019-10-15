package tconqserver.tconqserv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tconqserver.tconqserv.entities.SEntity;
import tconqserver.tconqserv.observer.Observer;
import tconqserver.tconqserv.observer.Subject;
import tconqserver.tconqserv.repositories.SEntityRepository;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

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

        for (SEntity newSEntity : newSEntities) {
            System.out.println(newSEntity + "dasdasd");

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

 
}

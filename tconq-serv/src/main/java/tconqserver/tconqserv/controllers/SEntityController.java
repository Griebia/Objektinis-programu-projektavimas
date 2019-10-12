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
            SEntity savedSEntity = repository.save(newSEntity);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(savedSEntity.getId()).toUri();
        }
        Subject sub = new Subject();

        Observer observer1 = new Observer(sub);

        sub.setEntity(newSEntities);

        return null;
    }
}

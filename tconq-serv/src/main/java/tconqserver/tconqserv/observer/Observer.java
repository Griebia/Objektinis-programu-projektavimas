package tconqserver.tconqserv.observer;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import tconqserver.tconqserv.entities.SEntity;
import tconqserver.tconqserv.repositories.SEntityRepository;

public class Observer {

    private ArrayList<SEntity> entities;

    private Long observerID;

    private Subject entityGrabber;

    public Observer(Subject entityGrabber, Long playerID) {
        this.entityGrabber = entityGrabber; // gets entities for observer

        this.observerID = playerID; // sets observer id to player id

        System.out.println("New Observer " + this.observerID);

        entityGrabber.register(this, playerID); // creates new observer
    }

    public Long getObserverId() {
        return observerID;
    }

    public Subject getSubject() {
        return entityGrabber;
    }

    public void setId(Long id) {
        this.observerID = id;
    }

    public void getSubject(Subject entityGrabber) {
        this.entityGrabber = entityGrabber;
    }

    // turetu kaskaip informuot kita player kad ivyko kaskokie pakitimai
    // kita playeri gauna per sesiona pasiema sezsiona kuriame yra playeris kuris
    // siuncia duomenis ir ziuri kas dar tame sesione yra
    // turi pasalint obijektus kurie buvo pakeisti ir palikt tik naujus arba istrint
    // visus senus playerio obijektus ir palikt tik naujus
    // arba atnaujint senus obijektus
    public void update(ArrayList<SEntity> entities, SEntityRepository repository){

        //gets all unique ids of players associated with passed entities
        Long playerId =entities.get(0).getPlayer().getId();
        //deleteSEntities(playerId, repository);
        /*
        for (SEntity ent: entities){
            if (!uniqueID.contains(ent.getPlayer().getId())){
                uniqueID.add(ent.getPlayer().getId());
            }
        }

        //deletes all entities from db that are associated with unique id's
        for (Long ID: uniqueID){
            deleteSEntity(ID, repository);
        }
        */

        //puts new entities to db for players
        for (SEntity entity : entities) {
            Optional<SEntity> dbEntities = repository.findbyXY(entity.getX(),entity.getY());
            if(!dbEntities.isPresent()){        // checks if this entity already exists in database
                SEntity savedSEntity = repository.save(entity);
                URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                        .buildAndExpand(savedSEntity.getId()).toUri();
            }
        }
        for (SEntity entity : entities){
            System.out.println("Entity id - " + entity.getId()+ " " + entity.getPlayer());
        }

    }

    public void deleteSEntities(Long id, SEntityRepository repository) {

        List<SEntity> entities = repository.findAll();
        for (SEntity ent: entities) {
            if (ent.getPlayer().getId().equals(id))
            {
                System.out.println("DELETE - " + ent.getId());
                repository.deleteById(ent.getId());
            }
        }

    }
}

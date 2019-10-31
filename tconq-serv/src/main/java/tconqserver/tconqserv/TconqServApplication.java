package tconqserver.tconqserv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tconqserver.tconqserv.entities.SEntity;
import tconqserver.tconqserv.observer.Observer;
import tconqserver.tconqserv.observer.Subject;

import java.util.ArrayList;

@SpringBootApplication
public class TconqServApplication {

	public static void main(String[] args) {
		SpringApplication.run(TconqServApplication.class, args);


//		ArrayList<SEntity> ent = new ArrayList<SEntity>();
//
//		Subject sub = new Subject();
//
//		Observer observer1 = new Observer(sub);
//
//		ent.add(new SEntity(1, 1, 2, SEntity.Type.HOUSE));
//		ent.add(new SEntity(2, 3, 2, SEntity.Type.HOUSE));
//		ent.add(new SEntity(3, 5, 2, SEntity.Type.HOUSE));
//
//		sub.setEntity(ent);




	}

}

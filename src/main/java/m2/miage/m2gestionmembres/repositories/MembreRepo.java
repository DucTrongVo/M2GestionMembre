package m2.miage.m2gestionmembres.repositories;

import m2.miage.m2gestionmembres.entities.Membre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembreRepo extends CrudRepository<Membre, String> {

}

package m2.miage.m2gestionmembres.repositories;

import m2.miage.m2gestionmembres.entities.Membre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MembreRepo extends CrudRepository<Membre, String> {
    Optional<Membre> findMembreByMail(String mail);
    List<Membre> findAll();
}

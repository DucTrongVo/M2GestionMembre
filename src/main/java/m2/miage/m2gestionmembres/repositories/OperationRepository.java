package m2.miage.m2gestionmembres.repositories;

import m2.miage.m2gestionmembres.entities.Membre;
import m2.miage.m2gestionmembres.entities.Operation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OperationRepository extends CrudRepository<Operation, Integer> {
    Optional<List<Operation>> findAllByMembre(Membre membre);
    List<Operation> findAll();
}

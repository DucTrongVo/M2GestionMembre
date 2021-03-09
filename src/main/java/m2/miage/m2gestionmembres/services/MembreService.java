package m2.miage.m2gestionmembres.services;

import m2.miage.m2gestionmembres.entities.Membre;
import m2.miage.m2gestionmembres.repositories.MembreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MembreService {

    @Autowired
    private MembreRepo membreRepo;

    public Membre creerUnMembre() {
        Membre membre  = new Membre();
        membreRepo.save(membre);
        return membre;
    }

}

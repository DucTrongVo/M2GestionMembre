package m2.miage.m2gestionmembres.services;

import m2.miage.m2gestionmembres.entities.Membre;
import m2.miage.m2gestionmembres.enums.EnumEtatUtilisateur;
import m2.miage.m2gestionmembres.enums.EnumTypeUtilisateur;
import m2.miage.m2gestionmembres.repositories.MembreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MembreServiceImpl implements MembreService{

    @Autowired
    private MembreRepo membreRepo;

    public Membre creerUnMembre() {
        Membre membre  = Membre.builder().nom("").prenom("").mail("").mdp("")
                        .adresse("").dateCertif(LocalDateTime.now()).niveau(1).numLicence("")
                        .type(EnumTypeUtilisateur.MEMBRE.name()).etat(EnumEtatUtilisateur.RETARD.name())
                        .build();
        membreRepo.save(membre);
        return membre;
    }

}

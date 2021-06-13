package m2.miage.m2gestionmembres.services;

import m2.miage.m2gestionmembres.Exception.ForbiddenException;
import m2.miage.m2gestionmembres.Exception.NotFoundException;
import m2.miage.m2gestionmembres.entities.Membre;
import m2.miage.m2gestionmembres.entities.dto.Statistique;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MembreService {

    Membre creerMembre(Membre membre) throws NotFoundException, ForbiddenException;
    Membre getMembreByEmail(String email) throws NotFoundException;
    List<Membre> getAllMembre(String emailRequester) throws NotFoundException, ForbiddenException;
    @Transactional
    Membre mettreAJourMembre(String emailRequester, String email, Membre membreAJour) throws NotFoundException, ForbiddenException;
    @Transactional
    Boolean supprimerMembre(String emailRequester, String emailMembre) throws NotFoundException, ForbiddenException;
    Boolean isMembreApte(String emailMembre) throws NotFoundException;

    Statistique getStatistique(String emailPresident) throws NotFoundException, ForbiddenException;
}

package m2.miage.m2gestionmembres.services;

import javassist.NotFoundException;
import m2.miage.m2gestionmembres.Exception.ForbiddenException;
import m2.miage.m2gestionmembres.entities.Membre;

import java.util.List;

public interface MembreService {

    Membre creerMembre(Membre membre) throws NotFoundException, ForbiddenException;
    Membre getMembreByEmail(String email) throws NotFoundException;
    List<Membre> getAllMembre(String emailRequester) throws NotFoundException, ForbiddenException;
    Membre mettreAJourMembre(String emailRequester, String email, Membre membreAJour) throws NotFoundException, ForbiddenException;
    Boolean supprimerMembre(String emailRequester, String emailMembre) throws NotFoundException, ForbiddenException;
    Boolean isMembreApte(String emailMembre) throws NotFoundException;
}

package m2.miage.m2gestionmembres.services;

import m2.miage.m2gestionmembres.entities.Membre;

public interface IRightService {
    boolean checkRight(Membre mebre, String right);
}

package m2.miage.m2gestionmembres.controllers;

import m2.miage.m2gestionmembres.entities.Membre;
import m2.miage.m2gestionmembres.services.MembreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(value = "/Membre")
public class MembreController {

    @Autowired
    private MembreService membreService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public @ResponseStatus
    ResponseEntity<Membre> ouvrirUnNouveauCompte(){
        return ResponseEntity.ok(membreService.creerUnMembre());
    }
}

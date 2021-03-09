package m2.miage.m2gestionmembres.controllers;

import m2.miage.m2gestionmembres.entities.Membre;
import m2.miage.m2gestionmembres.services.MembreServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(value = "/membres")
public class MembreController {
    private static final Logger logger = LoggerFactory.getLogger(MembreController.class);

    @Autowired
    private MembreServiceImpl membreServiceImpl;

    @GetMapping(value = "/")
    public ResponseEntity<Membre> ouvrirUnNouveauCompte(){
        try{
            return ResponseEntity.ok(membreServiceImpl.creerUnMembre());
        }catch (Exception e){
            logger.error("Erreur ",e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

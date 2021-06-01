package m2.miage.m2gestionmembres.controllers;

import javassist.NotFoundException;
import m2.miage.m2gestionmembres.Exception.ForbiddenException;
import m2.miage.m2gestionmembres.entities.Membre;
import m2.miage.m2gestionmembres.services.MembreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping( "/membres")
public class MembreController {
    private static final Logger logger = LoggerFactory.getLogger(MembreController.class);

    @Autowired
    private MembreService membreService;

    @GetMapping(value = "/allMembre")
    private ResponseEntity<?> getAllMembre(@RequestParam("emailRequester") String emailRequester) {
        try{
            return new ResponseEntity<>(membreService.getAllMembre(emailRequester), HttpStatus.OK);
        }  catch (NotFoundException e){
            return new ResponseEntity<>("Membre d'email "+emailRequester+" introuvable!", HttpStatus.FORBIDDEN);
        } catch (ForbiddenException e){
            return new ResponseEntity<>("Utilisateur d'email "+emailRequester+" ne possède pas le droit pour cette action!", HttpStatus.FORBIDDEN);
        } catch (Exception e){
            logger.error("Erreur ",e);
            return new ResponseEntity<>("Une erreur est survenue", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{email}")
    private ResponseEntity<?> getMembreByEmail(@PathVariable("email") String email) {
        try{
            return new ResponseEntity<>(membreService.getMembreByEmail(email), HttpStatus.OK);
        } catch (NotFoundException e){
            //return new ResponseEntity<>("Membre d'email "+email+" introuvable!", HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            logger.error("Erreur ",e);
            return new ResponseEntity<>("Une erreur est survenue", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> creerMembre(@RequestBody Membre membre){
        try{
            return ResponseEntity.ok(membreService.creerMembre(membre));
        }catch (ForbiddenException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
        catch (Exception e){
            logger.error("Erreur ",e);
            return new ResponseEntity<>("Une erreur est survenue", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/maj/{email}")
    public ResponseEntity<?> mettreAJourMembre(@RequestParam("emailRequester") String emailRequester, @PathVariable("email") String email, @RequestBody Membre membre){
        try{
            return ResponseEntity.ok(membreService.mettreAJourMembre(emailRequester, email, membre));
        }catch (ForbiddenException e){
            return new ResponseEntity<>("Utilisateur d'email "+emailRequester+" ne possède pas le droit pour cette action!", HttpStatus.FORBIDDEN);
        }
        catch (Exception e){
            logger.error("Erreur ",e);
            return new ResponseEntity<>("Une erreur est survenue", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/delete/{email}")
    public ResponseEntity<?> supprimerUnMembre(@RequestParam("emailRequester") String emailRequester, @PathVariable("email") String email){
        try{
            if(membreService.supprimerMembre(emailRequester, email)){
                return ResponseEntity.ok("Supression réussie !");
            }
            return new ResponseEntity<>("Une erreur est survenue lors de la suppression.",HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (ForbiddenException e){
            return new ResponseEntity<>("Utilisateur d'email "+emailRequester+" ne possède pas le droit pour cette action!", HttpStatus.FORBIDDEN);
        }
        catch (Exception e){
            logger.error("Erreur ",e);
            return new ResponseEntity<>("Une erreur est survenue", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package m2.miage.m2gestionmembres.controllers;

import javassist.NotFoundException;
import m2.miage.m2gestionmembres.Exception.ForbiddenException;
import m2.miage.m2gestionmembres.Exception.GeneralErreurException;
import m2.miage.m2gestionmembres.entities.Membre;
import m2.miage.m2gestionmembres.entities.Operation;
import m2.miage.m2gestionmembres.services.MembreService;
import m2.miage.m2gestionmembres.services.OperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping( "/membres")
public class MembreController {
    private static final Logger logger = LoggerFactory.getLogger(MembreController.class);

    @Autowired
    private MembreService membreService;

    @Autowired
    private OperationService operationService;

    @GetMapping(value = "/allMembre")
    private ResponseEntity<List<Membre>> getAllMembre(@RequestParam("emailRequester") String emailRequester) throws ForbiddenException, NotFoundException, GeneralErreurException {
        try{
            return new ResponseEntity<>(membreService.getAllMembre(emailRequester), HttpStatus.OK);
        }  catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (ForbiddenException e){
            throw new ForbiddenException(e.getMessage());
        } catch (Exception e){
            logger.error("Erreur ",e);
            throw new GeneralErreurException();
        }
    }

    @GetMapping(value = "/{email}")
    private ResponseEntity<Membre> getMembreByEmail(@PathVariable("email") String email) throws NotFoundException, GeneralErreurException {
        try{
            return new ResponseEntity<>(membreService.getMembreByEmail(email), HttpStatus.OK);
        } catch (NotFoundException e){
            //return new ResponseEntity<>("Membre d'email "+email+" introuvable!", HttpStatus.NOT_FOUND);
            throw new NotFoundException(e.getMessage());
        }
        catch (Exception e){
            logger.error("Erreur ",e);
            throw new GeneralErreurException();
        }
    }

    @PostMapping(value = "/")
    public ResponseEntity<Membre> creerMembre(@RequestBody Membre membre) throws GeneralErreurException, ForbiddenException {
        try{
            return ResponseEntity.ok(membreService.creerMembre(membre));
        }catch (ForbiddenException e){
            throw new ForbiddenException(e.getMessage());
        }
        catch (Exception e){
            logger.error("Erreur ",e);
            throw new GeneralErreurException();
        }
    }

    @PostMapping(value = "/maj/{email}")
    public ResponseEntity<Membre> mettreAJourMembre(@RequestParam("emailRequester") String emailRequester, @PathVariable("email") String email, @RequestBody Membre membre) throws GeneralErreurException, ForbiddenException {
        try{
            return ResponseEntity.ok(membreService.mettreAJourMembre(emailRequester, email, membre));
        }catch (ForbiddenException e){
            throw new ForbiddenException(e.getMessage());
        }
        catch (Exception e){
            logger.error("Erreur ",e);
            throw new GeneralErreurException();
        }
    }

    @PostMapping("/member/pay")
    ResponseEntity<Operation> paiement(@RequestParam("emailMembre")String emailMembre, @RequestParam("iban") String iban, @RequestParam("montant") String montant) throws NotFoundException, ForbiddenException, GeneralErreurException {
        try {
            double montantInDouble = Double.parseDouble(montant);
            return new ResponseEntity<>(operationService.paiement(emailMembre, iban, montantInDouble), HttpStatus.OK);
        } catch (NotFoundException exception) {
            throw new NotFoundException(exception.getMessage());
        } catch (ForbiddenException exception) {
            throw new ForbiddenException(exception.getMessage());
        } catch (Exception e){
            logger.error("Erreur ",e);
            throw new GeneralErreurException();
        }
    }

    @DeleteMapping(value = "/delete/{email}")
    public ResponseEntity<String> supprimerUnMembre(@RequestParam("emailRequester") String emailRequester, @PathVariable("email") String email) throws ForbiddenException, GeneralErreurException {
        try{
            if(membreService.supprimerMembre(emailRequester, email)){
                return ResponseEntity.ok("Supression réussie !");
            }
            throw new GeneralErreurException();
        }catch (ForbiddenException e){
            throw new ForbiddenException("Utilisateur d'email \"+emailRequester+\" ne possède pas le droit pour cette action!");
        }
        catch (Exception e){
            logger.error("Erreur ",e);
            throw new GeneralErreurException();
        }
    }

    @GetMapping(value = "/isApte/{email}")
    private ResponseEntity<?> isMembreApte(@PathVariable("email") String email) {
        try{
            if(membreService.isMembreApte(email)){
                return new ResponseEntity<>("Le membre est bien apte ! :)", HttpStatus.OK);
            }
            return new ResponseEntity<>("Le membre est non apte. :(", HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>("Membre d'email "+email+" introuvable!", HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            logger.error("Erreur ",e);
            return new ResponseEntity<>("Une erreur est survenue", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

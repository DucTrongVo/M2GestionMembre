package m2.miage.m2gestionmembres.controllers;

import javassist.NotFoundException;
import m2.miage.m2gestionmembres.Exception.ForbiddenException;
import m2.miage.m2gestionmembres.Exception.GeneralErreurException;
import m2.miage.m2gestionmembres.entities.Operation;
import m2.miage.m2gestionmembres.services.OperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping( "/paiement")
public class OperationController {
    private static final Logger logger = LoggerFactory.getLogger(OperationController.class);

    @Autowired
    private OperationService operationService;

    @GetMapping("/getAll")
    ResponseEntity<?> getAllOperation(@RequestParam("emailSec") String emailSec) throws NotFoundException, ForbiddenException {
        try {
            return new ResponseEntity<>(operationService.getAllOperation(emailSec), HttpStatus.OK);
        } catch (NotFoundException exception) {
            throw new NotFoundException(exception.getMessage());
        } catch (ForbiddenException e) {
            throw new ForbiddenException(e.getMessage());
        } catch (Exception e){
            logger.error("Erreur ",e);
            return new ResponseEntity<>("Une erreur est survenue", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*@PostMapping("/member/pay")
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
    }*/

    @PostMapping("/validatePayment/{idPayment}")
    ResponseEntity<?> validatePayment(@RequestParam("emailSec") String emailSec, @PathVariable("idPayment") String idOperation) throws NotFoundException, ForbiddenException {
        try {
            Integer idOp = Integer.parseInt(idOperation);
            return new ResponseEntity<>(operationService.validatePaiement(emailSec, idOp), HttpStatus.OK);
        } catch (NotFoundException exception) {
            throw new NotFoundException(exception.getMessage());
        }  catch (ForbiddenException exception) {
            throw new ForbiddenException(exception.getMessage());
        } catch (Exception e){
            logger.error("Erreur ",e);
            return new ResponseEntity<>("Une erreur est survenue", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/refusePayment/{idPayment}")
    ResponseEntity<?> refusePayment(@RequestParam("emailSec") String emailSec, @PathVariable("idPayment") String idOperation) throws NotFoundException, ForbiddenException {
        try {
            Integer idOp = Integer.parseInt(idOperation);
            return new ResponseEntity<>(operationService.refusePaiement(emailSec, idOp), HttpStatus.OK);
        } catch (NotFoundException exception) {
            throw new NotFoundException(exception.getMessage());
        }  catch (ForbiddenException exception) {
            throw new ForbiddenException(exception.getMessage());
        } catch (Exception e){
            logger.error("Erreur ",e);
            return new ResponseEntity<>("Une erreur est survenue", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

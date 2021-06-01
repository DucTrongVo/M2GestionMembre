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

import java.util.List;

@Controller
@RequestMapping( "/paiement")
public class OperationController {
    private static final Logger logger = LoggerFactory.getLogger(OperationController.class);

    @Autowired
    private OperationService operationService;

    @GetMapping("/getAll")
    ResponseEntity<List<Operation>> getAllOperation(@RequestParam("emailSec") String emailSec) throws NotFoundException, ForbiddenException, GeneralErreurException {
        try {
            return new ResponseEntity<>(operationService.getAllOperation(emailSec), HttpStatus.OK);
        } catch (NotFoundException exception) {
            throw new NotFoundException(exception.getMessage());
        } catch (ForbiddenException e) {
            throw new ForbiddenException(e.getMessage());
        } catch (Exception e){
            logger.error("Erreur ",e);
            throw new GeneralErreurException();
        }
    }

    @PostMapping("/validerPaiement/{idPaiement}")
    ResponseEntity<Operation> validatePayment(@RequestParam("emailSec") String emailSec, @PathVariable("idPaiement") String idOperation) throws NotFoundException, ForbiddenException, GeneralErreurException {
        try {
            Integer idOp = Integer.parseInt(idOperation);
            return new ResponseEntity<>(operationService.validerPaiement(emailSec, idOp), HttpStatus.OK);
        } catch (NotFoundException exception) {
            throw new NotFoundException(exception.getMessage());
        }  catch (ForbiddenException exception) {
            throw new ForbiddenException(exception.getMessage());
        } catch (Exception e){
            logger.error("Erreur ",e);
            throw new GeneralErreurException();
        }
    }

    @PostMapping("/refuserPaiement/{idPaiement}")
    ResponseEntity<Operation> refusePayment(@RequestParam("emailSec") String emailSec, @PathVariable("idPaiement") String idOperation) throws NotFoundException, ForbiddenException, GeneralErreurException {
        try {
            Integer idOp = Integer.parseInt(idOperation);
            return new ResponseEntity<>(operationService.refuserPaiement(emailSec, idOp), HttpStatus.OK);
        } catch (NotFoundException exception) {
            throw new NotFoundException(exception.getMessage());
        }  catch (ForbiddenException exception) {
            throw new ForbiddenException(exception.getMessage());
        } catch (Exception e){
            logger.error("Erreur ",e);
            throw new GeneralErreurException();
        }
    }
}

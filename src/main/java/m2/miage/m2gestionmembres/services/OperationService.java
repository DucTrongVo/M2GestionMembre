package m2.miage.m2gestionmembres.services;

import javassist.NotFoundException;
import m2.miage.m2gestionmembres.Exception.ForbiddenException;
import m2.miage.m2gestionmembres.entities.Operation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OperationService {
    List<Operation> getAllOperation(String emailSec) throws NotFoundException, ForbiddenException;
    @Transactional
    Operation creerPaiement(String emailMembre, String iban, double montant) throws NotFoundException, ForbiddenException;
    @Transactional
    Operation validerPaiement(String emailSec, Integer idOperation) throws NotFoundException, ForbiddenException;
    @Transactional
    Operation refuserPaiement(String emailSec, Integer idOperation) throws NotFoundException, ForbiddenException;
    List<Operation> getOperationByMembre(String emailSec, String emailMembre) throws NotFoundException, ForbiddenException;
}

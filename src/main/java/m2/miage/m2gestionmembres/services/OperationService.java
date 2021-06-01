package m2.miage.m2gestionmembres.services;

import javassist.NotFoundException;
import m2.miage.m2gestionmembres.Exception.ForbiddenException;
import m2.miage.m2gestionmembres.entities.Operation;

import java.util.List;

public interface OperationService {
    List<Operation> getAllOperation(String emailSec) throws NotFoundException, ForbiddenException;
    Operation paiement(String emailMembre, String iban, double montant) throws NotFoundException, ForbiddenException;
    Operation validatePaiement(String emailSec, Integer idOperation) throws NotFoundException, ForbiddenException;
    Operation refusePaiement(String emailSec, Integer idOperation) throws NotFoundException, ForbiddenException;
}

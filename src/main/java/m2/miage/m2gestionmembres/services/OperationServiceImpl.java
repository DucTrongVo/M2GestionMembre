package m2.miage.m2gestionmembres.services;

import javassist.NotFoundException;
import m2.miage.m2gestionmembres.Exception.ForbiddenException;
import m2.miage.m2gestionmembres.controllers.MembreController;
import m2.miage.m2gestionmembres.entities.Membre;
import m2.miage.m2gestionmembres.entities.Operation;
import m2.miage.m2gestionmembres.enums.EnumEtatPaiement;
import m2.miage.m2gestionmembres.enums.EnumEtatUtilisateur;
import m2.miage.m2gestionmembres.enums.EnumTypeUtilisateur;
import m2.miage.m2gestionmembres.repositories.MembreRepo;
import m2.miage.m2gestionmembres.repositories.OperationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class OperationServiceImpl implements OperationService{
    private static final Logger logger = LoggerFactory.getLogger(OperationServiceImpl.class);

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private MembreRepo membreRepo;

    @Autowired
    private IRightService rightService;

    @Override
    public List<Operation> getAllOperation(String emailSec) throws NotFoundException, ForbiddenException {
        Optional<Membre> membre = membreRepo.findMembreByMail(emailSec);
        if (membre.isEmpty()) {
            logger.error("Membre d'email {} introuvable!", emailSec);
            throw new NotFoundException("Membre d'email "+emailSec+" introuvable!");
        }
        if (rightService.checkRight(membre.get(),EnumTypeUtilisateur.SECRETAIRE.name())){
            return operationRepository.findAll();
        } else {
            logger.warn("Utilisateur d'email {} ne possède pas le droit pour cette action!", emailSec);
            throw new ForbiddenException("Utilisateur d'email "+emailSec+" ne possède pas le droit pour cette action!");
        }
    }

    @Override
    @Transactional
    public Operation paiement(String emailMembre, String iban, double montant) throws NotFoundException, ForbiddenException {
        Optional<Membre> membre = membreRepo.findMembreByMail(emailMembre);
        if (membre.isEmpty()) {
            logger.error("Membre d'email {} introuvable!", emailMembre);
            throw new NotFoundException("Membre d'email "+emailMembre+" introuvable!");
        }
        if (rightService.checkRight(membre.get(),EnumTypeUtilisateur.MEMBRE.name())) {
            Operation operation = Operation.builder().membre(membre.get()).iban(iban).montant(montant)
                .dateVerify(Calendar.getInstance()).status(EnumEtatPaiement.EN_ATTEND.name())
                .build();
            return operationRepository.save(operation);
        } else {
            logger.warn("Seulement un MEMBRE peut réaliser cette action! (Vous êtes {}", membre.get().getType()+ ")");
            throw new ForbiddenException("Seulement un MEMBRE peut réaliser cette action. (Vous êtes "+membre.get().getType()+")");
        }
    }

    @Override
    @Transactional
    public Operation validatePaiement(String emailSec, Integer idOperation) throws NotFoundException, ForbiddenException {
        Operation operation = getOperationById(idOperation);
        Optional<Membre> membre = membreRepo.findMembreByMail(emailSec);
        if (membre.isEmpty()) {
            logger.error("Membre d'email {} introuvable!", operation.getMembre().getMail());
            throw new NotFoundException("Membre d'email "+operation.getMembre().getMail()+" introuvable!");
        }
        if (rightService.checkRight(membre.get(), EnumTypeUtilisateur.SECRETAIRE.name())){
            operation.setStatus(EnumEtatPaiement.ACCEPTED.name());
            membre.get().setEtat(EnumEtatUtilisateur.REGLE.name());
            membreRepo.save(membre.get());
            return operationRepository.save(operation);
        } else {
            logger.warn("Utilisateur d'email {} ne possède pas le droit pour cette action!", emailSec);
            throw new ForbiddenException("Utilisateur d'email "+emailSec+" ne possède pas le droit pour cette action!");
        }
    }

    @Override
    @Transactional
    public Operation refusePaiement(String emailSec, Integer idOperation) throws NotFoundException, ForbiddenException {
        Operation operation = getOperationById(idOperation);
        Optional<Membre> membre = membreRepo.findMembreByMail(emailSec);
        if (membre.isEmpty()) {
            logger.error("Membre d'email {} introuvable!", operation.getMembre().getMail());
            throw new NotFoundException("Membre d'email "+operation.getMembre().getMail()+" introuvable!");
        }
        if (rightService.checkRight(membre.get(), EnumTypeUtilisateur.SECRETAIRE.name())){
            operation.setStatus(EnumEtatPaiement.REFUSED.name());
            return operationRepository.save(operation);
        } else {
            logger.warn("Utilisateur d'email {} ne possède pas le droit pour cette action!", emailSec);
            throw new ForbiddenException("Utilisateur d'email "+emailSec+" ne possède pas le droit pour cette action!");
        }
    }

    private Operation getOperationById(Integer idOperation) throws NotFoundException {
        Optional<Operation> operation = operationRepository.findById(idOperation);
        if (operation.isEmpty()) {
            logger.error("Opération d'id {} introuvable!", idOperation);
            throw new NotFoundException("Opération d'id "+idOperation+" introuvable!");
        }
        else {
            return operation.get();
        }
    }
}

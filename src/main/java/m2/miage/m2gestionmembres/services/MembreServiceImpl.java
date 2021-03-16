package m2.miage.m2gestionmembres.services;

import javassist.NotFoundException;
import m2.miage.m2gestionmembres.Exception.ForbiddenException;
import m2.miage.m2gestionmembres.controllers.MembreController;
import m2.miage.m2gestionmembres.entities.Membre;
import m2.miage.m2gestionmembres.enums.EnumEtatUtilisateur;
import m2.miage.m2gestionmembres.enums.EnumTypeUtilisateur;
import m2.miage.m2gestionmembres.repositories.MembreRepo;
import org.junit.platform.commons.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class MembreServiceImpl implements MembreService{

    private static final Logger logger = LoggerFactory.getLogger(MembreServiceImpl.class);

    @Autowired
    private MembreRepo membreRepo;

    public Membre creerMembre(Membre membre) {
        Membre newMembre  = Membre.builder().nom(membre.getNom()).prenom(membre.getPrenom())
                        .mail(membre.getMail()).mdp(membre.getMdp()).adresse(membre.getAdresse())
                        .type(StringUtils.isBlank(membre.getType()) ? EnumTypeUtilisateur.MEMBRE.name() : membre.getType())
                        .etat(EnumEtatUtilisateur.RETARD.name())
                        .build();
        membreRepo.save(newMembre);
        return membre;
    }

    @Override
    public Membre getMembreByEmail(String email) throws NotFoundException {
        Optional<Membre> membre = membreRepo.findMembreByMail(email);
        if (membre.isEmpty()) {
            logger.error("Membre d'email {} introuvable!", email);
            throw new NotFoundException("Membre d'email "+email+" introuvable!");
        } else {
            return membre.get();
        }
    }

    @Override
    public List<Membre> getAllMembre(String emailRequester) throws NotFoundException, ForbiddenException {
        Membre requester = getMembreByEmail(emailRequester);
        if (requester.getType().equals(EnumTypeUtilisateur.SECRETAIRE.name()) || requester.getType().equals(EnumTypeUtilisateur.PRESIDENT.name())) {
            List<Membre> allMembre = membreRepo.findAll();
            return allMembre;
        } else {
            logger.warn("Utilisateur d'email {} ne possède pas le droit pour cette action!", emailRequester);
            throw new ForbiddenException("Utilisateur d'email "+emailRequester+" ne possède pas le droit pour cette action!");
        }
    }

    @Override
    public Membre mettreAJourMembre(String emailRequester, String email, Membre membreAJour) throws NotFoundException, ForbiddenException {
        Membre requester = getMembreByEmail(emailRequester);
        if (!requester.getType().equals(EnumTypeUtilisateur.SECRETAIRE.name())) {
            logger.warn("Utilisateur d'email {} ne possède pas le droit pour cette action!", emailRequester);
            throw new ForbiddenException("Utilisateur d'email " + emailRequester + " ne possède pas le droit pour cette action!");
        }
        else{
            Optional<Membre> ancienMembre = membreRepo.findMembreByMail(email);
            if (!StringUtils.isBlank(membreAJour.getEtat())) {
                ancienMembre.get().setEtat(membreAJour.getEtat());
            }
            if (!StringUtils.isBlank(membreAJour.getType())) {
                ancienMembre.get().setType(membreAJour.getType());
            }
            if (!StringUtils.isBlank(membreAJour.getNumLicence())) {
                ancienMembre.get().setNumLicence(membreAJour.getNumLicence());
            }
            if (membreAJour.getNiveau() != null) {
                ancienMembre.get().setNiveau(membreAJour.getNiveau());
            }

            if (membreAJour.getDateCertif() != null) {
                String dateFormat = "dd-yMM-yyyy HH:mm:ss";
                //LocalDateTime dateString = membreAJour.getDateCertif().format(DateTimeFormatter.ofPattern(dateFormat));

                ancienMembre.get().setDateCertif(membreAJour.getDateCertif());
            }
            return membreRepo.save(ancienMembre.get());
        }
    }


}

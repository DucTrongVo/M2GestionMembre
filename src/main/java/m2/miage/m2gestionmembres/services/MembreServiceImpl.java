package m2.miage.m2gestionmembres.services;

import javassist.NotFoundException;
import m2.miage.m2gestionmembres.Exception.ForbiddenException;
import m2.miage.m2gestionmembres.entities.Membre;
import m2.miage.m2gestionmembres.enums.EnumEtatUtilisateur;
import m2.miage.m2gestionmembres.enums.EnumTypeUtilisateur;
import m2.miage.m2gestionmembres.repositories.MembreRepo;
import org.junit.platform.commons.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MembreServiceImpl implements MembreService{

    private static final Logger logger = LoggerFactory.getLogger(MembreServiceImpl.class);

    @Autowired
    private MembreRepo membreRepo;

    public Membre creerMembre(Membre membre) throws ForbiddenException {
        String email = membre.getMail();
        Optional<Membre> membreEnBase = membreRepo.findMembreByMail(email);
        if(!membreEnBase.isEmpty()){
            throw new ForbiddenException("Création impossible : l'email "+email+" est déja utilisé.");
        }
        else {
            Membre newMembre = Membre.builder().nom(membre.getNom()).prenom(membre.getPrenom())
                    .mail(membre.getMail()).mdp(membre.getMdp()).adresse(membre.getAdresse())
                    .type(StringUtils.isBlank(membre.getType()) ? EnumTypeUtilisateur.MEMBRE.name() : membre.getType())
                    .etat(EnumEtatUtilisateur.RETARD.name()).niveau(membre.getNiveau() == null ? 1 : membre.getNiveau())
                    .dateCertif(membre.getDateCertif()).numLicence(membre.getNumLicence())
                    .build();
            membreRepo.save(newMembre);
            return membre;
        }
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
            return membreRepo.findAll();
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

    @Override
    public Boolean supprimerMembre(String emailRequester, String emailMembreToDelete) throws NotFoundException, ForbiddenException {
        Membre requester = getMembreByEmail(emailRequester);
        if (!requester.getType().equals(EnumTypeUtilisateur.SECRETAIRE.name()) && !requester.getType().equals(EnumTypeUtilisateur.PRESIDENT.name())) {
            logger.warn("Utilisateur d'email {} ne possède pas le droit pour cette action!", emailRequester);
            throw new ForbiddenException("Utilisateur d'email " + emailRequester + " ne possède pas le droit pour cette action!");
        }
        else {
            Optional<Membre> membreToDelete = membreRepo.findMembreByMail(emailMembreToDelete);
            if(membreToDelete.isEmpty()){
                throw new NotFoundException("L'email "+emailMembreToDelete+"  n'existe pas");
            }
            membreRepo.delete(membreToDelete.get());
            return true;
        }
    }

    @Override
    public Boolean isMembreApte(String emailMembre) throws NotFoundException {
        Optional<Membre> membre = membreRepo.findMembreByMail(emailMembre);
        if(membre.isEmpty()){
            logger.error("Membre d'email "+emailMembre+" introuvable!");
            throw new NotFoundException("Membre d'email "+emailMembre+" introuvable!");
        }
        Calendar calendar = membre.get().getDateCertif();
        if (calendar == null){
            logger.error("Date certification pas encore transmet!");
            throw new NotFoundException("Date certification pas encore transmet!");
        }
        Date dateCertif = calendar.getTime();
        Date today = new Date();
        long difference = today.getTime() - dateCertif.getTime();
        float nbJours = (difference/ (1000*60*60*24));
        return nbJours < 365;
    }


}

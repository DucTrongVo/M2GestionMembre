package m2.miage.m2gestionmembres.entities;

import m2.miage.m2gestionmembres.enums.EnumEtatUtilisateur;
import m2.miage.m2gestionmembres.enums.EnumTypeUtilisateur;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Membre {

    @Id
    private String pseudo;

    private String nom;

    private String prenom;

    private String mail;

    private String mdp;

    private String adresse;

    private Date dateCertif;

    private Integer niveau;

    private Integer numLicence;

    private EnumTypeUtilisateur type;

    private EnumEtatUtilisateur etat;

    public Membre(String pseudo, String nom, String mdp){
        this.pseudo=pseudo;
        this.nom=nom;
        this.mdp=mdp;
        this.type = EnumTypeUtilisateur.MEMBRE;
        this.etat= EnumEtatUtilisateur.EN_RETARD_DE_PAIEMENT;
    }

    public Membre() {

    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Date getDateCertif() {
        return dateCertif;
    }

    public void setDateCertif(Date dateCertif) {
        this.dateCertif = dateCertif;
    }

    public Integer getNiveau() {
        return niveau;
    }

    public void setNiveau(Integer niveau) {
        this.niveau = niveau;
    }

    public Integer getNumLicence() {
        return numLicence;
    }

    public void setNumLicence(Integer numLicence) {
        this.numLicence = numLicence;
    }

    public EnumTypeUtilisateur getType() {
        return type;
    }

    public void setType(EnumTypeUtilisateur type) {
        this.type = type;
    }

    public EnumEtatUtilisateur getEtat() {
        return etat;
    }

    public void setEtat(EnumEtatUtilisateur etat) {
        this.etat = etat;
    }
}

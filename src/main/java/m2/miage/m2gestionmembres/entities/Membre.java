package m2.miage.m2gestionmembres.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@SuperBuilder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "membre")
public class Membre implements Serializable {

    private static final long serialVersionUID = -7499754709735547940L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "membre_generator", sequenceName = "membre_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "mail")
    private String mail;

    @Column(name = "mdp")
    private String mdp;

    @Column(name = "adresse")
    private String adresse;

    /**
     * date validité de la derniere certification
     */
    @Column(name = "dateCertif")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Calendar dateCertif;

    /**
     * niveau de classe
     */
    @Column(name = "niveau")
    private Integer niveau;

    /**
     * numé de licence
     */
    @Column(name = "numLicence")
    private String numLicence;

    /**
     * Type d'utilisateur
     */
    @Column(name = "type")
    private String type;

    /**
     * Etat du paiement
     */
    @Column(name = "etat")
    private String etat;
}

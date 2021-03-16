package m2.miage.m2gestionmembres.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

@SuperBuilder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "membre")
public class Membre {

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

    @Column(name = "dateCertif")
    private LocalDateTime dateCertif;

    @Column(name = "niveau")
    private Integer niveau;

    @Column(name = "numLicence")
    private String numLicence;

    @Column(name = "type")
    private String type;

    @Column(name = "etat")
    private String etat;
}

package m2.miage.m2gestionmembres.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Statistique implements Serializable
{
    private static final long serialVersionUID = -6594748129211872536L;

    private int nombreMembres;

    private int nombreEnseignants;

    private int totalCotisationPrevues;

    private int totalCotisationReglees;
}

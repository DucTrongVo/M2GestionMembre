package m2.miage.m2gestionmembres.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Apte implements Serializable {
    private static final long serialVersionUID = 8331130190807748979L;

    private boolean apte;

    private String description;
}

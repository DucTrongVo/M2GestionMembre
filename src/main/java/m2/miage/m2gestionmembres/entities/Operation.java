package m2.miage.m2gestionmembres.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Calendar;

@SuperBuilder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "operation")
public class Operation implements Serializable {

    private static final long serialVersionUID = 8187970757069569031L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "operation_generator", sequenceName = "operation_seq", allocationSize = 1)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "idMembre")
    private Membre membre;

    @Column(name = "iban")
    private String iban;

    @Column(name = "montant")
    private double montant;

    @Column(name = "status")
    private String status;

    @Column(name = "dateVerify")
//    @Temporal(TemporalType.TIMESTAMP)
//    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private String dateVerify;
}

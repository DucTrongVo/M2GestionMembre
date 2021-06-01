package m2.miage.m2gestionmembres.enums;

public enum EnumEtatPaiement {
    EN_ATTEND("En attend de vérification"),
    REFUSED("Paiement refusé"),
    ACCEPTED("Paiement accepté");

    private final String value;
    EnumEtatPaiement(String value) {
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}

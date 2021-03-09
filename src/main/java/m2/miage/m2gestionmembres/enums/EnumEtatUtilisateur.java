package m2.miage.m2gestionmembres.enums;

public enum EnumEtatUtilisateur {
    RETARD("En retard de paiement"),
    REGLE("En règle");

    private final String value;
    EnumEtatUtilisateur(String value) {
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}

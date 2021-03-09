package m2.miage.m2gestionmembres.enums;

public enum EnumTypeUtilisateur {
    MEMBRE("Un membre flingué"),
    ENSEIGNANT("Enseignant...."),
    SECRETAIRE("Sécrétaire aka Justine"),
    PRESIDENT("Soit Z soit Marquie");

    private final String value;
    EnumTypeUtilisateur(String value) {
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}

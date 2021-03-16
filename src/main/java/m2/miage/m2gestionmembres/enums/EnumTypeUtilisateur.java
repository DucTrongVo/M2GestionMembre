package m2.miage.m2gestionmembres.enums;

public enum EnumTypeUtilisateur {
    MEMBRE("Un membre"),
    ENSEIGNANT("Un enseignant"),
    SECRETAIRE("Un sécrétaire"),
    PRESIDENT("Le président");

    private final String value;
    EnumTypeUtilisateur(String value) {
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}

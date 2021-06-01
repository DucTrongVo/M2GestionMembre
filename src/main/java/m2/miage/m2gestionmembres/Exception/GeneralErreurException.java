package m2.miage.m2gestionmembres.Exception;

public class GeneralErreurException extends Exception{
    private static final long serialVersionUID = 7909298202875287973L;

    public GeneralErreurException() {
        String s = "Une erreur est survenue. Veuillez contactez notre développeur pour plus d'informaiton!";
    }

    public GeneralErreurException(Throwable t) {super(t);}
}

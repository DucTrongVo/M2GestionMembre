package m2.miage.m2gestionmembres.Exception;

public class ForbiddenException extends Exception{
    private static final long serialVersionUID = 5450360906116005075L;

    public ForbiddenException(String s) { super(s);}

    public ForbiddenException(Throwable t) {super(t);}
}

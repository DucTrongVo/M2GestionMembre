package m2.miage.m2gestionmembres.Exception;

public class NotFoundException extends Exception{
    private static final long serialVersionUID = 9138628445159698786L;

    public NotFoundException(String s) { super(s);}

    public NotFoundException(Throwable t) {super(t);}
}

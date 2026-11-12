package domain;

public class EcoSafariException extends Exception {
    // public static final String ON_CONSTRUCTION = "";
    public static final String GENERAL_ERROR = "Se ha presentado un error general.";

    public EcoSafariException(String ms){
        super(ms);
    }
}

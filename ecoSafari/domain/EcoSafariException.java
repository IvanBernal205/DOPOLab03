package domain;

public class EcoSafariException extends Exception {
    // public static final String ON_CONSTRUCTION = "";
    public static final String GENERAL_ERROR = "Se ha presentado un error general.";
    public static final String INVALID_EXTENSION_DAT = "La extension del archivo debe ser .dat";
    public static final String FILE_NOT_FOUND = "No se encontro el archivo.";
    public static final String NOT_SERIALIZABLE = "No se permite guardar el archivo porque hay elementos invalidos.";
    public static final String ERROR_WHILE_WRITING = "Ocurrio un error mientras se escribia el archivo.";

    public static final String CLASS_NOT_FOUND = "Una clase no existe dentro del archivo.";
    public static final String INCOMPATIBLE_VERSION = "El archivo corresponde a otra version.";
    public static final String ERROR_READING = "Ocurrio un error mientras se leia el archivo.";

    public static final String INVALID_EXTENSION_TXT = "La extension del archivo debe ser .txt";
    public static final String CREATE_FILE_NOT_POSSIBLE = "No fue posible crear el archivo.";
    public static final String INVALID_NUMBER = "Se esperaba un numero.";
    public static final String INCOMPLET_FORMAT = "Alguna de las lineas no esta completa.";

    public EcoSafariException(String ms){
        super(ms);
    }
}

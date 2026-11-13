package domain;

import java.io.*;
import java.util.Scanner;

/**
 * The EcoSafari class represents the environment where the simulation takes place.
 * It contains a grid of entities and manages their interactions.
 * 
 * @author Ivan Andres Bernal Sabogal
 * @author César Santiago Malaver Garnica 
 * @version 26-09-2026
 */
public class EcoSafari implements Serializable{

    private static final int SIZE=25;
    private Entity[][] cells;
    
    /**
     * Constructs a new EcoSafari
     */
    public EcoSafari() {
        cells=new Entity[SIZE][SIZE];
    }
    
    /**
     * Returns the size of the EcoSafari 
     * @return 
     */
    public int  getSize(){
        return SIZE;
    }

    /**
     * Determines whether a position is inside the EcoSafari
     * @param r the row
     * @param c the column
     * @return 
     */
    public boolean isInside(int r, int c){
        return ((0<=r) && (r<SIZE) && (0<=c) && (c<SIZE));
    }
    
    /**
     * Returns the entity located at a specified position
     * @param r the row
     * @param c the column
     * @return 
     */
    public Entity get(int r,int c){
        return (isInside(r,c)? cells[r][c]: null);
    }

    /**
     * Places an entity at a specified position
     * @param r the row
     * @param c the column
     */
    public void set(Entity e, int r, int c){
        if (isInside(r,c)){ 
            cells[r][c]=e;
        }
    }

    
    /**
     * Finds the position of a specified entity
     * @param e the entity
     * @return an array {row, column} if the entity is found. null otherwise
     */
    public int[]  find(Entity e){
        int[] position=null;
        for (int r=0 ; r<SIZE && position== null; r++){
            for (int c=0 ; c<SIZE && position==null ;c++){
                if (cells[r][c]==e){
                    position=new int [] {r,c};
                }
            }
        }
        return position;
    }
    

    /**
     * Advances the simulation by one time step
     */
    //First, all entities execute their tic() action
    //Then, all entities execute their tac() actions
    public void ticTac(){  
        for (Entity[] row: cells){ 
            for (Entity e : row){
                if (e !=null) e.tic();
            }
        }
        
        for (Entity[] row: cells){
            for (Entity e : row){
                if(e!=null) e.tac();
            }
        }
    }

    /**
     * Opens a file
     * @param file
     * @return true if the action was succesfull, else false
     * @throws EcoSafariException
     */
    public static boolean open00(File file) throws EcoSafariException{
        throw new EcoSafariException("Opcion Open en construcción. Archivo " + file.getName());
    }

    public static EcoSafari open01(File file) throws EcoSafariException{
        ObjectInputStream toOpen = null;
        try{
            toOpen = new ObjectInputStream(new FileInputStream(file));
            return (EcoSafari) toOpen.readObject();
        } catch (IOException | ClassNotFoundException e){
            throw new EcoSafariException(EcoSafariException.GENERAL_ERROR); 
        } finally {
            if (toOpen != null) {
                try{
                    toOpen.close();
                } catch (IOException e){
                    throw new EcoSafariException(EcoSafariException.GENERAL_ERROR);
                }
            }
        }
    }

    public static EcoSafari open(File file) throws EcoSafariException{
        if (!file.getName().endsWith(".dat")) throw new EcoSafariException(EcoSafariException.INVALID_EXTENSION_DAT);
        ObjectInputStream toOpen = null;
        try{
            toOpen = new ObjectInputStream(new FileInputStream(file));
            return (EcoSafari) toOpen.readObject();
        } catch (FileNotFoundException e) {
            throw new EcoSafariException(EcoSafariException.FILE_NOT_FOUND);
        } catch (InvalidClassException e) {
            throw new EcoSafariException(EcoSafariException.INCOMPATIBLE_VERSION);
        } catch (ClassNotFoundException e) {
            throw new EcoSafariException(EcoSafariException.CLASS_NOT_FOUND);
        } catch (IOException e) {
            throw new EcoSafariException(EcoSafariException.ERROR_READING);
        } finally {
            if (toOpen != null) {
                try{
                    toOpen.close();
                } catch (IOException e){
                    throw new EcoSafariException(EcoSafariException.ERROR_READING);
                }
            }
        }
    }

    /**
     * Saves a file
     * @param file
     * @return true if the action was succesfull, else false
     * @throws EcoSafariException
     */
    public static  boolean saveAs00(File file) throws EcoSafariException{
        throw new EcoSafariException("Opcion Save As en construcción. Archivo " + file.getName());
    }

    public static void saveAs01(File file, EcoSafari safari) throws EcoSafariException{
        ObjectOutputStream toSave = null;
        try{
            toSave = new ObjectOutputStream(new FileOutputStream(file));
            toSave.writeObject(safari);
        } catch (IOException e){
            throw new EcoSafariException(EcoSafariException.GENERAL_ERROR);
        } finally {
            if (toSave != null) {
                try{
                    toSave.close();
                } catch (IOException e){
                    throw new EcoSafariException(EcoSafariException.GENERAL_ERROR);
                }
            }
        }
    }

    public static void saveAs(File file, EcoSafari safari) throws EcoSafariException{
        if (!file.getName().endsWith(".dat")) throw new EcoSafariException(EcoSafariException.INVALID_EXTENSION_DAT);
        ObjectOutputStream toSave = null;
        try{
            toSave = new ObjectOutputStream(new FileOutputStream(file));
            toSave.writeObject(safari);
        } catch (FileNotFoundException e) {
            throw new EcoSafariException(EcoSafariException.FILE_NOT_FOUND);
        } catch (NotSerializableException e) {
            throw new EcoSafariException(EcoSafariException.NOT_SERIALIZABLE);
        } catch (IOException e) {
            throw new EcoSafariException(EcoSafariException.ERROR_WHILE_WRITING);
        } finally {
            if (toSave != null) {
                try{
                    toSave.close();
                } catch (IOException e){
                    throw new EcoSafariException(EcoSafariException.GENERAL_ERROR);
                }
            }
        }
    }

    /**
     * Imports a file
     * @param file
     * @return true if the action was succesfull, else false
     * @throws EcoSafariException
     */
    public static boolean import00(File file) throws EcoSafariException{
        throw new EcoSafariException("Opcion import en construcción. Archivo " + file.getName());
    }

    public static void import01(File file, EcoSafari safari) throws EcoSafariException{
        Scanner scan = null;
        try{
            scan = new Scanner(file);
            while (scan.hasNextLine()){
                String line = scan.nextLine().trim();
                if (!line.isEmpty()){
                    //0 Clase 1 row 2 column
                    String[] parts = line.split(" ");
                    String type = parts[0];
                    int r = Integer.parseInt(parts[1].replace(",", ""));
                    int c = Integer.parseInt(parts[2]);
                    addEntities(type,safari, r, c);

                }
            }
        } catch (FileNotFoundException | ArrayIndexOutOfBoundsException | NumberFormatException e){
            throw new EcoSafariException(EcoSafariException.GENERAL_ERROR);
        } finally {
            if (scan != null) scan.close();
        }
    }

    public static void import_(File file, EcoSafari safari) throws EcoSafariException{
        if (!file.getName().endsWith(".txt")) throw new EcoSafariException(EcoSafariException.INVALID_EXTENSION_TXT);
        Scanner scan = null;
        try{
            scan = new Scanner(file);
            while (scan.hasNextLine()){
                String line = scan.nextLine().trim();
                if (!line.isEmpty()){
                    //0 Clase 1 row 2 column
                    String[] parts = line.split(" ");
                    String type = parts[0];
                    int r = Integer.parseInt(parts[1].replace(",", ""));
                    int c = Integer.parseInt(parts[2]);
                    addEntities(type,safari, r, c);

                }
            }
        } catch (FileNotFoundException  e){
            throw new EcoSafariException(EcoSafariException.FILE_NOT_FOUND);
        } catch (NumberFormatException e){
            throw new EcoSafariException(EcoSafariException.INVALID_NUMBER);
        } catch (ArrayIndexOutOfBoundsException e){
            throw new EcoSafariException(EcoSafariException.INCOMPLET_FORMAT);
        } finally {
            if (scan != null) scan.close();
        }
    }
    
    /**
     * Exports a file
     * @param file
     * @return true if the action was succesfull, else false
     * @throws EcoSafariException
     */
    public static boolean exportAs00(File file) throws EcoSafariException{
        throw new EcoSafariException("Opcion Export As en construcción. Archivo " + file.getName());
    }

    public static void exportAs01(File file, EcoSafari safari) throws EcoSafariException{
        PrintWriter toExport = null;
        try{
            toExport = new PrintWriter(new FileWriter(file));
            for (int r = 0; r < safari.getSize(); r++){
                for (int c = 0; c < safari.getSize(); c++){
                    Entity e = safari.get(r, c);
                    if (e != null) toExport.println(e.getClass().getSimpleName() + " " + r + ", " + c);
                }
            }
        } catch (IOException e){
            throw new EcoSafariException(EcoSafariException.GENERAL_ERROR);
        } finally {
            if (toExport != null) {
                toExport.close();
            }
        }
    }

    public static void exportAs(File file, EcoSafari safari) throws EcoSafariException{
        if (!file.getName().endsWith(".txt")) throw new EcoSafariException(EcoSafariException.INVALID_EXTENSION_TXT);
        PrintWriter toExport = null;
        try{
            toExport = new PrintWriter(new FileWriter(file));
            for (int r = 0; r < safari.getSize(); r++){
                for (int c = 0; c < safari.getSize(); c++){
                    Entity e = safari.get(r, c);
                    if (e != null) toExport.println(e.getClass().getSimpleName() + " " + r + ", " + c);
                }
            }
        } catch (IOException e){
            throw new EcoSafariException(EcoSafariException.CREATE_FILE_NOT_POSSIBLE);
        } finally {
            if (toExport != null) {
                toExport.close();
            }
        }
    }

    private static void addEntities(String type, EcoSafari safari, int r, int c) throws EcoSafariException{
        switch (type) {
            case "Bush":
                new Bush(safari, r, c);
                break;
            case "Earth":
                new Earth(safari, r, c);
                break;
            case "Elephant":
                new Elephant(safari, r, c);
                break;
            case "Grass":
                new Grass(safari, r, c);
                break;
            case "Leopard":
                new Leopard(safari, r, c);
                break;
            case "Lion":
                new Lion(safari, r, c);
                break;
            case "Mamut":
                new Mamut(safari, r, c);
                break;
            case "Storm":
                new Storm(safari, r, c);
                break;
            case "Zebra":
                new Zebra(safari, r, c);
                break;
            case "Stella":
                new Stella(safari, r, c);
                break;
            default:
                throw new EcoSafariException(EcoSafariException.GENERAL_ERROR);
        }
    }

    


}

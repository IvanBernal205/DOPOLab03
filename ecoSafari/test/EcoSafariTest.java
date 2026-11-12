package test;

import domain.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import java.io.*;
import java.util.Scanner;

public class EcoSafariTest {
    @Test 
    public void shouldSaveASafariFile() throws EcoSafariException{
        EcoSafari safari = new EcoSafari();
        File file = new File("Safari.dat");
        EcoSafari.saveAs(file, safari);

        assertTrue(file.exists());//El archivo se crea
        assertTrue(file.length() > 0); //el archivo no es vacio
    }

    @Test 
    public void shouldOpenAFile() throws EcoSafariException{
        EcoSafari safari = new EcoSafari();
        safari.ticTac();
        safari.ticTac();
        File file = new File("Safari.dat");
        EcoSafari.saveAs(file, safari);

        EcoSafari opnedFile = EcoSafari.open(file);

        assertEquals(safari.getSize(), opnedFile.getSize());

        for (int r = 0; r < safari.getSize(); r++) {
            for (int c = 0; c < safari.getSize(); c++) {
                Entity safariEnt = safari.get(r,c);
                Entity openedEnt = opnedFile.get(r, c);
                if (safariEnt != null){
                    assertEquals(safariEnt.getClass(), openedEnt.getClass());
                }
            }
        }
        file.delete();
    }

    @Test 
    public void shouldExportAsAFileWithThCorrectFormat() throws EcoSafariException, IOException{
        EcoSafari safari = new EcoSafari();
        File file = new File("FormatoEsperado.txt");

        EcoSafari.exportAs(file, safari);

        Scanner scan = new Scanner(file);
        while (scan.hasNextLine()){
            String line = scan.nextLine();

            //0 Clase 1 row 2 column
            String[] parts = line.trim().split(" ");

            int r = Integer.parseInt(parts[1].replace(",", ""));
            int c = Integer.parseInt(parts[2]);

            Entity e = safari.get(r, c);
            assertEquals(parts[0], e.getClass().getSimpleName());
        }
        scan.close();
        file.delete();
    }

    @Test
    public void shouldImportAnEntity() throws EcoSafariException, IOException{
        EcoSafari safari = new EcoSafari();
        File file = new File("TextoExportado.txt");
        PrintWriter writter = null;

        try{
            writter = new PrintWriter(new FileWriter(file));
            writter.println("Elephant 2, 20");
            writter.close();

            EcoSafari.import_(file, safari);

            assertEquals(Elephant.class, safari.get(2, 20).getClass());
        } finally {
            if (writter != null){
                writter.close();
            }
        }
        file.delete(); 
    }
}
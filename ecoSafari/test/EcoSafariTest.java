package test;

import domain.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import java.io.*;

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
}
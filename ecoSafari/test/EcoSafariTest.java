package test;

import domain.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
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
}
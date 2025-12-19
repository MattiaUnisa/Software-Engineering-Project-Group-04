/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareengineeringproject.Test;

import com.mycompany.softwareengineeringproject.Model.*;
import java.io.*;
import java.nio.file.*;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author anton
 */
public class WriteOnFileActionTest {
    
    @Test
    void testExecuteAppendsContentToExistingFile() throws IOException {
        // Preparazione: creiamo un file che contiene già del testo
        File tempFile = File.createTempFile("test_append", ".txt");
        Files.write(tempFile.toPath(), "First line\n".getBytes());

        String newContent = "Second line ";
        WriteOnFileAction action = new WriteOnFileAction(tempFile.getAbsolutePath(), newContent);
        ActionContext context = new ActionContext();

        // Esecuzione
        action.execute(context);

        // Verifica
        List<String> lines = Files.readAllLines(tempFile.toPath());
        assertEquals(2, lines.size(), "The file should have tho lines");
        assertEquals("First line", lines.get(0), "The first line is overwritter");
        assertEquals(newContent, lines.get(1), "The second line isn't correctly add at the file");

        tempFile.delete();
    }
    
    @Test
    void testExecuteHandlesInvalidPath() {
        // Preparazione: usiamo un percorso palesemente non valido (es. una cartella come file)
        String invalidPath = "/not/valid/InvalidPath.txt";
        WriteOnFileAction action = new WriteOnFileAction(invalidPath, "Test");
        ActionContext context = new ActionContext();

        // Esecuzione
        action.execute(context);

        // Verifica: l'azione non deve lanciare eccezioni, ma scrivere nel log
        assertTrue(context.getExecutionLog().contains("ERROR"), "The log should contain an error message");
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareengineeringproject.Test;

import com.mycompany.softwareengineeringproject.Model.ActionContext;
import com.mycompany.softwareengineeringproject.Model.ExternalProgramAction;
import java.io.File;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author anton
 */
public class ExternalProgramActionTest {
    
    @Test
    void testExecuteStartsExternalProgram() {
        // Preparazione: usiamo un comando di sistema universale (es. "echo" o un eseguibile noto)
        // Su Windows potresti usare "notepad.exe"
        File program = new File("C:\\Windows\\System32\\notepad.exe");
        
        ExternalProgramAction action = new ExternalProgramAction(program);
        ActionContext context = new ActionContext();

        // Esecuzione
        action.execute(context);

        // Verifica
        assertTrue(context.getExecutionLog().contains("SUCCESS"), "The action should write in the context taht the program is started succesfully");
        assertFalse(context.getExecutionLog().contains("ERROR"), "There shouldn't be errors in the context");
    }
    
    @Test
    void testExecuteHandlesInvalidProgramPath() {
        // Prepariamo un file fittizio che non esiste
        File fakeProgram = new File("C:\\not\\existed\\path\\app.exe");
        ExternalProgramAction action = new ExternalProgramAction(fakeProgram);
        ActionContext context = new ActionContext();

        // Esecuzione
        action.execute(context);

        // Verifica: il log deve segnalare l'errore chiaramente
        assertTrue(context.getExecutionLog().contains("ERROR"), "There should registered an error for not valid path");
    }
}

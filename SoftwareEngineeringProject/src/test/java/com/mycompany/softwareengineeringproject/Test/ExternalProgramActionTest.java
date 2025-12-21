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
import org.junit.jupiter.api.Test;

/**
 *
 * @author anton
 */
public class ExternalProgramActionTest {
    
    //Test 1: Verify that a program is started correctly
    @Test
    void testExecuteStartsExternalProgram() {
        //Define the absoluteh path of the program
        File program = new File("cc");
        //create an instance of the action and of the context to save in that the feedback about the execution of the action
        ExternalProgramAction action = new ExternalProgramAction(program);
        ActionContext context = new ActionContext();

        action.execute(context);

        //Check if in the log is present the word "SUCCESS"
        assertTrue(context.getExecutionLog().contains("SUCCESS"), "The action should write in the context taht the program is started succesfully");
        //Check if in the log isn't present the word "ERROR"
        assertFalse(context.getExecutionLog().contains("ERROR"), "There shouldn't be errors in the context");
    }
    
    //Test 2: Verify that the sistem manage correctly the case in which is used a not valid path
    @Test
    void testExecuteHandlesInvalidProgramPath() {
        //Here the program prepare a fake file for the testesiste
        File fakeProgram = new File("C:\\not\\existed\\path\\app.exe");
        ExternalProgramAction action = new ExternalProgramAction(fakeProgram);
        ActionContext context = new ActionContext();

        action.execute(context);

        //Check if the log contain the string "ERROR" to confirm the managing of the error
        assertTrue(context.getExecutionLog().contains("ERROR"), "There should registered an error for not valid path");
    }
}

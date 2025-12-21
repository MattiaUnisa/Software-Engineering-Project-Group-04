/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.softwareengineeringproject.Test;

import com.mycompany.softwareengineeringproject.Model.ActionContext;
import com.mycompany.softwareengineeringproject.Model.DeleteFileAction;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author matda
 */
public class DeleteFileActionTest {
    
    //Test 1: Verify if a file has been succesfully deleated
    @Test
    void testExecuteMoveFileSuccessfully() throws IOException {
        //Create a file that already contain a text
        File tempFile = File.createTempFile("test_append", ".txt");
        //Create an instance of the action and of the context for the logs
        DeleteFileAction action = new DeleteFileAction(tempFile);
        ActionContext context = new ActionContext();

        action.execute(context);

        //Check if the file exists or is eliminated
        assertFalse(tempFile.exists(), "The file shouldn't exist because is deleted");

    }
    
    
    //Test 2: Verify that the action manage correctly the error if the file doesn't exist
    @Test
    void testExecuteHandlesMissingSourceFile() throws IOException {
        File nonExistentFile = new File("fake_source.txt"); // File that not exist 

        DeleteFileAction action = new DeleteFileAction(nonExistentFile);
        ActionContext context = new ActionContext();

        //The execution must not throw Java exception, but manage the error
        action.execute(context);

        //Check if the error has been register in the log        
        assertTrue(context.getExecutionLog().contains("ERROR"), "There should be a message error if the source is missed");
    }
}

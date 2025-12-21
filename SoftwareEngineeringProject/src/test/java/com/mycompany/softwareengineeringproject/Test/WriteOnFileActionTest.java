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
    
    //Test to Verify that the action add the content, and not that overwrite the previous content
    @Test
    void testExecuteAppendsContentToExistingFile() throws IOException {
        //Create a file that already contain a text
        File tempFile = File.createTempFile("test_append", ".txt");
        //Write an initial line in the file to simulate a file that already exist
        Files.write(tempFile.toPath(), "First line\n".getBytes());

        String newContent = "Second line ";
        //Create a new action with the path of the file and the message to add.
        WriteOnFileAction action = new WriteOnFileAction(tempFile.getAbsolutePath(), newContent);
        ActionContext context = new ActionContext();

        //Execution of the action
        action.execute(context);

        //Read al the lines froma the file to check the final result
        List<String> lines = Files.readAllLines(tempFile.toPath());
        //Check if there are two lines
        assertEquals(2, lines.size(), "The file should have tho lines");
        //check if the first line isn't modified
        assertEquals("First line", lines.get(0), "The first line is overwritter");
        //check if the second line is the same that was passed at the action
        assertEquals(newContent, lines.get(1), "The second line isn't correctly add at the file");

        tempFile.delete();
    }
    
    //Test to Verify that the action manage the not valid paths
    @Test
    void testExecuteHandlesInvalidPath() {
        //Here is saved a not valid path
        String invalidPath = "/not/valid/InvalidPath.txt";
        WriteOnFileAction action = new WriteOnFileAction(invalidPath, "Test");
        ActionContext context = new ActionContext();

        //Execution of the action
        action.execute(context);

        //Check if the in the log there is the word "ERROR"
        assertTrue(context.getExecutionLog().contains("ERROR"), "The log should contain an error message");
    }
}

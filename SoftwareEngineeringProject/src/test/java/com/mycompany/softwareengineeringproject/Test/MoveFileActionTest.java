/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.softwareengineeringproject.Test;

import com.mycompany.softwareengineeringproject.Model.ActionContext;
import com.mycompany.softwareengineeringproject.Model.MoveFileAction;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author matda
 */
public class MoveFileActionTest {
    
    //Test 1: Verify if a file has been succesfully moved from a folder to another one
    @Test
    void testExecuteMoveFileSuccessfully() throws IOException {
        //Create two temporary directory to simulate the filesystem of the user        
        Path sourceDir = Files.createTempDirectory("sourceDir");
        Path destDir = Files.createTempDirectory("destDir");
        //Create a file in source folder
        File sourceFile = Files.createFile(sourceDir.resolve("testMove.txt")).toFile();

        //Write on the source file to verify the integrity of the copy
        String content = "Content to move";
        Files.write(sourceFile.toPath(), content.getBytes());

        //Create an instance of the action and of the context for the logs
        MoveFileAction action = new MoveFileAction(sourceFile, destDir.toFile());
        ActionContext context = new ActionContext();

        action.execute(context);

        //Build the path where we the user want to find the copied file
        File expectedFile = Paths.get(destDir.toString(), sourceFile.getName()).toFile();
        //Check if the file exists in the destination
        assertTrue(expectedFile.exists(), "The file should exist in the destination folder");
        //Check if the content of the file has been not change during the coping
        assertEquals(content, Files.readAllLines(expectedFile.toPath()).get(0), "The content of the copied file not correspond");
        //The original file must still exist
        assertTrue(sourceFile.exists(), "The original file didn't remove during the copy");
    }
    
    //Test 2: Verify if the action overwrite a file if it already exist in the destination
    @Test
    void testExecuteOverwritesExistingFile() throws IOException {
        Path sourceDir = Files.createTempDirectory("sourceDir");
        Path destDir = Files.createTempDirectory("destDir");

        //Create the source with the new content
        File sourceFile = Files.createFile(sourceDir.resolve("replaceMe.txt")).toFile();
        Files.write(sourceFile.toPath(), "New content".getBytes());

        //Create a file with the same name in the destination, and we add at the content "Old content"        
        File existingFile = Files.createFile(destDir.resolve("replaceMe.txt")).toFile();
        Files.write(existingFile.toPath(), "Old content".getBytes());

        CopyFileAction action = new CopyFileAction(sourceFile, destDir.toFile());
        action.execute(new ActionContext());

        //Check if StandardCopyOption.REPLACE_EXISTING make the overwrite of the content
        String currentContent = Files.readAllLines(existingFile.toPath()).get(0);
        assertEquals("New content", currentContent, "The existing file should be overwritten");
    }
    
    //Test 3: Verify that the action manage correctly the error if the source file doesn't exist
    @Test
    void testExecuteHandlesMissingSourceFile() throws IOException {
        File nonExistentFile = new File("fake_source.txt"); // File that not exist on the disk
        Path destDir = Files.createTempDirectory("destDir");

        CopyFileAction action = new CopyFileAction(nonExistentFile, destDir.toFile());
        ActionContext context = new ActionContext();

        //The execution must not throw Java exception, but manage the error
        action.execute(context);

        //Check if the error has been register in the log        
        assertTrue(context.getExecutionLog().contains("ERROR"), "There should be a message error if the source is missed");
    }
}

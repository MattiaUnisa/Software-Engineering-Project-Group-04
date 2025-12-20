/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareengineeringproject.Model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anton
 */
public class CopyFileAction implements Action{
    
    private static File sourcePath;
    private static File destPath;

    public CopyFileAction(File sourcePath, File destPath) {
        this.sourcePath = sourcePath;
        this.destPath = destPath;
    }

    @Override
    public void execute(ActionContext context) {
        // Check of the existing of the source file
        if (sourcePath == null || !sourcePath.exists()) {
            context.appendToLog("ERROR: Source file does not exist: " + sourcePath);
            return;
        }

        try {
            // Here we create a new reference file that aim inside the destination folder
            File finalDest = new File(destPath, sourcePath.getName());

            // .toPath() convert the File in Path to make it compatible with Files.copy
            // StandardCopyOption.REPLACE_EXISTING allow to overwrite the files with the same names
            Files.copy(sourcePath.toPath(), finalDest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            // write on the log a feedback about the copy operation
            context.appendToLog("SUCCESS: File copied to " + finalDest.getAbsolutePath());
            if (context.getUiEventListener() != null) {
                context.getUiEventListener().onShowNotification("Success!", "File is well copied");
            } 


        } catch (IOException e) {
            context.appendToLog("ERROR: File operation failed: " + e.getMessage());
            if (context.getUiEventListener() != null) {
                context.getUiEventListener().onShowError("Error", "Cannot write on file", "Error: " + e.getMessage());
            }  
        }
    }

    @Override
    public void stop() {
        
    }

    //Format an Action object in a string 
    @Override
    public String formatString() {
        return "CopyFile: " + sourcePath + ";" + destPath;
    }
    
    //This method permit to rebuild an Action object from a string
    public static Action parseString(String action){
        if(!action.startsWith("CopyFile:")){
            throw new IllegalArgumentException("Invalid CopyFileAction format.");
        }
        //As separator between Source and Destination we used a semicolon
        String cmfPart = action.substring("CopyFile:".length());
        String[] parts = cmfPart.split(";");
        //If there isn't 2 fields the method throws an exception
        if (parts.length != 2) throw new IllegalArgumentException("CopyFileAction data error.");
        
        File source = new File(parts[0]);
        File dest = new File(parts[1]);
        //Use the factory to create the Action
        return ActionFactory.createCopyFile(source, dest);
    }

    public File getSourcePath() {
        return sourcePath;
    }

    public File getDestPath() {
        return destPath;
    }

    
    @Override
    public String toString() {
        return "CopyFileAction\n" + "sourcePath: " + sourcePath + "\ndestPath: " + destPath;
    }

    
    
    
}

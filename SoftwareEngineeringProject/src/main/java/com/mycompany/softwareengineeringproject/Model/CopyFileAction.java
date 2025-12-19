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
        // Controllo esistenza file sorgente
        if (sourcePath == null || !sourcePath.exists()) {
            context.appendToLog("ERROR: Source file does not exist: " + sourcePath);
            return;
        }

        try {
            // Costruiamo il file di destinazione finale (Directory + Nome File Sorgente)
            // La User Story richiede di spostare in una directory di destinazione
            File finalDest = new File(destPath, sourcePath.getName());

            // Copia il file convertendo in Path
            Files.copy(sourcePath.toPath(), finalDest.toPath(), StandardCopyOption.REPLACE_EXISTING);
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

    @Override
    public String formatString() {
        return "CopyFile: " + sourcePath + ";" + destPath;
    }
    
    public static Action parseString(String action){
        if(!action.startsWith("CopyFile: ")){
            throw new IllegalArgumentException("Invalid CopyFileAction format.");
        }
        String cmfPart = action.substring("CopyFile: ".length());
        String[] parts = cmfPart.split(";");
        
        if (parts.length != 2) throw new IllegalArgumentException("CopyFileAction data error.");
        
        File source = new File(parts[0]);
        File dest = new File(parts[1]);
        return ActionFactory.createCopyFile(sourcePath, destPath);
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

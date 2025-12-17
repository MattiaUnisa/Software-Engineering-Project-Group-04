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
public class CopyMoveFileAction implements Action{
    
    private static File sourcePath;
    private static File destPath;

    public CopyMoveFileAction(File sourcePath, File destPath) {
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
            
        
    } catch (IOException e) {
        context.appendToLog("ERROR: File operation failed: " + e.getMessage());
    }
    }

    @Override
    public void stop() {
        
    }

    @Override
    public String formatString() {
        return "CopyMoveFileAction: " + sourcePath + ";" + destPath;
    }
    
    public static Action parseString(String action){
        if(!action.startsWith("CopyMoveFileAction: ")){
            throw new IllegalArgumentException("Invalid CopyMoveFileAction format.");
        }
        String cmfPart = action.substring("CopyMoveFileAction: ".length());
        String[] parts = cmfPart.split(";");
        
        if (parts.length != 2) throw new IllegalArgumentException("CopyMoveAction data error.");
        
        File source = new File(parts[0]);
        File dest = new File(parts[1]);
        return ActionFactory.createCopyMoveFile(sourcePath, destPath);
    }

    @Override
    public String toString() {
        return "CopyMoveFileAction{" + "sourcePath=" + sourcePath + ", destPath=" + destPath + ", operationType=" + '}';
    }

    
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareengineeringproject.Model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anton
 */
public class CopyMoveFileAction implements Action{
    
    private static File sourcePath;
    private static File destPath;
    private final FileOperationType operationType;

    public CopyMoveFileAction(File sourcePath, File destPath, FileOperationType operationType) {
        this.sourcePath = sourcePath;
        this.destPath = destPath;
        this.operationType = operationType;
    }

    @Override
    public void execute(ActionContext context) {
        if (sourcePath == null) {
            context.appendToLog("ERROR: Missing source path");
            return;
        }
        if (destPath == null) {
            context.appendToLog("ERROR: Missing destination path");
            return;
        }
        if(operationType == FileOperationType.COPY){
            try {
                Files.copy(sourcePath.toPath(), destPath.toPath());
            } catch (IOException ex) {
                Logger.getLogger(CopyMoveFileAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            
        }
    }

    @Override
    public void stop() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String formatString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

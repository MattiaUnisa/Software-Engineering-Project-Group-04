package com.mycompany.softwareengineeringproject.Model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class MoveFileAction implements Action{
    
    private static File source;
    private static File destination;

    public MoveFileAction(File source, File destination) {
        this.source = source;
        this.destination = destination;
    }
    
    @Override
    public void execute(ActionContext context) {
        // Check if the source file exists
        if (source == null || !source.exists()) {
            context.appendToLog("ERROR: Source file does not exist: " + source);
            if (context.getUiEventListener() != null) {
                context.getUiEventListener().onShowError("Error", "Cannot move the file", "Source file does not exist");
            }  
            return;
        }

        try {
            // Build file of finalDestination
            File finalDest = new File(destination, source.getName());

            // Move converted file in Path
            Files.move(source.toPath(), finalDest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            context.appendToLog("SUCCESS: File moved to " + finalDest.getAbsolutePath());
            if (context.getUiEventListener() != null) {
                context.getUiEventListener().onShowNotification("Success!", "File is well moved");
            } 


        } catch (IOException e) {
            context.appendToLog("ERROR: File operation failed: " + e.getMessage());
            if (context.getUiEventListener() != null) {
                context.getUiEventListener().onShowError("Error", "Cannot move the file", "Error: " + e.getMessage());
            }  
        }
    }

    @Override
    public void stop() {
                
    }

    @Override
    public String formatString() {
        return "MoveFile:" + source + ";" + destination;
    }
    
    public static Action parseString(String action){
        if(!action.startsWith("MoveFile:")){
            throw new IllegalArgumentException("Invalid MoveFileAction format.");
        }
        String mfPart = action.substring("MoveFile:".length());
        String[] parts = mfPart.split(";");
        
        if (parts.length != 2) throw new IllegalArgumentException("MoveFileAction data error.");
        
        File source = new File(parts[0]);
        File dest = new File(parts[1]);
        return ActionFactory.createMoveFile(source, dest);
    }

    public File getSource() {
        return source;
    }

    public File getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return "MoveFileAction\n" + "sourcePath: " + source + "\ndestPath: " + destination;
    }
    
    
}



package com.mycompany.softwareengineeringproject.Model;

import java.io.File;

public class MoveFileAction implements Action{
    
    private static File source;
    private static File destination;

    public MoveFileAction(File source, File destination) {
        this.source = source;
        this.destination = destination;
    }
    
    @Override
    public void execute(ActionContext context) {
        if (source.exists() && !destination.exists()) {
            boolean success = source.renameTo(new File(destination, source.getName()));
            if (!success) {
                System.out.println("Failed to move the file.");
                }
            } else {
                System.out.println("Invalid source or destination.");
            }
    }

    @Override
    public void stop() {
                
    }

    @Override
    public String formatString() {
        return "MoveFile: " + source + ";" + destination;
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



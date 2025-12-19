
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
        return "";
    }

    
}



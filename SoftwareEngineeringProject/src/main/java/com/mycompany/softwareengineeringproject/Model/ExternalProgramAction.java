/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareengineeringproject.Model;

import java.io.File;

/**
 *
 * @author anton
 */
public class ExternalProgramAction implements Action{
    
    private static File programPath;
    
    public ExternalProgramAction(File programPath){
        this.programPath = programPath;
    }

    @Override
    public void execute(ActionContext context) {
        if(programPath == null || !programPath.exists()){
            context.appendToLog("ERROR: Program does not exist: " + programPath);
            return;
        }
        try{
            ProcessBuilder pb = new ProcessBuilder(programPath.getAbsolutePath());
            pb.start();
            context.appendToLog("SUCCESS: Program started: " + programPath.getName());
            if (context.getUiEventListener() != null) {
                context.getUiEventListener().onShowNotification("Success!", "Program " + programPath.getName() + " is well started");
            } 
        }catch(Exception e){
            context.appendToLog("ERROR: Esecution of program is failed: " + e.getMessage());
            if (context.getUiEventListener() != null) {
                context.getUiEventListener().onShowError("Error", "Cannot start the Program " + programPath.getName(), "Error: " + e.getMessage());
            } 
        }
    }

    @Override
    public void stop() {
        
    }

    @Override
    public String formatString() {
        return "ExternalProgram: " + programPath;
    }
    
    public static Action parseString(String action){
        if(!action.startsWith("ExternalProgram: ")){
            throw new IllegalArgumentException("Invalid ExternalProgramAction format.");
        }
        String path = action.substring("ExternalProgram: ".length());
        return ActionFactory.createExternalProgram(programPath);
    }

    public File getProgramPath() {
        return programPath;
    }
    
    
    
    @Override
    public String toString() {
        return "ExternalProgramAction{" + "programPath=" + programPath + '}';
    }
}

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
            //ProcessBuilder preparate the execution of the external program. 
            //getAbsolutePath() is used to pass the string of the complete path
            ProcessBuilder pb = new ProcessBuilder(programPath.getAbsolutePath());
            //Here the method open the external app
            pb.start();
            context.appendToLog("SUCCESS: Program started: " + programPath.getName());
            //Visual notification on the UI about the execution of the app
            if (context.getUiEventListener() != null) {
                context.getUiEventListener().onShowNotification("Success!", "Program " + programPath.getName() + " is well started");
            } 
        }catch(Exception e){
            //The method here manage the possible errors about the execution of the app
            context.appendToLog("ERROR: Esecution of program is failed: " + e.getMessage());
            if (context.getUiEventListener() != null) {
                context.getUiEventListener().onShowError("Error", "Cannot start the Program " + programPath.getName(), "Error: " + e.getMessage());
            } 
        }
    }

    @Override
    public void stop() {
        
    }

    //Format an Action object in a string 
    @Override
    public String formatString() {
        return "ExternalProgram:" + programPath;
    }
    
    //This method permit to rebuild an Action object from a string
    public static Action parseString(String action){
        if(!action.startsWith("ExternalProgram:")){
            throw new IllegalArgumentException("Invalid ExternalProgramAction format.");
        }
        //Extraxt the path of the file from the saved string        
        String part = action.substring("ExternalProgram:".length());
        //Use the factory to create the object
        File path = new File(part);
        return ActionFactory.createExternalProgram(path);

    }

    public File getProgramPath() {
        return programPath;
    }
    
    
    
    @Override
    public String toString() {
        return "ExternalProgramAction\n" + "programPath: " + programPath;
    }
}

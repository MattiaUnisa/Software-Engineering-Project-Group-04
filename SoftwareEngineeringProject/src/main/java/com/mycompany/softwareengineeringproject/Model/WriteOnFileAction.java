/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareengineeringproject.Model;

import java.io.*;

/**
 *
 * @author anton
*/ 
public class WriteOnFileAction implements Action{
    
    private static String filePath;
    private final String message;

    public WriteOnFileAction(String filePath, String message) {
        this.filePath = filePath;
        this.message = message;
    }
    
    public String getFilePath(){
        return filePath;
    }
    
    public String getMessage() {
        return message;
    }
    
    @Override
    public void execute(ActionContext context){
        //The "true" parameter enable the appen mode
        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(filePath, true)))){
            //Write the message in the file
            pw.println(message);
            context.appendToLog("SUCCESS: Wrote on file: " + message);
            //Show a succes notification in the UI
            if (context.getUiEventListener() != null) {
                context.getUiEventListener().onShowNotification("Success!", message);
            }  
        }catch(Exception e){
            context.appendToLog("ERROR: Cannot write on file: " + message + ". Cause: " + e.getMessage());
            //Show an error notification in the UI
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
    public String formatString(){
        return "WriteOnFile: " + this.filePath + ";" + this.message;
    }
    
    //This method permit to rebuild an Action object from a string
    public static Action parseString(String action){
        if(!action.startsWith("WriteOnFile: ")){
            throw new IllegalArgumentException("Invalid WriteToFile format.");
        }
        //Extraxt the path of the file from the saved string
        String wofPart = action.substring("WriteToFile: ".length());
        String[] parts = wofPart.split(";");
        
        if (parts.length != 2) throw new IllegalArgumentException("CopyMoveAction data error.");
        
        String path = parts[0];
        String msg = parts[1];
        return ActionFactory.createWriteOnFile(path, msg);
    }

    @Override
    public String toString() {
        return "WriteOnFileAction{" + "filePath=" + filePath + "message=" + message + '}';
    }

    
}

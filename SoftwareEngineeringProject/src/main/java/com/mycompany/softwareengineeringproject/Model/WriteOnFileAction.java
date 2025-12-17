/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareengineeringproject.Model;

import com.mycompany.softwareengineeringproject.View.DialogManager;
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
        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(filePath)))){
            pw.println(message);
            context.appendToLog("SUCCESS: Wrote on file: " + message);
        }catch(Exception e){
            context.appendToLog("ERROR: Cannot write on file: " + message + ". Cause: " + e.getMessage());
            DialogManager.showError("Error", "Cannot write on file", "Error: " + e.getMessage());

        }
    }
    
    
    @Override
    public void stop() {
        
    }
    
    @Override
    public String formatString(){
        return "WriteOnFile: " + this.message;
    }
    
    public static Action parseString(String action){
        if(!action.startsWith("WriteOnFile: ")){
            throw new IllegalArgumentException("Invalid WriteToFile format.");
        }
        String wofPart = action.substring("WriteToFile: ".length());
        return ActionFactory.createWriteOnFile(filePath, wofPart);
    }

    
}

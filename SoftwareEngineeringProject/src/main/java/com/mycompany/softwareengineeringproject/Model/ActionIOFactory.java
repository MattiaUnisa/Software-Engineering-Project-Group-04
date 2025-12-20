/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareengineeringproject.Model;

/**
 *
 * @author anton
 */
public class ActionIOFactory {
    
    //This method permit to rebuild an Action object from a string
    public static Action parseAction(String action){
        if (action.startsWith("PlayAudioAction:")) {
            return PlayAudioAction.parseString(action);
           
        }if (action.startsWith("Notification:")) {
            return NotificationAction.parseString(action);
            
        }if (action.startsWith("WriteOnFile:")){
            return WriteOnFileAction.parseString(action);
        }
        
        if (action.startsWith("CopyFile:")){
            return CopyFileAction.parseString(action);
        }
        
        if (action.startsWith("ExternalProgram:")){
            return ExternalProgramAction.parseString(action);
        }
        
        if (action.startsWith("MoveFile:")){
            return MoveFileAction.parseString(action);
        }

        throw new IllegalArgumentException("Unknown Action type in persistence data: " + action);
    }
}

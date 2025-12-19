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

//It allow to create the configured object PlayAudioAction or ShowNotification, in this way is not necessary the communication 
//with the other Action classes
public class ActionFactory {
    
    public static Action createPlayAudio(String filePath) {
        return new PlayAudioAction(filePath); 
    }
    
    public static Action createShowNotification(String msg) {
        return new NotificationAction(msg); 
    }
    
    public static Action createWriteOnFile(String filePath, String msg){
        return new WriteOnFileAction(filePath, msg);
    }
    
    public static Action createCopyFile(File sourcePath, File destPath){
        return new CopyFileAction(sourcePath, destPath);
    }
    
    public static Action createExternalProgram(File programPath){
        return new ExternalProgramAction(programPath);
    }
    
    public static Action createMoveFile(File sourcePath, File destPath){
        return new CopyFileAction(sourcePath, destPath);
    }
}

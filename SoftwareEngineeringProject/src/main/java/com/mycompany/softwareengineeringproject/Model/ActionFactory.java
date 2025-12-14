/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareengineeringproject.Model;

/**
 *
 * @author anton
 */

//It allow to create the configured object PlayAudioAction, in this way is not necessary the communication with the other Action classes
public class ActionFactory {
    
    public static Action createPlayAudio(String filePath) {
        return new PlayAudioAction(filePath); 
    }
    
    public static Action createShowNotification(String msg) {
        return new NotificationAction(msg); 
    }
}

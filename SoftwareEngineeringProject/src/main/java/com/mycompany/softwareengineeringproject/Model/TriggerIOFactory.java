/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareengineeringproject.Model;

/**
 *
 * @author anton
 */
public class TriggerIOFactory {
    
    public static Trigger parseTrigger(String trigger) {
        
        if (trigger.startsWith("TimeTrigger:")) {
            // Delega la ricostruzione alla classe TimeTrigger
            return TimeTrigger.parseString(trigger);
        }
        
        // Aggiungi qui altri tipi di Trigger (es. FileMonitorTrigger, etc.)
        
        throw new IllegalArgumentException("Unknown Trigger type in persistence data: " + trigger);
    }
}

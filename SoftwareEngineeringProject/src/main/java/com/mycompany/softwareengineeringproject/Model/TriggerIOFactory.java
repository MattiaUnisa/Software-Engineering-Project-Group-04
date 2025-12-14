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
    
    //This method permit to rebuild a Trigger object from a string
    public static Trigger parseTrigger(String trigger) {
        
        if (trigger.startsWith("TimeTrigger:")) {
            // Assign the rebuilding at TimeTrigger class
            return TimeTrigger.parseString(trigger);
        }
        
        throw new IllegalArgumentException("Unknown Trigger type in persistence data: " + trigger);
    }
}

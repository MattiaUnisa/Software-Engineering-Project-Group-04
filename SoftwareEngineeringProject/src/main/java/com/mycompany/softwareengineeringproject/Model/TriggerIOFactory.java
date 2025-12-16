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
        
        if (trigger.startsWith("DayOfWeekTrigger:")) {
            // Assign the rebuilding at DayOfWeekTrigger class
            return DayOfWeekTrigger.parseString(trigger);
        }
        
        if (trigger.startsWith("DayOfMonthTrigger:")) {
            // Assign the rebuilding at DayOfMonthTrigger class
            return DayOfMonthTrigger.parseString(trigger);
        }
        
        if (trigger.startsWith("DateTrigger:")) {
            // Assign the rebuilding at DateTrigger class
            return DateTrigger.parseString(trigger);
        }
        
        if (trigger.startsWith("FileTrigger:")) {
            // Assign the rebuilding at FileTrigger class
            return FileTrigger.parseString(trigger);
        }
        
        throw new IllegalArgumentException("Unknown Trigger type in persistence data: " + trigger);
    }
}

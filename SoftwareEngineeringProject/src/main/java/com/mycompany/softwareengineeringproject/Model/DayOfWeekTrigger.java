/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareengineeringproject.Model;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 *
 * @author matda
 */
public class DayOfWeekTrigger implements Trigger{
    
    private final DayOfWeek day;
    
    public DayOfWeekTrigger(DayOfWeek day){
        this.day = day;
    }

    public DayOfWeek getday() {
        return day;
    }
    
    //Condition for the creation of the trigger
    @Override
    public boolean isTriggered(){
        DayOfWeek today = LocalDate.now().getDayOfWeek();
        return today.equals(day);
    }
    
    @Override
    public String formatString() {
        return "DayOfWeekTrigger:" + this.day;
    }
    
    //This method permit to rebuild a Trigger object from a string
    public static Trigger parseString(String trigger){
        if(!trigger.startsWith("DayOfWeekTrigger:")){
            throw new IllegalArgumentException("Invalid DayOfWeekTrigger format.");        
        }
        String daypart = trigger.substring("DayOfWeekTrigger:".length());
        DayOfWeek dayOfWeek = DayOfWeek.valueOf(daypart);
        //Use the factory to create the object
        return TriggerFactory.createDayOfWeekTrigger(dayOfWeek);
    }

    @Override
    public String toString() {
        return "DayOfWeekTrigger{" + "DayOfWeek=" + day + '}';
    }
    
    
    
}

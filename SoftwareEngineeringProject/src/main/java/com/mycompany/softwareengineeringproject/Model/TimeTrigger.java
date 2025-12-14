/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareengineeringproject.Model;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author matda
 */
public class TimeTrigger implements Trigger{
    
    private final LocalTime time;

    //Truncate the time in order to consider only hours and minutes
    public TimeTrigger(LocalTime time) {
        this.time = time.truncatedTo(ChronoUnit.MINUTES);    
    }
    
    public LocalTime getTime() {
        return time;
    }
    
    //Condition for the creation of the trigger
    // It garantees no repetition of the action every secondo
    @Override
    public boolean isTriggered(){
        LocalTime now = LocalTime.now();
        
        // Control if the time insert in the UI is equal to the real time
        boolean sameTime = now.truncatedTo(ChronoUnit.MINUTES).equals(time);
        
        return sameTime;
    }
    
    //This method format a Trigger object in a string in this way: HH:MM
    @Override
    public String formatString() {
        return "TimeTrigger:" + this.time.getHour() + ":" + this.time.getMinute();
    }
    
    //This method permit to rebuild a Trigger object from a string
    public static Trigger parseString(String trigger){
        if(!trigger.startsWith("TimeTrigger:")){
            throw new IllegalArgumentException("Invalid TimeTrigger format.");        
        }
        String timePart = trigger.substring("TimeTrigger:".length());
        //As separator for hours and minutes the method use the colon
        String[] parts = timePart.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        LocalTime time = LocalTime.of(hour, minute);
        //Use the factory to create the object
        return TriggerFactory.createTimeTrigger(time);
    }
    
    @Override
    public String toString() {
        return "TimeTrigger{" + "time=" + time + '}';
    }
    
}

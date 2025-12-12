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
    
    private LocalTime time;

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
    
    @Override
    public String toString() {
        return "TimeTrigger{" + "time=" + time + '}';
    }
    
}

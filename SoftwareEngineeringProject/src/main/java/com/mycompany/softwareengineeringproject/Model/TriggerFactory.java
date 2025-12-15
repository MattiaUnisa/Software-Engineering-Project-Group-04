/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareengineeringproject.Model;

import java.time.DayOfWeek;
import java.time.LocalTime;

/**
 *
 * @author matda
 */

//This class contain all the method for the creation of Trigger Instance and this is the class called when there is the need to create a Trigger
//in this way is not necessary the communication with the other Trigger classes
public class TriggerFactory {
    
    
    public static Trigger createTimeTrigger(LocalTime time){
        return new TimeTrigger(time);
    }
    
    public static Trigger createDayOfWeekTrigger(DayOfWeek day){
        return new DayOfWeekTrigger(day);
    }
    
    public static Trigger createDayOfMonthTrigger(int day){
        return new DayOfMonthTrigger(day);
    }
    
}

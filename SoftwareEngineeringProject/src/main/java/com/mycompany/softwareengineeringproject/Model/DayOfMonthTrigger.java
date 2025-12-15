/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareengineeringproject.Model;

import java.time.LocalDate;

/**
 *
 * @author matda
 */
public class DayOfMonthTrigger implements Trigger{
    
    private final int day;
    
    public DayOfMonthTrigger(int day){
        this.day = day;
    }

    public int getday() {
        return day;
    }
    
    //Condition for the creation of the trigger
    @Override
    public boolean isTriggered(){
        int today = LocalDate.now().getDayOfMonth();
        return today == day;
    }
    
    @Override
    public String formatString() {
        return "DayOfMonthTrigger:" + this.day;
    }
    
    //This method permit to rebuild a Trigger object from a string
    public static Trigger parseString(String trigger){
        if(!trigger.startsWith("DayOfMonthTrigger:")){
            throw new IllegalArgumentException("Invalid DayOfMonthTrigger format.");        
        }
        String daypart = trigger.substring("DayOfMonthTrigger:".length());
        String[] parts = daypart.split(":");
        int dayOfMonth = Integer.parseInt(parts[0]);
        //Use the factory to create the object
        return TriggerFactory.createDayOfMonthTrigger(dayOfMonth);
    }

    @Override
    public String toString() {
        return "DayOfMonthTrigger{" + "day=" + day + '}';
    }


    
}

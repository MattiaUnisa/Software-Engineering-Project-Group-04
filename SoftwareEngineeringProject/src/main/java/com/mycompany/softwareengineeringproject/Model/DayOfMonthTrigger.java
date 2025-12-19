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
//This class say that the trigger isTriggered when the day of month ("ex. 17") 
//selected by the user is equals to today's day of month 
public class DayOfMonthTrigger implements Trigger{
    
    //the day of Month is an int number
    private final int day;
    
    public DayOfMonthTrigger(int day){
        this.day = day;
    }

    public int getDay() {
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
        int dayOfMonth = Integer.parseInt(daypart);
        //Use the factory to create the object
        return TriggerFactory.createDayOfMonthTrigger(dayOfMonth);
    }

    @Override
    public String toString() {
        return "DayOfMonthTrigger\n" + "day: " + day;
    }


    
}

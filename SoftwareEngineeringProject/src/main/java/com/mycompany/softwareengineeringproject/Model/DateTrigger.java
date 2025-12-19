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
//This class say that the trigger isTriggered when the date ("ex. 17/12/25") 
//selected by the user is equals to today's date 
public class DateTrigger implements Trigger{
    
    private final LocalDate date;
    
    public DateTrigger(LocalDate date){
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }
    
    //Condition for the creation of the trigger
    @Override
    public boolean isTriggered(){
        LocalDate today = LocalDate.now();
        return today.equals(date);
    }
    
    @Override
    public String formatString() {
        return "DateTrigger:" + this.date;
    }
    
    //This method permit to rebuild a Trigger object from a string
    public static Trigger parseString(String trigger){
        if(!trigger.startsWith("DateTrigger:")){
            throw new IllegalArgumentException("Invalid DateTrigger format.");        
        }
        String datepart = trigger.substring("DateTrigger:".length());
        LocalDate date = LocalDate.parse(datepart);
        //Use the factory to create the object
        return TriggerFactory.createDateTrigger(date);
    }

    @Override
    public String toString() {
        return "DateTrigger{" + "date=" + date + '}';
    }
    
}

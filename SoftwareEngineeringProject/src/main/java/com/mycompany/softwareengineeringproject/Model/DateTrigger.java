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
public class DateTrigger implements Trigger{
    
    private final LocalDate date;
    
    public DateTrigger(LocalDate date){
        this.date = date;
    }

    public LocalDate getdate() {
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
        String[] parts = datepart.split(":");
        LocalDate date = LocalDate.parse(parts[0]);
        //Use the factory to create the object
        return TriggerFactory.createDateTrigger(date);
    }

    @Override
    public String toString() {
        return "DateTrigger{" + "date=" + date + '}';
    }
    
}

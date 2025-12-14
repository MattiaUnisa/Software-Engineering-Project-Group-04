/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareengineeringproject.Model;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 *
 * @author matda
 */

//The class Repetition is created to generate a simpler object in order to don't increment the complexity of the class Rule
public class Repetition {
    
    private boolean oneTime;
    private Duration sleepPeriod;
    private LocalDateTime lastExecution;
    private int numRepetition;
    private int currentRepetition;

    public Repetition() {
    }
    
    public Repetition(boolean oneTime, Duration sleepPeriod, LocalDateTime lastExecution,int numRepetition){
        this.oneTime = oneTime;
        this.sleepPeriod = sleepPeriod;
        this.lastExecution = lastExecution;
        this.numRepetition = numRepetition;
    }

    public boolean isOneTime() {
        return oneTime;
    }

    public Duration getSleepPeriod() {
        return sleepPeriod;
    }

    public LocalDateTime getLastExecution() {
        return lastExecution;
    }

    public int getNumRepetition() {
        return numRepetition;
    }

    public int getCurrentRepetition() {
        return currentRepetition;
    }

    public void setOneTime(boolean oneTime) {
        this.oneTime = oneTime;
    }

    public void setSleepPeriod(Duration sleepPeriod) {
        this.sleepPeriod = sleepPeriod;
    }

    public void setLastExecution(LocalDateTime lastExecution) {
        this.lastExecution = lastExecution;
    }

    public void setNumRepetition(int numRepetition) {
        this.numRepetition = numRepetition;
    }

    public void setCurrentRepetition(int currentRepetition) {
        this.currentRepetition = currentRepetition;
    }
    
    //This method format a Repetition object in a string where the fields are separeted by a semicolon.
    public String formatRepetition(){
        return "Repetition:" +
           this.oneTime + ";" +
           //Manage the case in which the object have the fields Sleep Period and Last Execution equal to null
           (this.sleepPeriod == null ? "null" : this.sleepPeriod) + ";" + 
           (this.lastExecution == null ? "null" : this.lastExecution) + ";" + 
           this.numRepetition;
    }
    
    //This method permit to rebuild a Repetition object from a string
    public static Repetition parseRepetition(String repetition){
        if(!repetition.startsWith("Repetition:")){
            throw new IllegalArgumentException("Invalid Repetition format.");
        }
        //Removes the fixed "Repetition:" prefix from the string. The dataPart variable now contains only the field values.
        String dataPart = repetition.substring("Repetition:".length());
        //Divide the string dataPart in an array of string, on the base that every components of 
        //the array are divided by semicolon
        String[] fields = dataPart.split(";");
        
        //Verify that there are four fields
        if (fields.length != 4) throw new IllegalArgumentException("Repetition data error.");
        
        boolean oneTime = Boolean.parseBoolean(fields[0]);
        //Manage the NULL case, that is, if the field is null so it assigned NULL; if it isn't NULL, it make the parse of 
        //the sleep period and last execution
        Duration sleepPeriod = fields[1].equals("null") ? null : Duration.parse(fields[1]);
        LocalDateTime lastExecution = fields[2].equals("null") ? null : LocalDateTime.parse(fields[2]);
        int numRepetition = Integer.parseInt(fields[3]);
        return new Repetition(oneTime, sleepPeriod, lastExecution, numRepetition);
    }

    @Override
    public String toString() {
        return "Repetition{" + "oneTime=" + oneTime + ", sleepPeriod=" + sleepPeriod + ", lastExecution=" + lastExecution + ", numRepetition=" + numRepetition + ", currentRepetition=" + currentRepetition + '}';
    }
    
    
    
}

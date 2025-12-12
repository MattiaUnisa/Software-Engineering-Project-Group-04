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

    @Override
    public String toString() {
        return "Repetition{" + "oneTime=" + oneTime + ", sleepPeriod=" + sleepPeriod + ", lastExecution=" + lastExecution + ", numRepetition=" + numRepetition + ", currentRepetition=" + currentRepetition + '}';
    }
    
    
    
}

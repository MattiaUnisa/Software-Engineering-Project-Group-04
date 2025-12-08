/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareengineeringproject.Model;

/**
 *
 * @author anton
 */
//This class allows to register what happen during the execution
public class ActionContext {
    
    private final StringBuilder execution;
    
    public ActionContext() {
        this.execution = new StringBuilder();
    }
    
    //It add the message of the execution
    public void appendToLog(String message) {
        this.execution.append(message).append("\n");
    }
    
    //It return the summary of the action
    public String getExecutionLog() {
        return execution.toString();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareengineeringproject.Model;

/**
 *
 * @author matda
 */
public interface Trigger {
    
    //Use of Strategy pattern in order to manage different trigger without specific details
    public boolean isTriggered();
    
    public String formatString();
    
}

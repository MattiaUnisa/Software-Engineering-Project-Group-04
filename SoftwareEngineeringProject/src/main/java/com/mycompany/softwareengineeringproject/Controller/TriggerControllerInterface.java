/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.softwareengineeringproject.Controller;

import com.mycompany.softwareengineeringproject.Model.Trigger;

/**
 *
 * @author matda
 */
public interface TriggerControllerInterface {
    
    //Method for the creation of the Trigger Object
    public Trigger buildTrigger();
    
    // method to set data in the UI in the page to modify the rule starting from an existing trigger
    public void setTriggerData(Trigger trigger);
}

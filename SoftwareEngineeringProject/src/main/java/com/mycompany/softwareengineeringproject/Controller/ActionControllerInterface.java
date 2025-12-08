/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareengineeringproject.Controller;

import com.mycompany.softwareengineeringproject.Model.Action;
/**
 *
 * @author anton
 */
public interface ActionControllerInterface {
    
    //This method is used to create and return the action that the user have configured in the UI
    public Action buildAction();
}

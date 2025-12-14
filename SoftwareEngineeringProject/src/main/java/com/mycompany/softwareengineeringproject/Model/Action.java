/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareengineeringproject.Model;

/**
 *
 * @author matda
 */
public interface Action {
    
    public void execute(ActionContext context);
    
    public void stop();
    
    public String formatString();
    
}

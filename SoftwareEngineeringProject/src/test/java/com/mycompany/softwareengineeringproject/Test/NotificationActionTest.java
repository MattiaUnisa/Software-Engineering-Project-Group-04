/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareengineeringproject.Test;

import com.mycompany.softwareengineeringproject.Model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class NotificationActionTest {
    private final String notValid = "";
    
    @Test
    public void testExecuteWithMissingMessage(){
        NotificationAction action = new NotificationAction(notValid);
        ActionContext context = new ActionContext();
        
        action.execute(context);
        System.out.println("context.getExecutionLog()");
        assertTrue(context.getExecutionLog().contains("No Message is given by the user"));
    }
    
}

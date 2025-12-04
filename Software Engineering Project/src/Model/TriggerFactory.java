/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalTime;

/**
 *
 * @author matda
 */
public class TriggerFactory {
    
    
    public static Trigger createTimeTrigger(LocalTime time){
        return new TimeTrigger(time);
    }
    
}

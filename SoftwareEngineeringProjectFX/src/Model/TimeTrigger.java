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
public class TimeTrigger implements Trigger{
    
    private LocalTime time;

    public TimeTrigger(LocalTime time) {
        this.time = time;
    }
    
    public LocalTime getTime() {
        return time;
    }
    
    @Override
    public boolean isTriggered(){
        return LocalTime.now().equals(time);
    }

    @Override
    public String toString() {
        return "TimeTrigger{" + "time=" + time + '}';
    }
    
}

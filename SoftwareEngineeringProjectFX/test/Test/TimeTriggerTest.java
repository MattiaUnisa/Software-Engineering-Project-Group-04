/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Test;

import Model.*;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TimeTriggerTest {

    @Test
    public void testTriggerAtExactTime() {
        LocalTime now = LocalTime.now();
        TimeTrigger trigger = new TimeTrigger(now);

        assertTrue(trigger.isTriggered());
    }

    @Test
    public void testTriggerAtDifferentTime() {
        LocalTime triggerTime = LocalTime.of(10, 30);
        TimeTrigger trigger = new TimeTrigger(triggerTime);
        
        assertFalse(trigger.isTriggered());
    }
}

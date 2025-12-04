/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */


import Model.*;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TriggerFactoryTest {

    @Test
    public void testCreateTimeTrigger() {
        LocalTime time = LocalTime.of(9, 0);
        Trigger t = TriggerFactory.createTimeTrigger(time);

        assertNotNull(t);
        assertTrue(t instanceof TimeTrigger);
        assertEquals(time, ((TimeTrigger) t).getTime());
    }

    @Test
    public void testFactoryReturnsDifferentInstances() {
        Trigger t1 = TriggerFactory.createTimeTrigger(LocalTime.of(9, 0));
        Trigger t2 = TriggerFactory.createTimeTrigger(LocalTime.of(9, 0));

        assertNotSame(t1, t2);
    }
}
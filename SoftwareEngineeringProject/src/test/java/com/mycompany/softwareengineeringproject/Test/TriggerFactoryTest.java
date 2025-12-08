package com.mycompany.softwareengineeringproject.Test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */


import com.mycompany.softwareengineeringproject.Model.*;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TriggerFactoryTest {

    //Test to verify the correct creation of an instance of the object TimeTrigger
    @Test
    public void testCreateTimeTrigger() {
        LocalTime time = LocalTime.of(9, 0);
        Trigger t = TriggerFactory.createTimeTrigger(time);

        assertNotNull(t);
        assertTrue(t instanceof TimeTrigger);
        assertEquals(time, ((TimeTrigger) t).getTime());
    }

}
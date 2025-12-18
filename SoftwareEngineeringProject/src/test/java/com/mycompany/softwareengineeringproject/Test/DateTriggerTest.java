package com.mycompany.softwareengineeringproject.Test;

import com.mycompany.softwareengineeringproject.Model.DateTrigger;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DateTriggerTest {

    //Test to control if the trigger is triggered when condition is verified
    @Test
    public void testIsTriggeredTrueOnCorrectDate() {
        LocalDate today = LocalDate.now();
        DateTrigger trigger = new DateTrigger(today);

        assertTrue(trigger.isTriggered());
    }

    //Test to control if the trigger isn't triggered when condition isn't verified
    @Test
    public void testIsTriggeredFalseOnDifferentDate() {
        LocalDate today = LocalDate.now();
        LocalDate different = today.minusDays(1);

        DateTrigger trigger = new DateTrigger(different);

        assertFalse(trigger.isTriggered());
    }

    //Test to control that the date of the trigger is equal to the date given to the constructor
    @Test
    public void testGetDate() {
        LocalDate date = LocalDate.of(2024, 5, 15);
        DateTrigger trigger = new DateTrigger(date);

        assertEquals(date, trigger.getdate());
    }
}

package com.mycompany.softwareengineeringproject.Test;

import com.mycompany.softwareengineeringproject.Model.DayOfMonthTrigger;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DayOfMonthTriggerTest {

    //Test to control if the trigger is triggered when condition is verified
    @Test
    public void testIsTriggeredTrueOnCorrectDay() {
        int today = LocalDate.now().getDayOfMonth();
        DayOfMonthTrigger trigger = new DayOfMonthTrigger(today);
        assertTrue(trigger.isTriggered());
    }

    //Test to control if the trigger isn't triggered when condition isn't verified
    @Test
    public void testIsTriggeredFalseOnDifferentDay() {
        int today = LocalDate.now().getDayOfMonth();

        // choose a different day but valid
        int different = today == 1 ? today + 1 : today - 1;

        DayOfMonthTrigger trigger = new DayOfMonthTrigger(different);

        assertFalse(trigger.isTriggered());
    }
    
    //Test to control that the day of the trigger is equal to the day given to the constructor
    @Test
    public void testGetDay() {
        DayOfMonthTrigger trigger = new DayOfMonthTrigger(15);
        assertEquals(15, trigger.getday());
    }
}

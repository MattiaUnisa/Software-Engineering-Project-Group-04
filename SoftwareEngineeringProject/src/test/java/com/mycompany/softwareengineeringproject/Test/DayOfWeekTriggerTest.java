package com.mycompany.softwareengineeringproject.Test;

import com.mycompany.softwareengineeringproject.Model.DayOfWeekTrigger;
import java.time.DayOfWeek;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DayOfWeekTriggerTest {

    //Test to control if the trigger is triggered when condition is verified
    @Test
    public void testIsTriggeredTrueOnCorrectDay() {
        //roday
        DayOfWeek today = LocalDate.now().getDayOfWeek();
        DayOfWeekTrigger trigger = new DayOfWeekTrigger(today);

        assertTrue(trigger.isTriggered());
    }

    //Test to control if the trigger isn't triggered when condition isn't verified
    @Test
    public void testIsTriggeredFalseOnDifferentDay() {
        DayOfWeek today = LocalDate.now().getDayOfWeek();

        //prendo un giorno diverso da quello attuale
        DayOfWeek different = today.plus(1);
        if(different == today) 
            different = today.minus(1);

        DayOfWeekTrigger trigger = new DayOfWeekTrigger(different);

        assertFalse(trigger.isTriggered());
    }

}

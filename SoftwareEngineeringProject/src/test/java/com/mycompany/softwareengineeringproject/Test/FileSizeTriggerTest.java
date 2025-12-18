package com.mycompany.softwareengineeringproject.Test;

import com.mycompany.softwareengineeringproject.Model.FileSizeTrigger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileSizeTriggerTest {

    //Test to control if the trigger is triggered when condition is verified
    @Test
    public void testIsTriggeredTrueWhenFileSizeIsGreater() throws IOException {
        // create a temporary file
        File tempFile = new File("fileSizeTest1.txt");

        // create file of 100 bytes
        try (FileOutputStream out = new FileOutputStream(tempFile)) {
            out.write(new byte[100]);
        }

        FileSizeTrigger trigger = new FileSizeTrigger(tempFile.getPath(), 50);

        //size of file is bigger than the value inserted by the user
        assertTrue(trigger.isTriggered());

        //Cleanup the file created
        tempFile.delete();
    }

    
    //Test to control if the trigger isn't triggered when condition isn't verified
    @Test
    public void testIsTriggeredFalseWhenFileSizeIsSmaller() throws IOException {
        // create a temporary file
        File tempFile = new File("fileSizeTest2.txt");

        try (FileOutputStream out = new FileOutputStream(tempFile)) {
            out.write(new byte[50]);
        }

        FileSizeTrigger trigger = new FileSizeTrigger(tempFile.getPath(), 100);

        //size of file is smaller than the value inserted by the user
        assertFalse(trigger.isTriggered());

        //Cleanup the file created
        tempFile.delete();
    }

    //Test to control if the trigger isn't triggered when condition isn't verified
    @Test
    public void testIsTriggeredFalseWhenFileDoesNotExist() {
        FileSizeTrigger trigger = new FileSizeTrigger("fileNotExisting.txt", 10);

        //Trigger isn't triggered when the file doesn't exist
        assertFalse(trigger.isTriggered());
    }

    //Test to control that the file and size of file of the trigger are equal to the attribute given to the constructor
    @Test
    public void testGetters() {
        FileSizeTrigger trigger = new FileSizeTrigger("some/path.txt", 200);

        assertEquals("some/path.txt", trigger.getFilePath());
        assertEquals(200, trigger.getSize());
    }
}

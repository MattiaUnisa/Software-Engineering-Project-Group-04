package com.mycompany.softwareengineeringproject.Test;

import com.mycompany.softwareengineeringproject.Model.FileTrigger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

public class FileTriggerTest {

    //Test to control if the trigger is triggered when condition is verified
    @Test
    public void testIsTriggeredTrueWhenFileExists() throws IOException {
        // create a temporary directory
        File tempDir = new File("testDirExists");
        tempDir.mkdir();

        // Create a file into the directory
        File tempFile = new File(tempDir, "testFile.txt");
        tempFile.createNewFile();

        FileTrigger trigger = new FileTrigger(tempDir.getPath(), "testFile.txt");

        //The trigger activates when the file exists in the directory
        assertTrue(trigger.isTriggered());

        // Cleanup the file and the directory created
        tempFile.delete();
        tempDir.delete();
    }

    //Test to control if the trigger isn't triggered when condition isn't verified
    @Test
    public void testIsTriggeredFalseWhenFileDoesNotExist() {
        File tempDir = new File("testDirNoFile");
        tempDir.mkdir();

        // No file created, it doesn't exist
        FileTrigger trigger = new FileTrigger(tempDir.getPath(), "missingFile.txt");

        //the trigger doesn't activate if the file doesn't exist in the directory
        assertFalse(trigger.isTriggered());

        // Cleanup the directory created
        tempDir.delete();
    }

    //Test to control if the trigger isn't triggered when condition isn't verified
    @Test
    public void testIsTriggeredFalseWhenDirectoryDoesNotExist() {
        // No directory created, it doesn't exist
        FileTrigger trigger = new FileTrigger("nonExistingDir", "file.txt");

        //the trigger doesn't activate if the directory doesn't exist
        assertFalse(trigger.isTriggered());
    }

    //Test to control that the directory and filename of the trigger are equal to the attribute given to the constructor
    @Test
    public void testGetters() {
        FileTrigger trigger = new FileTrigger("some/path", "myfile.txt");

        assertEquals("some/path", trigger.getFilePath());
        assertEquals("myfile.txt", trigger.getFileName());
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareengineeringproject.Test;

import com.mycompany.softwareengineeringproject.Model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author anton
 */
public class ActionFactoryTest {
    @Test
    public void testCreatePlayAudio(){
        String testPath = "C:\\test\\audio.wav";
        Action a = ActionFactory.createPlayAudio(testPath);
        
        assertNotNull(a);
        assertTrue(a instanceof PlayAudioAction);
    }
}

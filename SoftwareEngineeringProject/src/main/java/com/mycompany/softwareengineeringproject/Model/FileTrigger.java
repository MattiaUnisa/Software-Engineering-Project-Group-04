/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareengineeringproject.Model;

import com.mycompany.softwareengineeringproject.View.DialogManager;
import java.io.File;
import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 *
 * @author matda
 */
public class FileTrigger implements Trigger{
    
    private final String filePath;
    private final String fileName;
    
    public FileTrigger(String filePath, String fileName) {
        this.filePath = filePath;
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFileName() {
        return fileName;
    }
    
    //Condition for the creation of the trigger
    @Override
    public boolean isTriggered(){
        File directory = new File(filePath);
        if (!directory.exists()) {
            //DialogManager.showError("ERROR", "Directory not found", null);
            return false;
        }
        File targetFile = new File(directory, fileName);
        return targetFile.exists();
    }
    
    
    @Override
    public String formatString() {
        return "FileTrigger:" + this.filePath + ";" + this.fileName;
    }
    
    //This method permit to rebuild a Trigger object from a string
    public static Trigger parseString(String trigger){
        if(!trigger.startsWith("FileTrigger:")){
            throw new IllegalArgumentException("Invalid FileTrigger format.");        
        }
        String filepart = trigger.substring("FileTrigger:".length());
        String[] parts = filepart.split(";");
        String path = parts[0];
        String name = parts[1];
        //Use the factory to create the object
        return TriggerFactory.createFileTrigger(path,name);
    }

    @Override
    public String toString() {
        return "FileTrigger{" + "filePath=" + filePath + ", fileName=" + fileName + '}';
    }

    
        
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareengineeringproject.Model;

import java.io.File;

/**
 *
 * @author matda
 */
//This class say that the trigger isTriggered when the size of a file is greater 
//than the size value insert by the user
public class FileSizeTrigger implements Trigger{
    
    private final String filePath;
    private final int size;
    
    public FileSizeTrigger(String filePath, int size) {
        this.filePath = filePath;
        this.size = size;
    }

    public String getFilePath() {
        return filePath;
    }

    public int getSize() {
        return size;
    }
    
    //Condition for the creation of the trigger
    @Override
    public boolean isTriggered(){
        File file = new File(filePath);
        if (!file.exists()) {
            return false;
        }
        return file.length() > size;
    }
    
    
    @Override
    public String formatString() {
        return "FileSizeTrigger:" + this.filePath + ";" + this.size;
    }
    
    //This method permit to rebuild a Trigger object from a string
    public static Trigger parseString(String trigger){
        if(!trigger.startsWith("FileSizeTrigger:")){
            throw new IllegalArgumentException("Invalid FileSizeTrigger format.");        
        }
        String filepart = trigger.substring("FileSizeTrigger:".length());
        String[] parts = filepart.split(";");
        String path = parts[0];
        int fileSize = Integer.parseInt(parts[1]);
        //Use the factory to create the object
        return TriggerFactory.createFileSizeTrigger(path,fileSize);
    }

    @Override
    public String toString() {
        return "FileTrigger{" + "filePath=" + filePath + ", size=" + size + '}';
    }
    
}

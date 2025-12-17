package com.mycompany.softwareengineeringproject.Model;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class PlayAudioAction implements Action {

    private final String filePath;

    //it's an object that allows us to reproduce media
    private Clip clip;

    public PlayAudioAction(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void execute(ActionContext context) {
        if (filePath == null || filePath.isEmpty()) {
            context.appendToLog("ERROR: Missing Path");
            return;
        }

        File audioFile = new File(filePath);
        if (!audioFile.exists()) {
            context.appendToLog("ERROR: File not found: " + filePath);
            //Get the listener, verify if it is null and if not call the method onShowNotification in order to Show a notification
            if (context.getUiEventListener() != null) {
                context.getUiEventListener().onShowError("ERROR", "File not found", null);
            }  
            return;
        }

        try {

            //Takes in input the audio file with the extension .wav
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            //Take a clip instance from AudioSystem
            clip = AudioSystem.getClip();

            //This method take audio data from audioStream and load them in memory
            clip.open(audioStream);

            //Play audio
            clip.start();
            context.appendToLog("Playing Audio (WAV): " + filePath);

            String fileName = new File(filePath).getName();
            //Get the listener, verify if it is null and if not call the method onShowAudioPlayer in order to Show it
            if (context.getUiEventListener() != null) {
                context.getUiEventListener().onShowAudioPlayer(fileName);
            }  

            stop();
            context.appendToLog("Audio stopped by user.");

        } catch (Exception e) {
            if (context.getUiEventListener() != null) {
                context.getUiEventListener().onShowError("Errore", "Cannot play audio", "Make sure to play an .WAV audio file.\nError: " + e.getMessage());
            }  
        }
    }

    @Override
    public void stop() {
        // close audio resource
        if (clip != null) {
            if (clip.isRunning()) {
                clip.stop();
            }
            clip.close();
        }
    }
    
    public String getFilePath() {
        return filePath;
    }
    
    //Format an Action object in a string 
    @Override
    public String formatString(){
        return "PlayAudioAction:" + this.filePath;
    }
    
    //This method permit to rebuild an Action object from a string
    public static Action parseString(String action){
        if (!action.startsWith("PlayAudioAction:")) {
            throw new IllegalArgumentException("Invalid PlayAudioAction format.");
        }
        String path = action.substring("PlayAudioAction:".length());
        //Use the factory to create the object
        return ActionFactory.createPlayAudio(path);
    }


    @Override
    public String toString() {
        return "PlayAudioAction{" + "filePath=" + filePath + '}';
    }
}

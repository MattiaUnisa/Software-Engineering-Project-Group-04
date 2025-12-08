/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareengineeringproject.Model;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
/**
 *
 * @author anton
 */

public class PlayAudioAction implements Action{
    
    private final String filePath;
    private MediaPlayer mediaPlayer;

    public PlayAudioAction(String filePath) {
        this.filePath = filePath;
    }
    
    //This method performs the audio playback action. The parameter "context" is used to register the result of the actions.
    @Override
    public void execute(ActionContext context){
        if(filePath == null || filePath.isEmpty()){
            context.appendToLog("ERROR: The path of the audio file isn't define");
            return;
        }
        try{
            //Here there is the conversion of the filePath in URI, that is necessary for JavaFX
            String mediaUrl = new File(filePath).toURI().toString();
            Media media = new Media(mediaUrl);
            
            media.setOnError(() -> context.appendToLog("ERROR: Loading failed of the file"));
            
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
            context.appendToLog("The audio is played from:" + filePath);
            
        }catch(Exception e){
            context.appendToLog("Impossible to play the audio. Error: " + e.getMessage());
        }
    }
    
    //This method interrupt the execution of the audio
    @Override
    public void stop(){
        if(mediaPlayer != null){
            mediaPlayer.stop();
        }
    }
}

package com.mycompany.softwareengineeringproject.Model;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.Optional;

public class PlayAudioAction implements Action {
    
    private final String filePath;
    
    //it's an object that allows us to reproduce media
    private Clip clip; 

    public PlayAudioAction(String filePath) {
        this.filePath = filePath;
    }
    
    @Override
    public void execute(ActionContext context){
        if(filePath == null || filePath.isEmpty()){
            context.appendToLog("ERROR: missing Path");
            return;
        }
        
        File audioFile = new File(filePath);
        if (!audioFile.exists()) {
             context.appendToLog("ERROR: File not found: " + filePath);
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

            // 3. Apertura Finestra di Stop (Alert)
            // Poiché siamo già dentro Platform.runLater (grazie al fix su RuleEngine), 
            // possiamo mostrare l'alert direttamente.
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Audio Player");
            alert.setHeaderText("Playing audio...");
            alert.setContentText("File: " + audioFile.getName() + "\nClick STOP to interrupt.");
            
            // Personalizzo il bottone
            ButtonType stopButton = new ButtonType("STOP Audio");
            alert.getButtonTypes().setAll(stopButton);

            // 4. Blocca l'esecuzione finché l'utente non preme il bottone
            Optional<ButtonType> result = alert.showAndWait();

            // 5. Quando la finestra si chiude, stoppa l'audio
            stop();
            context.appendToLog("Audio stopped by user.");
            
        } catch (Exception e) {
            // Gestione errori (es. se provi a caricare un MP3 invece di WAV)
            context.appendToLog("PLAYING ERROR: " + e.getMessage());
            System.err.println("ERROR DETAIL:");
            e.printStackTrace();
            
            // Mostriamo un alert di errore all'utente invece di crashare
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Format error");
            errorAlert.setHeaderText("File impossible to play");
            errorAlert.setContentText("Make sure to play an .WAV audio file.\nError: " + e.getMessage());
            errorAlert.showAndWait();
        }
    }
    
    @Override
    public void stop(){
        // Chiude la risorsa audio in modo pulito
        if(clip != null){
            if(clip.isRunning()) {
                clip.stop();
            }
            clip.close();
        }
    }

    @Override
    public String toString() {
        return "PlayAudioAction{" + "filePath=" + filePath + '}';
    }
}

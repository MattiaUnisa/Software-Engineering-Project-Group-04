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
    // Usiamo Clip (libreria standard) invece di MediaPlayer (JavaFX Media)
    // Questo bypassa l'errore della DLL mancante
    private Clip clip; 

    public PlayAudioAction(String filePath) {
        this.filePath = filePath;
    }
    
    @Override
    public void execute(ActionContext context){
        if(filePath == null || filePath.isEmpty()){
            context.appendToLog("ERROR: Path mancante");
            return;
        }
        
        File audioFile = new File(filePath);
        if (!audioFile.exists()) {
             context.appendToLog("ERROR: File non trovato: " + filePath);
             return;
        }

        try {
            // 1. Preparazione Audio (Sistema Standard javax.sound)
            // NOTA: Funziona solo con file .WAV
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            
            // 2. Avvio Audio
            clip.start();
            context.appendToLog("Playing Audio (WAV): " + filePath);

            // 3. Apertura Finestra di Stop (Alert)
            // Poiché siamo già dentro Platform.runLater (grazie al fix su RuleEngine), 
            // possiamo mostrare l'alert direttamente.
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Audio Player");
            alert.setHeaderText("Riproduzione in corso...");
            alert.setContentText("File: " + audioFile.getName() + "\nPremi STOP per interrompere.");
            
            // Personalizzo il bottone
            ButtonType stopButton = new ButtonType("STOP Audio");
            alert.getButtonTypes().setAll(stopButton);

            // 4. Blocca l'esecuzione finché l'utente non preme il bottone
            Optional<ButtonType> result = alert.showAndWait();

            // 5. Quando la finestra si chiude, stoppa l'audio
            stop();
            context.appendToLog("Audio interrotto dall'utente.");
            
        } catch (Exception e) {
            // Gestione errori (es. se provi a caricare un MP3 invece di WAV)
            context.appendToLog("ERRORE RIPRODUZIONE: " + e.getMessage());
            System.err.println("Dettaglio errore audio:");
            e.printStackTrace();
            
            // Mostriamo un alert di errore all'utente invece di crashare
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Errore Formato");
            errorAlert.setHeaderText("Impossibile riprodurre il file");
            errorAlert.setContentText("Assicurati di usare un file .WAV.\nErrore: " + e.getMessage());
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
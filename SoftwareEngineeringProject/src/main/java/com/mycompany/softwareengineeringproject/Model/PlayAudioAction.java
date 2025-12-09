package com.mycompany.softwareengineeringproject.Model;

import com.mycompany.softwareengineeringproject.View.DialogManager;
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
    public void execute(ActionContext context) {
        if (filePath == null || filePath.isEmpty()) {
            context.appendToLog("ERROR: missing Path");
            DialogManager.showError("ERROR", "Missing Path", null);
            return;
        }

        File audioFile = new File(filePath);
        if (!audioFile.exists()) {
            context.appendToLog("ERROR: File not found: " + filePath);
            DialogManager.showError("ERROR", "File not found", null);
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
            DialogManager.showAudioPlayerDialog(fileName);
            // in DialogManager .showAndWait freeze the execution of the code to the click by the user of button STOP. 
            // when that window is closed, the first method is stop(), so the audio playing is stopped.

            stop();
            context.appendToLog("Audio stopped by user.");

        } catch (Exception e) {
            DialogManager.showError("Errore", "Cannot play audio", "Make sure to play an .WAV audio file.\nError: " + e.getMessage());
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

    @Override
    public String toString() {
        return "PlayAudioAction{" + "filePath=" + filePath + '}';
    }
}

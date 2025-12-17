package com.mycompany.softwareengineeringproject.View;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.Optional;

public class DialogManager {

    // Method called for Warnings
    public static void showWarning(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    // Method called for Errors
    public static void showError(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    // Method called for Notifications (Information popup)
public static void showNotification(String title, String content) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    ButtonType closeButton = new ButtonType("OK");
    alert.getButtonTypes().setAll(closeButton);
    alert.showAndWait();
}


    // method to permits the user to stop the audio while playing
    public static boolean showAudioPlayerDialog(String fileName) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Audio Player");
        alert.setHeaderText("Playing audio...");
        alert.setContentText("File: " + fileName + "\nclick STOP to interrupt.");

        ButtonType stopButton = new ButtonType("STOP Audio");
        alert.getButtonTypes().setAll(stopButton);

        Optional<ButtonType> result = alert.showAndWait();
        // in DialogManager .showAndWait freeze the execution of the code to the click by the user of button STOP. 
        // when that window is closed, the first method is stop(), so the audio playing is stopped.
        
        return true;
    }

}

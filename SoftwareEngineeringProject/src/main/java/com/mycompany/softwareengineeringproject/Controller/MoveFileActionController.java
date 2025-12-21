
package com.mycompany.softwareengineeringproject.Controller;

import com.mycompany.softwareengineeringproject.Model.Action;
import com.mycompany.softwareengineeringproject.Model.ActionFactory;
import com.mycompany.softwareengineeringproject.Model.MoveFileAction;
import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class MoveFileActionController implements ActionControllerInterface {

    @FXML
    private TextField sourcePathField;

    @FXML
    private TextField destPathField;

    @FXML
    public void initialize() {
        // Initialization logic if needed
    }

    @FXML
    private void onSelectSource() {
        // FileChooser to select the source file
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Source File");

        Stage sourceStage = (Stage) sourcePathField.getScene().getWindow();
        File source = fileChooser.showOpenDialog(sourceStage);

        if (source != null) {
            // Show selected source file path
            sourcePathField.setText(source.getAbsolutePath());
        }
    }

    @FXML
    private void onSelectDest() {
        // DirectoryChooser to select the destination directory
        DirectoryChooser dirChooser = new DirectoryChooser();
        dirChooser.setTitle("Select Destination Directory");

        Stage destStage = (Stage) destPathField.getScene().getWindow();
        File dest = dirChooser.showDialog(destStage);

        if (dest != null) {
            // Show selected destination directory path
            destPathField.setText(dest.getAbsolutePath());
        }
    }

    @Override
    public Action buildAction() {
        // Check that both fields are filled before creating the action
        if ((sourcePathField == null || sourcePathField.getText().isEmpty()) ||
            (destPathField == null || destPathField.getText().isEmpty())) {
            return null;
        }

        // Create the MoveFileAction
        return ActionFactory.createMoveFile(new File(sourcePathField.getText()),new File(destPathField.getText()));
    }

    // method to get the instance of the MoveFileAction to set the spinner values in the UI
    // called by setAction method in ActionController
    @Override
    public void setActionData(Action action) {
        // Used to restore action data in the UI
        if (action instanceof MoveFileAction) {
            MoveFileAction moveAction = (MoveFileAction) action;

            if (moveAction.getSource() != null) {
                sourcePathField.setText(moveAction.getSource().getAbsolutePath());
            }

            if (moveAction.getDestination() != null) {
                destPathField.setText(moveAction.getDestination().getAbsolutePath());
            }
        }
    }
}


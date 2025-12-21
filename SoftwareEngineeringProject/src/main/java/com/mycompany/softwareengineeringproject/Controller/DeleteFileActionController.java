package com.mycompany.softwareengineeringproject.Controller;

import com.mycompany.softwareengineeringproject.Model.Action;
import com.mycompany.softwareengineeringproject.Model.ActionFactory;
import com.mycompany.softwareengineeringproject.Model.DeleteFileAction;
import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class DeleteFileActionController implements ActionControllerInterface {

    @FXML
    private TextField filePathField;

    @FXML
    public void initialize() {
        // Initialization if needed
    }

    @FXML
    private void onSelectFile() {
        // FileChooser to select the file to delete
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File to Delete");

        Stage stage = (Stage) filePathField.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            filePathField.setText(file.getAbsolutePath());
        }
    }

    @Override
    public Action buildAction() {
        // Check if the file path is valid
        if (filePathField == null || filePathField.getText().isEmpty()) {
            return null;
        }

        return ActionFactory.createDeleteFile(
                new File(filePathField.getText())
        );
    }

    @Override
    public void setActionData(Action action) {
        // Load data into UI when editing a rule
        if (action instanceof DeleteFileAction) {
            DeleteFileAction deleteAction = (DeleteFileAction) action;

            if (deleteAction.getFile() != null) {
                filePathField.setText(deleteAction.getFile().getAbsolutePath());
            }
        }
    }
}

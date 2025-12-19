
package com.mycompany.softwareengineeringproject.Controller;

import com.mycompany.softwareengineeringproject.Model.Action;
import com.mycompany.softwareengineeringproject.Model.ActionFactory;
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
        // Inicialización, si es necesario.
    }

    @FXML
    private void onSelectSource() {
        // FileChooser para seleccionar el archivo fuente.
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Source File");
        Stage sourceStage = (Stage) sourcePathField.getScene().getWindow();
        File source = fileChooser.showOpenDialog(sourceStage);
        if (source != null) {
            sourcePathField.setText(source.getAbsolutePath());  // Muestra la ruta del archivo seleccionado.
        }
    }

    @FXML
    private void onSelectDest() {
        // DirectoryChooser para seleccionar el directorio destino.
        DirectoryChooser dirChooser = new DirectoryChooser();
        dirChooser.setTitle("Select Destination Directory");
        Stage destStage = (Stage) destPathField.getScene().getWindow();
        File dest = dirChooser.showDialog(destStage);
        if (dest != null) {
            destPathField.setText(dest.getAbsolutePath());  // Muestra la ruta del directorio seleccionado.
        }
    }

    @Override
    public Action buildAction() {
        // Verifica que ambos campos estén completos antes de mover el archivo
        if (sourcePathField == null || sourcePathField.getText().isEmpty() && 
            (destPathField == null || destPathField.getText().isEmpty())) {
            return null;  // Si alguno de los campos está vacío, no hace nada.
        }
        // Crea la acción de mover el archivo.
        return ActionFactory.createMoveFile(new File(sourcePathField.getText()), new File(destPathField.getText()));
    }

    @Override
    public void setActionData(Action action) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

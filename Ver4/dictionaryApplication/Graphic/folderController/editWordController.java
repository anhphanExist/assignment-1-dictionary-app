package dictionaryApplication.Graphic.folderController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.net.URL;
import java.util.ResourceBundle;

import static dictionaryApplication.dictionaryApplication.catchingException;
import static dictionaryApplication.dictionaryApplication.window;

public class editWordController implements Initializable {
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    
    }
    
    @FXML
    public void handleSaveButton(ActionEvent event) {
    
    }
    
    /**
     * Cancel add word scene to go back to main scene
     * @param event
     */
    @FXML
    public void handleCancelButton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/dictionaryApplication/Graphic/FXML/dictionaryApplication.fxml"));
            window.setScene(new Scene(root));
            window.show();
        } catch (Exception e) {
            catchingException(e);
        }
    }
}

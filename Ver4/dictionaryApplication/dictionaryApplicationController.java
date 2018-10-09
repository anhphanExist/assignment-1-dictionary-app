package dictionaryApplication;

import dictionaryManagement.DictionaryManagement;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.TextFields;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class dictionaryApplicationController implements Initializable {
    
    @FXML
    private TextField input;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Dictionary dict = DictionaryManagement.insertFromFile("src/dictionaryApplication/data.txt");
        ArrayList<String> possibleTarget = new ArrayList<>();
        for (int i = 0; i < dict.getSize(); i++) {
            possibleTarget.add(dict.getDict().get(i).getTarget());
        }
        
        TextFields.bindAutoCompletion(input, possibleTarget);
    }
    
    /**
     * Close window
     * @param event
     */
    public void handleMenuItemClose(ActionEvent event) {
        // Exit user inteface
        Platform.exit();
        // Exit system
        System.exit(0);
    }
}

package dictionaryApplication;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.TextFields;


import java.net.URL;
import java.util.ResourceBundle;

public class dictionaryApplicationController implements Initializable {
    
    @FXML
    private TextField input;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Dictionary dict = DictionaryManagement.insertFromFile("src/dictionaryApplication/data.txt");
        String[] possibleTarget = new String[10000];
        for (int i = 0; i < dict.getSize(); i++) {
            possibleTarget[i] = dict.getDict().get(i).getTarget();
        }
        
        TextFields.bindAutoCompletion(input, possibleTarget);
    }
}

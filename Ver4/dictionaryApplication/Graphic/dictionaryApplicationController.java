package dictionaryApplication.Graphic;

import dictionaryApplication.BasicDict.DictionaryManagement;
import dictionaryApplication.BasicDict.Word;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.TextFields;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static dictionaryApplication.dictionaryApplication.dict;

public class dictionaryApplicationController implements Initializable {
    
    @FXML public TextField input;
    @FXML public Label explainLabel;
    @FXML public ListView<String> relatedTarget;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    
        ArrayList<String> possibleTarget = new ArrayList<>();
        for (int i = 0; i < dict.getSize(); i++) {
            possibleTarget.add(dict.getDict().get(i).getTarget());
        }
        ObservableList<String> targetForListView = FXCollections.observableArrayList(possibleTarget);
        relatedTarget.setItems(targetForListView);
    
        TextFields.bindAutoCompletion(input, possibleTarget);
    }

    public void searchButton(ActionEvent actionEvent) {
        String textSet = new String();
        String searchingInput = input.getText();
        if (searchingInput.isEmpty()){
            textSet = "MAY CHUA NHAP TU MA CON NGU";
        }
        else {
            if (DictionaryManagement.dictionarySearcher(dict, searchingInput, relatedTarget) != -1) {
                int indexSearch = DictionaryManagement.dictionarySearcher(dict, searchingInput, relatedTarget);
                Word inputWord = dict.getDict().get(indexSearch);
                textSet = inputWord.getExplain();
            }

            else {
                textSet = "DEO CO TU NAY TRONG TU DIEN";
            }
        }
        explainLabel.setText(textSet);
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

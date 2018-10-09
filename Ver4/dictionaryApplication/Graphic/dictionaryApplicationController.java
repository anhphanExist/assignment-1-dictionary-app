package dictionaryApplication.Graphic;

import dictionaryApplication.BasicDict.Dictionary;
import dictionaryApplication.BasicDict.DictionaryManagement;
import dictionaryApplication.BasicDict.Word;
import dictionaryApplication.dictionaryApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.TextFields;


import java.net.URL;
import java.util.ResourceBundle;

public class dictionaryApplicationController implements Initializable {
    Dictionary dict = DictionaryManagement.insertFromFile("src/dictionaryApplication/BasicDict/data.txt");
    @FXML
    public TextField input;
    @FXML
    public Label explainLabel,relatedLabel;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String[] possibleTarget = new String[10000];
        for (int i = 0; i < dict.getSize(); i++) {
            possibleTarget[i] = dict.getDict().get(i).getTarget();
        }
        
        TextFields.bindAutoCompletion(input, possibleTarget);
    }

    public void searchButton(ActionEvent actionEvent) {
        String textSet = new String();
        String searchingInput = input.getText();
        if (searchingInput.isEmpty()){
            textSet = "MAY CHUA NHAP TU MA CON NGU";
        }
        else {
            if (DictionaryManagement.dictionarySearcher(dict,searchingInput) != -1) {
                int indexSearch = DictionaryManagement.dictionarySearcher(dict,searchingInput);
                Word inputWord = dict.getDict().get(indexSearch);
                textSet = inputWord.getExplain();
            }

            else {
                textSet = "DEO CO TU NAY TRONG TU DIEN";
            }
        }
        explainLabel.setText(textSet);
    }
}

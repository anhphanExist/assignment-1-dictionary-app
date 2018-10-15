package dictionaryApplication.Graphic.folderController;

import dictionaryApplication.BasicDict.DictionaryManagement;
import dictionaryApplication.BasicDict.Word;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static dictionaryApplication.dictionaryApplication.*;

public class editWordController implements Initializable {
    
    @FXML public TextField searchEdit;
    @FXML public TextField explainEdit;
    @FXML public Label curExplain;
    @FXML public Button saveButton;
    @FXML public Button cancelButton;
    @FXML public Button searchButton;
    
    private int indexSearch;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Create ArrayList to store possible Target in List View
        ArrayList<String> possibleTarget = new ArrayList<>();
        for (int i = 0; i < dict.getSize(); i++) {
            possibleTarget.add(dict.getDict().get(i).getTarget());
        }
    
        // Bind auto complete textfield
        TextFields.bindAutoCompletion(searchEdit, possibleTarget);
    }
    
    /**
     * search for edit word
     * @param event
     */
    @FXML
    public void handleSearchButton(ActionEvent event) {
        // Print out explain to current explain
        String searchingInput = Word.standardizeString(searchEdit.getText());
        if (searchingInput.isEmpty()) {
            showAlert("YOU HAVEN'T SEARCH FOR ANYTHING YET !!");
        }
        else {
            indexSearch = DictionaryManagement.dictionarySearcher(dict, searchingInput, null);
            if (indexSearch >= 0) {
                curExplain.setText(dict.getDict().get(indexSearch).getExplain());
            }
        }
    }
    
    /**
     * save progress
     * @param event
     */
    @FXML
    public void handleSaveButton(ActionEvent event) {
        String newExplain = Word.standardizeString(explainEdit.getText());
        if (newExplain.isEmpty()) {
            showAlert("YOU HAVEN'T TYPE NEW EXPLAIN YET !!");
        }
        else if (curExplain.getText().isEmpty()) {
            showAlert("YOU HAVE TO SEARCH FOR A SUITABLE WORD FIRST !!");
        }
        else {
            dict.getDict().get(indexSearch).setExplain(newExplain);
            showAlert("EDIT SUCCESS");
        }
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

package dictionaryApplication.Graphic;

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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static dictionaryApplication.dictionaryApplication.catchingException;
import static dictionaryApplication.dictionaryApplication.dict;
import static dictionaryApplication.dictionaryApplication.window;

public class deleteWordController implements Initializable {
    
    @FXML public TextField searchDelete;
    @FXML public ListView listView;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Create ArrayList to store possible Target in List View
        ArrayList<String> possibleTarget = new ArrayList<>();
        for (int i = 0; i < dict.getSize(); i++) {
            possibleTarget.add(dict.getDict().get(i).getTarget());
        }
        // Set possible target to list view using observable list
        ObservableList<String> targetForListView = FXCollections.observableArrayList(possibleTarget);
        listView.setItems(targetForListView);
    
        // Bind auto complete textfield
        TextFields.bindAutoCompletion(searchDelete, possibleTarget);
    }
    
    @FXML
    public void handleSearchButton(ActionEvent event) {
    
    }
    
    @FXML
    public void handleSaveButton(ActionEvent event) {
    
    }
    
    @FXML
    public void handleCancelButton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/dictionaryApplication/Graphic/dictionaryApplication.fxml"));
            window.setScene(new Scene(root));
            window.show();
        } catch (Exception e) {
            catchingException(e);
        }
    }
    
}

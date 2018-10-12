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
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static dictionaryApplication.dictionaryApplication.*;

public class deleteWordController implements Initializable {
    
    @FXML public TextField searchDelete;
    @FXML public ListView<String> listView;
    @FXML public Button cancelButton;
    @FXML public Button deleteButton;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Create ArrayList to store possible Target in List View
        ArrayList<String> possibleTarget = new ArrayList<>();
        for (int i = 0; i < dict.getSize(); i++) {
            possibleTarget.add(dict.getDict().get(i).getTarget());
        }
        // Set possible target to list view using observable list
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        ObservableList<String> targetForListView = FXCollections.observableArrayList(possibleTarget);
        listView.setItems(targetForListView);
    
        // Bind auto complete textfield
        TextFields.bindAutoCompletion(searchDelete, possibleTarget);
    }
    
    @FXML
    public void handleSearchButton(ActionEvent event) {
        String searchInput = Word.standardizeString(searchDelete.getText());
        if (!searchInput.isEmpty()) {
            if(DictionaryManagement.dictionarySearcher(dict, searchInput, listView) == -1) {
                listView.setItems(FXCollections.observableArrayList("SORRY WE CAN'T FOUND THIS WORD !!"));
            }
        }
        else {
            listView.setItems(FXCollections.observableArrayList("YOU HAVEN'T WRITTEN ANYTHING DUDE !!"));
        }
    }
    
    @FXML
    public void handleDeleteButton(ActionEvent event) {
        try {
            // Get the target of word in the current dictionary
            ArrayList<String> deleteTargets = new ArrayList<>();
            List<String> listDelTargets = listView.getSelectionModel().getSelectedItems();
            for (String curTarget : listDelTargets) {
                deleteTargets.add(curTarget);
            }
            if (!deleteTargets.isEmpty()) {
                // Init an arraylist of words expected to be delete
                ArrayList<Word> deleteWords = DictionaryManagement.listSearch(deleteTargets);
                // Remove all words have been selected
                dict.getDict().removeAll(deleteWords);
                // Create ArrayList renew possible Target in List View
                ArrayList<String> possibleTarget = new ArrayList<>();
                for (int i = 0; i < dict.getSize(); i++) {
                    possibleTarget.add(dict.getDict().get(i).getTarget());
                }
                // Renew possible target to list view using observable list
                listView.setItems(FXCollections.observableArrayList(possibleTarget));
                listView.refresh();
                showAlert("DELETE SUCCESSFUL !!");
            } else {
                showAlert("NO WORDS TO DELETE !!");
            }
        } catch (Exception e) {
            catchingException(e);
        }
    }
    
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

package dictionaryApplication.Graphic.folderController;

import dictionaryApplication.BasicDict.Word;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import static dictionaryApplication.dictionaryApplication.catchingException;
import static dictionaryApplication.dictionaryApplication.dict;
import static dictionaryApplication.dictionaryApplication.window;

public class addWordController implements Initializable {
    
    //region addWord scene variables
    @FXML public TextField targetAdd;
    @FXML public TextField explainAdd;
    @FXML public Button saveButton;
    @FXML public Button cancelButton;
    
    //endregion
    
    //region addWord scene handle method
    
    /**
     * Init Controller
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Init addWord success");
    }
    
    /**
     * Save the add word to dict variable
     * @param event
     */
    @FXML
    public void saveAddWord(ActionEvent event) {
        Word newWord = new Word();
        newWord.setTarget(targetAdd.getText().toLowerCase());
        newWord.setExplain(explainAdd.getText());
        dict.addDict(newWord);
        dict.sortDict();
    }
    
    /**
     * Cancel add word scene to go back to main scene
     * @param event
     */
    @FXML
    public void cancelAddWord(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/dictionaryApplication/Graphic/FXML/dictionaryApplication.fxml"));
            window.setScene(new Scene(root));
            window.show();
        } catch (Exception e) {
            catchingException(e);
        }
    }
    //endregion
}

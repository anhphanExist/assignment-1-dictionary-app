package dictionaryApplication.Graphic.folderController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

import static dictionaryApplication.dictionaryApplication.catchingException;
import static dictionaryApplication.dictionaryApplication.window;

public class googleTranslateController implements Initializable {
    
    @FXML public WebView webView;
    private WebEngine engine;
    
    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        engine = webView.getEngine();
        engine.load("https://translate.google.com");
    }
    
    @FXML
    public void handleBackButton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/dictionaryApplication/Graphic/FXML/dictionaryApplication.fxml"));
            window.setScene(new Scene(root));
            window.show();
        } catch (Exception e) {
            catchingException(e);
        }
    }
}

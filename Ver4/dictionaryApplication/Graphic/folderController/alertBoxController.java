package dictionaryApplication.Graphic.folderController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

import static dictionaryApplication.dictionaryApplication.catchingException;
import static dictionaryApplication.dictionaryApplication.window;

public class alertBoxController implements Initializable {

    public void alertClose(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/dictionaryApplication/Graphic/FXML/addWord.fxml"));
            window.setScene(new Scene(root));
            window.show();
        } catch (Exception e) {
            catchingException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

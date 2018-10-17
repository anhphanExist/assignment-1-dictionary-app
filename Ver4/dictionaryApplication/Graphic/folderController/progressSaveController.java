package dictionaryApplication.Graphic.folderController;

import dictionaryApplication.Database.ConnectionDatabase;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static dictionaryApplication.dictionaryApplication.*;

public class progressSaveController implements Initializable{
    @FXML private ProgressBar pgBar;
    doWork task;

    public void cancelHandle(ActionEvent actionEvent) {
        try {
            if (!task.isComplete()) {
                task.cancel();
                pgBar.progressProperty().unbind();
                Parent root = FXMLLoader.load(getClass().getResource("/dictionaryApplication/Graphic/FXML/dictionaryApplication.fxml"));
                window.setScene(new Scene(root));
                window.centerOnScreen();
                window.show();
                showAlert("Saving process was canceled!");
            } else {
                Parent root = FXMLLoader.load(getClass().getResource("/dictionaryApplication/Graphic/FXML/dictionaryApplication.fxml"));
                window.setScene(new Scene(root));
                window.centerOnScreen();
                window.show();
                showAlert("Database saved successfully to saveDictionary.db!");
            }
        } catch (Exception e) {
            catchingException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            task = new doWork();
            pgBar.progressProperty().bind(task.progressProperty());
            new Thread(task).start();
        } catch (Exception e) {
            catchingException(e);
        }
    }
}

class doWork extends Task<Void> {
    private boolean complete = false;
    @Override
    protected Void call() throws Exception {
        ConnectionDatabase connectionDatabase = new ConnectionDatabase("jdbc:sqlite:src/dictionaryApplication/Database/saveDictionary.db");
        Connection connection = connectionDatabase.getConnection();
        PreparedStatement stm = connection.prepareStatement("DELETE FROM dict;VACUUM");
        stm.executeUpdate();
        String stmSTR = "INSERT INTO dict (idx, word, detail) VALUES (?,?,?)";
        int length = dict.getSize();
        for (int i = 0; i < length ; i++) {
            if (isCancelled()) {
                break;
            }
            stm = connection.prepareStatement(stmSTR);
            stm.setString(1, String.valueOf(i));

            stm.setString(2,dict.getDict().get(i).getTarget());
            stm.setString(3,dict.getDict().get(i).getExplain());

            stm.executeUpdate();
            updateProgress(i+1,length);
        }
        complete = true;
        return null;
    }

    public boolean isComplete() {
        return complete;
    }
}



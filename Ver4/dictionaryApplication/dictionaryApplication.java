package dictionaryApplication;

import dictionaryApplication.BasicDict.Dictionary;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static dictionaryApplication.BasicDict.DictionaryManagement.insertFromFile;

public class dictionaryApplication extends Application {
    
    public static Dictionary dict = insertFromFile("src/dictionaryApplication/BasicDict/data.txt");
    public static Stage window = null;
    
    /**
     * start the GUI Application
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            window = primaryStage;
            window.setResizable(false);
            Parent root = FXMLLoader.load(getClass().getResource("/dictionaryApplication/Graphic/dictionaryApplication.fxml"));
            window.setTitle("Simple Dictionary");
            window.setScene(new Scene(root));
            window.show();
        } catch (Exception e) {
            catchingException(e);
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
    
    //region Catching exception
    /**
     * print the catching error messeage
     */
    public static void catchingException(Exception e){
        System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
        for (Throwable t: e.getSuppressed()) {
            System.out.println("Surpress: " + t.getMessage());
        }
    }
    //endregion
}

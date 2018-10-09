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
    
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent firstScene = FXMLLoader.load(getClass().getResource("Graphic/dictionaryApplication.fxml"));
            primaryStage.setTitle("Simple Dictionary");
            primaryStage.setScene(new Scene(firstScene, 800, 600));
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName() + " " + e.getMessage());
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

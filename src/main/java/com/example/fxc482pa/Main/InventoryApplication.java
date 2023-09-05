package com.example.fxc482pa.Main;

/**
 * @author Jesus Villalobos
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

//Folder with javadocs is in the java folder in this application, it is labeled "JavaDocs C482".

/**This class creates the Inventory Management Application.*/
public class InventoryApplication extends Application {
    /**The start function for the Inventory Management Application */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(InventoryApplication.class.getResource("/com/example/fxc482pa/MainForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 400);
        stage.setTitle("Inventory");
        stage.setScene(scene);
        stage.show();
    }

    /**This is the main method for the Inventory Management Application.
     * RUNTIME ERROR: The exception that was given at runtime was the NumberFormatException, this occurred in my program
     * when an empty string was parsed as an integer, this happened multiple times throughout the code when it came down.
     * to adding or modifying a product or part. The solution for this ws wrapping the scope of the code in a try catch
     * statement in order to catch these exceptions, a warning is displayed letting the user know that there is invalid or
     * missing values that were input.
     * FUTURE ENHANCEMENT: I always think that less noise in code is great,throughout this code there is various
     * blocks of code that are redundant and seen throughout the code through the various classes. Extracting these
     * blocks of code into their own functions so these functions can be used through the code without all the noise.
     * Abstraction is another improvement, hiding all the unnecessary details and the implementation from the data
     * that was used.*/
    public static void main(String[] args) {
        launch();
    }
}
package com.mycompany.softwareengineeringproject;

import com.mycompany.softwareengineeringproject.Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import static javafx.application.Application.launch;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("Home"), 1500, 800);

        stage.setScene(scene);
        
        stage.setMaximized(true);
        stage.setTitle("Rule Engine Manager"); 
        
        stage.setOnCloseRequest(event -> {
            System.out.println("Atempting to save rules...");
            RuleEngine.getInstance().saveRules("RulesFile.txt");
        });
        stage.show();
    }

    
    // static method to change screen, used by controllers
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    
    // Overload method: accept a Parent node already loaded
    public static void setRoot(Parent node) {
        scene.setRoot(node);
    }

    // load FXML file thanks to FXMLLoader
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    

    public static void main(String[] args) {
        RuleEngine.getInstance().loadRules("RulesFile.txt");
        
        Thread ruleEngineThread = new Thread(() -> {
            RuleEngine rules = RuleEngine.getInstance();
            while(true){
                rules.CheckAllRules();
                try {
                    Thread.sleep(1000); // one second between a cicle and another
                } catch (InterruptedException ex) {
                    System.err.println("Rule Engine Loop Interrupted.");
                }
            }
        }, "RuleEngine-Thread");
        
        // Set the thread as a daemon. This mean that the program will close automatically
        // also if this thread is already in execution, when all non-daemon thread (include JavaFX UI) are finished
        ruleEngineThread.setDaemon(true);
        
        // Start the thread execution
        ruleEngineThread.start();
        
        
        launch();
    }

}
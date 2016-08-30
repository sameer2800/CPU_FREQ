/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testfxml;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author sameer
 */
public class NewFXMain extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Button btn = new Button();

        Parent root = FXMLLoader.load(getClass().getResource("FXMLmain.fxml"));

       Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();

      /*  
        
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            
            
        try {
           
            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Stage stage = new Stage();
            stage.setTitle("My New Stage Title");
            stage.setScene(new Scene(root, 450, 450));
            stage.show();

            //hide this current window (if this is whant you want
          //  ((Node)(event.getSource())).getScene().getWindow().hide();

        } catch (IOException e) {
            e.printStackTrace();
        } 
                
            }
        });
        
       StackPane root = new StackPane();
        root.getChildren().add(btn);
       
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
   
         */
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

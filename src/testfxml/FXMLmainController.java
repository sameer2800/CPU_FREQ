/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testfxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sameer
 */
public class FXMLmainController implements Initializable {

    @FXML
    private TextField passw;
    @FXML
    private Button subbut;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        subbut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    System.out.println("SAM PLEASE");

                   FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));     

            Parent root = (Parent)fxmlLoader.load();          
            FXMLDocumentController controller = fxmlLoader.<FXMLDocumentController>getController();
            
            
           controller.setUser(passw.getText());
        Scene scene = new Scene(root); 
        Stage stage = new Stage();
        stage.setScene(scene);    

        stage.show();   
        
        Stage stage12 = (Stage) subbut.getScene().getWindow();
    // do what you have to do
    stage12.close();
        
        
                   /*       Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("My New Stage Title");
                    stage.setScene(new Scene(root, 901, 685));
                    stage.show();
                   */  } catch (IOException ex) {
                    Logger.getLogger(FXMLmainController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

    }

}

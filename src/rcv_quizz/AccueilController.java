/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcv_quizz;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jc
 */
public class AccueilController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button accueilQuizz,accueilRegle;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void handleButtonAccueilQuizz() throws IOException{
        System.out.println("boutton presse");
        Stage stage;
        Parent root;
        
        stage=(Stage) accueilQuizz.getScene().getWindow();
        // pour l'instant choix quizz pas implemente
        root = FXMLLoader.load(getClass().getResource("quizz.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/flatterfx/flatterfx.css");
          
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleButtonAccueilRegle() throws IOException{
        System.out.println("boutton presse");
        Stage stage;
        Parent root;
        
        stage=(Stage) accueilRegle.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("liste_rcv.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/flatterfx/flatterfx.css");
          
        stage.setScene(scene);
        stage.show();
    }    
}

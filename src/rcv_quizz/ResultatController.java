/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcv_quizz;

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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jc
 */
public class ResultatController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    Text textPerd;
    
    @FXML
    Text textGagne;
    
    @FXML
    Button buttonAccueilRetour;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buttonAccueilRetour.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            retourAccueil();
                        } catch (IOException ex) {
                            Logger.getLogger(ResultatController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
    }
    
    public void setVal(int gagne, int perd){
        textPerd.setText(String.format("Nombre de mauvaises reponses : %d", perd));
        textGagne.setText(String.format("Nombre de bonnes reponses : %d", gagne));

    }
    
    public void retourAccueil() throws IOException{
        Stage stage;
        Parent root;
        
        stage=(Stage) textPerd.getScene().getWindow();
        // pour l'instant choix quizz pas implemente
        root = FXMLLoader.load(getClass().getResource("accueil.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/flatterfx/flatterfx.css");
          
        stage.setScene(scene);
        stage.show();
        
    }
    
}

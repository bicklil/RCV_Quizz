/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcv_quizz;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import org.json.simple.parser.ParseException;
import reglePackage.Regle;

/**
 * FXML Controller class
 *
 * @author jc
 */
public class Liste_rcvController implements Initializable {

    
    @FXML
    private VBox listeRegleVbox;
    
    @FXML
    private TextFlow explication ;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            initListeRegle();
        } catch (IOException | ParseException ex) {
            Logger.getLogger(Liste_rcvController.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }

    public void initListeRegle() throws IOException, FileNotFoundException, ParseException{
        String [] listefichiers; 
        String numero;
        String Titre;
        Button lien;
        File repertoire = new File("res/regle");
        listefichiers=repertoire.list(); 
        for (String listefichier : listefichiers) {
            numero = listefichier.substring(0, listefichier.length() - 5);
            Titre = Regle.getTitre(numero);
            lien = new Button(String.format("Regle n°%s : %s\n",numero,Titre));
            lien.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event){
                    affichageRegle(event);
                }
            });
            listeRegleVbox.getChildren().add(lien);
        }
       
    }
    
    public void affichageRegle(ActionEvent event){
        //parse le texte du bouton
        Button but = (Button) event.getSource();
        String texte = but.getText().substring(8,but.getText().length());
        System.out.println(texte);
                //il faut split
    }
    
}

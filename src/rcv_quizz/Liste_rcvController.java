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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
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
            lien.setMaxWidth(Double.MAX_VALUE);
            lien.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event){
                    try {
                        affichageRegle(event);
                    } catch (IOException | ParseException ex) {
                        Logger.getLogger(Liste_rcvController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            listeRegleVbox.getChildren().add(lien);
        }
       
    }
    
    public void affichageRegle(ActionEvent event) throws IOException, FileNotFoundException, ParseException{
        Regle reg;
        Button but = (Button) event.getSource();
        String texte = but.getText().substring(8,but.getText().length());
        String[] res = texte.split(" :");
        reg = new Regle(res[0]);
        explication.getChildren().removeAll(explication.getChildren()); 
        Text text1 = new Text(reg.getContenu());
        text1.setFill(Color.BLACK);
        text1.setFont(Font.font("Helvetica", FontPosture.REGULAR, 20));
        explication.getChildren().add(text1);
        
    }
    
}

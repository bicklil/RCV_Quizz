/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcv_quizz;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class DescriptionRegleController implements Initializable {
    int num;
    /**
     * Initializes the controller class.
     */
    
    @FXML
    TextFlow laRegle;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*try {
            affichageRegle();
        } catch (IOException | ParseException ex) {
            Logger.getLogger(DescriptionRegleController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

    public void setNum(int num){
        this.num = num;
        try {
            affichageRegle();
        } catch (IOException | ParseException ex) {
            Logger.getLogger(DescriptionRegleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        public void affichageRegle() throws IOException, FileNotFoundException, ParseException{
        Regle reg;
        reg = new Regle(String.valueOf(num));
        laRegle.getChildren().removeAll(laRegle.getChildren());
        Text text = new Text("Titre:"+reg.getTitre()+"\n");
        text.setFill(Color.RED);
        text.setFont(Font.font("Helvetica", FontPosture.REGULAR, 30));
        Text text1 = new Text(reg.getContenu());
        text1.setFill(Color.BLACK);
        text1.setFont(Font.font("Helvetica", FontPosture.REGULAR, 20));
        laRegle.getChildren().add(text);
        laRegle.getChildren().add(text1);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcv_quizz;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.json.simple.parser.ParseException;
import reglePackage.Situation;

/**
 * FXML Controller class
 *
 * @author jc
 */
public class QuizzController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextFlow textFlow;
    @FXML
    private Pane animCadre;
    @FXML
    private FlowPane buttonStock;
    
    private List<Button> myButton;
    private Situation affiche;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            nouvelleSituation(1);
        } catch (IOException | ParseException ex) {
            Logger.getLogger(QuizzController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    private void nouvelleSituation(int i) throws IOException, FileNotFoundException, ParseException{
            affiche = new Situation(i);
            System.out.println(affiche.getNumero());
            createButton();
            initTextFlow();
    }
    
    private void createButton(){
        myButton = new ArrayList<>();
        buttonStock.getChildren().removeAll(buttonStock.getChildren());
        for (int i=0;i<affiche.getNombreReponse();i++){
            myButton.add(new Button(affiche.getTexteReponse().get(i)));
            buttonStock.getChildren().add(myButton.get(i));
            if(i == affiche.getReponseJuste()){
                myButton.get(i).setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                       actionVictoire();
                    }
                });
            } else {
                myButton.get(i).setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                       actionDefaite();
                    }
                });
            }
            
        }
        
    }

    private void initTextFlow() {
        textFlow.getChildren().removeAll(textFlow.getChildren()); 
        Text text1 = new Text("Regardez la situation et choissisez une reponse");
        text1.setFill(Color.RED);
        text1.setFont(Font.font("Helvetica", FontPosture.ITALIC, 30));
        textFlow.getChildren().add(text1);
    }

    private void actionDefaite() {
        Text text1 = new Text("Mauvaise Reponse\n");
        text1.setFill(Color.RED);
        text1.setFont(Font.font("Helvetica", FontPosture.ITALIC, 20));
        Text text2 = new Text(affiche.getExplication());
        text2.setFill(Color.GREY);
        text2.setFont(Font.font("Helvetica", FontPosture.ITALIC, 15));
        textFlow.getChildren().removeAll(textFlow.getChildren()); 
        textFlow.getChildren().add(text1);
        textFlow.getChildren().add(text2);
        questionSuivante();
    }

    private void actionVictoire() {
        Text text1 = new Text("Bonne Reponse\n");
        text1.setFill(Color.GREEN);
        text1.setFont(Font.font("Helvetica", FontPosture.ITALIC, 20));
        Text text2 = new Text(affiche.getExplication());
        text2.setFill(Color.GREY);
        text2.setFont(Font.font("Helvetica", FontPosture.ITALIC, 15));
        textFlow.getChildren().removeAll(textFlow.getChildren()); 
        textFlow.getChildren().add(text1);
        textFlow.getChildren().add(text2);
        questionSuivante();
    }
    
    private void questionSuivante(){
        buttonStock.getChildren().removeAll(buttonStock.getChildren());
        Button next = new Button("Question suivante");
        next.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    nouvelleSituation(affiche.getNumero()+1);
                } catch (IOException | ParseException ex) {
                    Logger.getLogger(QuizzController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        buttonStock.getChildren().add(next);
    }
}

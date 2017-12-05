/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcv_quizz;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
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
    private MediaView animCadre;
    @FXML
    private VBox buttonStock;
    
    private List<Button> myButton;
    private Situation affiche;
    private int nombreSituation;
    private int nombreJuste;
    private int nombreFaux;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nombreJuste = 0;
        nombreFaux = 0;
        try {
            nbSituation();
            nouvelleSituation(1);
        } catch (IOException | ParseException ex) {
            Logger.getLogger(QuizzController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    private void nbSituation(){
        nombreSituation = 0;
        File repertoire = new File("res/situation");
        String[] listefichiers=repertoire.list(); 
        for (String listefichier : listefichiers) {
            if (listefichier.endsWith("json"))
            nombreSituation = nombreSituation+1;
        }
    }
    
    private void nouvelleSituation(int i) throws IOException, FileNotFoundException, ParseException{
            if (i <= nombreSituation)
            {
                affiche = new Situation(i);
                createButton();
                initTextFlow();
                afficheAnim();
            }
            else{
                //plus de situation  on affiche les result
                affichePasDeSitutuation();
            }
    }
    
    private void afficheAnim() throws MalformedURLException{
    File f = new File("res/situation", affiche.getMedia());
    Media media = new Media(f.toURI().toURL().toString());
    MediaPlayer play = new MediaPlayer(media);
    
    play.setAutoPlay(true);
    play.setCycleCount(MediaPlayer.INDEFINITE);
    animCadre.setMediaPlayer(play);
    
    
    }
    
    private void createButton(){
        myButton = new ArrayList<>();
        buttonStock.getChildren().removeAll(buttonStock.getChildren());
        for (int i=0;i<affiche.getNombreReponse();i++){
            myButton.add(new Button(affiche.getTexteReponse().get(i)));
            myButton.get(i).setMaxWidth(Double.MAX_VALUE);
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
        nombreFaux = nombreFaux +1;
        Text text1 = new Text("Mauvaise Reponse\n");
        text1.setFill(Color.RED);
        text1.setFont(Font.font("Helvetica", FontPosture.ITALIC, 20));
        Text text2 = new Text(affiche.getExplication());
        text2.setFill(Color.GREY);
        text2.setFont(Font.font("Helvetica", FontPosture.ITALIC, 15));
        textFlow.getChildren().removeAll(textFlow.getChildren()); 
        textFlow.getChildren().add(text1);
        textFlow.getChildren().add(text2);
        reference();
        questionSuivante();
    }

    private void actionVictoire() {
        nombreJuste = nombreJuste +1;
        Text text1 = new Text("Bonne Reponse\n");
        text1.setFill(Color.GREEN);
        text1.setFont(Font.font("Helvetica", FontPosture.ITALIC, 20));
        Text text2 = new Text(affiche.getExplication());
        text2.setFill(Color.GREY);
        text2.setFont(Font.font("Helvetica", FontPosture.ITALIC, 15));
        textFlow.getChildren().removeAll(textFlow.getChildren()); 
        textFlow.getChildren().add(text1);
        textFlow.getChildren().add(text2);
        reference();
        questionSuivante();
    }
    
    private void reference(){
        textFlow.getChildren().add(new Text("\nvoir les regles :"));
        Hyperlink hp ;
        for(String num:affiche.getReference()){
            hp = new Hyperlink();
            hp.setText(num);
            hp.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent e){
                    // faire une nouvelle fenetre avec la regle
                    Parent root;
                    
                    Stage stage;
                    Scene scene;
                    try{
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("descriptionRegle.fxml"));
                        root = (Parent) fxmlLoader.load();
                        DescriptionRegleController controller=fxmlLoader.<DescriptionRegleController>getController();
                        controller.setNum(Integer.parseInt(num));
                        stage = new Stage();
                        stage.setTitle("Description de la regle");
                        scene = new Scene(root, 450, 450);
                        scene.getStylesheets().add("/flatterfx/flatterfx.css");

                        stage.setScene(scene);
                        stage.show();

                    }
                    catch(IOException ex){
                        Logger.getLogger(Liste_rcvController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            textFlow.getChildren().add(hp);
        }
        
    }
    
    private void questionSuivante(){
        buttonStock.getChildren().removeAll(buttonStock.getChildren());
        Button next;
        if (affiche.getNumero()+1 <= nombreSituation){
            next = new Button("Question suivante");
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
        }
        else{
            next = new Button("Voir les resultat");
            next.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        afficheResultat();
                    } catch (IOException ex) {
                        Logger.getLogger(QuizzController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            
        }
        buttonStock.getChildren().add(next);
    }

    private void afficheResultat() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resultat.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        ResultatController controller=fxmlLoader.<ResultatController>getController();
        controller.setVal(nombreJuste,nombreFaux);
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/flatterfx/flatterfx.css");
        Stage stage=(Stage) buttonStock.getScene().getWindow();
 
        stage.setScene(scene);
        stage.show();
        
    }

    private void affichePasDeSitutuation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

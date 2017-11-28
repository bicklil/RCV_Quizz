/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reglePackage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author jc
 */
public class Regle {
    private String numero;
    private String titre;
    private String contenu;
    private String suivant;
    private String precedent;
    private String situation;
    
    public Regle(String num) throws FileNotFoundException, IOException, ParseException{
        JSONParser parser = new JSONParser();
        JSONObject a = (JSONObject) parser.parse(new FileReader("res/regle/"+num+".json"));
        numero = num;
        titre = (String) a.get("titre");
        contenu = (String) a.get("contenu");
        suivant = (String) a.get("suivant");
        precedent = (String) a.get("precedent");
        situation = (String) a.get("situation");
    }
    
    public String getNumero(){
        return numero;
    }
    
    public String getTitre(){
        return titre;
    }
    
    public String getContenu(){
        return contenu;
    }
    
    public String getSuivant(){
        return suivant;
    }
    
    public String getPrecedent(){
        return precedent;
    }
    
    public String getSituation(){
        return situation;
    }
}

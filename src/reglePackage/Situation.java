/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reglePackage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author jc
 */
public class Situation {
    int numero;
    String explication;
    long nombreReponse;
    long reponseJuste;
    String media;
    List<String> texteReponse;
    List<String> reference;
    
    public Situation(int num) throws FileNotFoundException, IOException, ParseException{
        numero = num;
        texteReponse = new ArrayList<>();
        reference = new ArrayList<>();
        JSONParser parser = new JSONParser();
        JSONObject a = (JSONObject) parser.parse(new FileReader("res/situation/"+num+".json")) ;
        explication = (String) a.get("explication");
        nombreReponse = (long) a.get("nombreReponse");
        reponseJuste =(long) a.get("reponseJuste");
        JSONArray msg = (JSONArray) a.get("texteReponse");
        Iterator<String> iterator = msg.iterator();
        while (iterator.hasNext()) {
            texteReponse.add(iterator.next());
        }
        media =(String) a.get("media");
        JSONArray ref = (JSONArray) a.get("references");
        
        Iterator<String> iterator2 = ref.iterator();
        while(iterator2.hasNext()){
            reference.add(iterator2.next());
        }
    }
    
    public int getNumero(){
        return numero;
    }
    
    public List<String> getTexteReponse(){
        return texteReponse;
    }
    
    public long getReponseJuste(){
        return reponseJuste;
    }
    
    public String getExplication(){
        return explication;
    }
    
    public long getNombreReponse(){
        return nombreReponse;
    }
    
    public List<String> getReference(){
        return reference;
    }
        
    public String getMedia(){
        return media;
    }
    
    
}

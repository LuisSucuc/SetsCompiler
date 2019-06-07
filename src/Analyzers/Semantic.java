
package Analyzers;

import Elementos.Conjunto;
import Elementos.Operaciones;
import Principal.Token;
import static Principal.Token.*;
import Tools.Tools;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;


public class Semantic extends Analyzer{
     
    Hashtable<String, Conjunto> conjuntos = new Hashtable<String, Conjunto>(); 
    Conjunto universo = new Conjunto();
    
    public Semantic(List<String> inputs, List<Token> tokens) {
        //System.out.println(tokens);
        this.inputs = inputs;
        this.tokens = tokens;
    }
    
    
    public void Analizar() throws FileNotFoundException, UnsupportedEncodingException{
        for (int posicion = 0; posicion < inputs.size(); posicion++) {
            Token token = tokens.get(posicion);
            String linea = inputs.get(posicion);
            
            switch (token) {
                case OPERACION:
                case DEFINICION:
                    responses.add("Aceptado");
                    break;
                case CONJUNTO_UNIVERSO:
                    Conjunto nuevo0 = new Conjunto();
                    nuevo0.setElements(linea);
                    conjuntos.put(nuevo0.getNombre(), nuevo0);
                    
                    universo.setElements(linea);
                    responses.add("Aceptado");
                    break;
                case CONJUNTO_VACIO:
                case CONJUNTO:
                    Conjunto nuevo = new Conjunto();
                    nuevo.setElements(linea);
                    nuevo.elementosValidos(universo);
                    if (nuevo.elementosNoUniverso.size() > 0) {
                        responses.add("Elementos no definidos en universo: " + nuevo.noUniverso());
                    }
                    else{
                        responses.add("Aceptado");
                    }
                    conjuntos.put(nuevo.getNombre(), nuevo);
                    break;
                 
                case OPERACION_CONJUNTO:
                    Operaciones operacion = new Operaciones(linea);
                    if(operacion.elementosValidos(conjuntos)){
                        responses.add(operacion.resultadoFinal);
                    }
                    else{
                        operacion.operar(conjuntos, universo);
                        responses.add(operacion.resultadoFinal);
                    }
                    break;
                default:
                    responses.add("Pendiente");
                    break;
            }
            
        }
        //System.out.println(this.responses);
        String textoFinal = "";
        for (int linea = 0; linea < inputs.size(); linea++) {
            textoFinal = textoFinal + inputs.get(linea) +  "                                             >>>>>>>>>          " + responses.get(linea) + "\n";
        }
        Tools.crearReporte(textoFinal);
    }
    
    
}

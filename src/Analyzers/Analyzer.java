
package Analyzers;

import java.util.ArrayList;
import java.util.List;
import Principal.Response;



public class Analyzer {
    public List<String> listaErrores;
    public List<String> inputs;
    public List<String> responses;
    public boolean success;

    public Analyzer() {
         listaErrores = new ArrayList<String>();
         inputs       = new ArrayList<String>();
         responses    = new ArrayList<String>();
         success      = true;
    }
    
    
  
     
     public boolean existeError(String stringError){
        //Se recorren todos los elementos en la lista
        for(String error: listaErrores) {
            //Se comprueba si conicide con el error enviado
            if(error.trim().contains(stringError))
               return true;
        }
        return false;
    }
     
     public Response getResult(){
         return new Response(inputs, responses, success);
     }


    
}

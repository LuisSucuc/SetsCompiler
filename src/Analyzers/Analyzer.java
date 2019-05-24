
package Analyzers;

import java.util.ArrayList;
import java.util.List;
import Principal.Response;
import Principal.Token;



public class Analyzer {
  
    public List<String> inputs;
    public List<String> responses;
    public List<Token> tokens;
    public boolean success;

    public Analyzer() {
         inputs       = new ArrayList<String>();
         responses    = new ArrayList<String>();
         success      = true;
         tokens     = new ArrayList<Token>();
    }
    
    
   
     
     public Response getResult(){
         return new Response(inputs, responses, success);
     }


    
}

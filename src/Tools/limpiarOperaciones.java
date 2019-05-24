
package Tools;

import Elementos.Operador;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class limpiarOperaciones {
    
      public static List<String> ordenarElementos(List<String> elementos, Operador operador){
       List<String>  respuesta = new ArrayList<String>();
        
        for (String elemento : elementos) {
            
            List<String> nuevosElementos = splitt(elemento, operador.operadorRegex);
            //System.out.println(elemento);
            if (nuevosElementos.size() >1) {
                
                 List<String> temp = new ArrayList<String>();
                 
                for (int pos = 0; pos < nuevosElementos.size() ; pos++) {
                    if (!"".equals(nuevosElementos.get(pos))) {
                       temp.add(nuevosElementos.get(pos));
                    }
                    
                    
                    if ( pos != nuevosElementos.size() -1 ) {
                        temp.add(operador.operador);
                    }
                    
                }
                
                nuevosElementos = temp;
            }
            
             if ("\\^c".equals(operador.operadorRegex)) {
                String substring = elemento.length() > 2 ? elemento.substring(elemento.length() - 2) : elemento;
                if ("^c".equals(substring)) {
                    nuevosElementos.add("^c");
                }
             }
            
           respuesta.addAll(nuevosElementos);
        }
        
        return respuesta;
    }
    
    
    /*public static void main(String[] args) {
      String c = "A*B/C&D^c$E/X";
      List<String> complemento =  new ArrayList<String>();
      complemento.add(c);
      
      complemento =  ordenarElementos(complemento, "/");
      //System.out.println(complemento);
      complemento =  ordenarElementos(complemento, "\\^c");
      complemento =  ordenarElementos(complemento, "\\*");
      complemento =  ordenarElementos(complemento, "\\$");
      complemento =  ordenarElementos(complemento, "\\&");
      System.out.println(c); 
      System.out.println(complemento);
    }*/
    
    
    
    private static List<String> splitt(String str, String splitStr){
        String[] array = str.split(splitStr);
        
        List<String> convertido = new ArrayList<String>();
        
        for (String string : array) {
            convertido.add(string);
        }
        //System.out.println(convertido);
        /*if ("\\^c".equals(splitStr)) {
            String substring = str.length() > 2 ? str.substring(str.length() - 2) : str;
            if ("^c".equals(substring)) {
                convertido.add("^c");
            }
        }*/
        
        return convertido;
    }
    
    
}

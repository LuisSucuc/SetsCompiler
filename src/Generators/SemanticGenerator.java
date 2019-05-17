package Generators;

import Tools.Vars;
import java.io.File;


public class SemanticGenerator {

  
    public static void main(String[] args) {
        String ubicacion = Vars.url + "Semantic.lexer";
        generarLexer(ubicacion);
    }
    
    /**
     * Método que genera y actualiza la clase Lexer basada en la
     * definicion del archivo Lexer.lex
     * @param ubicacion Ubicación del archivo Lexer.lex
     */
    public static void generarLexer(String ubicacion){
       File file = new File(ubicacion);
       jflex.Main.generate(file);   
   }
    
}

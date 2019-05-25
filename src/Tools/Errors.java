/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Generators.SintacticLexer;

/**
 *
 * @author luis
 */
public class Errors {
    
    public static String textError(String cadenaTokens, SintacticLexer lexer) {
        return cadenaTokens + "No reconocido '" + lineaColumna(lexer);
    }
    
    
    public static String esperabaConjunto(String cadenaTokens, SintacticLexer lexer) {
        return cadenaTokens + "Se esperaba definición de conjunto" + lineaColumna(lexer);
    }
    
    public static String esperabaOperacion(String cadenaTokens, SintacticLexer lexer) {
        return cadenaTokens + "Se esperaba operación" + lineaColumna(lexer);
    }
    
    
    public static String elementosInvalidos(String cadenaTokens, SintacticLexer lexer) {
        return cadenaTokens + "Elementos de conjunto inválidos" + lineaColumna(lexer);
    }
     
    public static String elementoFaltante(String cadenaTokens, SintacticLexer lexer) {
        return cadenaTokens + "Elementos faltante, conjunto no finalizado" + lineaColumna(lexer);
    }
    
    public static String elementoConjuntoFaltante(String cadenaTokens, SintacticLexer lexer) {
        return cadenaTokens + "Conjunto faltante, operación no finalizada" + lineaColumna(lexer);
    }
    
    public static String elementoSimboloFaltante(String cadenaTokens, SintacticLexer lexer) {
        return cadenaTokens + "Simbolo faltante" + lineaColumna(lexer);
    }

    
    
    public static String lineaColumna(SintacticLexer lexer){
        //return ". Error inicia en " + lexer.yytext() + "' en línea " + lexer.line_count + " columna " + lexer.column_count + ". ";
        return "";
    }
    
}

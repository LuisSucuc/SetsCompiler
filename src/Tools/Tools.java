/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Generators.LexicalLexer;
import Generators.SintacticLexer;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author luis
 */
public class Tools {
    public static String textError(String cadenaTokens, LexicalLexer lexer) {
        return cadenaTokens + "No reconocido '" + lexer.yytext() + "' en línea " + lexer.line_count + " columna " + lexer.column_count + ". ";
    }
    
    public static String sumarTexto(String linea, LexicalLexer lexer) {
        return linea + lexer.yytext();
    }
    
    public static String textError(String cadenaTokens, SintacticLexer lexer) {
        return cadenaTokens + "No reconocido '" + lexer.yytext() + "' en línea " + lexer.line_count + " columna " + lexer.column_count + ". ";
    }
    
    public static String sumarTexto(String linea, SintacticLexer lexer) {
        return linea + lexer.yytext();
    }
    
    public static void crearReporte(String texto) throws FileNotFoundException, UnsupportedEncodingException{
        System.out.println("GENERANDO ARCHIVO");
        try (PrintWriter archivoReporte = new PrintWriter("Salida2.txt", "UTF-8")) {
            archivoReporte.println(texto);
            archivoReporte.close();
        }
    }
    
    public static String textoAceptado(String textoArchivo, String lineaActual ){
        return textoArchivo + lineaActual + "       ---> Aceptado \n";
    }
    
    public static String textoConError(String textoArchivo, String lineaActual,String textoRespuesta  ){
        return textoArchivo + lineaActual + "       ---> "+  textoRespuesta + "\n";
    }
    
}


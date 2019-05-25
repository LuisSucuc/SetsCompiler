
package Analyzers;

import Generators.SintacticLexer;
import Principal.Response;
import Tools.Errors;
import Principal.Token;
import static Principal.Token.*;
import Tools.Tools;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;




public class Sintactic extends Analyzer{
   
    public void Analizar(String ubicacionArchivo) throws FileNotFoundException, IOException{
        //Se crea el objeto que manipulará el archivo selecionado

        //ubicacionArchivo = "/home/luis/Dropbox/UMG/Compiladores/Proyectos/SetAnalyzer/Entrada.txt";
        //Se crea la instancia del analizador léxico (JFlex) y se le envía el archivo a analizar
        SintacticLexer lexer = new SintacticLexer(new BufferedReader(new FileReader(ubicacionArchivo)));

        //String que guardará el texto original
        String lineaActual = "";
        //String que guardará los tokens reconocidos
        String textoRespuesta   = "";
        //String que concatenará el texto original y los tokens para guardarlo en el reporte y mostrarlo en el textarea
        String textoArchivo  = "";
        Token previous = null;
        //Variable que definirá si se encontraron errores
        boolean errors = false;
        boolean operacion = false;

        //Se crea un ciclo "infinito"
        //boolean line_operation = false;
        while (true){


            //Objeteo de la clase token, que retornará el token que encontró para su posterior evaluación
            Token token = lexer.yylex();

            //Si se legó el final del archivo
            if (token == null){
                //System.out.println(responses);
                //if (!success) {
                if (true) {
                    Tools.crearReporte(textoArchivo);
                }
                return;
            }
            if (token != NUEVA_LINEA ){
                previous = token;
            }

            //Se evalúa el token encontrado
            switch (token){

                //Si se encuentra una nueva línea
                case NUEVA_LINEA:
                    //Si las cadenas no están vacías (Esto se da cuando solo se encuentran errores)
                        inputs.add(lineaActual);
                        tokens.add(previous);
                        
                        //Se guarda la cadenaOriginal leida y la cadena de tokens separada por una flecha
                        if("".equals(lineaActual) ){
                            textoArchivo = textoArchivo + "\n";
                            responses.add("Aceptado");
                            continue;
                        }
                        
                        else if(errors){
                            textoArchivo = Tools.textoConError(textoArchivo, lineaActual, textoRespuesta);
                            responses.add(textoRespuesta);
                            errors = false;

                        }
                        else{        
                            textoArchivo = Tools.textoAceptado(textoArchivo, lineaActual);
                            responses.add("Aceptado");
                         }
                        //Se limpia la cadenaOriginal y cadenaTokens
                        textoRespuesta = lineaActual = "";
                        
                    break;

                case ERROR:
                    lineaActual = Tools.sumarTexto(lineaActual, lexer);
                    
                    if(!errors) {
                        
                        if (operacion) {
                            textoRespuesta = Errors.esperabaOperacion(textoRespuesta, lexer);
                        }
                        else{
                            textoRespuesta = Errors.esperabaConjunto(textoRespuesta, lexer);
                        }   
                    }
                    errors = true;                   
                    success = false;
                    break;

                case CONJUNTO_ELEMENTOS_ERROR:
                    lineaActual = Tools.sumarTexto(lineaActual, lexer);
                     if(!errors) {
                         textoRespuesta = Errors.elementosInvalidos(textoRespuesta, lexer);
                    }
                    errors = true;                   
                    success = false;
                    break;
                    
                 case CONJUNTO_FALTA_ELEMENTO_ERROR:
                    lineaActual = Tools.sumarTexto(lineaActual, lexer);
                     if(!errors) {
                         textoRespuesta = Errors.elementoFaltante(textoRespuesta, lexer);
                    }
                    errors = true;                   
                    success = false;
                    break;
                
                case OPERACION_CONJUNTO_FALTANTE:
                     
                    lineaActual = Tools.sumarTexto(lineaActual, lexer);
                     if(!errors) {
                         textoRespuesta = Errors.elementoConjuntoFaltante(textoRespuesta, lexer);
                    }
                    errors = true;                   
                    success = false;
                    break;
                case OPERACION_SIGNO_FALTANTE:
                    lineaActual = Tools.sumarTexto(lineaActual, lexer);
                     if(!errors) {
                         textoRespuesta = Errors.elementoConjuntoFaltante(textoRespuesta, lexer);
                    }
                    errors = true;                   
                    success = false;
                    break;
                    
                default:
                    if(token == OPERACION){
                        operacion = true;
                    }
                    //lineaActual = Tools.sumarTexto(token + " - " + lineaActual , lexer);
                    lineaActual = Tools.sumarTexto(lineaActual , lexer);
                    
            }
        }
    }
    
    
     public Response getResult(){
         return new Response(inputs, responses, tokens, success);
     }
     
     
}

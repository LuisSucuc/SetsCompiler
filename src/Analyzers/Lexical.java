
package Analyzers;

import Generators.LexicalLexer;
import Principal.Token;
import Tools.Tools;
import static Principal.Token.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;


public class Lexical extends Analyzer{
    public void Analizar(String ubicacionArchivo) throws FileNotFoundException, IOException{
        //Se crea el objeto que manipulará el archivo selecionado

        //ubicacionArchivo = "/home/luis/Dropbox/UMG/Compiladores/Proyectos/SetAnalyzer/Entrada.txt";
        Reader leerArchivo = new BufferedReader(new FileReader(ubicacionArchivo));
        //Se crea la instancia del analizador léxico (JFlex) y se le envía el archivo a analizar
        LexicalLexer lexer = new LexicalLexer(leerArchivo);

        //String que guardará el texto original
        String lineaActual = "";
        //String que guardará los tokens reconocidos
        String textoRespuesta   = "";
        //String que concatenará el texto original y los tokens para guardarlo en el reporte y mostrarlo en el textarea
        String textoArchivo  = "";
        //Variable que definirá si se encontraron errores
        boolean errors = false;

        //Se crea un ciclo "infinito"
        //boolean line_operation = false;
        while (true){


            //Objeteo de la clase token, que retornará el token que encontró para su posterior evaluación
            Token token = lexer.yylex();

            //Si se legó el final del archivo
            if (token == null){
                if (!success) {
                    
                    Tools.crearReporte(textoArchivo);
                }
                return;
            }

            //Se evalúa el token encontrado
            switch (token){

                //Si se encuentra una nueva línea
                case NUEVA_LINEA:
                    //Si las cadenas no están vacías (Esto se da cuando solo se encuentran errores)
                        inputs.add(lineaActual);
                        
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
                    //if(!errors) {
                        textoRespuesta = Tools.textError(textoRespuesta, lexer);
                    //}

                    errors = true;
                    success = false;
                    break;
                default:
                    lineaActual = Tools.sumarTexto(lineaActual, lexer);
            }
        }
    }
     
     
}

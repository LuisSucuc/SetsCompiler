
package Analyzers;

import Generators.LexicalLexer;
import Principal.Token;
import static Principal.Token.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import javax.swing.JOptionPane;


public class Lexical extends Analyzer{
   
      public void Analizar(String ubicacionArchivo) throws FileNotFoundException, IOException{

        // Se crea el objeto que generará el reporte
        PrintWriter archivoReporte = new PrintWriter("Salida.txt", "UTF-8");

        //Se crea el objeto que manipulará el archivo selecionado

        //ubicacionArchivo = "/home/luis/Dropbox/UMG/Compiladores/Proyectos/SetAnalyzer/Entrada.txt";
        Reader leerArchivo = new BufferedReader(new FileReader(ubicacionArchivo));
        //Se crea la instancia del analizador léxico (JFlex) y se le envía el archivo a analizar
        LexicalLexer lexer = new LexicalLexer(leerArchivo);

        //String que guardará el texto original
        String cadenaOriginal = "";
        //String que guardará los tokens reconocidos
        String cadenaTokens   = "";
        //String que concatenará el texto original y los tokens para guardarlo en el reporte y mostrarlo en el textarea
        String cadenaReporte  = "";
        //Variable que definirá si se encontraron errores
        Boolean errors = false;

        //Se crea un ciclo "infinito"
        boolean line_operation = false;
        while (true){


            //Objeteo de la clase token, que retornará el token que encontró para su posterior evaluación
            Token token = lexer.yylex();

            //System.out.println("TEXTO A ANALIZAR: " + lexer.yytext());
            //System.out.println("TOKEN RECIBIDO: " + token);

            //Si se legó el final del archivo
            if (token == null){
                //Se muestra el resultado en el label
                //--txtResultado.setText(cadenaReporte);
                //Se guarda en el archivo
                archivoReporte.println(cadenaReporte);
                //Se cierra el archivo
                archivoReporte.close();
                //Si exiten errores se muestra la lista de errores
                if (errors) {
                    JOptionPane.showMessageDialog(null, "Los siguientes elementos no se reconocieron" + listaErrores,
                                                    "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                //Se finaliza el procedimiento de análisis
                return;
            }

            //Se evalúa el token encontrado
            switch (token){

                //Si se encuentra una nueva línea
                case NUEVA_LINEA:
                    //Si las cadenas no están vacías (Esto se da cuando solo se encuentran errores)
                        inputs.add(cadenaOriginal);
                        
                        //Se guarda la cadenaOriginal leida y la cadena de tokens separada por una flecha
                        String finLinea = "\n";
                        if(!line_operation && "".equals(cadenaOriginal) ){
                            cadenaReporte = cadenaReporte + finLinea;
                            responses.add("Aceptado");
                            continue;
                        }

                        line_operation = false;
                        if(!errors){
                            cadenaReporte = cadenaReporte + cadenaOriginal + "       --->  Línea "+ lexer.line_count + " correcta. \n"; //+ cadenaTokens + finLinea;
                            responses.add("Aceptado");
                        }
                        else{
                            cadenaReporte = cadenaReporte + cadenaOriginal + "       ---> "+  cadenaTokens + finLinea;
                            responses.add(cadenaTokens);
                            errors = false;
                        }
                        
                        //Se limpia la cadenaOriginal y cadenaTokens
                        cadenaTokens = cadenaOriginal = "";

                    break;

                case ERROR:
                    //Si aún no existe el error
                    if (!existeError(lexer.yytext())) {
                        //Se inserta en la lista el nuevo elemento
                        listaErrores.add(lexer.yytext());
                    }
                    //Se suma a la cadena original el texto-palabra que se está evaluando
                    cadenaOriginal = cadenaOriginal + " "+ lexer.yytext();
                    //Se suma a la cadena de tokens el token obtenido (ERROR)
                    if(!errors) {
                        cadenaTokens = cadenaTokens + "No reconocido '" + lexer.yytext() + "' en línea " + lexer.line_count + " columna " + lexer.column_count + ". ";
                    }
                    

                    //Indica que existen errores para posteriormente mostrar al ventana
                    errors = true;
                    break;

                /*case SPACES:
                    //Si exiten espacios se añade al texto original
                    cadenaOriginal = cadenaOriginal + " ";
                    break;
                */

                //Para todos los lexemas reconocidos
                default:
                    //Se suma a la cadena original el texto-palabra que se está evaluando
                    cadenaOriginal = cadenaOriginal + lexer.yytext();
                    if (token == CONJUNTO_UNIVERSO || token == DEFINICION || token == CONJUNTO || token == OPERACION_CONJUNTO || token == OPERACION) {
                        //cadenaTokens = cadenaTokens + " Línea "+ lexer.line_count + " correcta";  //token;
                        line_operation = true;
                    }
                    else{
                        //cadenaTokens = cadenaTokens + " Línea "+ lexer.line_count + " correcta";  //token;
                        line_operation = true;
                    }
            }
        }
    }
     
     
}

package Elementos;

import static Tools.limpiarOperaciones.ordenarElementos;

import static Elementos.Operadores.*;
import static Elementos.Operacion.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Operaciones {
    public List<String> elementos = new ArrayList<String>();
    public List<String> elementosNoUniverso =  new ArrayList<String>();
    public boolean errores = false;
    public String resultadoFinal = "";
    
    
    
    public Operaciones(String linea){
        createElements(linea);
    }
    
    private String limpiarElementos(String operacion){
        return operacion.replace(" ", "").replace("(", "").replace(")", "");
    }
    
    private void createElements(String linea){
        elementos.add(limpiarElementos(linea));
        elementos = ordenarElementos( elementos, complemento );
        elementos = ordenarElementos(elementos, interseccion);
        elementos = ordenarElementos(elementos, diferencia);
        elementos = ordenarElementos(elementos, complemento);
        elementos = ordenarElementos(elementos, productoCruz);
        elementos = ordenarElementos(elementos, union);
        //System.out.println("LINEA");
        //System.out.println(elementos);
    }
    
    
    public boolean elementosValidos(Hashtable<String, Conjunto> conjuntos){
        
        for (String elemento : elementos) {
            
            if (esOperacion(elemento)) {
                continue;
            }
            
            if(conjuntos.containsKey(elemento)){
               Conjunto conjunto =  conjuntos.get(elemento);
                if (conjunto.elementosNoUniverso.size() > 0) {
                    resultadoFinal = resultadoFinal + "El conjunto "  + elemento + " contiene elementos inválidos";
                    errores = true;
                    break;
                }
            }
            else{
                resultadoFinal = resultadoFinal + "Conjunto no definido:  "  + elemento;
                errores = true;
                break;
            }
        }
        return errores;
    }
    
    private boolean esOperacion(String elemento){
        return complemento.operador.equals(elemento) || diferencia.operador.equals(elemento) || union.operador.equals(elemento) || productoCruz.operador.equals(elemento) || interseccion.operador.equals(elemento);
        
    }
    
    public void operar(Hashtable<String, Conjunto> conjuntos, Conjunto universo){
        Set<String> resultadoComplemento = new HashSet<String>();
        int posicion = -1;
        
        for (String elemento : elementos) {
            posicion++;
            if (!elemento.equals(complemento.operador)) { continue; }
            Conjunto conjunto1 =  conjuntos.get(elementos.get(posicion-1));
            
            resultadoComplemento =  complemento(conjunto1.elementos, universo.elementos);
            
            String key = getKey(posicion);
            conjuntos.put(key, new Conjunto(key, resultadoComplemento));
            
            if (elementos.size()>1) {
                elementos.set(posicion-1,key);
            }
            else{
                elementos.add(key);
                resultadoFinal = resultadoFinal + resultadoComplemento;
                return;
            }
            
            System.out.println(elementos);
        }
        removeAll(elementos, complemento.operador);
        System.out.println(elementos);
        
        if (elementos.size()==1) {
            generarRespuesta(conjuntos);
            return;
        }

        
    }
    
    
    private String getKey(int posicion){
        return  "_" + elementos.get(posicion-1) + elementos.get(posicion);
    }
    
    @SuppressWarnings("empty-statement")
    private void removeAll(List<String> list, String element) {
        while (list.remove(element));
    }
    
    private void generarRespuesta(Hashtable<String, Conjunto> conjuntos){
        Set<String> respuesta  = new HashSet<String>();
        respuesta =  conjuntos.get(elementos.get(0)).elementos;
        String respuestaStr = "{ ";
        for (String elemento : respuesta) {
            respuestaStr = respuestaStr + elemento + ", ";
        }
        resultadoFinal = respuestaStr + " }. Aceptado";
    }
    
}

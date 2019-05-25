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
        
        if (operacionComplemento(conjuntos, universo)) {
            return;
        }
        else if (operaciones(conjuntos, union.operador)) {
            return;
        }
        
        else if (operaciones(conjuntos, interseccion.operador)) {
            return;
        }
         else if (operaciones(conjuntos, diferencia.operador)) {
            return;
        }
        
         else if (operaciones(conjuntos, productoCruz.operador)) {
            return;
        }
        
        
        

            
    }
    
    private boolean operacionComplemento(Hashtable<String, Conjunto> conjuntos, Conjunto universo){
        Set<String> resultadoOperacion = new HashSet<String>();
        int posicion = -1;
        
        for (String elemento : elementos) {
            posicion++;
            if (!elemento.equals(complemento.operador)) { continue; }
            Conjunto conjunto1 =  conjuntos.get(elementos.get(posicion-1));
            
            resultadoOperacion =  complemento(conjunto1.elementos, universo.elementos);
            
            String key = getKeyComplemento(posicion);
            conjuntos.put(key, new Conjunto(key, resultadoOperacion));
            
            if (elementos.size()>1) {
                elementos.set(posicion-1,key);
            }
            else{
                elementos.add(key);
                resultadoFinal = resultadoFinal + resultadoOperacion;
                return true;
            }
            
            //System.out.println(elementos);
        }
        removeAll(elementos, complemento.operador);
        //System.out.println(elementos);
        
        if (elementos.size()==1) {
            generarRespuesta(conjuntos);
            
        }
        return elementos.size()==1;
    }
    
    
    
    private boolean operaciones(Hashtable<String, Conjunto> conjuntos, String operador){
        Set<String> resultadoOperacion = new HashSet<String>();
        int posicion = -1;
        System.out.println(elementos);
        
        for (String elemento : elementos) {
            posicion++;
            if (!elemento.equals(operador)) { continue; }
            Conjunto conjunto1 =  conjuntos.get(elementos.get(posicion-1));
            Conjunto conjunto2 =  conjuntos.get(elementos.get(posicion+1));
            System.out.println("C1" +conjunto1.elementos);
            System.out.println("C2" + conjunto2.elementos);
            if (operador.equals(union.operador)) {
                resultadoOperacion =  union(conjunto1.elementos, conjunto2.elementos);
            }
            else if(operador.equals(interseccion.operador)){
                resultadoOperacion =  interseccion(conjunto1.elementos, conjunto2.elementos);
            }
            else if(operador.equals(diferencia.operador)){
                resultadoOperacion =  diferencia(conjunto1.elementos, conjunto2.elementos);
            }
            
            else if(operador.equals(productoCruz.operador)){
                resultadoOperacion =  productoCruz(conjunto1.elementos, conjunto2.elementos);
            }
            
            System.out.println("Resultado" + resultadoOperacion);
            
            String key = getKey(posicion);
            conjuntos.put(key, new Conjunto(key, resultadoOperacion));
            
            if (elementos.size()>1) {
                elementos.set(posicion+1,key);
                elementos.set(posicion-1,"---");
            }
            else{
                elementos.add(key);
                resultadoFinal = resultadoFinal + resultadoOperacion;
            }
            
            System.out.println(elementos);
        }
        removeAll(elementos, operador);
        removeAll(elementos, "---");
        System.out.println(elementos);
        
        if (elementos.size()==1) {
            generarRespuesta(conjuntos);
            
        }
        return elementos.size()==1;
    }
    
    
    private String getKeyComplemento(int posicion){
        return  "_" + elementos.get(posicion-1) + elementos.get(posicion);
    }
    
    private String getKey(int posicion){
        return  "_" + elementos.get(posicion-1) + elementos.get(posicion) +  elementos.get(posicion+1);
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

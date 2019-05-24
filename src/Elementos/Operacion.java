package Elementos;

import static Tools.limpiarOperaciones.ordenarElementos;

import static Elementos.Operadores.*;
import java.util.ArrayList;
import java.util.List;


public class Operacion {
    public List<String> elementos = new ArrayList<String>();;
    public List<String> elementosNoUniverso =  new ArrayList<String>();
    
    public Operacion(String linea){
        elementos.add(limpiarElementos(linea));
        elementos = ordenarElementos( elementos, complemento );
        elementos = ordenarElementos(elementos, interseccion);
        elementos = ordenarElementos(elementos, diferencia);
        elementos = ordenarElementos(elementos, complemento);
        elementos = ordenarElementos(elementos, productoCruz);
        elementos = ordenarElementos(elementos, union);
        //System.out.println("LINEA");
        System.out.println(elementos);
    }
    
    
    
    
    
    private String limpiarElementos(String operacion){
        return operacion.replace(" ", "").replace("(", "").replace(")", "");
    }
    
}


package Elementos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Conjunto {
    
    String nombre;
    public Set<String> elementos  = new HashSet<String>();
    public List<String> elementosNoUniverso;

    public Conjunto(){
        elementosNoUniverso = new ArrayList<String>();
    }

    Conjunto(String nombre, Set<String> elementos) {
        this.nombre = nombre;
        this.elementos = elementos;
    }
    
    public void setElements(String conjuntoStr){
        String[] partesConjunto = limpiarElementos(conjuntoStr).split("=");
        this.nombre = partesConjunto[0];
        
        if (partesConjunto.length>1) {
            String[] elementosSeparados = partesConjunto[1].split(",");

            for (String elemento : elementosSeparados) {
                //System.out.println("ELEMENTO: " + elemento);
                elementos.add(elemento);
            }
        }
    }
    
    private String limpiarElementos(String conjunto){
        return conjunto.replace(" ", "").replace("{", "").replace("}", "").replace("\"", "");
    }

    public String getNombre() {
        return nombre;
    }
    
    
    public void elementosValidos(Conjunto universo){
        //System.out.println("\n \n \n"+ this.nombre);
        boolean encontrado;
        for (String elemento : this.elementos) {
            encontrado = false;
            for (String elementoUniverso : universo.elementos) {
                //System.out.println(elemento + " <----> " + elementoUniverso);
                if (elemento.equals(elementoUniverso)) {
                    encontrado = true;
                    break;
                }
            }
            if (! encontrado) {
                elementosNoUniverso.add(elemento);
            }
            
        }
        
    }
    
    public String noUniverso(){
        //System.out.println("\n \n \n"+ this.nombre);
        String noUniverso = "";
        for (String elemento : elementosNoUniverso) {
            noUniverso = noUniverso + elemento + ", ";
            //System.out.println("No encontrado " + elemento);
        }
        return noUniverso;
    }
    
    
    
    
    
}

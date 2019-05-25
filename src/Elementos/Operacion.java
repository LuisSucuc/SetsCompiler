

package Elementos;

import com.google.common.collect.Sets;
import java.util.HashSet;
import java.util.Set;


public class Operacion {
    
    
      public static  Set<String> union(Set<String> C1, Set<String> C2){
        return Sets.union(C1, C2);
    }
       
        
    
    public static Set<String> interseccion(Set<String> C1, Set<String> C2){
        return Sets.intersection(C1, C2);
    }
    
    public static Set<String> diferencia(Set<String> C1, Set<String> C2){
        return Sets.difference(C1, C2);
    }
    
    public static Set<String> complemento(Set<String> C1, Set<String> C2){
        return Sets.difference(C2, C1);
    }
    
    public static Set<String> productoCruz(Set<String> C1, Set<String> C2){
        Set<String>  set = new HashSet<String>();
        for (String string1 : C1) {
            for (String string2 : C2) {
                set.add("(" + string1 + ", " + string2 +")" );
            }
        }
        return set;
    }
    
  
  
    
    
}

package v2;

import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author marti
 */

public class FunctionStorage {
    HashMap<String, LinkedList<String>> Function = new HashMap<String, LinkedList<String>>();

/**
* Este metodo retorna la funcion
* @return Function
*/    
    public HashMap<String, LinkedList<String>> getFunction() {
		return Function;
	}
/**
* Este metodo crea la funcion
* @param function
* @return None
*/ 
	public void setFunction(HashMap<String, LinkedList<String>> function) {
		Function = function;
	}
/**
* Este metodo almacena la funcion
* @param Key
* @param Instructions
* @return None
*/ 
	public void storeNewFunction(String Key, LinkedList<String> Instructions) {

        if (Function.containsKey(Key)) {
            Function.replace(Key, Function.get(Key), Instructions);
        }

        else {
            Function.put(Key, Instructions);
        }
    }
/**
* Este metodo devuelve las instrucciones de la funcion
* @param Key
* @return Function Instructions LinkedList
*/ 
    public LinkedList<String> getData(String Key){
        return Function.get(Key);
    }
}

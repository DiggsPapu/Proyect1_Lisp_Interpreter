package v2;


import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author marti
 */

public class FunctionStorage {
    HashMap<String, LinkedList<String>> Function = new HashMap<String, LinkedList<String>>();

    
    public HashMap<String, LinkedList<String>> getFunction() {
		return Function;
	}
	public void setFunction(HashMap<String, LinkedList<String>> function) {
		Function = function;
	}
	public void storeNewFunction(String Key, LinkedList<String> Instructions) {

        if (Function.containsKey(Key)) {
            Function.replace(Key, Function.get(Key), Instructions);
        }

        else {
            Function.put(Key, Instructions);
        }
    }
    public LinkedList<String> getData(String Key){
        return Function.get(Key);
    }
}
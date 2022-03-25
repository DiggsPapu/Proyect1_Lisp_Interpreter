package function;

import java.util.HashMap;

/**
 *
 * @author marti
 */

public class FunctionStorage {
    HashMap<String, String[]> Function = new HashMap<String, String[]>();

    public void storageNewFunction(String Key, String[] Instructions) {

        if (Function.containsKey(Key)) {
                Function.replace(Key, Function.get(Key), Instructions);
        }

        else {
                Function.put(Key, Instructions);
        }
    }
    public String[] getData(String Key){
        return Function.get(Key);
    }
}

package function;

import java.util.HashMap;

public class FunctionStorage {
    HashMap Function;

    public void storageNewFunction(String functionName, String[] arrayInstructions) {

        if (Function.containsKey(functionName)) {
                Function.replace(functionName, Function.get(functionName), arrayInstructions);
        }

        else {
                Function.put(functionName, arrayInstructions);
        }
    }
}

package function;

import java.util.Stack;
import java.util.HashMap;
public class FunctionStorage {
	HashMap functionS;
	
	public void storageNewFunction(String functionName, String[] arrayInstructions) {
		/**
		 * To replace a function
		 */
		if (functionS.containsKey(functionName)) {
			functionS.replace(functionName, functionS.get(functionName), arrayInstructions);
		}
		/**
		 * To create a function
		 */
		else {
			functionS.put(functionName, arrayInstructions);
		}
<<<<<<< HEAD
		
	}

=======
	}
>>>>>>> fc447724fbb935177f26eaea38b5525a5873c3e5
}

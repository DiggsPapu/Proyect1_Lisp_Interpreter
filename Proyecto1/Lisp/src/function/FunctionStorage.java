package function;
import java.util.HashMap;
import java.util.Stack;
public class FunctionStorage {
	private HashMap<String, Stack<String>> functionStorage;
	
	
	public HashMap<String, Stack<String>> getFunctionStorage() {
		return functionStorage;
	}



	public void setFunctionStorage(HashMap<String, Stack<String>> functionStorage) {
		this.functionStorage = functionStorage;
	}
	
	
	public FunctionStorage() {
		functionStorage =  new HashMap<String, Stack<String> >();
	}



	private void StorageNewFunction(String nameFunction, Stack<String> Instructions) {
		getFunctionStorage().put(nameFunction, Instructions);
	}
	
	private Stack<String> searchFunction(String nameFunction){
		if (getFunctionStorage().containsKey(nameFunction)) {
			return getFunctionStorage().get(nameFunction);
		}else {
			return null;
		}
	}
}

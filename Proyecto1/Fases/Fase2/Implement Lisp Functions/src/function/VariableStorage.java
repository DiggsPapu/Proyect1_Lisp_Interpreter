package function;

import java.util.TreeMap;
public class VariableStorage {
	private TreeMap<String, String> variableStorage;



	public TreeMap<String, String> getVariableStorage() {
		return variableStorage;
	}

	public VariableStorage(){
		variableStorage = new TreeMap<>();
	}

	public void CreateVariable(String name, String value) {
		variableStorage.put(name, value);
	}
}
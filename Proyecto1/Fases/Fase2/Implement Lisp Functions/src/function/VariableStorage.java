package function;

import java.util.TreeMap;
public class VariableStorage {
	private TreeMap<String, String> variableStorage;



	public TreeMap<String, String> getVariableStorage() {
		return variableStorage;
	}



	public void setVariableStorage(TreeMap<String, String> variableStorage) {
		this.variableStorage = variableStorage;
	}

	public VariableStorage(){
		variableStorage = new TreeMap<>();
	}

	public void CreateVariable(String name, String value) {
		variableStorage.put(name, value);
	}
}
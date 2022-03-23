package function;
<<<<<<< HEAD
=======

>>>>>>> fc447724fbb935177f26eaea38b5525a5873c3e5
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

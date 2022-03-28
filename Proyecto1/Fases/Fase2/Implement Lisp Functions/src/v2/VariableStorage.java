package v2;


import java.util.TreeMap;
/**
* Este metodo constructor
*/
public class VariableStorage {
	private TreeMap<String, String> variableStorage;
/**
* Este metodo devuelve la variable almacenada.
* @return Treemap con las variables guardadas
*/
	public TreeMap<String, String> getVariableStorage() {
		return variableStorage;
	}

/**
* Este metodo crea el Treemap para guardar las variables
* @return None
*/	

	public VariableStorage(){
		variableStorage = new TreeMap<>();
	}
/**
* Con este metodo se almacenan las variables
* @param value
* @param name
* @return None
*/
	public void CreateVariable(String name, String value) {
		variableStorage.put(name, value);
	}
}

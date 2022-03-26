package function;

import java.util.HashMap;
import java.util.Scanner;
public class VariableStorage {
	//The HashMap is private, has string 4 the keys and arrayList<String> 2 storage values
	private HashMap<String, String> dataStructure;
	
	
	
	public HashMap<String, String> getDataStructure() {
		return dataStructure;
	}



	public void setDataStructure(HashMap<String, String> dataStructure) {
		this.dataStructure = dataStructure;
	}


	public VariableStorage() {
		dataStructure = new HashMap<String, String>();
	}
	public String searchVariable(String variableName) {
		if (getDataStructure().containsKey(variableName)) {
			System.out.print(getDataStructure().get(variableName));
			return getDataStructure().get(variableName);
		}
		else {
			System.out.print("No se encontro la variable, sera creada\nIngrese el valor de la variable: ");
			Scanner scanner = new Scanner(System.in);
			createVariable(variableName, scanner.nextLine());
			scanner.close();
			return "Ya fue creada la variable";
		}
	}
	
	private void createVariable(String variableName, String value) {
		
		getDataStructure().put(variableName, value);
	}
	
//	public static void main(String[] args) {
//		VariableStorage variableStorage = new VariableStorage();
//		variableStorage.searchVariable("483920");
//		variableStorage.searchVariable("483920");
//
//	}
}

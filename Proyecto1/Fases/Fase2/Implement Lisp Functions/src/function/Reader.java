package function;
import java.util.regex.Pattern;
import java.util.Stack;
import java.util.HashMap;
import java.util.regex.Matcher;

public class Reader {
	FunctionStorage functionStorage;
	FunctionStorage variableStorage;
	
	
	public void caseReader(String scan) {
		switch (getCase(scan)) {
		
		case "function":{
			/**
			 * @param scanName 
			 * @param Instructions
			 * El nombre de la funcion y el set de instrucciones en array
			 */
			System.out.print("Entro en el function case\n");
//			System.out.print(splitingExpresion(scan)[4]);
//			functionStorage.storageNewFunction(splitingExpresion(scan)[4], splitingExpresion(scan));
//			
		}
		
		case "variable":{
			
		}

		case "exp":{
			
		}
		
		
	}
	}
	/**
	 * 
	 * @param regularExpresion
	 * @param expresion
	 * @return boolean
	 */
	private boolean evaluateExpresion(String regularExpresion, String expresion) {
		Pattern exp = Pattern.compile(regularExpresion, Pattern.CASE_INSENSITIVE);
		Matcher match = exp.matcher(expresion);
		System.out.print("Ingresa a evaluar la expresion\n");
		return match.find();
	}
	
	private String getCase(String scann) {
		if (evaluateExpresion("^[(][ ]*defun[ ]+[[A-Za-z][0-9]*]+[)]$", scann)) {
			System.out.print("Reconoce el case\n");
			return "function";
		}
			
		else if (evaluateExpresion("^[a-z]+@uvg.edu.gt$",scann)) //This is a professor
			return "variable";
		else if (evaluateExpresion("^[a-z]+_adm@uvg.edu.gt$",scann)) //This is an employee
			return "exp";
		else {
			return "none";
		}

	}
	
	
	private String[] splitingExpresion(String theScan) {
		String[] functionArray = theScan.split("^[(]*[ ]defun*[ ]+[[a-zA-Z]*[a-zA-Z0-9_]]+[ ][(][a-z][)][)]$");
		return functionArray;
	}
	
}

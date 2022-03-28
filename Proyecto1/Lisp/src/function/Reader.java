<<<<<<< HEAD
package function;
import function.FunctionStorage;
import function.VariableStorage;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//This is the lexer
public class Reader {
	private VariableStorage variableStorage;
	private FunctionStorage functionStorage;
	
	protected static final String LETTER = "[a-zA-Z]";
	protected static final String LITERAL = "[a-zA-Z0-9]+";
	protected static final String VALID_FUNCTION_NAME = "[a-zA-Z][a-zA-Z0-9]*";
	protected static final String WHIESPACE = "[\\s]+";
	protected static final String NUMERIC_ATOM = "[\\d\\+\\-]?[\\d]*";
	protected static final String SYMBOL = "[().]";
	
	public static enum type{
		setf, ATOM, LIST, SETQ, QUOTE, DEFUN, COND,
	}
	
	public void caseReader(String scan) {
		switch (getCase(scan)) {
		
		case "function":{
			/**
			 * @param scanName 
			 * @param Instructions
			 * El nombre de la funcion y el set de instrucciones en array
			 */
			System.out.print("Entro en el function case\n");
			System.out.print(splitingExpresion(scan).length);
			
			
		}
		
		case "variable":{
			variableStorage.searchVariable(scan);
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
		if (evaluateExpresion("^[(][ ]*defun[ ]+[a-zA-Z]+$", scann)) {
			System.out.print("Reconoce el case\n");
			return "function";
		}
		
		else {
			return "none";
		}
	}
	
	
	private String[] splitingExpresion(String theScan) {
		String[] functionArray = theScan.split("[(][ ]defun*");
		for (int k=0;k<functionArray.length;k++) {
			System.out.print(functionArray[k]+"\n");
		}
		return functionArray;
	}
	

	public static void main(String[] args) {
		
		
		
		
	}
	
	
}
=======
package function;
import function.FunctionStorage;
import function.VariableStorage;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//This is the lexer
public class Reader {
	private VariableStorage variableStorage;
	private FunctionStorage functionStorage;
	
	protected static final String LETTER = "[a-zA-Z]";
	protected static final String LITERAL = "[a-zA-Z0-9]+";
	protected static final String VALID_FUNCTION_NAME = "[a-zA-Z][a-zA-Z0-9]*";
	protected static final String WHIESPACE = "[\\s]+";
	protected static final String NUMERIC_ATOM = "[\\d\\+\\-]?[\\d]*";
	protected static final String SYMBOL = "[().]";
	
	public static enum type{
		setf, ATOM, LIST, SETQ, QUOTE, DEFUN, COND,
	}
	
	public void caseReader(String scan) {
		switch (getCase(scan)) {
		
		case "function":{
			/**
			 * @param scanName 
			 * @param Instructions
			 * El nombre de la funcion y el set de instrucciones en array
			 */
			System.out.print("Entro en el function case\n");
			System.out.print(splitingExpresion(scan).length);
			
			
		}
		
		case "variable":{
			variableStorage.searchVariable(scan);
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
		if (evaluateExpresion("^[(][ ]*defun[ ]+[a-zA-Z]+$", scann)) {
			System.out.print("Reconoce el case\n");
			return "function";
		}
		
		else {
			return "none";
		}
	}
	
	
	private String[] splitingExpresion(String theScan) {
		String[] functionArray = theScan.split("[(][ ]defun*");
		for (int k=0;k<functionArray.length;k++) {
			System.out.print(functionArray[k]+"\n");
		}
		return functionArray;
	}
	

	public static void main(String[] args) {
		
		
		
		
	}
	
	
}
>>>>>>> 6f24e712079cd755ef2d83c702f6cb1680e0df3c

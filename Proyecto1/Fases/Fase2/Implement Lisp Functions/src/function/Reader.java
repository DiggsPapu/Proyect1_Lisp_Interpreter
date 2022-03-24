package function;

import java.util.regex.Pattern;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;

public class Reader {
	private FunctionStorage functionStorage;
	private VariableStorage variableStorage;
	
	protected static final String LETTER = "[a-zA-Z]";
	protected static final String LITERAL = "[a-zA-Z0-9]+";
	protected static final String VALID_NAME = "[a-zA-Z][a-zA-Z0-9]*";
	protected static final String WHIESPACE = "[\\s]+";
	protected static final String NUMERIC_ATOM = "[\\d\\+\\-\\*\\/\\^]?[\\d]*";
	protected static final String SYMBOL = "[().]";
	protected static final String QUOTATION = "[\"]";
	protected static final String ATOM = "ATOM";
	protected static final String LIST = "LIST";
	protected static final String SETQ = "SETQ";
	protected static final String QUOTE = "QUOTE";
	protected static final String DEFUN = "DEFUN";
	protected static final String COND = "COND";
	protected static final String EQUAL = "EQUAL";
	
	
	public FunctionStorage getFunctionStorage() {
		return functionStorage;
	}

	public void setFunctionStorage(FunctionStorage functionStorage) {
		this.functionStorage = functionStorage;
	}

	public VariableStorage getVariableStorage() {
		return variableStorage;
	}

	public void setVariableStorage(VariableStorage variableStorage) {
		this.variableStorage = variableStorage;
	}
	
	public Reader() {
		this.functionStorage = new FunctionStorage();
		this.variableStorage = new VariableStorage();
	}
	
	/**
	 * It divides the text in valid tokens
	 * @param command
	 * @return
	 */
	
	public LinkedList <String> tokenize(String command){
		int i = 0;
		LinkedList <String> tokens = new LinkedList <String>();
		if ( command.length() == 1 ){
			System.out.print("Error, ingresado no valido");
			return null;
		}
		while (  i < command.length() ){
			int j = i + 1;
			if ( command.substring(i, j).matches(LETTER) || command.substring(i, j).matches(NUMERIC_ATOM)||command.substring(i, j).matches(QUOTATION) ){
				while ( command.substring(i,j + 1).matches(LITERAL) || command.substring(i, j + 1).matches(NUMERIC_ATOM) ){
					j++;
				}
				tokens.add(command.substring(i,j).trim());
			} else if ( command.substring(i, j).matches(SYMBOL) ){
				tokens.add(command.substring(i,j).trim());
			}
			i = j;
		}
		System.out.print("tOKEN SIZE:"+ String.valueOf(tokens.size())+"\n" );
		return tokens;
	}
	
	public Integer getCase(LinkedList<String> lista) {
		System.out.println(lista);
		if (QUOTE.equals(lista.get(1).trim())) {
			return 1;
		}else if (DEFUN.equals(lista.get(1))) {
			return 2;
		}else if (SETQ.equals(lista.get(1))) {
			return 3;
		}else if (ATOM.equals(lista.get(1))) {
			return 4;
			
		}else if (LIST.equals(lista.get(1))) {
			return 5;
			
		}else if (EQUAL.equals(lista.get(1))) {
			return 6;
		}else if (COND.equals(lista.get(1))) {
			return 7;
		}else {
			return (Integer) null;
		}
	}
	
	
	
	public void caseReader(String scan) {
		LinkedList<String> array = new LinkedList<String>();
		array = tokenize(scan);
		switch (getCase(array)) {
		case 1:{
			//Quote just prints the instructions
			for (int k = 2; k < array.size()-1 ; k++) {
				System.out.print(array.get(k)+" ");
			}
			break;
		}
		case 2: {
			
		}
		case 3:{
			caseSETQ(array);
		}
		case 4:{
			
		}
		default:{
			
		}
//		
//		case "variable":{
//			
//			
//		}
//
//		case "exp":{
//			
//		}
//		
//		
	}
	}
//	.substring(0,array.get(array.size()-1).length()-1)
	public void caseSETQ(LinkedList<String> array) {
//		System.out.print(array.get(2).substring(0, array.get(2).length()-1).matches(VALID_NAME) && array.get(array.size()-1).matches(SYMBOL) );
		if (array.get(2).substring(0, array.get(2).length()-1).matches(VALID_NAME) && array.get(array.size()-1).matches(SYMBOL) ) {
			// Para verificar que es valida 
			System.out.print("Primer if\n");
			System.out.print(array.get(3).matches(SYMBOL) && array.get(array.size()-2).matches(SYMBOL) && array.get(4).matches(QUOTATION) &&  array.get(array.size()-3).matches(QUOTATION));
			if ( array.get(3).matches(SYMBOL) && array.get(array.size()-2).matches(SYMBOL) && array.get(4).matches(QUOTATION) &&  array.get(array.size()-3).matches(QUOTATION) ) {
				System.out.print("SegundoIf\n");
				// Es para almacenar el valor tipo string
				String value = new String();
				//Para verificar que es valida la concatenacion
				boolean valido = true;
				//Para correr el array
				for (int k = 5 ; k < array.size()-3; k++ ) {
					//El if es para verificar si hay una quotation o si es valido
					//Si hay un " entonces significa que no es valido y pasa al else para no almacenar mas valores.
					if (!array.get(k).matches(QUOTATION) && valido) {
						System.out.print("3er if");
						//Se concatena el string
						value=value+" "+array.get(k);
						if (k == array.size()-4) {
							value.trim();
							System.out.print("Final if");
							
						}
						System.out.print("vALOR"+value);
					}else if(array.get(3).matches(SYMBOL) && array.get(array.size()-2).matches(SYMBOL) && NUMERIC_ATOM.equals(4)) {
						Arithmetic_Operations AMOP = new Arithmetic_Operations(array);
						System.out.println(AMOP.Result());
						
					}
					else {
						valido = false;
					}
					
				}
				if (valido) {
					//Es para almacenar el valor de la variable en el variable Storage
					getVariableStorage().CreateVariable(array.get(2), value);
				}else {
					System.out.print("No es valida la sintaxis 2");
				}
				
				
			}
			else {
				System.out.print("No es una sintaxis valida");
			}
		}else {
			System.out.print("No es valida la sintaxis.");
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
		Reader lector = new Reader();
		Scanner scanner = new Scanner(System.in);
		
//		System.out.println(lector.getCase(lector.tokenize(scanner.nextLine())));
		lector.caseReader(scanner.nextLine());
		
	}
	
}

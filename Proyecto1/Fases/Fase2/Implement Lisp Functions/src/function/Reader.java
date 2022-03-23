package function;
import java.util.regex.Pattern;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;

public class Reader {
	FunctionStorage functionStorage;
	FunctionStorage variableStorage;
	
	protected static final String LETTER = "[a-zA-Z]";
	protected static final String LITERAL = "[a-zA-Z0-9]+";
	protected static final String VALID_FUNCTION_NAME = "[a-zA-Z][a-zA-Z0-9]*";
	protected static final String WHIESPACE = "[\\s]+";
	protected static final String NUMERIC_ATOM = "[\\d\\+\\-]?[\\d]*";
	protected static final String SYMBOL = "[().]";
	protected static final String ATOM = "ATOM";
	protected static final String LIST = "LIST";
	protected static final String SETQ = "SETQ";
	protected static final String QUOTE = "QUOTE";
	protected static final String DEFUN = "DEFUN";
	protected static final String COND = "COND";
	protected static final String EQUAL = "EQUAL";
	
	
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
			if ( command.substring(i, j).matches(LETTER) || command.substring(i, j).matches(NUMERIC_ATOM) ){
				while ( command.substring(i,j + 1).matches(LITERAL) || command.substring(i, j + 1).matches(NUMERIC_ATOM) ){
					j++;
				}
				tokens.add(command.substring(i,j).trim());
			} else if ( command.substring(i, j).matches(SYMBOL) ){
				tokens.add(command.substring(i,j).trim());
			}
			i = j;
		}
		System.out.print(tokens.size());
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
		} default:{
			
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
		
		System.out.println(lector.getCase(lector.tokenize(scanner.nextLine())));
		lector.caseReader(scanner.nextLine());
		
	}
	
}

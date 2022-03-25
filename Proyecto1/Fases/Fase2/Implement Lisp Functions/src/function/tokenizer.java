package function;

import java.util.LinkedList;
//import java.util.Scanner;

public class tokenizer {
	/**
	 * Es el metodo para tokenizar el comando, de manera que lo separa y tiene excepciones, en el caso de tener excepciones retorna null	
	 * @param command
	 * @return lista, devuelve una lista tokenizada
	 */
	private static LinkedList<String> tokenized(String command){
		int i =0;
		LinkedList<String> tokenList = new LinkedList<String>();
		if(command.length()==1) {
			return null;
		}
		
		if(command.charAt(0)=='(' && command.charAt(command.length()-1)==')' ) {
			
			while (i< command.length()) {
				//Para verificar que sea misma cantidad y que sea valido el parentesis
				
				// to move in the chars
				int j = i + 1;
				//In case de next substring matches de pattern it enters in the if to get the token
				if (command.substring(i,j).matches(Patterns.LETTER) || command.substring(i, j).matches(Patterns.NUMERIC_ATOM) || command.substring(i, j).matches(Patterns.QUOTATION) || command.substring(i, j).matches(Patterns.OPERATIONS) ) {
					//Mientras que el substring matches Literales o numericos entonces concatena todo lo que haya
					while ( command.substring(i , j+1).matches(Patterns.LITERAL) || command.substring(i, j+1).matches(Patterns.NUMERIC_ATOM)) {
						j++;
					}
					tokenList.add(command.substring(i, j).trim());
					
				}else if (command.substring(i , j).matches(Patterns.SYMBOL)) {
					
					tokenList.add(command.substring(i, j).trim());
				}
				i = j;
			}
			return tokenList;
		}else {
			System.out.print("EXCEPTION no puso par de parentesis encerrando su funcion\n");
			return null;
		}
	}
	/**
	 * Es para revisar que haya igualdad de parentesis en el comando del user.
	 * @param command
	 * @return lista, se retorna una lista con los tokens, sera tokenizada
	 */
	public static LinkedList<String> equalParenthesis(String command) {
		//Asignar la lista tokenizada a una lista linkeada
		LinkedList<String> tokenList = tokenizer.tokenized(command);
		//Si es diferente de nulo entonces realiza el proceso
		if (tokenList!= null) {
			int cop = 1;
			int ccp = 0;
			for (int k = 1 ; k < tokenList.size() ; k++ ) {
//				System.out.print("Ingreso al for\n");
				if ( tokenList.get(k).equals("(")) {
					cop++;
//					System.out.print("OP");
				}else if( tokenList.get(k).equals(")")) {
					ccp++;
//					System.out.print("CP");
				}
			}
			if (cop == ccp) {
//				System.out.print(tokenList+"\n");
				return tokenList;
			}else {
				System.out.print("EXCEPTION no hay igualdad de parentesis\n");
				return null;
			}
		}else {
			
			return null;
		}
	}

	
//	public static void main(String[] args) {
//		Scanner scanner = new Scanner(System.in);
//		
////		System.out.println(lector.getCase(lector.tokenize(scanner.nextLine())));
//		tokenizer.equalParenthesis(scanner.nextLine());
//		scanner.close();
//	}
}

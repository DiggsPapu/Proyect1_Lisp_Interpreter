package v2;

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
					while ( command.substring(i , j+1).matches(Patterns.LITERAL) || command.substring(i, j+1).matches(Patterns.NUMERIC_ATOM) ) {
						j++;
					}
					tokenList.add(command.substring(i, j).trim());
					
				}else if (command.substring(i , j).matches(Patterns.SYMBOL)) {
					
					tokenList.add(command.substring(i, j).trim());
				}
				i = j;
			}
//			System.out.print(tokenList);
//			System.out.print(tokenList.hashCode());
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
	
	private static LinkedList<String> quotation(LinkedList<String> lista) {
		//Si la lista es diferente de nulo y contiene comillas
		LinkedList<String> corrected = new LinkedList<String>();
		//Si no es nula y contiene quotation
		if (lista!= null && lista.contains("\"")) {
			//Moverse a traves de la lista
			for (int k = 0 ; k < lista.size() ; k++) {
				//Si se encuentra un token que sea de quotation
				if (lista.get(k).equals("\"")) {
					k++;
					
					String quotation = new String();
					while (!lista.get(k).equals("\"") && k<lista.size()-1) {
						quotation = quotation+ " " + lista.get(k);
						k++;
					}
					if (k>= lista.size()-1) {
						//En el caso de que no haya encontrado otro parentesis es invalido
						System.out.print("No hay igualdad de comillas\n");
						return null;
					}
					corrected.add(quotation);
				}else {
					corrected.add(lista.get(k));
				}
				
			}
			
		}else if (lista != null){
			return lista;
		
		}else {
			
			return null;
		}
//		System.out.print("Corregido: "+corrected+"\n");
		return corrected;
	}

	public static LinkedList<String> equalParenthesis(String command) {
		//Asignar la lista tokenizada a una lista linkeada
		LinkedList<String> tokenList = tokenizer.tokenized(command);
		//Si es diferente de nulo entonces realiza el proceso
		tokenList = tokenizer.quotation(tokenList);
//		System.out.print(tokenList+"\n");
		
		//En el caso de que el que la lista sea diferente de nula
		if (tokenList!= null) {
			
			//Se crean contadores para parentesis
			int cop = 1;
			int ccp = 0;
			//Se recorre la lista para contar los parentesis
			for (int k = 1 ; k < tokenList.size() ; k++ ) {
//				System.out.print("Ingreso al for\n");
				
				//En el caso de que sea parentesis abierto
				if ( tokenList.get(k).equals("(")) {
					cop++;
//					System.out.print("OP");
					
					
				//En el caso de que sea parentesis cerrado
				}else if( tokenList.get(k).equals(")")) {
					ccp++;
//					System.out.print("CP");
				}
			}
			//Si son iguales entonces devuelve la lista, sino devuelve null
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
//		System.out.print(tokenizer.equalParenthesis(scanner.nextLine()));
//		scanner.close();
//	}
}
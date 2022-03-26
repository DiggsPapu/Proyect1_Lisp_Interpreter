package function;

import java.util.LinkedList;
import java.util.Scanner;

public class Predicates {

public static LinkedList<String> evaluateList(LinkedList<String> lista, VariableStorage variableStorage) {
		
		if (lista!= null) {
			if ( lista.get(1).equals(Patterns.LIST) || lista.get(1).equals("list") ) {
				LinkedList<String> listaEvaluar = new LinkedList<String>(lista);
				LinkedList<String> listaFinal = new LinkedList<String>();
				//Se eliminan el par de parentesis del final y el setq
				listaEvaluar.removeLast();
				listaEvaluar.removeFirst();
				listaEvaluar.removeFirst();
//				System.out.print(listaEvaluar+"\n");
				
				int size = listaEvaluar.size();
				int counter = 0 ;
				while (counter < size) {
					//Si es un parentesis en posicion 1 entonces es una operacion
//					System.out.print(listaEvaluar+"\n");
					if (listaEvaluar.getFirst().equals("(")) {
						continue;
					//Si se encuentra un patron valido de nombre en la primera posicion	
					}else if(listaEvaluar.getFirst().matches(Patterns.VALID_NAME)) {
						
						//Unico caso o esta el almacenada la variable para el valor o no lo esta
						if (variableStorage.getVariableStorage().containsKey(listaEvaluar.getFirst())) {
							//Se crea y se almacena en el map la nueva variable con el valor de la variable anterior
							listaFinal.add(variableStorage.getVariableStorage().get(listaEvaluar.getFirst()));
							//Se remueve el primero de la fila
							listaEvaluar.removeFirst();
						}else {
							System.out.print("No fue posible almacenar la variable dado que no existe la variable para el valor\n");
							return null;
						}
						
					}else {
						System.out.print(listaEvaluar.getFirst());
						listaFinal.add(listaEvaluar.getFirst());
						listaEvaluar.remove();
					}
					counter++;
				}
				return listaFinal;
				
			}else {
				System.out.print("No es lista\n");
				return lista;
			}
		
		}else {
			System.out.print("La lista no es valida\n");
			return null;
		}
	}



public static LinkedList<String> evaluateAtom(LinkedList<String> lista, VariableStorage variableStorage) {
	if ( lista!= null ) {
		if ( lista.get(1).equals(Patterns.ATOM) || lista.get(1).equals("atom")) {
			LinkedList<String> listaEvaluar = new LinkedList<String>(lista);
			LinkedList<String> listaFinal = new LinkedList<String>();
			//Se eliminan el par de parentesis del final y el setq
			listaEvaluar.removeLast();
			listaEvaluar.removeFirst();
			listaEvaluar.removeFirst();
//			System.out.print(listaEvaluar+"\n");
//			System.out.print(listaEvaluar==null);
			if(listaEvaluar.getFirst()!=null) {
				//En el caso de que sea operacion
				if (listaEvaluar.getFirst().equals("(")) {
					return lista;
				//Si se encuentra un patron valido de nombre en la primera posicion	
				}else if(listaEvaluar.getFirst().matches(Patterns.VALID_NAME)) {
					
					//Unico caso o esta el almacenada la variable para el valor o no lo esta
					if (variableStorage.getVariableStorage().containsKey(listaEvaluar.getFirst())) {
						//Se crea y se almacena en el map la nueva variable con el valor de la variable anterior
						listaFinal.add(variableStorage.getVariableStorage().get(listaEvaluar.getFirst()));
						//Se remueve el primero de la fila
						listaEvaluar.removeFirst();
						//Si contiene o no solo un numero la instruccion
						if (listaEvaluar.size()== 0){
							return listaFinal;
						}else {
							System.out.print("No es un atom\n");
							return null;
						}
					}else {
						System.out.print("No fue posible almacenar la variable dado que no existe la variable para el valor\n");
						return null;
					}
					
				}else {
//					System.out.print(listaEvaluar.getFirst());
					listaFinal.add(listaEvaluar.getFirst());
					listaEvaluar.remove();
					//Si contiene o no solo un numero la instruccion
					if (listaEvaluar.size()== 0){
						return listaFinal;
					}else {
						System.out.print("No es un atom\n");
						return null;
					}
				}
			}else {
				System.out.print("El atom no tenia suficientes argumentos");
				return null;
			}
			
		//En el caso de que no sea atom	
		}else {
			return lista;
		}
	}else {
		System.out.print("La lista no es valida\n");
		return null;
	}
	
}
	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		VariableStorage vs = new VariableStorage();
		vs.CreateVariable("value", "8293");
		System.out.print("Lista: " + Predicates.evaluateList(tokenizer.equalParenthesis("(list 1 2 value )"), vs)+"\n");
		System.out.print("Atom: " + Predicates.evaluateAtom(tokenizer.equalParenthesis("(atom 1)"), vs) +"\n");
		System.out.print("Atom: " + Predicates.evaluateAtom(tokenizer.equalParenthesis("(atom value)"), vs) + "\n");
		System.out.print("Atom: " + Predicates.evaluateAtom(tokenizer.equalParenthesis("(atom 12 3)"), vs) + "\n");
		System.out.print("Ingrese un atom que sea valido con comillas: ");
		System.out.print("Atom: " + Predicates.evaluateAtom(tokenizer.equalParenthesis(scann.nextLine()), vs) + "\n");
		System.out.print("Ingrese un atom que no sea valido con comillas: ");
		System.out.print("Atom: " + Predicates.evaluateAtom(tokenizer.equalParenthesis(scann.nextLine()), vs) + "\n");
		
		
		System.out.print("Lista2: "+ Predicates.evaluateList(tokenizer.equalParenthesis(scann.nextLine()), vs) + "\n");
		
		
		scann.close();
	}
}

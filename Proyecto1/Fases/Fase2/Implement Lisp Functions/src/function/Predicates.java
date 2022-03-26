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
				if (!listaEvaluar.isEmpty()) {
					int size = listaEvaluar.size();
					int counter = 0 ;
					
					while (counter < size) {
						//Si es un parentesis en posicion 1 entonces es una operacion
//						System.out.print(counter);
//						System.out.print(listaFinal+"\n");
						if (listaEvaluar.getFirst().equals("(")) {
							//Esta es la lista donde se almacenara la operacion
							LinkedList<String> operacion = new LinkedList<String>();
							//Se add a la operacion el parentesis abierto y se remueve de la lista Evaluar
							operacion.add(listaEvaluar.getFirst());
							listaEvaluar.removeFirst();
							//Se crean contadores para los parentesis abiertos y cerrados
							int op = 1 ;
							int cp = 0 ;
							//Se mueve al siguiente valor en la size de la lista original
							counter++;
							while (counter < size && op!= cp) {
								//Si se encuentra un parentesis abierto se add a la operacion, se quita de la lista evaluada y se suma al contador abierto
								if (listaEvaluar.getFirst().equals("(")) {
									operacion.add(listaEvaluar.getFirst());
									listaEvaluar.removeFirst();
									op++;
								}
								//Si se encuentra un parentesis cerrado se add a la operacion, se quita de la lista evaluada y se suma al contador abierto
								else if (listaEvaluar.getFirst().equals(")")) {
									operacion.add(listaEvaluar.getFirst());
									listaEvaluar.removeFirst();
									cp++;
								}else if (listaEvaluar.getFirst().matches(Patterns.VALID_NAME)) {
									if (variableStorage.getVariableStorage().containsKey(listaEvaluar.getFirst())) {
										operacion.add(listaEvaluar.getFirst());
										listaEvaluar.removeFirst();
									}else {
										System.out.print("El parametro pasado es invalido para realizar la operacion");
										return null;
									}
									
								}else {
									operacion.add(listaEvaluar.getFirst());
									listaEvaluar.removeFirst();
								}
								counter++;
								
							}
							if (op==cp) {
								Calculator calc = new Calculator(operacion, variableStorage);
								if (operacion.get(1).equals("+") || operacion.get(1).equals("-") || operacion.get(1).equals("*") || operacion.get(1).equals("/") || operacion.get(1).equals("^")) {
									listaFinal.add(Float.toString(calc.Result()));
								}else if (operacion.get(1).equals("EQUALS") || operacion.get(1).equals(">") || operacion.get(1).equals("<") || operacion.get(1).equals("=") || operacion.get(1).equals("equal")) {
									listaFinal.add(Boolean.toString(calc.ResultComp()));
								}else {
									System.out.print("No es una operacion valida\n");
									return null;
								}
							}
							
						//Si se encuentra un patron valido de nombre en la primera posicion	
						}else if(listaEvaluar.getFirst().matches(Patterns.VALID_NAME)) {
							
							//Unico caso o esta el almacenada la variable para el valor o no lo esta
							if (variableStorage.getVariableStorage().containsKey(listaEvaluar.getFirst())) {
								//Se crea y se almacena en el map la nueva variable con el valor de la variable anterior
								listaFinal.add(variableStorage.getVariableStorage().get(listaEvaluar.getFirst()));
								//Se remueve el primero de la fila
								listaEvaluar.removeFirst();
								counter++;
							}else {
								System.out.print("No fue posible almacenar la variable dado que no existe la variable para el valor\n");
								return null;
							}
							
						}else {
//							System.out.print(listaEvaluar.getFirst());
							listaFinal.add(listaEvaluar.getFirst());
							listaEvaluar.remove();
							counter++;
						}
						
					}
					return listaFinal;
				}else {
					System.out.print("La lista no tenia suficientes argumentos\n");
					return null;
				}
				
				
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
			if(!listaEvaluar.isEmpty()) {
				//En el caso de que sea operacion
				if (listaEvaluar.getFirst().equals("(")) {
					if (listaEvaluar.getFirst().equals("(") && listaEvaluar.getLast().equals(")")) {
//						System.out.print("Ingreso a operacion\n");
						
						Calculator calc = new Calculator(listaEvaluar, variableStorage);
						if (listaEvaluar.get(1).equals(">") || listaEvaluar.get(1).equals("<") || listaEvaluar.get(1).equals("=") || listaEvaluar.get(1).equals("EQUAL") || listaEvaluar.get(1).equals("equal") ) {
							
							listaFinal.add(Boolean.toString(calc.ResultComp()));
						}else if (listaEvaluar.get(1).equals("+") || listaEvaluar.get(1).equals("-") || listaEvaluar.get(1).equals("*") || listaEvaluar.get(1).equals("/") ) {
							listaFinal.add(Float.toString(calc.Result()));
						}else {
							System.out.print("La operacion no tenia operadores validos");
							return null;
						}
						return listaFinal;
					}else {
						
						System.out.print("Es un atom con una operacion mal hecha\n");
						return null;
					}
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
		System.out.print("El atom no es valido\n");
		return null;
	}
	
}
//	public static void main(String[] args) {
//		Scanner scann = new Scanner(System.in);
//		VariableStorage vs = new VariableStorage();
//		vs.CreateVariable("value", "8293");
//		System.out.print("Lista: " + Predicates.evaluateList(tokenizer.equalParenthesis("(list 1 2 value )"), vs)+"\n");
//		System.out.print("Lista: " + Predicates.evaluateList(tokenizer.equalParenthesis("(list  )"), vs)+"\n");
//		System.out.print("Atom: " + Predicates.evaluateAtom(tokenizer.equalParenthesis("(atom 1)"), vs) +"\n");
//		System.out.print("Atom: " + Predicates.evaluateAtom(tokenizer.equalParenthesis("(atom value)"), vs) + "\n");
//		System.out.print("Atom: " + Predicates.evaluateAtom(tokenizer.equalParenthesis("(atom )"), vs) + "\n");
//		System.out.print("Atom: " + Predicates.evaluateAtom(tokenizer.equalParenthesis("(atom (+ 1 2))"), vs) + "\n");
//		System.out.print("Atom: " + Predicates.evaluateAtom(tokenizer.equalParenthesis("(atom (= 1 value))"), vs) + "\n");
//		System.out.print("Lista: " + Predicates.evaluateList(tokenizer.equalParenthesis("(list (+ 1 2) 3 4 value )"), vs)+"\n");
//		System.out.print("Lista: " + Predicates.evaluateList(tokenizer.equalParenthesis("(list  (+ 100 value) 0 1 )"), vs)+"\n");
//		System.out.print("Lista: " + Predicates.evaluateList(tokenizer.equalParenthesis("(list  (< 100 value) 0 1 )"), vs)+"\n");
//		System.out.print("Lista: " + Predicates.evaluateList(tokenizer.equalParenthesis("(list  (> 100 value) 0 1 )"), vs)+"\n");
//		System.out.print("Lista: " + Predicates.evaluateList(tokenizer.equalParenthesis("(list  (= 100 100) 0 1 )"), vs)+"\n");
//		
//		System.out.print("Ingrese un atom que sea valido con comillas: "); // por ejemplo (atom "fjdkls")
//		System.out.print("Atom: " + Predicates.evaluateAtom(tokenizer.equalParenthesis(scann.nextLine()), vs) + "\n");
//		System.out.print("Ingrese un atom que no sea valido con comillas: "); // por ejemplo (atom "fjdkls)
//		System.out.print("Atom: " + Predicates.evaluateAtom(tokenizer.equalParenthesis(scann.nextLine()), vs) + "\n");
//		System.out.print("Ingrese un lista valida con comillas: "); //por ejemplo (list (^ 23 (+ 2 3 )) "hola"  )
//		System.out.print("Lista2: "+ Predicates.evaluateList(tokenizer.equalParenthesis(scann.nextLine()), vs) + "\n");
//		
//		
//		scann.close();
//	}
}

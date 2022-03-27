package function;

import java.util.LinkedList;
import java.util.Scanner;

public class Predicates {

public static LinkedList<String> evaluateList(LinkedList<String> lista0, VariableStorage variableStorage) {
		
		if (lista0!= null) {
			if ( lista0.get(1).equals(Patterns.LIST) || lista0.get(1).equals("list") ) {
				LinkedList<String> listaEvaluar = new LinkedList<String>(lista0);
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
										System.out.print("El parametro pasado es invalido para realizar la operacion\n");
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
					System.out.print(listaFinal+"\n");
					return listaFinal;
				}else {
					System.out.print("La lista no tenia suficientes argumentos\n");
					return null;
				}
				
				
			}else {
				System.out.print("No es lista\n");
				return null;
			}
		
		}else {
			System.out.print("La lista no es valida\n");
			return null;
		}
	}



public static LinkedList<String> evaluateAtom(LinkedList<String> lista1, VariableStorage variableStorage) {
	if ( lista1!= null ) {
		if ( lista1.get(1).equals(Patterns.ATOM) || lista1.get(1).equals("atom")) {
			LinkedList<String> listaEvaluar = new LinkedList<String>(lista1);
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
							System.out.print("La operacion no tenia operadores validos\n");
							return null;
						}
						System.out.print(listaFinal);
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
						System.out.print(listaFinal+"\n");
						return listaFinal;
					}else {
						System.out.print("No es un atom\n");
						return null;
					}
				}
			}else {
				System.out.print("El atom no tenia suficientes argumentos\n");
				return null;
			}
			
		//En el caso de que no sea atom	
		}else {
			System.out.print("No es atom\n");
			return null;
		}
	}else {
		System.out.print("El atom no es valido\n");
		return null;
	}
	
	
}

public static LinkedList<String> caseQuote(LinkedList<String> lista2){
	if (lista2!=null) {
		if (lista2.get(1).equals(Patterns.QUOTE) || lista2.get(1).equals("quote")) {
			lista2.removeFirst();
			lista2.removeFirst();
			lista2.removeLast();
			String quote = new String();
			for (int k = 0; k < lista2.size() ; k++ ) {
				if (lista2.get(k).startsWith("\"")) {
					quote = quote + lista2.get(k);
					
				}else {
					quote = quote + " " + lista2.get(k);
					
				}
			}

			System.out.print(quote+"\n");
			return lista2;
		}else {
			System.out.print("No es quote\n");
			return lista2;
		}
		
	}else {
		System.out.print("La lista era nula entonces no se pudo imprimir \n");
		return null;
	}
	
}

public static Boolean caseEqual(LinkedList<String> lista3, VariableStorage variableStorage) {
	if (lista3!= null) {
		if (lista3.get(1).equals("EQUAL") || lista3.get(1).equals("equal") || lista3.get(1).equals("=") || lista3.get(1).matches(Patterns.LOGICAL)) {
			//Se remueven los parentesis que encierran la expresion y se remueve el equal o un igual
			LinkedList<String> evaluateList = new LinkedList<String>(lista3);
			evaluateList.removeFirst();
			evaluateList.removeFirst();
			evaluateList.removeLast();
			// Si es un equal deben de quedar solo dos tokens a menos que sea operaciones
			if (evaluateList.size()==2) {
				//Si los dos tienen nombres validos 
				if (evaluateList.getFirst().matches(Patterns.VALID_NAME) && evaluateList.getLast().matches(Patterns.VALID_NAME)) {
					//Se hace un try and catch en el caso de que alguno sea nulo entonces dara error y no se mostrara.
					try{
						//Si son iguales se retorna true
						if (variableStorage.getVariableStorage().get(evaluateList.getFirst()).equals(variableStorage.getVariableStorage().get(evaluateList.getLast()))  ) {
//							System.out.print("true");
							return true;
						}
						//Si no son iguales se retorna false
						else {
//							System.out.print("false");
							return false;
						}
					}
					catch(Exception e) {
						System.out.print("Al menos uno de los valores ingresados no existe en el almacenamiento\n");
						return null;
					}
					
				} 
				//Si alguno es un patron de nombre valido
				else if (evaluateList.getFirst().matches(Patterns.VALID_NAME) || evaluateList.getLast().matches(Patterns.VALID_NAME)) {
					
					if (variableStorage.getVariableStorage().containsKey(evaluateList.getFirst())) {
//						System.out.print(variableStorage.getVariableStorage().get(evaluateList.getFirst()).equals(evaluateList.getLast()) );
						return variableStorage.getVariableStorage().get(evaluateList.getFirst()).equals(evaluateList.getLast()) ;
					}
					else if (variableStorage.getVariableStorage().containsKey(evaluateList.getLast())) {
//						System.out.print(variableStorage.getVariableStorage().get(evaluateList.getLast()).equals(evaluateList.getFirst()) );
						return variableStorage.getVariableStorage().get(evaluateList.getLast()).equals(evaluateList.getFirst()) ;
					}
					else {
						return null;
					}
					
				}else if (evaluateList.getFirst().startsWith(" ") && !evaluateList.getLast().startsWith(" ")){
					return null;
				}
				else {
//					System.out.print(evaluateList.getFirst().equals(evaluateList.getLast()));
					return evaluateList.getFirst().equals(evaluateList.getLast());
				}
			}else if((evaluateList.getFirst().equals("(") && evaluateList.getLast().equals(")") ) && evaluateList.get(1).matches(Patterns.OPERATIONS)) {
				Calculator calc = new Calculator (lista3, variableStorage);
				return calc.ResultComp();
			}
			else {
				System.out.print("Es un equal con mas de 1 argumento, no es valido\n");
				return null;
			}
			
		}else {
			System.out.print("No es un equal\n");
			return null;
		}
	}
	else {
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
		
//		System.out.print(Predicates.caseEqual(tokenizer.equalParenthesis("(EQUAL 1 2)"), vs));
//		System.out.print("\n");
//		System.out.print(Predicates.caseEqual(tokenizer.equalParenthesis("(EQUAL 1 1)"), vs));
//		System.out.print("\n");
//		System.out.print(Predicates.caseEqual(tokenizer.equalParenthesis("(equal 1 2)"), vs));
//		System.out.print("\n");
//		System.out.print(Predicates.caseEqual(tokenizer.equalParenthesis("(equal 1 1)"), vs));
//		System.out.print("\n");
//		System.out.print(Predicates.caseEqual(tokenizer.equalParenthesis("(= 1 2)"), vs));
//		System.out.print("\n");
//		System.out.print(Predicates.caseEqual(tokenizer.equalParenthesis("(= 2 2)"), vs));
//		System.out.print("\n");
//		System.out.print(Predicates.caseEqual(tokenizer.equalParenthesis("(EQUAL value 2)"), vs));
//		System.out.print("\n");
//		System.out.print(Predicates.caseEqual(tokenizer.equalParenthesis("(EQUAL 1 value)"), vs));
//		System.out.print("\n");
//		System.out.print(Predicates.caseEqual(tokenizer.equalParenthesis("(EQUAL 8293 value)"), vs));
//		System.out.print("\n");
//		System.out.print(Predicates.caseEqual(tokenizer.equalParenthesis("(= value valor)"), vs));
//		System.out.print("\n");
//		System.out.print(Predicates.caseEqual(tokenizer.equalParenthesis("(= value value)"), vs));
//		System.out.print("\n");
//		System.out.print(Predicates.caseEqual(tokenizer.equalParenthesis("(= \"jfkdsl\" 2)"), vs));
//		System.out.print("\n");
//		System.out.print(Predicates.caseEqual(tokenizer.equalParenthesis("(= \"jfkdsl\"  \"jfkdsl\")"), vs));
//		System.out.print("\n");
//		System.out.print(Predicates.caseEqual(tokenizer.equalParenthesis("(= (+ 1 2)  (+ 1 2) )"), vs));
//		System.out.print("\n");
//		System.out.print(Predicates.caseEqual(tokenizer.equalParenthesis("(= (+ 2 2)  (+ 1 2) )"), vs));
//		System.out.print("\n");
//		
//		scann.close();
//	}
}
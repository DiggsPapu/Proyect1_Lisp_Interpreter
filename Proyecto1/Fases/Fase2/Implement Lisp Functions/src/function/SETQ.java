package function;

import java.util.LinkedList;
//import java.util.Scanner;

public class SETQ {
	
	public static void evaluateSETQ(LinkedList<String> lista, VariableStorage variableStorage) {
		
		if (lista!= null) {
			if ( lista.get(1).equals(Patterns.SETQ) || lista.get(1).equals("setq") && lista.get(2).matches(Patterns.VALID_NAME) ) {
				LinkedList<String> listaEvaluar = new LinkedList<String>(lista);
				//Se eliminan el par de parentesis del final y el setq
				listaEvaluar.removeLast();
				listaEvaluar.removeFirst();
				listaEvaluar.removeFirst();
				
				//Si es un parentesis en posicion 1 y en el final entonces es una operacion
				if (listaEvaluar.getFirst().matches(Patterns.VALID_NAME) && listaEvaluar.getLast().equals(")") && listaEvaluar.get(1).equals("(")) {
					
					
				}else if(listaEvaluar.getFirst().matches(Patterns.VALID_NAME) && listaEvaluar.get(1).matches(Patterns.VALID_NAME) && listaEvaluar.get(1).equals(listaEvaluar.getLast())) {
					
					//Unico caso o esta el almacenada la variable para el valor o no lo esta
					if (variableStorage.getVariableStorage().containsKey(listaEvaluar.get(1))) {
						//Se crea y se almacena en el map la nueva variable con el valor de la variable anterior
						variableStorage.CreateVariable(listaEvaluar.getFirst(), variableStorage.getVariableStorage().get(listaEvaluar.get(1)));
						System.out.print("Se ha creado la variable exitosamente\n");
					}else {
						System.out.print("No fue posible almacenar la variable dado que no existe la variable para el valor\n");
					}
					
				} else if (listaEvaluar.getFirst().matches(Patterns.VALID_NAME) && listaEvaluar.get(1).equals(listaEvaluar.getLast())) {
					variableStorage.CreateVariable(listaEvaluar.getFirst(), listaEvaluar.get(1));
					System.out.print("Se ha creado la variable exitosamente\n");
				}else {
					System.out.print("No se ha podido almacenar por mala sintaxis\n");
				}
			}else {
				System.out.print("El nombre de la variable es invalida\n");
			}
		
		}else {
			System.out.print("La lista no es valida\n");
		}
	}
//	public static void main(String[] args) {
//		Scanner scann = new Scanner(System.in);
//		VariableStorage vs = new VariableStorage();
//		
//		SETQ.evaluateSETQ(tokenizer.equalParenthesis(scann.nextLine()), vs);
//		
//		scann.close();
//	}
	
	
	
//	public static caseSetq(LinkedList<String>) 
//	public void caseSETQ(LinkedList<String> array) {
////		System.out.print(array.get(2).substring(0, array.get(2).length()-1).matches(VALID_NAME) && array.get(array.size()-1).matches(SYMBOL) );
//		if (array.get(2).substring(0, array.get(2).length()-1).matches(Patterns.VALID_NAME) && array.get(array.size()-1).matches(Patterns.SYMBOL) ) {
//			// Para verificar que es valida 
//			System.out.print("Primer if\n");
////			System.out.print(array.get(3).matches(SYMBOL) && array.get(array.size()-2).matches(SYMBOL) && array.get(4).matches(QUOTATION) &&  array.get(array.size()-3).matches(QUOTATION));
//			if ( array.get(3).matches(Patterns.SYMBOL) && array.get(array.size()-2).matches(Patterns.SYMBOL) && array.get(4).matches(Patterns.QUOTATION) &&  array.get(array.size()-3).matches(Patterns.QUOTATION) ) {
//				System.out.print("SegundoIf\n");
//				// Es para almacenar el valor tipo string
//				String value = new String();
//				//Para verificar que es valida la concatenacion
//				boolean valido = true;
//				//Para correr el array
//				for (int k = 5 ; k < array.size()-3; k++ ) {
//					//El if es para verificar si hay una quotation o si es valido
//					//Si hay un " entonces significa que no es valido y pasa al else para no almacenar mas valores.
//					if (!array.get(k).matches(QUOTATION) && valido) {
//						System.out.print("3er if\n");
//						//Se concatena el string
//						value=value+" "+array.get(k);
//						if (k == array.size()-4) {
//							value.trim();
//							System.out.print("Final if\n");
//							
//						}
//						System.out.print("VALOR: "+value+"\n");
//					}
//					else {
//						valido = false;
//					}
//					
//				}
//				if (valido) {
//					//Es para almacenar el valor de la variable en el variable Storage
//					getVariableStorage().CreateVariable(array.get(2), value);
//				}else {
//					System.out.print("No es valida la sintaxis 2\n");
//				}
//				
//				
//			}else if(array.get(3).matches(SYMBOL) && array.get(array.size()-2).matches(SYMBOL) && array.get(4).matches(OPERATIONS)) {
//				//Modificar la lista para que se adapte al caso de operaciones
//				System.out.print("Entro a variable numerica en setq\n");
//				LinkedList<String> operationsArray = new LinkedList<String>();
//				for (int k = 3 ; k < array.size()-1 ; k++) {
//					operationsArray.add(array.get(k));
//				}
//				if (caseOperation(operationsArray)!=null) {
//					Operations calc = new Operations(caseOperation(operationsArray));
//					System.out.print(operationsArray);
//					getVariableStorage().CreateVariable(array.get(2), String.valueOf(calc.Result()));
//					System.out.print("El valor de la variable es: "+getVariableStorage().getVariableStorage().get(array.get(2))+"\n");
//				}
//				else {
//					System.out.print("No era valida la expresion\n");
//				}
//			}
//			else if(array.get(4).matches(NUMERIC_ATOM) && array.get(5).equals(")") && array.get(6).equals(")")) {
//				try {
//					Float.valueOf(array.get(4));
//					getVariableStorage().getVariableStorage().put(array.get(2), array.get(4));
//					System.out.print("El valor es: " + getVariableStorage().getVariableStorage().get(array.get(2))+"\n");
//				}
//				catch(Exception e) {
//					System.out.print("No era un valor valido\n");
//				}
//			}
//			else {
//				System.out.print("No es una sintaxis valida\n");
//			}
//		}else {
//			System.out.print("No es valida la sintaxis.\n");
//		}
//	}
}

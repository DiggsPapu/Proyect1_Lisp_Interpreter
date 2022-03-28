package v2;

import java.util.LinkedList;
import java.util.Scanner;

public class SETQ {
	public SETQ() {
		
	}
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
					LinkedList<String> copia = new LinkedList<String>(listaEvaluar);
					//Se le quita el nombre y queda la operacion
					copia.removeFirst();
					Calculator calc = new Calculator(copia, variableStorage);
					//Si es una operacion
					if (copia.get(1).equals("+") || copia.get(1).equals("-") || copia.get(1).equals("*") || copia.get(1).equals("/") || copia.get(1).equals("^") ) {
						variableStorage.CreateVariable(listaEvaluar.getFirst(), Float.toString(calc.Result()));
					} else if (copia.get(1).equals(">") || copia.get(1).equals("<") || copia.get(1).equals("=") || copia.get(1).equals("equals") || copia.get(1).equals("EQUALS") ) {
						variableStorage.CreateVariable(listaEvaluar.getFirst(), Boolean.toString(calc.ResultComp()));
					}else {
						System.out.print("No se pudo almacenar nada");
					}
						
					
					
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
	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		VariableStorage vs = new VariableStorage();
		
		SETQ.evaluateSETQ(tokenizer.equalParenthesis("(setq valor1 (+ 1 2 ))"), vs);
		System.out.print(vs.getVariableStorage().get("valor1")+"\n");
		
		SETQ.evaluateSETQ(tokenizer.equalParenthesis("(setq valor2 (< 1 2))"), vs);
		System.out.print(vs.getVariableStorage().get("valor2")+"\n");
		
		scann.close();
	}
	
}

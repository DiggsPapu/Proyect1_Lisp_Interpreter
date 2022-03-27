package function;

import java.util.LinkedList;

//import jdk.vm.ci.code.site.Call;

public class Function {
	
	
	public static void Defun(LinkedList<String> lista, VariableStorage variableStorage, FunctionStorage functionStorage) {
		if (lista != null ) {
			//Cumple con los requisitos de parentesis, defun|DEFUN y nombre valido
			if (lista.getFirst().equals("(") && lista.getLast().equals(")") && lista.get(1).matches(Patterns.DEFUN) && lista.get(2).matches(Patterns.VALID_NAME)) {
//				System.out.print(lista);
				LinkedList<String> copia = new LinkedList<String>(lista);
				//Se quitan los parentesis, la palabra reservada y el nombre
				copia.removeFirst();
				copia.removeFirst();
				copia.removeFirst();
				copia.removeLast();
				//Aqui se revisara si es recursiva o no de manera que se almacene de 1 forma u otra
				boolean recursion = false;
				for (int k = 0 ; k < copia.size() ; k++) {
					//Si se encuentra el nombre de la funcion entonces recursion es verdadera
					if (copia.get(k).equals(lista.get(2))) {
						recursion = true;
					}
				}
				if (recursion) {
					copia.addFirst("r");
					functionStorage.getFunction().put(lista.get(2), copia);
					
				}else {
					copia.addFirst("n");
					functionStorage.getFunction().put(lista.get(2), copia);
					
				}
				
				System.out.print("Fue posible almacenar la funcion\n");
			}
			else {
				System.out.print("No es posible almacenar la funcion\n");
			}
		}
		else {
			System.out.print("\nEs nulo\n");
		}
	}
	
	public static String functionExecution(LinkedList<String> callF, FunctionStorage fun, VariableStorage var) {
		//Diferente de nulo
		if (callF != null) {
			//Verificar par de parentesis y que sea funcion almacenada
//			System.out.print(callF.get(1));
//			System.out.print(fun.getFunction().containsKey(callF.get(1)));
			if (callF.getFirst().equals("(") && callF.getLast().equals(")") && fun.getFunction().containsKey(callF.get(1))) {
				//Se crea una lista con las instrucciones almacenadas
				LinkedList<String> ins = new LinkedList<String>(fun.getFunction().get(callF.get(1)));
				if (ins.getFirst().equals("n")) {
					ins.removeFirst();
					return Function.notRecursive(callF,ins, var, fun);
						
				}
				else {
					//Esto lo modificare luego porque es para recursivas

					System.out.print("Nulo1");
					return null;
				}
				
				
			}
			else {
				System.out.print("Nulo2");
				return null;
			}
		}
		else {
			System.out.print("Nulo3");
			return null;
		}
	}
	private static String notRecursive(LinkedList<String> call,LinkedList<String> ins, VariableStorage var, FunctionStorage fun){
		//Se deja a la lista llamada solo con los argumentos, se le quitan los parentesis y el nombre
		call.removeFirst();
		call.removeFirst();
		call.removeLast();
		//Se crea una lista con los parametros y otra con las instrucciones
		LinkedList<String> inst = new LinkedList<String>(Function.separate(ins).get(0));
		LinkedList<String> param = new LinkedList<String>(Function.separate(ins).get(1));
		//Aqui se cambian los argumentos
		call = Function.variableCases(call, var, fun);
//		System.out.print("\n\n"+ call + "\n\n");
		param.removeFirst();
		param.removeLast();
//		System.out.print(param);
//		System.out.print(inst);
		
		//La cantidad de argumentos ingresados coincide con la cantidad de parametros almacenados
		if (call.size()==param.size()) {
//			System.out.print("Ingreso aqui");
			//Se cambian los argumentos en las instrucciones
			inst = Function.changeArgs(call, param, inst, var, fun);
			return Function.executeCases(inst, var, fun);
		}
		else {
			return null;
		}
	}
	
	private static String executeCases(LinkedList<String> ins, VariableStorage var, FunctionStorage fun) {
//		System.out.print("Ejecuto caso\n");
		if (ins.getFirst().equals("(") && ins.getLast().equals(")") && (ins.get(1).equals("quote") || ins.get(1).equals("QUOTE"))) {
			return Predicates.caseQuote(ins).toString();
						
		}
		else if (ins.getFirst().equals("(") && ins.getLast().equals(")") && (ins.get(1).equals("setq") || ins.get(1).equals("SETQ"))) {
			SETQ.evaluateSETQ(ins, var);
			return var.getVariableStorage().get(ins.get(2));
		}
		else if (ins.getFirst().equals("(") && ins.getLast().equals(")") && (ins.get(1).equals("atom") || ins.get(1).equals("ATOM"))) {
			return Predicates.evaluateAtom(ins, var).toString();
		}

		else if (ins.getFirst().equals("(") && ins.getLast().equals(")") && (ins.get(1).equals("list") || ins.get(1).equals("LIST"))) {
			return Predicates.evaluateList(ins, var).toString();
			
			
		}

		else if (ins.getFirst().equals("(") && ins.getLast().equals(")") && (ins.get(1).equals("equal") || ins.get(1).equals("EQUAL") || ins.get(1).matches(Patterns.LOGICAL))) {
			Predicates.caseEqual(ins, var);
			if (Predicates.caseEqual(ins, var) ==null) {
				System.out.print("La expresion no es correcta\n");
				return null;
			}else if(Predicates.caseEqual(ins, var)==false) {
				return "false";
			}else {
				return "true";
			}
		}

		else if (ins.size()==3) {
			return ins.get(1);
		}

		else if (ins.getFirst().equals("(") && ins.getLast().equals(")") && ins.get(1).matches(Patterns.OPERATIONS) ) {
//			System.out.print("Entro a la calculadora\n");
			Calculator calc = new Calculator (ins, var);
			
			if (ins.get(1).matches(Patterns.LOGICAL)) {
				return Boolean.toString(calc.ResultComp());
			}else if (ins.get(1).matches(Patterns.ARITHMETIC)) {
				return Float.toString(calc.Result());
			}else {
				System.out.print("No es operacion valida");
				return null;
			}
		}
		else {
			return null;
		}
//		else if (ins.getFirst().equals("(") && ins.getLast().equals(")") && (ins.get(1).equals("cond") || ins.get(1).equals("COND"))) {
//			CONDI.COND(ins, var);
//		}
		
	}
	
	
	
	private static LinkedList<String> changeArgs(LinkedList<String> call,LinkedList<String> params,LinkedList<String> inst, VariableStorage var, FunctionStorage fun){
//		System.out.print(params.size()+" "+call.size());
		//Es para ir moviendose de parametro en parametro
		for (int k = 0 ; k < params.size() ; k++) {
			//Es para ir moviendose en cada token de la instruccion
			for (int j = 0; j < inst.size() ; j++) {
				//En el caso de que el arg sea usado entonces se modifica por el argumento real
				if (inst.get(j).equals(params.get(k))) {
					//Se add en la posicion j el param real y se remueve el valor falso en el j+1
					inst.add(j, call.get(k));
					inst.remove(j+1);
				}
				//En cualquier otro caso no sucede nada
			
			}
			
		}
//		System.out.print(inst);
		return inst;
	}
	
	private static LinkedList<String> variableCases(LinkedList<String> call, VariableStorage var, FunctionStorage fun){
		LinkedList<String> list = new LinkedList<String>();
		
		
		int size = call.size() ;
		int counter = 0 ;
		while ( counter < size ) {
//			System.out.print(size);
//			System.out.print(call.getFirst()+"\n");

			//En caso de ser variable
			if ( call.getFirst().matches(Patterns.VALID_NAME)) {
				//Se add a la lista final por el valor de la variable;
				list.add( counter, var.getVariableStorage().get(call.get(counter)) );
				call.removeFirst();
				counter++;
				
			}else if ( call.getFirst().equals("(") && call.get(1).matches(Patterns.LOGICAL)) {
				LinkedList<String> operation = new LinkedList<String>();
				int op = 1;
				int cp = 0;
				operation.add(call.getFirst());
				call.removeFirst();
				counter++;
				while (op != cp && counter < size) {
					if (call.getFirst().equals("(")) {
						op++;
					}
					else if (call.getFirst().equals(")")) {
						cp++;
					}
					else {
						
					}
					operation.add(call.getFirst());
					call.remove();
					counter++;
							
				}
				if (op==cp) {

					Calculator calc = new Calculator (operation, var);
					list.add(Boolean.toString(calc.ResultComp()));
				}
				else {
					System.out.print("1entro aqui\n");
					return null;
				}
				//En el caso de que sea una operacion aritmetica
				
			}else if ( call.getFirst().equals("(") && call.get(1).matches(Patterns.ARITHMETIC)) {
				LinkedList<String> operation = new LinkedList<String>();
				int op = 1;
				int cp = 0;
				operation.add(call.getFirst());
				call.removeFirst();
				counter++;
				while (op != cp && counter < size) {
					if (call.getFirst().equals("(")) {
						op++;
					}
					else if (call.getFirst().equals(")")) {
						cp++;
					}
					operation.add(call.getFirst());
					call.removeFirst();
					counter++;
							
				}
				if (op==cp) {

					Calculator calc = new Calculator (operation, var);
					list.add(Float.toString(calc.Result()));
				}
				else {
					System.out.print("2entro aqui\n");
					return null;
				}
			}else {
				list.add(call.getFirst());
				call.remove();
				counter++;
			}
//			System.out.print("\n"+counter+ " : " + list + "\n");
//			//En el caso de que sea una funcion
//			else if ( call.getFirst().equals("(") && call.get(1).matches(Patterns.VALID_NAME) && var.getVariableStorage().containsKey(call.get(1))) {
//				LinkedList<String> operation = new LinkedList<String>();
//				int op = 1;
//				int cp = 0;
//				operation.add(call.getFirst());
//				call.removeFirst();
//				counter++;
//				while (op != cp && counter < size) {
//					if (call.getFirst().equals("(")) {
//						op++;
//					}
//					else if (call.getFirst().equals(")")) {
//						cp++;
//					}
//							
//				}
//				if (op==cp) {
//
//					
//					list.add(Function.functionExecution(operation, fun, var));
//					
//				}
//				else {
//					return null;
//				}
//			}
			
			
			
			
		}
		return list;
	}
	private static LinkedList<LinkedList<String>> separate(LinkedList<String> lista){
		//Creacion de las 2 sublistas y la lista a evaluar
//		System.out.print(lista+"\n");
		LinkedList<String> lista1 = new LinkedList<String>();
		LinkedList<String> lista2 = new LinkedList<String>();
		LinkedList<String> ev = new LinkedList<String>(lista);
		LinkedList<LinkedList<String>> listaFinal = new LinkedList<LinkedList<String>>();
		//Tres contadores, uno para moverme en la lista ev y los otros para contar los open y close parenthesis
		int counter = 0;
		int op = 1;
		int cp = 0;
		lista1.add(ev.getFirst());
		ev.removeFirst();
		counter++;
		while (op != cp && counter < lista.size()) {
			if (ev.getFirst().equals("(")) {
				op++;
			}else if (ev.getFirst().equals(")")) {
				cp++;
			}
			lista1.add(ev.getFirst());
			ev.removeFirst();
			counter++;
		}
//		System.out.print(lista1);
		op++;
		counter++;
		lista2.add(ev.getFirst());
		ev.removeFirst();
		while (op != cp && counter < lista.size()) {
			if (ev.getFirst().equals("(")) {
				op++;
			}else if (ev.getFirst().equals(")")) {
				cp++;
			}
			lista2.add(ev.getFirst());
			ev.removeFirst();
			counter++;
		}
		if (ev.isEmpty()) {
			listaFinal.add(lista2);
			listaFinal.add(lista1);
			return listaFinal;
		}
		else {
			return null;
		}
	}
	
	
	
	public static void main(String[] args) {
		VariableStorage var = new VariableStorage();
		FunctionStorage fu = new FunctionStorage();
		
		Function.Defun(tokenizer.equalParenthesis("(DEFUN operacion1 (arg arg1 arg2) (+ arg1 (* arg2 (^ arg2 arg1))) )"), var, fu);
		System.out.print(fu.getFunction().get("operacion1")+"\n");
		System.out.print(Function.functionExecution(tokenizer.equalParenthesis("(operacion1 4 3 2)"), fu, var)+"\n");
		System.out.print(Function.functionExecution(tokenizer.equalParenthesis("(operacion1 3 3 7)"), fu, var)+"\n");
		
		Function.Defun(tokenizer.equalParenthesis("(DEFUN valorNuevo (arg0 arg1 arg2 arg3) (setq valor1 (/ arg3 (- arg2 (* arg1 arg3)))) )"), var, fu);
		Function.functionExecution(tokenizer.equalParenthesis("(valorNuevo 5 3 1 900)"), fu, var);
		System.out.print(var.getVariableStorage().get("valor1")+"\n");
		
		Function.Defun(tokenizer.equalParenthesis("(DEFUN listaNueva (arg0 arg1 arg2 arg3) (list (/ arg3 (- arg2 (* arg1 arg3))) valor1 9 \"listaa\")  )"), var, fu);
		Function.functionExecution(tokenizer.equalParenthesis("(listaNueva 5 3 1 2)"), fu, var);
		
		Function.Defun(tokenizer.equalParenthesis("(DEFUN atomNuevo (arg0 arg1 arg2 arg3) (atom 7)  )"), var, fu);
		Function.functionExecution(tokenizer.equalParenthesis("(atomNuevo 5 3 1 2)"), fu, var);
		
		Function.Defun(tokenizer.equalParenthesis("(DEFUN quoteNuevo (arg0 arg1 arg2 arg3) (quote arg0 fdkssjl asjkl doe0)  )"), var, fu);
		Function.functionExecution(tokenizer.equalParenthesis("(quoteNuevo 5 3 1 2)"), fu, var);
		
		
		System.out.print("\n");
		LinkedList<String> list = tokenizer.equalParenthesis("(1 (+ 1 2) 9)");
		list.removeFirst();
		list.removeLast();
		LinkedList<String> list1 = tokenizer.equalParenthesis("(2 (= 1 2) 4 \"hola\")");
		list1.removeFirst();
		list1.removeLast();
		System.out.print(Function.variableCases(list1, var, fu));
		
		LinkedList<String> call = new LinkedList<String>(Function.variableCases(list, var, fu));
		System.out.print("\n"+call);
		LinkedList<String> params = tokenizer.equalParenthesis("(arg1 arg2 arg3)");
		params.removeFirst();
		params.removeLast();
		
		LinkedList<String> list4 = tokenizer.equalParenthesis("(+ arg1 (* arg2 (^ arg2 arg1)))");
		System.out.print(Function.changeArgs(call, params, list4, var, fu));
		
		
	}
}

package function;

import java.util.LinkedList;

public class CONDI {
	//, VariableStorage variableStorage
	public static void COND(LinkedList<String> lista , VariableStorage variableStorage) {
		if (lista != null) {
			if (lista.getFirst().equals("(") && lista.getLast().equals(")") && ( lista.get(1).equals("cond") || lista.get(1).equals(Patterns.COND) )) {
				//Se dejan solo las instrucciones
				lista.removeFirst();
				lista.removeFirst();
				lista.removeLast();
				//la lista que devuelva el ifs no tiene que ser nula
				if (CONDI.getIfs(lista) !=null) {
					//Aqui se debe de dividir en los ifs mediante hacer sublistas primer if segundo y tercero
					//Ademas se obtiene el condicional para cada if y su respectiva lista de instrucciones
					LinkedList<String> clist1 = new LinkedList<String>(CONDI.getConditional(CONDI.getIfs(lista).get(0)).get(0));
					LinkedList<String> plist1 = new LinkedList<String>(CONDI.getConditional(CONDI.getIfs(lista).get(0)).get(1));
					LinkedList<String> clist2 = new LinkedList<String>(CONDI.getConditional(CONDI.getIfs(lista).get(1)).get(0));
					LinkedList<String> plist2 = new LinkedList<String>(CONDI.getConditional(CONDI.getIfs(lista).get(1)).get(1));
					
					LinkedList<String> list3 = new LinkedList<String> (CONDI.getIfs(lista).get(2));
					
//					System.out.print(( clist1.getFirst().equals("(") && clist1.getLast().equals(")")  && (clist1.get(1).matches(Patterns.LOGICAL) || clist1.get(1).equals("EQUAL") || clist1.get(1).equals("equal")) ) );
					
					
					boolean cond1= ( clist1.getFirst().equals("(") && clist1.getLast().equals(")")  && (clist1.get(1).matches(Patterns.LOGICAL) || clist1.get(1).equals("EQUAL") || clist1.get(1).equals("equal")) ); 
//					System.out.print(( clist2.getFirst().equals("(") && clist2.getLast().equals(")")  && (clist2.get(1).matches(Patterns.LOGICAL) || clist2.get(1).equals("EQUAL") || clist2.get(1).equals("equal")) ) );
					boolean cond2 = ( clist2.getFirst().equals("(") && clist2.getLast().equals(")")  && (clist2.get(1).matches(Patterns.LOGICAL) || clist2.get(1).equals("EQUAL") || clist2.get(1).equals("equal")) )  ;
//					System.out.print( list3.getFirst().equals("(") && list3.getLast().equals(")") && list3.get(1).equals("t"));
					boolean cond3 = list3.getFirst().equals("(") && list3.getLast().equals(")") && list3.get(1).equals("t");
					
					//Aqui se chequea que los cond esten bien hechos superficialmente
					if (cond1 && cond2 && cond3) {
						
						//Aqui entran los ifs del cond
//						System.out.print("AQUI");
//						System.out.print(Float.valueOf(clist1.get(2))> Float.valueOf(clist1.get(3)));
//						System.out.print(clist1.get(2)>clist1.get(3));
						if (Predicates.caseEqual(clist1, variableStorage)) {
							System.out.print("Entro al if\n");
							CONDI.getCases(plist1, variableStorage);
							
						}
						else if (Predicates.caseEqual(clist2, variableStorage)) {
							System.out.print("Entro al else if\n");	
							CONDI.getCases(plist2, variableStorage);
							
						}
						else {
							System.out.print("Entro al else\n");
							list3.removeFirst();
							list3.removeFirst();
							list3.removeLast();
							
							CONDI.getCases(list3, variableStorage);
							
							//Se remueve el parentesis abierto, cerrado y la t, listo para ingresar a los casos
							list3.removeFirst();
							list3.removeFirst();
							list3.removeLast();
							
						}
					}
					else {
						System.out.print("Error de escritura del cond\n");
					}
					
					
				}
				else {
					System.out.print("COND INVALIDO\n");
				}
				
			}
		}
		else {
			System.out.print("No es un cond valido\n");
			
		}
	}
	
	public static LinkedList<LinkedList<String>> getIfs(LinkedList<String> lista){
		//Creacion de las tres sublistas y la lista a evaluar
//		System.out.print(lista+"\n");
		LinkedList<String> lista1 = new LinkedList<String>();
		LinkedList<String> lista2 = new LinkedList<String>();
		LinkedList<String> lista3 = new LinkedList<String>();
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
		op++;
		counter++;
		lista3.add(ev.getFirst());
		ev.removeFirst();
		while (op != cp && counter < lista.size()) {
			if (ev.getFirst().equals("(")) {
				op++;
			}else if (ev.getFirst().equals(")")) {
				cp++;
			}
			lista3.add(ev.getFirst());
			ev.removeFirst();
			counter++;
		}
//		System.out.print(lista1+"\n");
//		System.out.print(lista2+"\n");
//		System.out.print(lista3+"\n");
		listaFinal.add(lista1);
		listaFinal.add(lista2);
		listaFinal.add(lista3);
		//Si no hubo errores
		if (op == cp && counter == lista.size()) {
			
			return listaFinal;
		}
		else {
			return null;
		}
		
	}
	
	public static LinkedList<LinkedList<String>> getConditional(LinkedList<String> lista){
		//Se crea una lista para la condicion del if, otra para las instrucciones a ejecutar, la que se evaluara y una final
		LinkedList<String> conditional = new LinkedList<String>();
		LinkedList<String> eva = new LinkedList<String>(lista);
		LinkedList<LinkedList<String>> listaFinal = new LinkedList<LinkedList<String>>();
		//Al evaluado se le quitan los parentesis que lo encierran para dar lugar a solo el condicional y las instrucciones
		eva.removeFirst();
		eva.removeLast();
		//Se crea un contador para moverse en la lista, otro para open y closed parenthesis
		int counter = 0;
		int op = 1;
		int cp = 0;
		//Se add el primer parentesis y se remueve del evaluado, luego se suma al contador para moverse
		conditional.add(eva.getFirst());
		eva.removeFirst();
		counter++;
		//Mientras que los open parenthesis no sean igual que los closed parenthesis and el contador es menor que el size de la lista
		while (op != cp && counter < lista.size()) {
			if (eva.getFirst().equals("(")) {
				op++;
			}else if (eva.getFirst().equals(")")) {
				cp++;
			}
			conditional.add(eva.getFirst());
			eva.removeFirst();
			counter++;
		}
		//Si de instrucciones no viene en parentesis entonces debe de venir solo 1 token
		if (counter++==lista.size() && lista.getFirst().matches(Patterns.SYMBOL)) {
			//Se le add el conditional
			listaFinal.add(conditional);
			//Se le add el set de instructions dado que solo eso queda en la eva
			listaFinal.add(eva);
			return listaFinal;
		}
		//Si el set de instrucciones es de mas de un valor entonces debe de venir en parentesis
		else if (lista.getFirst().equals("(") && lista.getLast().equals(")")){
			//Se le add el conditional
			listaFinal.add(conditional);
			//Se le add el set de instructions dado que solo eso queda en la lista
			listaFinal.add(eva);
			return listaFinal;
		}
		//No se retorna nada
		else {
			return null;
		}
	}
	
	public static void getCases(LinkedList<String> ins, VariableStorage variableStorage) {
		if (ins.getFirst().equals("(") && ins.getLast().equals(")") && (ins.get(1).equals("quote") || ins.get(1).equals("QUOTE"))) {
			System.out.print("Ingreso quote\n");
			Predicates.caseQuote(ins);
		}
		else if (ins.getFirst().equals("(") && ins.getLast().equals(")") && (ins.get(1).equals("setq") || ins.get(1).equals("SETQ"))) {
			System.out.print("Ingreso setq\n");
			SETQ.evaluateSETQ(ins, variableStorage);
		}
		else if (ins.getFirst().equals("(") && ins.getLast().equals(")") && (ins.get(1).equals("atom") || ins.get(1).equals("ATOM"))) {
			System.out.print(Predicates.evaluateAtom(ins, variableStorage));
		}

		else if (ins.getFirst().equals("(") && ins.getLast().equals(")") && (ins.get(1).equals("list") || ins.get(1).equals("LIST"))) {
			Predicates.evaluateList(ins, variableStorage);
			System.out.print(Predicates.evaluateList(ins, variableStorage));
			
		}

		else if (ins.getFirst().equals("(") && ins.getLast().equals(")") && (ins.get(1).equals("equal") || ins.get(1).equals("EQUAL") || ins.get(1).matches(Patterns.LOGICAL))) {
			System.out.print(Predicates.caseEqual(ins, variableStorage));
		}

		else if (ins.getFirst().equals("(") && ins.getLast().equals(")") && (ins.get(1).equals("cond") || ins.get(1).equals("COND"))) {
			CONDI.COND(ins, variableStorage);
		}

		else if (ins.size()==1) {
			System.out.print(ins.get(0));
		}

		else if (ins.getFirst().equals("(") && ins.getLast().equals(")") && ins.get(1).matches(Patterns.OPERATIONS) ) {
			System.out.print("Entro a la calculadora\n");
			Calculator calc = new Calculator (ins, variableStorage);
			System.out.print(calc.Result());;
		}
	}
	
	public static void main(String[] args) {
		//Este print solo si se hace el metodo publico
		VariableStorage variableStorage = new VariableStorage();
//		System.out.print("\n\n(COND ((< 14 2) (+ 1 2) ) ( (= 22 1) 93 ) (t (quote kjflds 1 2 3 dkf)) )");
//		CONDI.COND(tokenizer.equalParenthesis("(COND ((< 14 2) (+ 1 2) ) ( (= 22 1) 93 ) (t (quote kjflds 1 2 3 dkf)) )"), variableStorage);
//		System.out.print("\n\n(COND ((< 1 21) (+ 1 2) ) ( (= 1 15) 093 ) (t (quote kjfldkjljs 1 2 3 dkf)) )");
//		CONDI.COND(tokenizer.equalParenthesis("(COND ((< 1 21) (+ 1 2) ) ( (= 1 15) 093 ) (t (quote kjfldkjljs 1 2 3 dkf)) )"), variableStorage);
//		System.out.print("\n\n(COND ((= 2 23) (+ 1 2) ) ( (> 1 11) 1093 ) (t (quote kjflds 1 2 3 dkf jkl)) )");
//		CONDI.COND(tokenizer.equalParenthesis("(COND ((= 2 23) (+ 1 2) ) ( (> 1 11) 1093 ) (t (quote kjflds 1 2 3 dkf jkl)) )"), variableStorage);
//		System.out.print("\n\n(COND ((< 24 2) (+ 1 (* 8 9)) ) ( (> 11 1) 4893 ) (t (quote kjflds 1 2 3 dkf)) )");
//		CONDI.COND(tokenizer.equalParenthesis("(COND ((< 24 2) (+ 1 (* 8 9)) ) ( (> 11 1) 4893 ) (t (quote kjflds 1 2 3 dkf)) )"), variableStorage);
//		System.out.print("\n\n(COND ((< 24 2) (+ 1 (* 8 9)) ) ( (> 11 1) 4893 ) (t (quote kjflds 1 2 3 dkf)) )");
//		CONDI.COND(tokenizer.equalParenthesis("(COND (( 2 2) (SETQ value4 (> 3 2) ) ) ( (> 1 15) 2093 ) (t (quote kjflds 1 2 3 dkf)) )"), variableStorage);
//		System.out.print("Vlaue4: "+variableStorage.getVariableStorage().get("value4")+"\n");
//		CONDI.COND(tokenizer.equalParenthesis("(COND ((= 2 2) (SETQ value4 (< 3 2) ) ) ( (< 2 1a) 93 ) (t (setq value41 \"hola soy diego\")) )"), variableStorage);
//		System.out.print(variableStorage.getVariableStorage().get("value4"));
		CONDI.COND(tokenizer.equalParenthesis("(COND ((= \"hola\" \"hla\") (SETQ value4 (< 33 2) ) ) ( (= \"hola\" \"hola\") 0993 ) (t (setq value42 \"hola soy diego\")) )"), variableStorage);
		
		CONDI.COND(tokenizer.equalParenthesis("(COND ((= \"hola\" \"hola\") (SETQ value4 (< 33 2) ) ) ( (= \"hola\" \"hla\") 0993 ) (t (setq value42 \"hola soy diego\")) )"), variableStorage);
		
		CONDI.COND(tokenizer.equalParenthesis("(COND ((= \"hla\" \"hola\") (SETQ value4 (< 33 2) ) ) ( (= \"hola\" \"hla\") 0993 ) (t (setq value42 \"hola soy diego\")) )"), variableStorage);
		
		CONDI.COND(tokenizer.equalParenthesis("(COND ((= 1 1) (SETQ value4 (< 33 2) ) ) ( (= \"hola\" \"hla\") 0993 ) (t (setq value42 \"hola soy diego\")) )"), variableStorage);
		
		CONDI.COND(tokenizer.equalParenthesis("(COND ((= 1 2) (SETQ value4 (< 33 2) ) ) ( (= \"hola\" \"hla\") 0993 ) (t (setq value42 \"hola soy diego\")) )"), variableStorage);
		
		CONDI.COND(tokenizer.equalParenthesis("(COND ((= 1 2) (SETQ value4 (< 33 2) ) ) ( (= \"kl\" \"kl\") 0993 ) (t (setq value42 \"hola soy diego\")) )"), variableStorage);
		
		CONDI.COND(tokenizer.equalParenthesis("(COND ((= 23 49) (SETQ value4 (= 33 2) ) ) ( ( = 1 1) 0993 ) (t (setq value42 \"hola soy diego\")) )"), variableStorage);
		
		CONDI.COND(tokenizer.equalParenthesis("(COND ((= 44 44) (SETQ value4 (= 33 2) ) ) ( ( = 1 1) 0993 ) (t (setq value42 \"hola soy diego\")) )"), variableStorage);
		
//		CONDI.COND(tokenizer.equalParenthesis("(COND ((< 23 49) (SETQ value4 (< 33 2) ) ) ( (= \"hola\" \"hla\") 0993 ) (t (setq value42 \"hola soy diego\")) )"), variableStorage);
		
//		CONDI.COND(tokenizer.equalParenthesis("(COND ((> 23 49) (SETQ value4 (> 33 2) ) ) ( ( > 22 1) 0993 ) (t (setq value42 \"hola soy diego\")) )"), variableStorage);
		
//		CONDI.COND(tokenizer.equalParenthesis("(COND ((< 24 59) (SETQ value4 (> 45 1) ) ) ( ( < -1 0) 89203) (t (90)) )"), variableStorage);
		
		CONDI.COND(tokenizer.equalParenthesis("(COND ((= 23 49) (SETQ value4 (= 33 2) ) ) ( ( = 1 1) 0993 ) (t (setq value42 \"hola soy diego\")) )"), variableStorage);
		
		CONDI.COND(tokenizer.equalParenthesis("(COND ((= 423879 value4) (SETQ value4 (= 33 2) ) ) ( ( = 1 1) 0993 ) (t (setq value42 \"hola soy diego\")) )"), variableStorage);
		
		CONDI.COND(tokenizer.equalParenthesis("(COND ((= value4 value4) (SETQ value3 (= 33 2) ) ) ( ( = 1 1) 0993 ) (t (setq value42 \"hola soy diego\")) )"), variableStorage);
		System.out.print(variableStorage.getVariableStorage().get("value3"));
		CONDI.COND(tokenizer.equalParenthesis("(COND ((= value4 value4) (list 1 2 3 ) ) ( ( = 1 1) 0993 ) (t (setq value42 \"hola soy diego\")) )"), variableStorage);
		
		CONDI.COND(tokenizer.equalParenthesis("(COND ((= 2 value4) (list 1 2 3 ) ) ( ( = 1 1) (atom 3892) ) (t (setq value42 \"hola soy diego\")) )"), variableStorage);
		
		CONDI.COND(tokenizer.equalParenthesis("(COND ((= 6 value4) (list 1 2 3 ) ) ( ( = 1 1) (- 3 (* 2 3)) ) (t (setq value42 \"hola soy diego\")) )"), variableStorage);

	}
	
	
}

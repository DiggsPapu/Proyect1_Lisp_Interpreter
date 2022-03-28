package v2;

import java.util.LinkedList;


public class Lector {
	private VariableStorage variableStorage;
	private FunctionStorage functionStorage;
	
	
	/**
	 * Este metodo es para obtener la VariableStorage dado que es privada
	 * @return
	 */
	public VariableStorage getVariableStorage() {
		return variableStorage;
	}
/**
 * Este metodo es para asignar un valor a la VariableStorage daod que es privada.
 * @param variableStorage
 */
	public void setVariableStorage(VariableStorage variableStorage) {
		this.variableStorage = variableStorage;
	}
/**
 * Este metodo es para obtener la FunctionStorage dado que es privada
 * @return
 */
	public FunctionStorage getFunctionStorage() {
		return functionStorage;
	}
/**
 * Este metodo es para asignarle un valor a la function Storage dado que es privado
 * @param functionStorage
 */
	public void setFunctionStorage(FunctionStorage functionStorage) {
		this.functionStorage = functionStorage;
	}
/**
 * Este metodo es un constructor para inicializar el lector.
 * Notese que el lector trae el Parser y el interprete en 1 solo
 */
	public Lector () {
		this.variableStorage = new VariableStorage();
		this.functionStorage = new FunctionStorage();
		
	}
	/**
	 * Este metodo sirve para evaluar una entrada basado en lisp
	 * @param scan string
	 * @return none, es void.
	 */
	public void readInstruccion(String scan) {
		if (tokenizer.equalParenthesis(scan)==null) {
//			System.out.print("Es nulo\n");
			
		}else {
//			System.out.print("No es nulo\n");
			System.out.print(getCases(tokenizer.equalParenthesis(scan), getVariableStorage()));
			
		}
		
	}
	/**
	 * Este metodo sirve para encontrar los casos que se puedan presentar, si es una quote, si es una funcion
	 * Si se esta definiendo una funcion o un setq, etc. 
	 * Llama los metodos estaticos de las otras clases para un desempenio mas ordenado.
	 * @param ins
	 * @param variableStorage
	 * @return String del resultado que se haya obtenido
	 */
	private String getCases(LinkedList<String> ins, VariableStorage variableStorage) {
		if (ins.getFirst().equals("(") && ins.getLast().equals(")") && (ins.get(1).equals("quote") || ins.get(1).equals("QUOTE"))) {
			return Predicates.caseQuote(ins).toString();
						
		}
		else if (ins.getFirst().equals("(") && ins.getLast().equals(")") && (ins.get(1).equals("setq") || ins.get(1).equals("SETQ"))) {
			SETQ.evaluateSETQ(ins, getVariableStorage());
			return getVariableStorage().getVariableStorage().get(ins.get(2));
		}
		else if (ins.getFirst().equals("(") && ins.getLast().equals(")") && (ins.get(1).equals("atom") || ins.get(1).equals("ATOM"))) {
			if (Predicates.evaluateAtom(ins, variableStorage)!= null) {
				return Predicates.evaluateAtom(ins, variableStorage).toString();
			}else {
				return null;
			}
		}

		else if (ins.getFirst().equals("(") && ins.getLast().equals(")") && (ins.get(1).equals("list") || ins.get(1).equals("LIST"))) {
			return Predicates.evaluateList(ins, variableStorage).toString();
			
		}

		else if (ins.getFirst().equals("(") && ins.getLast().equals(")") && (ins.get(1).equals("equal") || ins.get(1).equals("EQUAL"))) {
			if (Predicates.caseEqual(ins, variableStorage) ==null) {
				return null;
			}else if(Predicates.caseEqual(ins, variableStorage)==false) {
				return "";
			}else {
				return "";
			}
		}

		else if (ins.size()==3) {
			return ins.get(1);
		}else if (ins.size()==1) {
			return ins.get(0);
		}else if (ins.size()==1 && getVariableStorage().getVariableStorage().containsKey(ins.get(0))) {
			System.out.print(ins.get(0));
			return getVariableStorage().getVariableStorage().get(ins.get(0));
		}

		else if (ins.getFirst().equals("(") && ins.getLast().equals(")") && ins.get(1).matches(Patterns.OPERATIONS) ) {
			System.out.print("Entro a la calculadora\n");
			Calculator calc = new Calculator (ins, variableStorage);
			System.out.print(ins.get(1).matches(Patterns.LOGICAL));
			if (ins.get(1).matches(Patterns.LOGICAL)) {
//				System.out.print(Boolean.toString(calc.ResultComp()));
				return Boolean.toString(calc.ResultComp());
			}else if (ins.get(1).matches(Patterns.ARITHMETIC)) {
//				System.out.print(Float.toString(calc.Result()));
				return Float.toString(calc.Result());
			}else {
				System.out.print("No es operacion valida");
				return null;
			}
		}
		
		else if (ins.getFirst().equals("(") && ins.getLast().equals(")") && (ins.get(1).equals("cond") || ins.get(1).equals("COND"))) {
			return CONDI.COND(ins, variableStorage);
		}
		else if (ins.getFirst().equals("(") && ins.getLast().equals(")") && (ins.get(1).equals("defun") || ins.get(1).equals("DEFUN"))) {
			Function.Defun(ins, variableStorage, functionStorage);
			if (functionStorage.getFunction().containsKey(ins.get(2))) {
				return "";
			}else {
				return "";
			}
		}
		else if (ins.getFirst().equals("(") && ins.getLast().equals(")") && (functionStorage.getFunction().containsKey(ins.get(1))) ) {
			return Function.functionExecution(ins, functionStorage, variableStorage);
		}
		else {
			return null;
		}
	}
//	public static void main(String[] args) {
//		Lector lector = new Lector();
//		
//		System.out.print("Instruccion1: ");
//		lector.readInstruccion("(QUOTE jkl jkl)");
//		
//		System.out.print("Instruccion2: ");
//		lector.readInstruccion("(QUOTE \"jkl jkl\")");
//		
//
//		System.out.print("Instruccion3: ");
//		lector.readInstruccion("(setq valor0 (+ 1 ( * 2 (^ 3 9))) )");
//		System.out.print(lector.getVariableStorage().getVariableStorage().get("valor0"));
//		
//		System.out.print("Instruccion4: ");
//		lector.readInstruccion("(atom 29)");
//		
//		System.out.print("Instruccion5: ");
//		lector.readInstruccion("(atom 29)");
//		
//		System.out.print("Instruccion6: ");
//		lector.readInstruccion("(list 1 2 \"fdjks\" )");
//		
//		System.out.print("Instruccion7: ");
//		lector.readInstruccion("(list 1 2 \"fdjks\" (- 57 valor0 ) )");
//		
//		System.out.print("Instruccion8: ");
//		lector.readInstruccion("(= \" fdjskl\" 8)");
//		
//		System.out.print("Instruccion9: ");
//		lector.readInstruccion("(= 8 8)");
//		
//		System.out.print("Instruccion10: ");
//		lector.readInstruccion("(= valor0 39367.0)");
//		
//		System.out.print("Instruccion11: ");
//		lector.readInstruccion("(= \"sjdk\" 8)");
//		
//		System.out.print("Instruccion12: ");
//		lector.readInstruccion("(= \"sjdk\" 8)");
//		
//		System.out.print("Instruccion13: ");
//		lector.readInstruccion("(= \"sjdk\" \"8\")");
//		
//		System.out.print("Instruccion14: ");
//		lector.readInstruccion("(= \"8\" \"8\")");
//		
//		System.out.print("Instruccion15: ");
//		lector.readInstruccion("( \"8\" )");
//
//		System.out.print("Instruccion16: ");
//		lector.readInstruccion("( + 1 ( ^ 4 ( / 5 6)) )");
//		
//		System.out.print("Instruccion17: ");
//		lector.readInstruccion("( setq value1 \"sjdk\")");
//		
//		System.out.print("Instruccion18: ");
//		lector.readInstruccion("( setq value2 (^ 2 (+ 50 1)))");
//		
//		System.out.print("Instruccion19: ");//(cond (()) (()) (t()) )
//		lector.readInstruccion("(cond ((= value1 value2) (setq value3 \"hola soy diego\")) ((= value1 value1) (+ 1 23)) (t(quote value1)) )");
//		
//		System.out.print("Instruccion20: ");//(cond (()) (()) (t()) )
//		lector.readInstruccion("(cond ((= value1 value1) (setq value3 \"hola soy diego\")) ((= value1 value2) (+ 1 23)) (t(quote value1)) )");
//		
//		System.out.print("Instruccion21: ");//(cond (()) (()) (t()) )
//		lector.readInstruccion("(cond ((= value1 value2) (setq value3 \"hola soy diego\")) ((= value2 value1) (+ 1 23)) (t(quote value1)) )");
//		
//		System.out.print("Instruccion22: ");//(cond (()) (()) (t()) )
//		lector.readInstruccion("(cond ((= 1 2) (setq value3 \"hola soy diego\")) ((= 5 6) (+ 1 23)) (t(cond ((= 1 1) (+ 1 1)) ((= 3 4) \"jdf\") (t(setq value5 23)) )) )");
//		
//		System.out.print("Instruccion23: ");//(cond (()) (()) (t()) )
//		lector.readInstruccion("(cond ((= 1 2) (setq value3 \"hola soy diego\")) ((= 5 6) (+ 1 23)) (t(cond ((= 1 1) \"funciona\") ((= 3 4) \"jdf\") (t(setq value5 23)) )) )");
//		System.out.print("Instruccion24: ");//(cond (()) (()) (t()) )
//		lector.readInstruccion("(cond ((= 1 1) (cond ((= 4 5) \"Hola soy guapo\") ((= 2 2) (list 1 2)) (t(setq value7 1)) )) ((= 5 6) (+ 1 23)) (t(cond ((= 1 1) \"funciona\") ((= 3 4) \"jdf\") (t(setq value5 23)) )) )");
//		
//	}
}

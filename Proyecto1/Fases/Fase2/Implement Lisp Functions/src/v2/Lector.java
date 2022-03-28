package v2;

import java.util.LinkedList;

import v2.FunctionStorage;
import v2.VariableStorage;

public class Lector {
	private VariableStorage variableStorage;
	private FunctionStorage functionStorage;
	
	
	
	public VariableStorage getVariableStorage() {
		return variableStorage;
	}

	public void setVariableStorage(VariableStorage variableStorage) {
		this.variableStorage = variableStorage;
	}

	public FunctionStorage getFunctionStorage() {
		return functionStorage;
	}

	public void setFunctionStorage(FunctionStorage functionStorage) {
		this.functionStorage = functionStorage;
	}

	public Lector () {
		this.variableStorage = new VariableStorage();
		this.functionStorage = new FunctionStorage();
		
	}
	
	public void readInstruccion(String scan) {
		if (tokenizer.equalParenthesis(scan)==null) {
//			System.out.print("Es nulo\n");
			
		}else {
//			System.out.print("No es nulo\n");
			getCases(tokenizer.equalParenthesis(scan), getVariableStorage());
			
		}
		
	}
	
	private String getCases(LinkedList<String> ins, VariableStorage variableStorage) {
		if (ins.getFirst().equals("(") && ins.getLast().equals(")") && (ins.get(1).equals("quote") || ins.get(1).equals("QUOTE"))) {
			return Predicates.caseQuote(ins).toString();
						
		}
		else if (ins.getFirst().equals("(") && ins.getLast().equals(")") && (ins.get(1).equals("setq") || ins.get(1).equals("SETQ"))) {
			SETQ.evaluateSETQ(ins, getVariableStorage());
			return getVariableStorage().getVariableStorage().get(ins.get(2));
		}
		else if (ins.getFirst().equals("(") && ins.getLast().equals(")") && (ins.get(1).equals("atom") || ins.get(1).equals("ATOM"))) {
			return Predicates.evaluateAtom(ins, variableStorage).toString();
		}

		else if (ins.getFirst().equals("(") && ins.getLast().equals(")") && (ins.get(1).equals("list") || ins.get(1).equals("LIST"))) {
			return Predicates.evaluateList(ins, variableStorage).toString();
			
		}

		else if (ins.getFirst().equals("(") && ins.getLast().equals(")") && (ins.get(1).equals("equal") || ins.get(1).equals("EQUAL") || ins.get(1).matches(Patterns.LOGICAL))) {
			if (Predicates.caseEqual(ins, variableStorage) ==null) {
				return null;
			}else if(Predicates.caseEqual(ins, variableStorage)==false) {
				return "false";
			}else {
				return "true";
			}
		}

		else if (ins.size()==3) {
			return ins.get(1);
		}else if (ins.size()==1) {
			return ins.get(0);
		}

		else if (ins.getFirst().equals("(") && ins.getLast().equals(")") && ins.get(1).matches(Patterns.OPERATIONS) ) {
			System.out.print("Entro a la calculadora\n");
			Calculator calc = new Calculator (ins, variableStorage);
			
			if (ins.get(1).matches(Patterns.LOGICAL)) {
				System.out.print(Boolean.toString(calc.ResultComp()));
				return Boolean.toString(calc.ResultComp());
			}else if (ins.get(1).matches(Patterns.ARITHMETIC)) {
				System.out.print(Float.toString(calc.Result()));
				return Float.toString(calc.Result());
			}else {
				System.out.print("No es operacion valida");
				return null;
			}
		}
		
		else if (ins.getFirst().equals("(") && ins.getLast().equals(")") && (ins.get(1).equals("cond") || ins.get(1).equals("COND"))) {
			return CONDI.COND(ins, variableStorage);
		}
		else {
			return null;
		}
	}
	public static void main(String[] args) {
		Lector lector = new Lector();
		
		System.out.print("Instruccion1: ");
		lector.readInstruccion("(QUOTE jkl jkl)");
		
		System.out.print("Instruccion2: ");
		lector.readInstruccion("(QUOTE \"jkl jkl\")");
		

		System.out.print("Instruccion3: ");
		lector.readInstruccion("(setq valor0 (+ 1 ( * 2 (^ 3 9))) )");
		System.out.print(lector.getVariableStorage().getVariableStorage().get("valor0"));
		
		System.out.print("Instruccion4: ");
		lector.readInstruccion("(atom 29)");
		
		System.out.print("Instruccion5: ");
		lector.readInstruccion("(atom 29)");
		
		System.out.print("Instruccion6: ");
		lector.readInstruccion("(list 1 2 \"fdjks\" )");
		
		System.out.print("Instruccion7: ");
		lector.readInstruccion("(list 1 2 \"fdjks\" (- 57 valor0 ) )");
		
		System.out.print("Instruccion8: ");
		lector.readInstruccion("(= \" fdjskl\" 8)");
		
		System.out.print("Instruccion9: ");
		lector.readInstruccion("(= 8 8)");
		
		System.out.print("Instruccion10: ");
		lector.readInstruccion("(= valor0 39367.0)");
		
		System.out.print("Instruccion11: ");
		lector.readInstruccion("(= \"sjdk\" 8)");
		
		System.out.print("Instruccion12: ");
		lector.readInstruccion("(= \"sjdk\" 8)");
		
		System.out.print("Instruccion13: ");
		lector.readInstruccion("(= \"sjdk\" \"8\")");
		
		System.out.print("Instruccion14: ");
		lector.readInstruccion("(= \"8\" \"8\")");
		
		System.out.print("Instruccion15: ");
		lector.readInstruccion("( \"8\" )");

		System.out.print("Instruccion16: ");
		lector.readInstruccion("( + 1 ( ^ 4 ( / 5 6)) )");
		
		System.out.print("Instruccion17: ");
		lector.readInstruccion("( setq value1 \"sjdk\")");
		
		System.out.print("Instruccion18: ");
		lector.readInstruccion("( setq value2 (^ 2 (+ 50 1)))");
		
		System.out.print("Instruccion19: ");//(cond (()) (()) (t()) )
		lector.readInstruccion("(cond ((= value1 value2) (setq value3 \"hola soy diego\")) ((= value1 value1) (+ 1 23)) (t(quote value1)) )");
		
		System.out.print("Instruccion20: ");//(cond (()) (()) (t()) )
		lector.readInstruccion("(cond ((= value1 value1) (setq value3 \"hola soy diego\")) ((= value1 value2) (+ 1 23)) (t(quote value1)) )");
		
		System.out.print("Instruccion21: ");//(cond (()) (()) (t()) )
		lector.readInstruccion("(cond ((= value1 value2) (setq value3 \"hola soy diego\")) ((= value2 value1) (+ 1 23)) (t(quote value1)) )");
		
		System.out.print("Instruccion22: ");//(cond (()) (()) (t()) )
		lector.readInstruccion("(cond ((= 1 2) (setq value3 \"hola soy diego\")) ((= 5 6) (+ 1 23)) (t(cond ((= 1 1) (+ 1 1)) ((= 3 4) \"jdf\") (t(setq value5 23)) )) )");
		
		System.out.print("Instruccion23: ");//(cond (()) (()) (t()) )
		lector.readInstruccion("(cond ((= 1 2) (setq value3 \"hola soy diego\")) ((= 5 6) (+ 1 23)) (t(cond ((= 1 1) \"funciona\") ((= 3 4) \"jdf\") (t(setq value5 23)) )) )");
		System.out.print("Instruccion24: ");//(cond (()) (()) (t()) )
		lector.readInstruccion("(cond ((= 1 1) (cond ((= 4 5) \"Hola soy guapo\") ((= 2 2) (list 1 2)) (t(setq value7 1)) )) ((= 5 6) (+ 1 23)) (t(cond ((= 1 1) \"funciona\") ((= 3 4) \"jdf\") (t(setq value5 23)) )) )");
		
	}
}

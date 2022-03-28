package v2;

import java.util.Stack;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author marti
 */
public class Calculator {
    float Final = 0 ;
    Stack<String> Stack;
    boolean Comparison_operation = false;
    String OPType = "None";
    boolean Finalbool = true ;
    
  
    public Calculator(LinkedList<String> lista, VariableStorage variableStorage){
        Stack = new Stack<String>();
        LinkedList<String> list = fixList(lista, variableStorage);
        if (list!=null) {
        	String[] Tokens = list.toArray(new String[list.size()]);
            for (int i=0;i<Tokens.length;i++){
              Stack.push(Tokens[i]);
              if(Tokens[i].equals(")")) Interpret(); 
            }
        }else {
        	System.out.print("No se pudo realizar la operacion, no es valida. calculator\n");
        }
    }
    private void Interpret(){
        String tok;
        Stack<String> EvaluateStack = new Stack<String>();
        tok = Stack.pop();
        while(!(tok=Stack.pop()).equals("(")){
          EvaluateStack.push(tok);
        }
        Evaluate(EvaluateStack);
    }
  
    private void Evaluate(Stack<String> callStack){
        String func = callStack.pop();
        if(func.equals("+")) {
            float result = Plus(callStack);
            Stack.push(String.valueOf(result));
            OPType = "+";
        }else if(func.equals("-")) {
            float result = Minus(callStack);
            Stack.push(String.valueOf(result));
            OPType = "-";
        }else if(func.equals("*")) {
            float result = Multi(callStack);
            Stack.push(String.valueOf(result));
            OPType = "*";
        }else if(func.equals("/")) {
            float result = Div(callStack);
            Stack.push(String.valueOf(result));
            OPType = "/";
        }else if(func.equals("^")) {
            float result = Pow(callStack);
            Stack.push(String.valueOf(result));
            OPType = "^";
        }else if(func.equals(">")) {
            Comparison_operation = true;
            GreaterThan(callStack);
            OPType = ">";
        }else if(func.equals("<")) {
            Comparison_operation = true;
            SmallerThan(callStack);
            OPType = "<";
        }else if(func.equals("=")) {
            Comparison_operation = true;
            Equals(callStack);
            OPType = "=";
        }
    }
  
    private float Plus(Stack<String> callStack){
        float a = Float.parseFloat(callStack.pop());
        float b = Float.parseFloat(callStack.pop());
        Final = a+b;
        return(a+b);
    }
    private float Minus(Stack<String> callStack){
        float a = Float.parseFloat(callStack.pop());
        float b = Float.parseFloat(callStack.pop());
        Final = a-b;
        return(a-b);
    }
    private float Multi(Stack<String> callStack){
        float a = Float.parseFloat(callStack.pop());
        float b = Float.parseFloat(callStack.pop());
        Final = a*b;
        return(a*b);
    }
    private float Div(Stack<String> callStack){
        float a = Float.parseFloat(callStack.pop());
        float b = Float.parseFloat(callStack.pop());
        Final = a/b;
        return(a/b);
    }
    private float Pow(Stack<String> callStack){
        float a = Float.parseFloat(callStack.pop());
        float b = Float.parseFloat(callStack.pop());
        Final = (float)Math.pow(a, b);
        return((float)Math.pow(a, b));
    }
    
    
    private boolean GreaterThan(Stack<String> callStack){
        float a = Float.parseFloat(callStack.pop());
        float b = Float.parseFloat(callStack.pop());
        if (a > b){
            Finalbool = true;
        }else{
            Finalbool = false;
        }
        return Finalbool;
    }
    private boolean SmallerThan(Stack<String> callStack){
        float a = Float.parseFloat(callStack.pop());
        float b = Float.parseFloat(callStack.pop());
        if (a < b){
            Finalbool = true;
        }else{
            Finalbool = false;
        }
        return Finalbool;
    }
    private boolean Equals(Stack<String> callStack){
        float a = Float.parseFloat(callStack.pop());
        float b = Float.parseFloat(callStack.pop());
        if (a == b){
            Finalbool = true;
        }else{
            Finalbool = false;
        }
        return Finalbool;
    }
        public String getOpType(){
            return OPType;
        }
        public float Result(){
        	
            return Final;
    }
        public boolean ResultComp(){
        	
            return Finalbool;
        }
        
   private LinkedList<String> fixList(LinkedList<String> lista, VariableStorage variableStorage){
	   if ( lista!=null) {

		   if (lista.getFirst().equals("(") && !lista.isEmpty()) {
			   
		   }else {
			   System.out.print("No es una operacion, calculator");
			   return null;
		   }
		   //Se crea la lista que se evaluara y otra que sea la final donde se guarde todo
		   LinkedList<String> evaluateLista = new LinkedList<String>(lista);
		   LinkedList<String> finalLista = new LinkedList<String>();
		   
		   int counter = 0 ;
		   int op = 1;
		   int cp = 0;
		   
		   //Le add el parentesis abierto al final y le remove el parentesis abierto al evaluado
		   finalLista.add(evaluateLista.getFirst());
		   evaluateLista.removeFirst();
		   
		   while (counter<lista.size()-1 && op != cp) {
			   
//			   System.out.print(evaluateLista.getFirst()+"\n");
			   //Si it matches el pattern de nombre valido entonces ingresa
			   if (evaluateLista.getFirst().matches(Patterns.VALID_NAME)) {
				   //Si esta la llave se almacena
				   if (variableStorage.getVariableStorage().containsKey(evaluateLista.getFirst())) {
					   //Si contiene la llave entonces se almacena en el final y se remueve en el evaluado
					   finalLista.add( variableStorage.getVariableStorage().get(evaluateLista.getFirst() ) );
					   evaluateLista.removeFirst();
					   
				   }
				   //En el caso de que la llave no exista se retorna nulo
				   else {
					   System.out.print("Uno de los parametros utilizados para la operacion no existe como variable, calculator\n");
					   return null;
				   }
			   //En el caso de que sea un parentesis abierto se suma al contador, se aniade en el final y se quita en el evaluado
			   }else if (evaluateLista.getFirst().equals("(")) {
				   op++;
				   finalLista.add(evaluateLista.getFirst());
				   evaluateLista.removeFirst();
				   
			   //En el caso de que sea un parentesis cerrado se suma al contador, se aniade en el final y se quita en el evaluado      
			   }else if (evaluateLista.getFirst().equals(")")) {
				   cp++;
				   finalLista.add(evaluateLista.getFirst());
				   evaluateLista.removeFirst();
			   
			   //En el caso de que sea un string entonces se devuelve nulo porque no se pueden operar strings.   
			   }else if (evaluateLista.getFirst().startsWith(" ")) {
				   System.out.print("No se aceptan strings en la evaluacion, calculator.\n");
				   return null;
				   
			   //En el caso de que sea cualquier otra cosa se add   
			   }else if (evaluateLista.getFirst().equals(Patterns.EQUAL) || evaluateLista.getFirst().startsWith("=") || evaluateLista.getFirst().equals("equal")) {
				   finalLista.add("=");
				   evaluateLista.removeFirst();
				   
			   //En el caso de que sea cualquier otra cosa se add   
			   }else {
				   finalLista.add(evaluateLista.getFirst());
				   evaluateLista.removeFirst();
			   }
			   
			   counter++;
		   }
		   //Si son signos iguales entonces se retorna la lista
		   if(op==cp) {
//			   System.out.print(finalLista+"\n");
			   
			   return finalLista;
		   //Si no son signos iguales entonces se retorna nulo
		   }else {
			   System.out.print("La operacion no es valida, calculator\n");
			   return null;
		   }
	   }
	   //En el caso de que sea nula o este vacia la lista
	   else {
		   System.out.print("No es una operacion valida, calculator\n");
		   return null;
	   }
   }
   public static void main(String[] args) {
	   VariableStorage variableStorage = new VariableStorage();
	   Scanner scann = new Scanner(System.in);
	   Calculator calc = new Calculator(tokenizer.equalParenthesis("(+ 1 (* 9 4))"),variableStorage);
	   System.out.print(calc.Result()+"\n");
	   variableStorage.CreateVariable("valor", "50");
	   Calculator calc1 = new Calculator(tokenizer.equalParenthesis("(+ 1 (* valor 4))"),variableStorage);
	   System.out.print(calc1.Result()+"\n");
	   Calculator calc0 = new Calculator(tokenizer.equalParenthesis("(+ 1 (/ valor 0))"),variableStorage);
	   System.out.print(calc0.Result()+"\n");
	   Calculator calc2 = new Calculator(tokenizer.equalParenthesis("(< 1 9)"),variableStorage);
	   System.out.print(calc2.ResultComp()+"\n");
	   Calculator calc3 = new Calculator(tokenizer.equalParenthesis("(> 1 9)"),variableStorage);
	   System.out.print(calc3.ResultComp()+"\n");
	   Calculator calc4 = new Calculator(tokenizer.equalParenthesis("(> (^ 1 8) (* 9 valor))"),variableStorage);
	   System.out.print(calc4.ResultComp()+"\n");
	   Calculator calc5 = new Calculator(tokenizer.equalParenthesis("(< (^ 1 8) (* 9 valor))"),variableStorage);
	   System.out.print(calc5.ResultComp()+"\n");
	   Calculator calc6 = new Calculator(tokenizer.equalParenthesis("(+ 1 (/ valor 0)))"),variableStorage);
	   System.out.print(calc6.Result()+"\n");
	   System.out.print("Ingrese una operacion con comillas");
	   Calculator calc7 = new Calculator(tokenizer.equalParenthesis(scann.nextLine()),variableStorage);
	   System.out.print(calc7.Result()+"\n");
	   scann.close();
   }
}



package function;

import java.util.Stack;
import java.util.LinkedList;

/**
 *
 * @author marti
 */
public class Operations {
    float Final = 0 ;
    Stack<String> Stack;
    boolean Comparison_operation = false;
    String OPType = "None";
  
    public Operations(LinkedList<String> list){
        Stack = new Stack<String>();
        String[] Tokens = list.toArray(new String[list.size()]);
        for (int i=0;i<Tokens.length;i++){
          Stack.push(Tokens[i]);
          if(Tokens[i].equals(")")) Interpret(); 
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
    
    boolean Finalbool = true ;
 
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
}

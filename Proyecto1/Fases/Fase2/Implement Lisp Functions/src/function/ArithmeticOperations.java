import java.util.Stack;
import java.lang.Math;

/**
 *
 * @author marti
 */
public class Arithmetic_Operations {
    
    Stack<String> Stack;
    
    public Arithmetic_Operations(String[] Tokens){
        Stack = new Stack<String>();
        for (int i=0;i<Tokens.length;i++){
          Stack.push(Tokens[i]);
          if(Tokens[i].equals(")")) Interpret(); 
        }
    }
    public void Interpret(){
        String tok;
        Stack<String> EvaluateStack = new Stack<String>();
        tok = Stack.pop(); /* This is the ) character */
        while(!(tok=Stack.pop()).equals("(")){
          EvaluateStack.push(tok);
        }
        Evaluate(EvaluateStack);
    }
  
  public void Evaluate(Stack<String> callStack){
        String func = callStack.pop(); /* This is the operator or function */
        if(func.equals("+")) {
            float result = Plus(callStack);
            Stack.push(String.valueOf(result));
        }else if(func.equals("-")) {
            float result = Minus(callStack);
            Stack.push(String.valueOf(result));
        }else if(func.equals("*")) {
            float result = Multi(callStack);
            Stack.push(String.valueOf(result));
        }else if(func.equals("/")) {
            float result = Div(callStack);
            Stack.push(String.valueOf(result));
        }else if(func.equals("^")) {
            float result = Pow(callStack);
            Stack.push(String.valueOf(result));
        }
    }
  
    public float Plus(Stack<String> callStack){
        float a = Float.parseFloat(callStack.pop());
        float b = Float.parseFloat(callStack.pop());
        System.out.println(a+b);
        return(a+b);
    }
    public float Minus(Stack<String> callStack){
        float a = Float.parseFloat(callStack.pop());
        float b = Float.parseFloat(callStack.pop());
        System.out.println(a-b);
        return(a-b);
    }
    public float Multi(Stack<String> callStack){
        float a = Float.parseFloat(callStack.pop());
        float b = Float.parseFloat(callStack.pop());
        System.out.println(a*b);
        return(a*b);
    }
    public float Div(Stack<String> callStack){
        float a = Float.parseFloat(callStack.pop());
        float b = Float.parseFloat(callStack.pop());
        System.out.println(a/b);
        return(a/b);
    }
    public float Pow(Stack<String> callStack){
        float a = Float.parseFloat(callStack.pop());
        float b = Float.parseFloat(callStack.pop());
        System.out.println(Math.pow(a, b));
        return(Math.pow(a, b));
    }
  }

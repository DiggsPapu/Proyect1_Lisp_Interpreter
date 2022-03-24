package function;
import java.util.Stack;
import java.util.LinkedList;

/**
 *
 * @author marti
 */
public class Arithmetic_Operations {
    float Final = 0 ;
    Stack<String> Stack;
    
    public Arithmetic_Operations(LinkedList<String> list){
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
    public float Result(){
        return Final;
    }
  }


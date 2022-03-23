import java.util.Stack;

/**
 *
 * @author marti
 */
public class Artithmetic_Operations {
    
    Stack<String> Stack;
    
    public Artithmetic_Operations(String[] Tokens){
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
        String func = callStack.pop();
        if(func.equals("+")) {
            float result = Add(callStack);
            Stack.push(String.valueOf(result));
        }else if(func.equals("-")) {
            float result = Subs(callStack);
            Stack.push(String.valueOf(result));
        }else if(func.equals("*")) {
            float result = Multi(callStack);
            Stack.push(String.valueOf(result));
        }else if(func.equals("/")) {
            float result = Div(callStack);
            Stack.push(String.valueOf(result));
        }
    }
  
    public float Add(Stack<String> callStack){
        float a = Float.parseFloat(callStack.pop());
        float b = Float.parseFloat(callStack.pop());
        System.out.println(a+b);
        return(a+b);
    }
    public float Subs(Stack<String> callStack){
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
  }

package function;

import java.util.Stack;
import java.util.LinkedList;
    
/**
 *
 * @author marti
 */
public class Comparator {
    boolean Final = true ;
    Stack<String> Stack;
    
    public Comparator(LinkedList<String> list){
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
        if(func.equals(">")) {
            GreaterThan(callStack);
        }else if(func.equals("<")) {
            SmallerThan(callStack);
        }else if(func.equals("EQUAL")) {
            Equals(callStack);
        }
    }
  
    private boolean GreaterThan(Stack<String> callStack){
        float a = Float.parseFloat(callStack.pop());
        float b = Float.parseFloat(callStack.pop());
        if (a > b){
            Final = true;
        }else{
            Final = false;
        }
        return Final;
    }
    private boolean SmallerThan(Stack<String> callStack){
        float a = Float.parseFloat(callStack.pop());
        float b = Float.parseFloat(callStack.pop());
        if (a < b){
            Final = true;
        }else{
            Final = false;
        }
        return Final;
    }
    private boolean Equals(Stack<String> callStack){
        float a = Float.parseFloat(callStack.pop());
        float b = Float.parseFloat(callStack.pop());
        if (a == b){
            Final = true;
        }else{
            Final = false;
        }
        return Final;
    }
    

    public boolean Result(){
        return Final;
    }
}

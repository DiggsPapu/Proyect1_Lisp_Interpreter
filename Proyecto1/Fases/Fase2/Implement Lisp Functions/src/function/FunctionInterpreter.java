package function;

import java.util.LinkedList;

/**
 *
 * @author marti
 */

public class FunctionInterpreter {
    
    String NombreKey = "";    
    FunctionStorage FS = new FunctionStorage();
    
    FunctionInterpreter(){
        
    }
    public void add_Function(LinkedList<String> list){
        NombreKey = list.get(2);
        
        LinkedList <String> Result = new LinkedList<String>();

        int Counter = 3;
        int Counter2 = list.size()-5;
        int Counter3 = 0;
        int Counter4 = 0;
        // Add Arguments
        
        while(Counter2 > 0){
            if(list.get(Counter2+4).equals("(")){
                Counter3 ++;
            }
            Counter2 --;
        }
        
        while(Counter3 > 0){
            if(list.get(Counter).equals(")")){
                Counter3 --;
            }
            Result.add(list.get(Counter));
            Counter++ ;
        }
        // Add Number of Arguments
        while(!list.get(Counter4).equals(")") ){
            Counter4++ ;
        }
        Result.addFirst(String.valueOf(Counter4-4));

        // Add Instructions
        while(!list.get(Counter).equals(")") ){
            Result.add(list.get(Counter));
            Counter++ ;
        }
        Result.add(list.get(Counter));
        
        FS.storeNewFunction(NombreKey, Result);
    }   
    public LinkedList<String> getInstructions(String Key){
        return FS.getData(Key);
    }
}

// ( DEFUN nombre ( arg1 arg2 arg3)( + arg1 (* arg2(/ arg4(+ arg6 arg3 )))))
// ( DEFUN nombre ( arg1 arg2 arg4 arg5)( + arg1 arg2))

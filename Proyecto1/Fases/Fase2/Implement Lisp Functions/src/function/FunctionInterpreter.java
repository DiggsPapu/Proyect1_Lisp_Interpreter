package function;

import java.util.LinkedList;

/**
 *
 * @author marti
 */

public class FunctionInterpreter {
    
    String NombreKey = "";    
    FunctionStorage FS = new FunctionStorage();
    
    FunctionInterpreter(LinkedList<String> list){
        NombreKey = list.get(2);
        
        LinkedList <String> Result = new LinkedList <String>();

        int Counter = 4;
        // Add Arguments
        while(!list.get(Counter).equals(")") ){
            Result.add(list.get(Counter));
            Counter++ ;
        }
        // Add Numbre of Arguments
        Result.addFirst(String.valueOf(Result.size()));
        Counter++;
        // Add Instructions
        while(!list.get(Counter).equals(")") ){
            Result.add(list.get(Counter));
            Counter++ ;
        }
        
        String[] Tokens = Result.toArray(new String[Result.size()]);
        FS.storageNewFunction(NombreKey, Tokens);
    }   
    public String[] getInstructions(String Key){
        return FS.getData(Key);
    }
}

// ( DEFUN nombre ( arg1 arg2 )( + arg1 arg2 ))

package function;

import java.util.LinkedList;

/**
 *
 * @author marti
 */

  
  
public class Functionality_Operators {

    LinkedList<String> Atom = new LinkedList<String>();
    
    Functionality_Operators(){
        
    }
    public void create_Atom(LinkedList<String> list){
        if(list.size() == 5){
            System.out.println("Error: ATOM fue definido como CONS (No debe tener dos variables)");
        }else{
            Atom = list;
        }
    }
    public LinkedList<String> getAtom(){
        return Atom;
    }
}
//ejemplo (ATOM 1 2 3 4 5)
//ejemplo (ATOM 1)
//ejemplo (ATOM 1 2)
  

    private FunctionStorage functionStorage;
    private VariableStorage variableStorage;
   
   
    
    private static Object SaveOperands(LinkedList<String> list, FunctionStorage functionStorage, VariableStorage variableStorage){
        if(list.get(1).equals("COND")&& list.get(0).equals("(") && list.get(list.size()-1).equals(")")) {
            LinkedList <String> Temp = new LinkedList <String>();
                int Counter = 3;
            while(!list.get(Counter).equals(")") ){
                Temp.add(list.get(Counter));
                Counter++ ;
            }
            Temp.add(list.get(Counter));

        Operations OP = new Operations(Temp);

        if(OP.getOpType() == ">"){

            //Si la lista matches el patron de que sea un nombre valido entonces devolver el valor almacenado
            if(list.get(Counter).matches("[a-zA-Z][a-zA-Z0-9]*") && variableStorage.getVariableStorage().containsKey(list.get(Counter))){
                System.out.print("El valor almacenado es: "+variableStorage.getVariableStorage().get(list.get(Counter))+"\n");
                return variableStorage.getVariableStorage().get(list.get(Counter)); //Retorna el valore almacenado del almacenamiento de variables


            }else if(list.get(Counter).matches("[a-zA-Z][a-zA-Z0-9]*") && !variableStorage.getVariableStorage().containsKey(list.get(Counter))){
                System.out.print("Devolvio nulo");
                return null;

            }else if (list.get(Counter).equals("(") && list.get(Counter+1).matches("[\\+\\-\\*\\^\\/]")){
                LinkedList <String> Temp1 = new LinkedList <String>();
                Operations tempOP = new Operations(Temp1);
                //return Operations.Result();


            }else if (list.get(Counter).equals("(") && list.get(Counter+1).matches("[<>.]")){
                LinkedList <String> Temp1 = new LinkedList <String>();
                Operations tempOP = new Operations(Temp1);
                //return Operations.ResultComp();

            }

        }else if (OP.getOpType() == "<"){

        }else if(OP.getOpType() == "="){

        }else{
            System.out.print("Error de Operando de comparación");      
        }
    }
}

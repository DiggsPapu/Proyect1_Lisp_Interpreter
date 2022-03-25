package function;

import java.util.LinkedList;

/**
 *
 * @author marti
 */

  
  
public class Functionality_Operators {
    
    LinkedList<String> Atom = new LinkedList<String>();
    LinkedList<String> Cons = new LinkedList<String>();
    
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
    public void create_Cons(LinkedList<String> list){
        if(list.size() != 5){
            System.out.println("Error: CONS fue definido como ATOM (No debe tener mas o menos de dos variables)");
        }else{
            Cons = list;
        }
    }
    public LinkedList<String> getCons(){
        return Cons;
    }
}
//ejemplo (ATOM 1 2 3 4 5)
//ejemplo (ATOM 1)
//ejemplo (ATOM 1 2)

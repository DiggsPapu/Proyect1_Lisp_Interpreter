import java.util.LinkedList;


/**
 *
 * @author marti
 */
public class Functionality_Operators {
    public Functionality_Operators(){
    }
    private static Object Result(LinkedList<String> list){
        if(list.get(1).equals("COND")&& list.get(0).equals("(") && list.get(list.size()-1).equals(")")) {
            LinkedList <String> Temp = new LinkedList <String>();
                int Counter = 3;
                while(!list.get(Counter).equals(")") ){
                    Temp.add(list.get(Counter));
                    Counter++ ;
                }
                Temp.add(list.get(Counter));
               
            Operations OP = new Operations(Temp);
            System.out.println(OP.ResultComp());
            if(OP.ResultComp() == true){
                
            }
        }
        return list;
    }
}

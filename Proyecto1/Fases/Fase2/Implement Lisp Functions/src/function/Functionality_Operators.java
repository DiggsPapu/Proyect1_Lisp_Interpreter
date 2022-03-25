package function;

import java.util.LinkedList;

/**
 *
 * @author marti
 */

  
  //Es el cond
public class Functionality_Operators {

    private FunctionStorage functionStorage;
    private VariableStorage variableStorage;
    
    
   
    
    public static Object SaveOperands(LinkedList<String> list, FunctionStorage functionStorage, VariableStorage variableStorage){
        if(list.get(1).equals("COND")&& list.get(0).equals("(") && list.get(list.size()-1).equals(")")) {
            //Esta lista es para generar un temporal para poder hacer las operaciones booleanas para averiguar si es true el if
        	System.out.print("Entro a saveOperands\n");
        	LinkedList <String> Temp = new LinkedList <String>();
            int Counter = 3;
            while(!list.get(Counter).equals(")") ){
                Temp.add(list.get(Counter));
                Counter++ ;
            }
            //adds el parentesis cerrado
            Temp.add(list.get(Counter));
            System.out.print("Temp0: "+ Temp+"\n");
            //Si esta bien escrito ahora esta en la posicion de la ejecucion del cond
            Counter++;
            //Si se paso del size de la lista es error 
            if (Counter>=list.size()) {
            	return null;
            }
            
            Operations OP = new Operations(Temp);
            System.out.print("El valor booleano del primer if es: "+ OP.ResultComp()+"\n");
            //Moverse al siguiente else if
            int oparenthesis = 1;
            int cparenthesis = 0;
            int contador = 3;
            LinkedList<String> prueba = new LinkedList<String>();
            while (contador<list.size() && oparenthesis!=cparenthesis) {
            	if (list.get(contador).equals("(")) {
            		oparenthesis++;
            		
            	}else if (list.get(contador).equals(")")) {
            		cparenthesis++;
            	}
            	prueba.add(list.get(contador));
            	System.out.print("Contador opar: "+ oparenthesis+"\n");
            	System.out.print("Contador cpar: "+ cparenthesis+"\n");

            	System.out.print(contador+" "+prueba+"\n\n");
            	contador++;
            	
            }
            //Obtener el booleano del else if
            LinkedList <String> Temp1 = new LinkedList <String>();
            int Counter1 = contador+1;
            while(!list.get(Counter1).equals(")") ){
                Temp1.add(list.get(Counter1));
                Counter1++ ;
            }
            Temp1.add(list.get(Counter1));
            System.out.print("Temp1: "+ Temp1+"\n");
            Operations OP1 = new Operations(Temp1);
            System.out.print("El valor booleano del else if es: "+ OP1.ResultComp()+"\n");
            
            
            //(COND ((< 2 3) (+ 0 9)) ((> 233 4) 9) (t (+ 1 2)) )
            
            
            
            
	        //Si el primer if del cond is true
	        if(OP.ResultComp()){
	        	System.out.print("Ingreso al if\n");
	        	
	        	int op = 1;
	        	int cp = 0;
	        	LinkedList<String> ifList = new LinkedList<String>();
	        	ifList.add(list.get(Counter));
	        	Counter++;
	        	while (op!=cp && Counter<list.size()) {
	        		if (list.get(Counter).equals("(")) {
	            		op++;
	            		
	            	}else if (list.get(Counter).equals(")")) {
	            		cp++;
	            	}
	            	ifList.add(list.get(Counter));
	            	System.out.print("Contador opar: "+ op+"\n");
	            	System.out.print("Contador cpar: "+ cp+"\n");
	            	System.out.print(Counter+" "+ifList+"\n\n");
	            	Counter++;
	        	}
	        	System.out.print("IfList: "+ ifList+"\n");
	        	for (int k = 0 ; k < ifList.size() ; k++) {
	        		//Si la lista matches el patron de que sea un nombre valido entonces devolver el valor almacenado
	        		System.out.print("IfList: "+ ifList+"\n");
		        	System.out.print(ifList.get(k).equals("(") && ifList.get(k+1).matches("[\\+\\-\\*\\^\\/]"));
	        		if(ifList.get(k).matches("[a-zA-Z][a-zA-Z0-9]*") && variableStorage.getVariableStorage().containsKey(ifList.get(k))){
		                System.out.print("El valor almacenado es: "+variableStorage.getVariableStorage().get(ifList.get(k))+"\n");
		                return variableStorage.getVariableStorage().get(ifList.get(k)); //Retorna el valore almacenado del almacenamiento de variables
		
		
		            }else if(ifList.get(k).matches("[a-zA-Z][a-zA-Z0-9]*") && !variableStorage.getVariableStorage().containsKey(ifList.get(k))){
		                System.out.print("Devolvio nulo");
		                return null;
		
		            }else if (ifList.get(k).equals("(") && ifList.get(k+1).matches("[\\+\\-\\*\\^\\/]")){
		                LinkedList <String> temp = new LinkedList <String>();
		                Operations tempOP = new Operations(ifList);
		                System.out.print("\nEl resultado es: "+tempOP.Result());
		                return tempOP.Result();
		
		
		            }else if (ifList.get(k).equals("(") && ifList.get(k+1).matches("[<>.]")){
		                LinkedList <String> temp = new LinkedList <String>();
		                Operations tempOP = new Operations(ifList);
		                System.out.print("\nLa desigualdad es: "+ tempOP.ResultComp());
		                return tempOP.ResultComp();
		
		            }else if (ifList.get(k).equals("(") && ifList.get(k+1).matches("[\\+\\-]?[\\d]+[\\.\\d]*")){
		                System.out.print(ifList.get(k+1));
		                return ifList.get(k+1);
		
		            }
	        	}
	
	            
	        //Para saber si el segundo if del cond is true
	        	
	        	 //(COND ((> 2 3) (+ 0 9)) ((> 233 4) (9)) (t (+ 1 2)) )
	        }else if (OP1.ResultComp()){
	        	
	        	System.out.print("Ingreso al else if\n");
	        	
	        	int op = 1;
	        	int cp = 0;
	        	LinkedList<String> ifList = new LinkedList<String>();
	        	
	        	ifList.add(list.get(Counter1));
	        	Counter1++;
	        	while (op!=cp && Counter1<list.size()) {
	        		if (list.get(Counter1).equals("(")) {
	            		op++;
	            		
	            	}else if (list.get(Counter1).equals(")")) {
	            		cp++;
	            	}
	            	ifList.add(list.get(Counter1));
	            	System.out.print("Contador opar: "+ op+"\n");
	            	System.out.print("Contador cpar: "+ cp+"\n");
	            	System.out.print(Counter1+" "+ifList+"\n\n");
	            	Counter1++;
	        	}
	        	System.out.print("IfList: "+ ifList+"\n");
	        	for (int k = 0 ; k < ifList.size() ; k++) {
	        		//Si la lista matches el patron de que sea un nombre valido entonces devolver el valor almacenado
	        		System.out.print("IfList: "+ ifList+"\n");
		        	System.out.print(ifList.get(k).equals("(") && ifList.get(k+1).matches("[\\+\\-\\*\\^\\/]"));
	        		if(ifList.get(k).matches("[a-zA-Z][a-zA-Z0-9]*") && variableStorage.getVariableStorage().containsKey(ifList.get(k))){
		                System.out.print("El valor almacenado es: "+variableStorage.getVariableStorage().get(ifList.get(k))+"\n");
		                return variableStorage.getVariableStorage().get(ifList.get(k)); //Retorna el valore almacenado del almacenamiento de variables
		
		
		            }else if(ifList.get(k).matches("[a-zA-Z][a-zA-Z0-9]*") && !variableStorage.getVariableStorage().containsKey(ifList.get(k))){
		                System.out.print("Devolvio nulo");
		                return null;
		
		            }else if (ifList.get(k).equals("(") && ifList.get(k+1).matches("[\\+\\-\\*\\^\\/]")){
		                LinkedList <String> temp = new LinkedList <String>();
		                Operations tempOP = new Operations(ifList);
		                System.out.print(tempOP.Result());
		                return tempOP.Result();
		
		
		            }else if (ifList.get(k).equals("(") && ifList.get(k+1).matches("[<>.]")){
		                LinkedList <String> temp = new LinkedList <String>();
		                Operations tempOP = new Operations(ifList);
		                System.out.print("\nEl desigualdad es: "+ tempOP.ResultComp());
		                return tempOP.ResultComp();
		
		            }
	        	}
//	        //En el caso de que el else aplique dado que tiene la t de true.
//	        }else if(list.get(Counter+2).equals("t")){
//	        	//Si la lista matches el patron de que sea un nombre valido entonces devolver el valor almacenado
//	            if(list.get(Counter).matches("[a-zA-Z][a-zA-Z0-9]*") && variableStorage.getVariableStorage().containsKey(list.get(Counter))){
//	                System.out.print("El valor almacenado es: "+variableStorage.getVariableStorage().get(list.get(Counter))+"\n");
//	                return variableStorage.getVariableStorage().get(list.get(Counter)); //Retorna el valore almacenado del almacenamiento de variables
//	
//	
//	            }else if(list.get(Counter).matches("[a-zA-Z][a-zA-Z0-9]*") && !variableStorage.getVariableStorage().containsKey(list.get(Counter))){
//	                System.out.print("Devolvio nulo");
//	                return null;
//	
//	            }else if (list.get(Counter).equals("(") && list.get(Counter+1).matches("[\\+\\-\\*\\^\\/]")){
//	                LinkedList <String> Temp1 = new LinkedList <String>();
//	                Operations tempOP = new Operations(Temp1);
//	                System.out.print(tempOP.Result());
//	                return tempOP.Result();
//	
//	
//	            }else if (list.get(Counter).equals("(") && list.get(Counter+1).matches("[<>.]")){
//	                LinkedList <String> Temp1 = new LinkedList <String>();
//	                Operations tempOP = new Operations(Temp1);
//	                System.out.print(tempOP.Result());
//	                return tempOP.ResultComp();
//	
//	            }
	        	
	        }else{
	            System.out.print("Error de Operando de comparacion");   
	            return null;
	        }
    }
		return null;
}}
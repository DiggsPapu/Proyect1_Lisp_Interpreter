package function;

import java.util.LinkedList;

/**
 *
 * @author marti
 */

  
  //Es el cond
public class Cond {

   
    
   
    
    public static Object SaveOperands(LinkedList<String> list, FunctionStorage functionStorage, VariableStorage variableStorage){
        if(list.get(1).equals("COND")&& list.get(0).equals("(") && list.get(list.size()-1).equals(")")) {
            //Esta lista es para generar un temporal para poder hacer las operaciones booleanas para averiguar si es true el if
        	System.out.print("Entro a saveOperands\n");
        	LinkedList <String> Temp = new LinkedList <String>();
            int Counter = 3;
            while(!list.get(Counter).equals(")") ){
                Temp.add(list.get(Counter));
                if (Temp.getLast().equals("EQUAL")) {
                	Temp.removeLast();
                	Temp.add("=");
                }
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
            	if (prueba.getLast().equals("EQUAL")) {
            		prueba.removeLast();
            		prueba.add("=");
            	}
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
                if (Temp1.getLast().equals("EQUAL")) {
            		Temp1.removeLast();
            		Temp1.add("=");
            	}
                Counter1++ ;
            }
            Temp1.add(list.get(Counter1));
            System.out.print("Temp1: "+ Temp1+"\n");
            Operations OP1 = new Operations(Temp1);
            System.out.print("El valor booleano del else if es: "+ OP1.ResultComp()+"\n");
            
            
          //Moverse al  else
            int Counter2 = contador+1;
            LinkedList<String> prueba2 = new LinkedList<String>();
            int opa = 1;
            int cpa = 0;		
            while (Counter2<list.size() && opa!=cpa) {
            	if (list.get(Counter2).equals("(")) {
            		opa++;
            		
            	}else if (list.get(Counter2).equals(")")) {
            		cpa++;
            	}
            	prueba2.add(list.get(Counter2));
            	if (prueba2.getLast().equals("EQUAL")) {
            		prueba2.removeLast();
            		prueba2.add("=");
            	}
            	System.out.print("Contador opa: "+ opa+"\n");
            	System.out.print("Contador cpa: "+ cpa+"\n");

            	System.out.print(Counter2+" "+prueba2+"\n\n");
            	Counter2++;
            	
            }
           
            LinkedList<String> prueba3 = new LinkedList<String>();
            int opae = 1;
            int cpae = 0;
            prueba3.add(list.get(Counter2));
            Counter2++;
            while (Counter2<list.size() && opae!=cpae) {
            	if (list.get(Counter2).equals("(")) {
            		opae++;
            		
            	}else if (list.get(Counter2).equals(")")) {
            		cpae++;
            	}
            	prueba3.add(list.get(Counter2));
            	if(prueba3.getLast().matches("[a-zA-Z][a-zA-Z0-9]*") && variableStorage.getVariableStorage().containsKey(prueba3.getLast())){
	                prueba3.add(variableStorage.getVariableStorage().get(prueba3.getLast()));
	                prueba3.remove(prueba3.size()-2);
        		}else if (prueba3.getLast().equals("EQUAL")) {
            		prueba3.removeLast();
            		prueba3.add("=");
            	}
            	
            	System.out.print("Contador opae: "+ opae+"\n");
            	System.out.print("Contador cpae: "+ cpae+"\n");

            	System.out.print(Counter2+" "+prueba3+"\n\n");
            	Counter2++;
            	
            }
            
            
            //(SETQ VALUE( + 2 (* 3 (+ 4 7))))
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
	            	
	            	if(ifList.getLast().matches("[a-zA-Z][a-zA-Z0-9]*") && variableStorage.getVariableStorage().containsKey(ifList.getLast())){
		                ifList.add(variableStorage.getVariableStorage().get(ifList.getLast()));
		                ifList.remove(ifList.size()-2);
	        		}else if(ifList.getLast().equals("EQUAL")) {
	        			ifList.removeLast();
	        			ifList.add("=");
	        		}
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
		                System.out.print("No es un valor valido");
		                return null;
		
		            }else if (ifList.get(k).equals("(") && ifList.get(k+1).matches("[\\+\\-\\*\\^\\/]")){
		                Operations tempOP = new Operations(ifList);
		                System.out.print("\nEl resultado es: "+tempOP.Result());
		                return tempOP.Result();
		
		
		            }else if (ifList.get(k).equals("(") && ifList.get(k+1).matches("[<>.]")||ifList.get(k+1).equals("=")){
		            	if(ifList.get(k).matches("[a-zA-Z][a-zA-Z0-9]*")){
			                return null;
		        		}
		            	Operations tempOP = new Operations(ifList);
		                System.out.print("\nLa desigualdad es: "+ tempOP.ResultComp());
		                return tempOP.ResultComp();
		
		            }else if (ifList.get(k).equals("(") && ifList.get(k+1).matches("[\\+\\-]?[\\d]+[\\.\\d]*")){
		                System.out.print(ifList.get(k+1));
		                return ifList.get(k+1);
		
		            }
	        	}
	
	            
	        //Para saber si el segundo if del cond is true
	        	//(SETQ VALUE( + 2 (* 3 (+ 4 7))))
	        	 //(COND ((> 2 3) (+ 0 9)) ((> 233 4) (9)) (t (+ 1 2)) )
	        	//(COND ((EQUAL 2 3) (EQUAL 0 9)) ((> 233 4) (9)) (t (+ 1 2)) )
	        }else if (OP1.ResultComp()){
	        	
	        	System.out.print("Ingreso al else if\n");
	        	
	        	int op = 1;
	        	int cp = 0;
	        	LinkedList<String> ifList = new LinkedList<String>();

	        	Counter1++;
	        	ifList.add(list.get(Counter1));
	        	Counter1++;
	        	while (op!=cp && Counter1<list.size()) {
	        		if (list.get(Counter1).equals("(")) {
	            		op++;
	            		
	            	}else if (list.get(Counter1).equals(")")) {
	            		cp++;
	            	}
	            	ifList.add(list.get(Counter1));
	            	if(ifList.getLast().matches("[a-zA-Z][a-zA-Z0-9]*") && variableStorage.getVariableStorage().containsKey(ifList.getLast())){
		                ifList.add(variableStorage.getVariableStorage().get(ifList.getLast()));
		                ifList.remove(ifList.size()-2);
	        		}else if (prueba.getLast().equals("EQUAL")) {
	            		ifList.removeLast();
	            		ifList.add("=");
	            	}
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
		                System.out.print("No es un valor valido");
		                return null;
		
		            }else if (ifList.get(k).equals("(") && ifList.get(k+1).matches("[\\+\\-\\*\\^\\/]")){
		                Operations tempOP = new Operations(ifList);
		                System.out.print("\nEl resultado es: "+tempOP.Result());
		                return tempOP.Result();
		
		
		            }else if (ifList.get(k).equals("(") && ifList.get(k+1).matches("[<>.]")||ifList.get(k+1).equals("EQUAL")){
		            	if(ifList.get(k).matches("[a-zA-Z][a-zA-Z0-9]*")){
			                return null;
		        		}else if(ifList.get(k).equals("EQUAL")) {
		        			ifList.add(k, "=");
		        			ifList.remove(k+1);
		        		}
		            	Operations tempOP = new Operations(ifList);
		                System.out.print("\nLa desigualdad es: "+ tempOP.ResultComp());
		                return tempOP.ResultComp();
		
		            }else if (ifList.get(k).equals("(") && ifList.get(k+1).matches("[\\+\\-]?[\\d]+[\\.\\d]*")){
		                System.out.print(ifList.get(k+1));
		                return ifList.get(k+1);
		
		            }
	        	}
	        	
	       	//(SETQ VALUE( + 2 (* 3 (+ 4 7))))
	        //(COND ((> 2 3) (+ 0 9)) ((< 233 4) (9)) (t (+ 1 2)) )
	        }else if(prueba3.get(1).equals("t") && prueba3.get(0).equals("(") && prueba3.get(prueba3.size()-1).equals(")")){
	        	
	        	System.out.print("Ingreso al else\n");
	        	prueba3.remove();
	        	prueba3.remove();
	        	prueba3.removeLast();
	        	
	        	for (int k = 0 ; k < prueba3.size() ; k++) {
	        		if(prueba3.get(k).equals("EQUAL")) {
	        			prueba3.add(k, "=");
	        			prueba3.remove(k+1);
	        		}
	        		//Si la lista matches el patron de que sea un nombre valido entonces devolver el valor almacenado
	        		System.out.print("prueba3: "+ prueba3+"\n");
		        	System.out.print(prueba3.get(k).equals("(") && prueba3.get(k+1).matches("[\\+\\-\\*\\^\\/]"));
	        		if(prueba3.get(k).matches("[a-zA-Z][a-zA-Z0-9]*") && variableStorage.getVariableStorage().containsKey(prueba3.get(k))){
		                System.out.print("El valor almacenado es: "+variableStorage.getVariableStorage().get(prueba3.get(k))+"\n");
		                return variableStorage.getVariableStorage().get(prueba3.get(k)); //Retorna el valore almacenado del almacenamiento de variables
		
		
		            }else if(prueba3.get(k).matches("[a-zA-Z][a-zA-Z0-9]*") && !variableStorage.getVariableStorage().containsKey(prueba3.get(k))){
		                System.out.print("No es un valor valido");
		                return null;
		
		            }else if (prueba3.get(k).equals("(") && prueba3.get(k+1).matches("[\\+\\-\\*\\^\\/]")){
		                Operations tempOP = new Operations(prueba3);
		                System.out.print("\nEl resultado es: "+tempOP.Result());
		                return tempOP.Result();
		
		
		            }else if (prueba3.get(k).equals("(") && prueba3.get(k+1).matches("[<>.]")||prueba3.get(k+1).equals("=")){
		            	if(prueba3.get(k).matches("[a-zA-Z][a-zA-Z0-9]*")){
			                return null;
		        		}
		            	
		            	Operations tempOP = new Operations(prueba3);
		                System.out.print("\nLa desigualdad es: "+ tempOP.ResultComp());
		                return tempOP.ResultComp();
		
		            }else if (prueba3.get(k).equals("(") && prueba3.get(k+1).matches("[\\+\\-]?[\\d]+[\\.\\d]*")){
		                System.out.print(prueba3.get(k+1));
		                return prueba3.get(k+1);
		
		            }
	        	}
	        	
	        }else{
	            System.out.print("Error de Operando de comparacion");   
	            return null;
	        }
    }
		return null;
}}

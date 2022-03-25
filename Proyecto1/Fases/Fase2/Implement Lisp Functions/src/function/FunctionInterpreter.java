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
    public LinkedList<String> prepareInstructions(LinkedList<String> lista, VariableStorage variableStorage){
    	//Se guardan las instrucciones temporalmente
    	LinkedList<String> instrucciones = getInstructions(lista.get(1));
    	int paramCounter = 0;
    	LinkedList<String> parameters = new LinkedList<String>();
    	//Porque desde que esta el nombre de la funcion hasta que termina la lista solo hay parametros
    	for (int k = 2; k<lista.size()-1 ; k++) {
    		
    		//Imprimir el valor de k para saber en donde estamos
    		System.out.print("K:"+k+"\n");
    		//Para imprimir que token de la lista
    		System.out.print(lista.get(k)+"\n");
    		parameters.add(lista.get(k));
    		//En el caso de que sea un parentesis
    		if (lista.get(k).equals("(")) {
    			//Contadores de abierto y cerrado
    			int op =1;
    			int cp =0;
    			//Lista para almacenar todos los valores entre el parentesis
    			LinkedList<String> ifList = new LinkedList<String>();
    			//Add tokens to the list parenthesis
	        	ifList.add(lista.get(k));
	        	
	        	k++;
	        	while (op!=cp && k<lista.size()) {
	        		if (lista.get(k).equals("(")) {
	            		op++;
	            		
	            	}else if (lista.get(k).equals(")")) {
	            		cp++;
	            	}
	        		
	            	ifList.add(lista.get(k));
	            	
	            	if(ifList.getLast().matches("[a-zA-Z][a-zA-Z0-9]*") && variableStorage.getVariableStorage().containsKey(ifList.getLast())){
		                ifList.add(variableStorage.getVariableStorage().get(ifList.getLast()));
		                ifList.remove(ifList.size()-2);
	        		}else if(ifList.getLast().equals("EQUAL")) {
	        			ifList.removeLast();
	        			ifList.add("=");
	        		}
	            	System.out.print(k+" "+ifList+"\n\n");
	            	
	            	k++;
	        	}
	        	//Add the (
	        	parameters.removeLast();
	        	parameters.add(ifList.toString());
	        	k--;
    		}
    		
    		paramCounter++;
    		System.out.print("Counter: "+ paramCounter+"\n");
    		System.out.print("Parametros: "+ parameters+"\n");
    	}
    	System.out.print(paramCounter);
    	if (String.valueOf(paramCounter).equals(instrucciones.get(0))) {
    		System.out.print("Ingreso a la correccion de instrucciones\n");
    		//Copia de instrucciones
    		LinkedList<String> copy= new LinkedList<String>(instrucciones);
    		//Sin cantidad de variables y sin las variables
    		System.out.print("Original: "+copy+"\n");
    		int co=0;
    		while (!instrucciones.get(co).equals(")")){
				System.out.print(copy);
				copy.remove();
				co++;
			}
    		copy.removeFirst();
    		copy.removeLast();
    		System.out.print("\nPrimera correccion: "+copy+"\n");
    		if (paramCounter==1) {
    			for (int k =0; k<copy.size();k++) {
    				
    				if(instrucciones.get(2).equals(copy.get(k))) {
    					System.out.print(copy.get(k)+" "+ copy.get(k+1));
    					copy.add(k, parameters.get(0));
    					copy.remove(k+1);
    				}
    			}
    			
    			
    			
    			
    			
    			
    			
    			
    			
    		}else if (paramCounter==2) {
    			for (int k =0; k<copy.size();k++) {
    				System.out.print("Primera correccion: "+copy+"\n");
    				System.out.print(k+" "+ copy.get(k)+"\n");
    				System.out.print(instrucciones);
    				if(instrucciones.get(2).equals(copy.get(k))) {
    					System.out.print("Param 1: "+ instrucciones.get(2)+" Copy: "+ copy.get(k));
    					System.out.print(" "+copy.get(k-1)+" "+copy.get(k)+" "+ copy.get(k+1)+"\n");
    					
    					copy.add(k, parameters.get(0));
    					System.out.print("Instrucciones cuando se les agrega el reemplazo: "+ copy);
    					copy.remove(k+1);
    					System.out.print("Instrucciones cuando se les quita el reemplazo: "+ copy+"\n");
    				}else if (instrucciones.get(3).equals(copy.get(k))) {
    					System.out.print("Param 2: "+ instrucciones.get(3)+" Copy: "+ copy.get(k));
    					System.out.print(" "+copy.get(k-1)+" "+copy.get(k)+" "+ copy.get(k+1)+"\n");
    					copy.add(k, parameters.get(1));
    					copy.remove(k+1);
    				}
    			}
    			
    			
    			
    			
    			
    			
    			
    			
    			
    		}else if (paramCounter==3) {
    			for (int k =0; k<copy.size();k++) {
    				if(instrucciones.get(2).equals(copy.get(k))) {
    					copy.add(k, parameters.get(0));
    					copy.remove(k+1);
    				}else if(instrucciones.get(3).equals(copy.get(k))) {
    					copy.add(k, parameters.get(1));
    					copy.remove(k+1);
    				}else if(instrucciones.get(4).equals(copy.get(k))) {
    					copy.add(k, parameters.get(2));
    					copy.remove(k+1);
    				}
    			}
    		}else if (paramCounter==4) {
    			for (int k =0; k<copy.size();k++) {
    				if(instrucciones.get(2).equals(copy.get(k))) {
    					copy.add(k, parameters.get(0));
    					copy.remove(k+1);
    				}else if(instrucciones.get(3).equals(copy.get(k))) {
    					copy.add(k, parameters.get(1));
    					copy.remove(k+1);
    				}else if(instrucciones.get(4).equals(copy.get(k))) {
    					copy.add(k, parameters.get(2));
    					copy.remove(k+1);
    				}else if(instrucciones.get(5).equals(copy.get(k))) {
    					copy.add(k, parameters.get(3));
    					copy.remove(k+1);
    				}
    			}
    		}else if (paramCounter==5) {
    			for (int k =0; k<copy.size();k++) {
    				
    				if(instrucciones.get(2).equals(copy.get(k))) {
    					copy.add(k, parameters.get(0));
    					copy.remove(k+1);
    				}else if(instrucciones.get(3).equals(copy.get(k))) {
    					copy.add(k, parameters.get(1));
    					copy.remove(k+1);
    				}else if(instrucciones.get(4).equals(copy.get(k))) {
    					copy.add(k, parameters.get(2));
    					copy.remove(k+1);
    				}else if(instrucciones.get(5).equals(copy.get(k))) {
    					copy.add(k, parameters.get(3));
    					copy.remove(k+1);
    				}else if(instrucciones.get(6).equals(copy.get(k))) {
    					copy.add(k, parameters.get(4));
    					copy.remove(k+1);
    				}
    			}
    		}
    		else {
    			System.out.print("No es valido");
    		}
    		System.out.print("\nListo para entrar en el interprete: "+copy+"\n");
    		return copy;
    	}else {
    		return null;
    	}
    }
}

// ( DEFUN nombre ( arg1 arg2 arg3)( + arg1 (* arg2(/ arg4(+ arg6 arg3 )))))
// ( DEFUN nombre ( arg1 arg2 arg4 )( + arg1 (* arg2 arg4)))
//( DEFUN nombre ( arg1 arg2 )( + arg1 (* arg2 3)))
//(nombre (+ 1 2) (3) )
//(nombre (+ 1 2) (3) 4 )

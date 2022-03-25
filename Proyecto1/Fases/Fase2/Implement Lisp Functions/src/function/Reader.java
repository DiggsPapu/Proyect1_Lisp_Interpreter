package function;

import java.util.LinkedList;
import java.util.Scanner;

public class Reader {
	private FunctionStorage functionStorage;
	private VariableStorage variableStorage;
	
	protected static final String LETTER = "[a-zA-Z]";
	protected static final String LITERAL = "[a-zA-Z0-9]+";
	protected static final String VALID_NAME = "[a-zA-Z][a-zA-Z0-9]*";
	protected static final String WHIESPACE = "[\\s]+";
	protected static final String NUMERIC_ATOM = "[\\+\\-]?[\\d]+[\\.\\d]*";
	protected static final String OPERATIONS =  "[\\+\\-\\*\\^\\/\\<\\>]?";
	protected static final String SYMBOL = "[().]";
	protected static final String QUOTATION = "[\"]";
        protected static final String CONS = "CONS";
	protected static final String ATOM = "ATOM";
	protected static final String LIST = "LIST";
	protected static final String SETQ = "SETQ";
	protected static final String QUOTE = "QUOTE";
	protected static final String DEFUN = "DEFUN";
	protected static final String COND = "COND";
	protected static final String EQUAL = "EQUAL";
		
	public FunctionStorage getFunctionStorage() {
		return functionStorage;
	}

	public void setFunctionStorage(FunctionStorage functionStorage) {
		this.functionStorage = functionStorage;
	}

	public VariableStorage getVariableStorage() {
		return variableStorage;
	}

	public void setVariableStorage(VariableStorage variableStorage) {
		this.variableStorage = variableStorage;
	}
	
	public Reader() {
		this.functionStorage = new FunctionStorage();
		this.variableStorage = new VariableStorage();
	}
		
	public LinkedList <String> tokenize(String command){
		int i = 0;
		LinkedList <String> tokens = new LinkedList <String>();
		if ( command.length() == 1 ){
			System.out.print("Error, ingresado no valido");
			return null;
		}
		while (  i < command.length() ){
			int j = i + 1;
			if ( command.substring(i, j).matches(LETTER) || command.substring(i, j).matches(NUMERIC_ATOM)||command.substring(i, j).matches(QUOTATION)||command.substring(i, j).matches(OPERATIONS) ){
				while ( command.substring(i,j + 1).matches(LITERAL) || command.substring(i, j + 1).matches(NUMERIC_ATOM) ){
					j++;
				}
				tokens.add(command.substring(i,j).trim());
			} else if ( command.substring(i, j).matches(SYMBOL) ){
				tokens.add(command.substring(i,j).trim());
			}
			i = j;
		}
		System.out.print("Token Size:"+ String.valueOf(tokens.size())+"\n" );
		return tokens;
	}
	
	
	
	
	
	public void caseReader(String scan) {
		LinkedList<String> array = new LinkedList<String>();
		array = tokenize(scan);
		switch (getCase(array)) {
		case 1:{
			//Quote just prints the instructions
			for (int k = 2; k < array.size()-1 ; k++) {
				System.out.print(array.get(k)+" ");
			}
			break;
		}
		case 2: {
			break;
		}
		case 3:{
			System.out.print("Entro a setq\n");
			caseSETQ(array);
			break;
		}
		case 4:{
			break;
		}
		case 5:{
			caseList(array);
			break;
		}
		case 6:{
//			caseEqual(array);
			break;
		}
        case 7:{
			System.out.print("Entro a cond");
			Cond.SaveOperands(array, functionStorage, variableStorage);

        	break;
        }
		case 8:{
			break;
		}
		case 9:{
			break;
		}
		default:{
			break;
		}
		
		}
	}
	
	
	
	
	public Integer getCase(LinkedList<String> lista) {
		System.out.println(lista);
		
		if (QUOTE.equals(lista.get(1).trim())) {
			return 1;
                        
		}else if (DEFUN.equals(lista.get(1))) {
                    FunctionInterpreter FI = new FunctionInterpreter(lista);
                    System.out.println(FI.getInstructions("nombre"));
			return 2;
                        
		}else if (SETQ.equals(lista.get(1))) {
			return 3;
                        
		}else if (ATOM.equals(lista.get(1))) {
                        Functionality_Operators FO= new Functionality_Operators();
                        FO.create_Atom(lista);
                        System.out.println(FO.getAtom());
			return 4;
			
		}else if (LIST.equals(lista.get(1))) {
			System.out.print("Ingreso a list\n");
			return 5;
                        
		}else if (CONS.equals(lista.get(1))) {
                        Functionality_Operators FO= new Functionality_Operators();
                        FO.create_Cons(lista);
                        System.out.println(FO.getCons());
			return 6;
			
		}else if (COND.equals(lista.get(1))) {
                        //Functionality_Operators FO= new Functionality_Operators();
			return 7;
                        
                }else if (EQUAL.equals(lista.get(1))) {
                        lista.removeFirst();
                        lista.removeFirst();
                        lista.addFirst("=");
                        lista.addFirst("(");
                        Operations calc = new Operations(lista);
                        System.out.print(calc.ResultComp());
			return 8;      
                        
		}else if(lista.get(1).matches(OPERATIONS)||lista.get(1).equals("(")) {
			Operations calc = new Operations(caseOperation(lista));
		        if(calc.getOpType().equals(">") || calc.getOpType().equals("<") || calc.getOpType().equals("=")){
		        System.out.print(calc.ResultComp());
		        }else{
		        System.out.print(calc.Result());
		        }
			return 9;
			
                    }
		else {
			return (Integer) null;
		}
	}
		
		
	public LinkedList<String> caseList(LinkedList<String> lista){
		LinkedList<String> list = new LinkedList<String>();
		if (lista.get(0).equals("(") && lista.get(lista.size()-1).equals(")")) {
			System.out.print("Primer if de linked list\n");
			for(int k = 2 ; k < lista.size()-1 ; k++ ) {
				System.out.print("List value actual: "+ lista.get(k)+"\n");
				//En el caso de que sea un string definido entre comillas
				if (lista.get(k).matches(QUOTATION)) {
					k++;
					String tempString = new String();
					//En el caso de que sea un string, definido entre comillas
					while (!lista.get(k).matches(QUOTATION) && k < lista.size()-1) {
						//Se concatena
						tempString = tempString + " " + lista.get(k);
						//Significa que llego al final de la lista y concateno el parentesis cerrado, mal escrito entonces se retorna null.
						if (k==lista.size()-2) {
							return null;
						}
						//Se sube el contador
						k++;
					}
					//Si se mantuvo entonces se add the string
					list.add(tempString);
					
				
				}
				//En el caso cumpla con el patron de ser numerico entonces se add
				else if (lista.get(k).matches(NUMERIC_ATOM)){
					list.add(lista.get(k));
				}
				//En el caso de que sea un nombre valido y que exista en el almacenamiento de variables se busca y se add
				else if (lista.get(k).matches(VALID_NAME) && getVariableStorage().getVariableStorage().containsKey(lista.get(k))) {
					list.add(getVariableStorage().getVariableStorage().get(lista.get(k)));
				}
				//En el caso de que tenga o no un nombre valido y no este almacenado en el almacenamiento de variables
				else if (lista.get(k).matches(VALID_NAME) || lista.get(k).matches(VALID_NAME) && !getVariableStorage().getVariableStorage().containsKey(lista.get(k))) {
					return null;
				}
				else {
					
				}
				
			}
			System.out.print("La lista final"+list+"\n");
			return list;
		}
		//En el caso de que no cumpla con que tenga parentesis se devuelve nulo
		return null;
	}
	
	
	
	
	public LinkedList<String> caseOperation(LinkedList<String> lista) {
		int oparenthesisCounter = 0;
		int cparenthesisCounter = 0;
		
		//Para correr la lista de operaciones
		for (int k = 0; k < lista.size() ; k++) {
			System.out.print(lista.get(k));
			//Si es una variable almacenada
			if (getVariableStorage().getVariableStorage().containsKey(lista.get(k))) {
				try {
					//Trata de convertirlo a numero float
					Float.parseFloat(getVariableStorage().getVariableStorage().get(lista.get(k)));
					//Si es numero se agrega y suplementa al valor que aparecia
					lista.add(k, getVariableStorage().getVariableStorage().get(lista.get(k)));
					lista.remove(k+1);
					
				}
				//En el caso de que no sea numero significa que es error del usuario
				catch(Exception e) {
					System.out.print("No es numero la variable\n");
					return null;
				}
				
			}
			//En el caso de que sea igual a parentesis abierto 
			else if (lista.get(k).equals("(")) {
				oparenthesisCounter++;
				//Si difiere en ser el siguiente valor operaciones.
				if (lista.get(k+1).matches(OPERATIONS)) {
					
				}
				//Si difiere en ser el siguiente valor parentesis abierto
				else if(lista.get(k+1).equals("(")) {
				}
				else {
					System.out.print("El siguiente valor no es operaciones o parentesis abierto\n");
					return null;
				}
				
			}
			else if (lista.get(k).equals(")")) {
				System.out.print("Entro a parentesis cerrado\n");
				cparenthesisCounter++;
			}
			//En caso de que no sea una key de variable es error de usuario
			else if (lista.get(k).matches(VALID_NAME) && !getVariableStorage().getVariableStorage().containsKey(lista.get(k))|| lista.get(k).matches(QUOTATION)) {
				System.out.print("no es key valida\n");
				return null;
				
			//En caso de que sea una operacion y el anterior no sea parentesis abierto es error	
			}else if(lista.get(k).matches(OPERATIONS) && !lista.get(k-1).equals("(")) {
				System.out.print("El anterior no es parentesis abierto\n");
				return null;
			}
			
		}
		//No balanceados los parentesis
		if (cparenthesisCounter!=oparenthesisCounter) {
			System.out.print("\n"+cparenthesisCounter+" "+oparenthesisCounter);
			return null;
		}
		//Si pasa todo esto entonces retornara la lista
		return lista;
	}
	
//	.substring(0,array.get(array.size()-1).length()-1)
	public void caseSETQ(LinkedList<String> array) {
//		System.out.print(array.get(2).substring(0, array.get(2).length()-1).matches(VALID_NAME) && array.get(array.size()-1).matches(SYMBOL) );
		if (array.get(2).substring(0, array.get(2).length()-1).matches(VALID_NAME) && array.get(array.size()-1).matches(SYMBOL) ) {
			// Para verificar que es valida 
			System.out.print("Primer if\n");
//			System.out.print(array.get(3).matches(SYMBOL) && array.get(array.size()-2).matches(SYMBOL) && array.get(4).matches(QUOTATION) &&  array.get(array.size()-3).matches(QUOTATION));
			if ( array.get(3).matches(SYMBOL) && array.get(array.size()-2).matches(SYMBOL) && array.get(4).matches(QUOTATION) &&  array.get(array.size()-3).matches(QUOTATION) ) {
				System.out.print("SegundoIf\n");
				// Es para almacenar el valor tipo string
				String value = new String();
				//Para verificar que es valida la concatenacion
				boolean valido = true;
				//Para correr el array
				for (int k = 5 ; k < array.size()-3; k++ ) {
					//El if es para verificar si hay una quotation o si es valido
					//Si hay un " entonces significa que no es valido y pasa al else para no almacenar mas valores.
					if (!array.get(k).matches(QUOTATION) && valido) {
						System.out.print("3er if\n");
						//Se concatena el string
						value=value+" "+array.get(k);
						if (k == array.size()-4) {
							value.trim();
							System.out.print("Final if\n");
							
						}
						System.out.print("VALOR: "+value+"\n");
					}
					else {
						valido = false;
					}
					
				}
				if (valido) {
					//Es para almacenar el valor de la variable en el variable Storage
					getVariableStorage().CreateVariable(array.get(2), value);
				}else {
					System.out.print("No es valida la sintaxis 2\n");
				}
				
				
			}else if(array.get(3).matches(SYMBOL) && array.get(array.size()-2).matches(SYMBOL) && array.get(4).matches(OPERATIONS)) {
				//Modificar la lista para que se adapte al caso de operaciones
				System.out.print("Entro a variable numerica en setq\n");
				LinkedList<String> operationsArray = new LinkedList<String>();
				for (int k = 3 ; k < array.size()-1 ; k++) {
					operationsArray.add(array.get(k));
				}
				if (caseOperation(operationsArray)!=null) {
					Operations calc = new Operations(caseOperation(operationsArray));
					System.out.print(operationsArray);
					getVariableStorage().CreateVariable(array.get(2), String.valueOf(calc.Result()));
					System.out.print("El valor de la variable es: "+getVariableStorage().getVariableStorage().get(array.get(2))+"\n");
				}
				else {
					System.out.print("No era valida la expresion\n");
				}
			}
			else if(array.get(4).matches(NUMERIC_ATOM) && array.get(5).equals(")") && array.get(6).equals(")")) {
				try {
					Float.valueOf(array.get(4));
					getVariableStorage().getVariableStorage().put(array.get(2), array.get(4));
					System.out.print("El valor es: " + getVariableStorage().getVariableStorage().get(array.get(2))+"\n");
				}
				catch(Exception e) {
					System.out.print("No era un valor valido\n");
				}
			}
			else {
				System.out.print("No es una sintaxis valida\n");
			}
		}else {
			System.out.print("No es valida la sintaxis.\n");
		}
	}
	
	
	
	public static void main(String[] args) {
		Reader lector = new Reader();
		Scanner scanner = new Scanner(System.in);
		
//		System.out.println(lector.getCase(lector.tokenize(scanner.nextLine())));
		lector.caseReader(scanner.nextLine());
		
		lector.caseReader(scanner.nextLine());

		scanner.close();
	}
	
}
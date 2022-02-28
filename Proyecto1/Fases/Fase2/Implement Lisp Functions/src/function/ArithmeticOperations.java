package function;

public class ArithmeticOperations {
  public class Suma{
		{
	        double n1, n2, suma;

	        Scanner reader = new Scanner( System.in );

	        System.out.print( "Introduzca primer número: " );
	        n1 = reader.nextInt();

	        System.out.print( "Introduzca segundo número: " );
	        n2 = reader.nextInt();

	        suma = n1 + n2;

	        System.out.println( "La suma de " + n1 + " más " + n2 + " es " + suma + "." );
	    }
		
	}
	
	public class Resta{
		{
			 double n1, n2, resta;
			
			Scanner reader = new Scanner(System.in);
			
			System.out.println("Introduce el primer número:");			
			n1 = reader.nextInt();
						
			System.out.println("Introduce el segundo número:");
			n2 = reader.nextInt();
			
			resta = n1-n2;
			
			System.out.println("La resta es " + n1 + " - " + n2 + " = " + resta);
		}
		
	}
	
	public class Multiplicacion{
		{
			Scanner reader = new Scanner(System.in);
			
			double n1, n2, multiplicacion;
			System.out.println("Introduce el primer número:");			
			n1 = reader.nextFloat();
					
			System.out.println("Introduce el segundo número:");
			n2 = reader.nextFloat();
			
			multiplicacion = n1*n2;
			
			multiplicacion = n1*n2;
			System.out.println("La multiplicación es " + n1 + " x " + n2 + " = " + multiplicacion);	
		}
		
		public class division{
			{
				Scanner reader = new Scanner(System.in);
				
				double n1, n2, division;
				System.out.println("Introduce el primer número:");			
				n1 = reader.nextFloat();
						
				System.out.println("Introduce el segundo número:");
				n2 = reader.nextFloat();
				
				division = n1/n2;
				System.out.println("La división es " + n1 + " / " + n2 + " = " + division);
				
			}
		}
		
	}

}

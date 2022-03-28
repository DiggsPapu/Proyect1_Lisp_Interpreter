package function;
import java.util.Scanner;
/**
 * 
 * @author Diego Alonzo - 20172
 *
 */
public class Main {
/**
 * Main 
 * @param args
 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/**
		 * @see String inputLector
		 * Para leer el input del usuario
		 */
		Scanner inputLector = new Scanner (System.in);
		Reader reader= new Reader();
		while (true) {
			
			String instruction =inputLector.nextLine();
			System.out.print(instruction+"\n");
			/**
			 * En caso de querer salir del programa
			 * @returns break
			 */
			if (instruction.contentEquals("(exit)")) {
				break;
			}
			else if (instruction.contentEquals(":exit")) {
				break;
			}
			reader.caseReader(instruction);
			
			
			
			
			
		}
		
	}


}

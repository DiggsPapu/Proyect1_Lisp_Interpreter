<<<<<<< HEAD
package junit;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import v2.tokenizer;

import java.util.Scanner;
class testTokenizer {

	@Test
	void testEqualParenthesis() {
		Scanner scann = new Scanner(System.in);
		assertNotEquals(tokenizer.equalParenthesis("(setq 1 2 3)"), null);
		assertEquals(tokenizer.equalParenthesis("(setq 1 2 3"), null);
		assertEquals(tokenizer.equalParenthesis("(setq 1 2 (3)"), null);
		assertEquals(tokenizer.equalParenthesis("fjkl"), null);
		assertEquals(tokenizer.equalParenthesis("exit"), null);
		assertEquals(tokenizer.equalParenthesis("e"), null);
		System.out.print("Ingresar un input que contenga comillas balanceadas: "); //(setq fkdls "fjksdl fdsa")
		assertNotEquals(tokenizer.equalParenthesis("(value())"), null);
		System.out.print("Ingresar un input que contenga comillas desbalanceadas: "); //(setq fkdls "fjksdl )
		assertNull(tokenizer.equalParenthesis("(value ()"));
		scann.close();
	}

}
=======
package junit;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import function.tokenizer;

import java.util.Scanner;
class testTokenizer {

	@Test
	void testEqualParenthesis() {
		Scanner scann = new Scanner(System.in);
		assertNotEquals(tokenizer.equalParenthesis("(setq 1 2 3)"), null);
		assertEquals(tokenizer.equalParenthesis("(setq 1 2 3"), null);
		assertEquals(tokenizer.equalParenthesis("(setq 1 2 (3)"), null);
		assertEquals(tokenizer.equalParenthesis("fjkl"), null);
		assertEquals(tokenizer.equalParenthesis("exit"), null);
		assertEquals(tokenizer.equalParenthesis("e"), null);
		System.out.print("Ingresar un input que contenga comillas balanceadas: "); //(setq fkdls "fjksdl fdsa")
		assertNotEquals(tokenizer.equalParenthesis(scann.nextLine()), null);
		System.out.print("Ingresar un input que contenga comillas desbalanceadas: "); //(setq fkdls "fjksdl )
		assertNull(tokenizer.equalParenthesis(scann.nextLine()));
		scann.close();
	}

}
>>>>>>> 6f24e712079cd755ef2d83c702f6cb1680e0df3c

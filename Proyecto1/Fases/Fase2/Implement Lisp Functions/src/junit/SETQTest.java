
package junit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

import v2.VariableStorage;
import v2.SETQ;
import v2.tokenizer;

class SETQTest {

	@Test
	void testEvaluateSETQ() {
		
		VariableStorage vs = new VariableStorage();
		Scanner scann = new Scanner(System.in);
		//Caso en el que la lista es null
		SETQ.evaluateSETQ(tokenizer.equalParenthesis("(setq 1 2"), vs);
		SETQ.evaluateSETQ(tokenizer.equalParenthesis("(setq 1 2)"), vs);
		SETQ.evaluateSETQ(tokenizer.equalParenthesis("(setq value 2)"), vs);
		SETQ.evaluateSETQ(tokenizer.equalParenthesis("(SETQ value 1)"), vs);
		SETQ.evaluateSETQ(tokenizer.equalParenthesis("(setq value1 value)"), vs);
		SETQ.evaluateSETQ(tokenizer.equalParenthesis("(setq value1 velo)"), vs);
		SETQ.evaluateSETQ(tokenizer.equalParenthesis("(setq 1 value)"), vs);
		SETQ.evaluateSETQ(tokenizer.equalParenthesis("(juan val 90)"), vs);
		SETQ.evaluateSETQ(tokenizer.equalParenthesis("(setq val 90 3 4)"), vs);
		SETQ.evaluateSETQ(tokenizer.equalParenthesis("(SETQ value2 (+ 1 2))"), vs);
		assertEquals(vs.getVariableStorage().get("value2"), "3.0");
		SETQ.evaluateSETQ(tokenizer.equalParenthesis("(SETQ value2 (- 1 2))"), vs);
		SETQ.evaluateSETQ(tokenizer.equalParenthesis("(SETQ value3 (< 1 2))"), vs);
		SETQ.evaluateSETQ(tokenizer.equalParenthesis("(SETQ value4 (< 3 2))"), vs);
		
		SETQ.evaluateSETQ(tokenizer.equalParenthesis("(SETQ value5 (> 1 2))"), vs);
		SETQ.evaluateSETQ(tokenizer.equalParenthesis("(SETQ value6 (> 3 2))"), vs);
		
		SETQ.evaluateSETQ(tokenizer.equalParenthesis("(SETQ value7 (= 1 2))"), vs);
		SETQ.evaluateSETQ(tokenizer.equalParenthesis("(SETQ value8 (= 1 1))"), vs);
		
		assertEquals(vs.getVariableStorage().get("value"), "1");
		assertEquals(vs.getVariableStorage().get("value1"), "1");
		assertEquals(vs.getVariableStorage().get("value"), vs.getVariableStorage().get("value1"));
		assertEquals(vs.getVariableStorage().get("value2"), "-1.0");
		assertEquals(vs.getVariableStorage().get("value3"), "true");
		assertEquals(vs.getVariableStorage().get("value4"), "false");
		assertEquals(vs.getVariableStorage().get("value5"), "false");
		assertEquals(vs.getVariableStorage().get("value6"), "true");
		assertEquals(vs.getVariableStorage().get("value7"), "false");
		assertEquals(vs.getVariableStorage().get("value8"), "true");
		
//		System.out.print("Ingrese uno que aplique a setq con valor de comillas valido: ");//POr ejemplo (SETQ val " hola mi nombre es")
//		String value = scann.nextLine();
		SETQ.evaluateSETQ(tokenizer.equalParenthesis("(SETQ val \" hola mi nombre es\")"), vs);
//		System.out.print("Ingrese uno que aplique a setq con valor de comillas invalido: ");// Por ejemplo (setq val "hola mi nombre es)
//		String value1 = scann.nextLine();
		SETQ.evaluateSETQ(tokenizer.equalParenthesis("(setq val \"hola mi nombre es)"), vs);
		scann.close();
		
		
		//Este dependera del usuario enteramente
//		assertNotNull(vs.getVariableStorage().get(value));
		assertNull(vs.getVariableStorage().get("LeoDicaprio"));
		
	}

}

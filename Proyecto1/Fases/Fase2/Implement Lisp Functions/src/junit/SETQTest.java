package junit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

import function.SETQ;
import function.VariableStorage;
import function.tokenizer;

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
		
		assertEquals(vs.getVariableStorage().get("value"), "1");
		assertEquals(vs.getVariableStorage().get("value1"), "1");
		assertEquals(vs.getVariableStorage().get("value"), vs.getVariableStorage().get("value1"));
		
		System.out.print("Ingrese uno que aplique a setq con valor de comillas valido");
		String value = scann.nextLine();
		System.out.print("Ingrese uno que aplique a setq con valor de comillas invalido");
		String value1 = scann.nextLine();
		SETQ.evaluateSETQ(tokenizer.equalParenthesis(value), vs);
		SETQ.evaluateSETQ(tokenizer.equalParenthesis(value1), vs);
		scann.close();
		
		
		//Este dependera del usuario enteramente
//		assertNotNull(vs.getVariableStorage().get(value));
		assertNull(vs.getVariableStorage().get("LeoDicaprio"));
		
	}

}

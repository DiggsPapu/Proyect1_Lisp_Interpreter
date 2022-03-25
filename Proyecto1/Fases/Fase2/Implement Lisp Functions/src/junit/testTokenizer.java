package junit;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import function.tokenizer;

class testTokenizer {

	@Test
	void testEqualParenthesis() {
		assertNotEquals(tokenizer.equalParenthesis("(setq 1 2 3)"), null);
		assertEquals(tokenizer.equalParenthesis("(setq 1 2 3"), null);
		assertEquals(tokenizer.equalParenthesis("(setq 1 2 (3)"), null);
		assertEquals(tokenizer.equalParenthesis("fjkl"), null);
		assertEquals(tokenizer.equalParenthesis("exit"), null);
		assertEquals(tokenizer.equalParenthesis("e"), null);
		
	}

}

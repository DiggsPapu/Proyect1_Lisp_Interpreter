package junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import v2.CONDI;
import v2.VariableStorage;
import v2.tokenizer;

class CONDITest {

	@Test
	void testCOND() {
		VariableStorage var = new VariableStorage();
		String cond1 =CONDI.COND(tokenizer.equalParenthesis("(cond  ((>1 2) \"hola\" ) ((= 1 1) (list 1 2 3) ) (t (quote 1 2 3)) )"), var);
		assertEquals(cond1, "[1, 2, 3]"); 
		String cond2=CONDI.COND(tokenizer.equalParenthesis("(cond  ((<1 2) \"hola\" ) ((= 1 1) (list 1 2 3) ) (t (quote 1 2 3)) )"), var);
		assertEquals(cond2, " hola");
		String cond3=CONDI.COND(tokenizer.equalParenthesis("(cond  ((=1 2) \"hola\" ) ((= 1 1) (list 1 2 3) ) (t (quote 1 2 3)) )"), var);
		assertEquals(cond3, "[1, 2, 3]");
		String cond4=CONDI.COND(tokenizer.equalParenthesis("(cond  ((=1 2) \"hola\" ) ((> 1 1) (list 1 2 3) ) (t (list 1 2 3)) )"), var);
		assertEquals(cond4, "[1, 2, 3]");
		String cond5=CONDI.COND(tokenizer.equalParenthesis("(cond  ((=1 2) \"hola\" ) ((= 2 2) (atom \"eo\") ) (t (list 1 2 3)) )"), var);
		assertEquals(cond5, "[ eo]");
		String cond6=CONDI.COND(tokenizer.equalParenthesis("(cond  ((=1 2) \"hola ) ((> 1 1) (list 1 2 3) ) (t (list 1 2 3)) )"), var);
		assertEquals(cond6, null);
		String cond7=CONDI.COND(tokenizer.equalParenthesis("(cond  ((>23 1) (* 1 2 ) ((> 1 1) (list 1 2 3) ) (t (list 1 2 3)) )"), var);
		assertEquals(cond7, null);
		String cond8=CONDI.COND(tokenizer.equalParenthesis("(cond  ((>23 1) (* 1 2 )) ((> 1 1) (list 1 2 3) ) (t (list 1 2 3)) )"), var);
		assertEquals(cond8, "2.0");
		String cond9=CONDI.COND(tokenizer.equalParenthesis("(cond  ((>23 1) (* 1 2 ) ((> 1 1)(cond  ((<23 1) (* 1 2 ) ((= 1 1) \"the crispyman\") (t (list 1 2 3)) ) ) (t (list 1 2 3)) )"), var);
		assertEquals(cond9, null);
		String cond10=CONDI.COND(tokenizer.equalParenthesis("(cond  ((<23 1) (* 1 2 )) ((= 1 1) (cond  ((= 1 1) 1 ) ((1> 2) 2) (t(list 1 2 3) )) ) (t (list 1 2 3)) )"), var);
		assertEquals(cond10, null);
		String cond11=CONDI.COND(tokenizer.equalParenthesis("(cond  ((<23 1) (* 1 2 )) ((= 1 1) (cond  ((= 1 1) 1 ) ((> 1 2) 2) (t(list 1 2 3) )) ) (t (list 1 2 3)) )"), var);
		assertEquals(cond11, "1");
		String cond12 =CONDI.COND(tokenizer.equalParenthesis("(cond  ((>11 2) (setq value 23) ) ((= 1 1) (list 1 2 3) ) (t (quote 1 2 3)) )"), var);
		assertEquals(cond12, "23"); 
		String cond13 =CONDI.COND(tokenizer.equalParenthesis("(cond  ((<11 2) (setq value 23) ) ((< 1 1) (list 1 2 3) ) (t (quote 1 2 3)) )"), var);
		assertEquals(cond13, "[1, 2, 3]"); 
		
		
		//"(cond  (() ) (() ) (() ))"
	}

}

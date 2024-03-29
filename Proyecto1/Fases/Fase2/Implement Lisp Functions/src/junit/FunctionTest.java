package junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import v2.Function;
import v2.FunctionStorage;
import v2.VariableStorage;
import v2.tokenizer;

class FunctionTest {

	@Test
	void testfun() {
		VariableStorage var = new VariableStorage();
		FunctionStorage fun = new FunctionStorage();
		Function.Defun(tokenizer.equalParenthesis("(defun fun2 (arg1 arg2) (cond ((< 1 2) \"valor\") ((= 1 1) (+ 23 45)) (t(atom 2))))"), var, fun);
		assertEquals(Function.functionExecution(tokenizer.equalParenthesis("(fun2 1 2)"), fun, var),  " valor");
		Function.Defun(tokenizer.equalParenthesis("(defun fun1 (arg1 arg2) (+ arg1 arg2))"), var, fun);
		assertEquals(Function.functionExecution(tokenizer.equalParenthesis("(fun1 34 25)"), fun, var), "59.0");
		assertEquals(Function.functionExecution(tokenizer.equalParenthesis("(fun1 34 25"), fun, var), null);
		Function.Defun(tokenizer.equalParenthesis("(defun fun3 () (+ arg1 arg2))"), var, fun);
		assertEquals(Function.functionExecution(tokenizer.equalParenthesis("(fun3 34 25)"), fun, var), null);
		Function.Defun(tokenizer.equalParenthesis("(defun fun4 () (> arg1 arg2))"), var, fun);
		assertEquals(Function.functionExecution(tokenizer.equalParenthesis("(fun4 34 25)"), fun, var), null);
		assertEquals(Function.functionExecution(tokenizer.equalParenthesis("(fun4 (* 1 (+ 2 3)) 25)"), fun, var), null);
		assertEquals(Function.functionExecution(tokenizer.equalParenthesis("(fun4 (= 1 (+ 2 3)) 25)"), fun, var), null);
		assertEquals(Function.functionExecution(tokenizer.equalParenthesis("(fun4 (= 1 (+ 2 3)) 25)"), fun, var), null);
		Function.Defun(tokenizer.equalParenthesis("(defun fun5 (arg1) (list arg1 \"hola\"))"), var, fun);
		assertEquals(Function.functionExecution(tokenizer.equalParenthesis("(fun5 25)"), fun, var), "[25,  hola]");
		Function.Defun(tokenizer.equalParenthesis("(defun fun6 (arg1) (atom \"hola\"))"), var, fun);
		assertEquals(Function.functionExecution(tokenizer.equalParenthesis("(fun6 25)"), fun, var), "[ hola]");
		Function.Defun(tokenizer.equalParenthesis("(defun fun7 (arg1) (fun6 1))"), var, fun);
		assertEquals(Function.functionExecution(tokenizer.equalParenthesis("(fun6 25)"), fun, var), "[ hola]");
		Function.Defun(tokenizer.equalParenthesis("(defun fun7 (arg1) (equals arg1 2))"), var, fun);
		assertEquals(Function.functionExecution(tokenizer.equalParenthesis("(fun7 35)"), fun, var), null);
		
	}

}

package junit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

import v2.VariableStorage;
import v2.Calculator;
import v2.tokenizer;

class CalculatorTest {

	@Test
	void testCalculator() {
		VariableStorage variableStorage = new VariableStorage();
		   Scanner scann = new Scanner(System.in);
		   Calculator calc = new Calculator(tokenizer.equalParenthesis("(+ 1 (* 9 (- 9 2)))"),variableStorage);
		   assertEquals(calc.Result(), 64.0);
		   
		   variableStorage.CreateVariable("valor", "50");
		   Calculator calc1 = new Calculator(tokenizer.equalParenthesis("(+ 1 (* valor 4))"),variableStorage);
		   assertEquals(calc1.Result(), 201.0);

		   Calculator calc0 = new Calculator(tokenizer.equalParenthesis("(+ 1 (/ valor 0))"),variableStorage);
		   System.out.print(calc0.Result()+"\n");
		   
		   Calculator calc2 = new Calculator(tokenizer.equalParenthesis("(< 1 9)"),variableStorage);
		   assertEquals(calc2.ResultComp(), true);
		   
		   Calculator calc3 = new Calculator(tokenizer.equalParenthesis("(> 1 9)"),variableStorage);
		   assertEquals(calc3.ResultComp(), false);
		   
		   Calculator calc4 = new Calculator(tokenizer.equalParenthesis("(> (^ 1 8) (* 9 valor))"),variableStorage);
		   assertEquals(calc4.ResultComp(), false);
		   
		   Calculator calc5 = new Calculator(tokenizer.equalParenthesis("(< (^ 1 8) (* 9 valor))"),variableStorage);
		   assertEquals(calc5.ResultComp(), true);
		   Calculator calc6 = new Calculator(tokenizer.equalParenthesis("(+ 1 (/ valor 0)))"),variableStorage);
		   assertEquals(calc6.Result(),0.0);
		   
		   System.out.print("Ingrese una operacion con comillas"); //como este (+ 1 "fkjds")
		   Calculator calc7 = new Calculator(tokenizer.equalParenthesis("(+ 1 \"fkjds\")"),variableStorage);
		   assertEquals(calc7.Result(), 0.0);
		   
		   Calculator calc8 = new Calculator(tokenizer.equalParenthesis("(> (^ 10 3) (* 9 valor))"),variableStorage);
		   assertEquals(calc8.ResultComp(), true );
		   
		   Calculator calc9 = new Calculator(tokenizer.equalParenthesis("(= (^ 10 3) (* 9 valor))"),variableStorage);
		   assertEquals(calc9.ResultComp(), false );
		   
		   scann.close();
	}

}

package junit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

import function.Predicates;
import function.VariableStorage;
import function.tokenizer;

class PredicatesTest {

	@Test
	void testEvaluatePredicates() {
		Scanner scann = new Scanner(System.in);
		VariableStorage vs = new VariableStorage();
		vs.CreateVariable("value", "8293");
		assertEquals(Predicates.caseEqual(tokenizer.equalParenthesis("(EQUAL 1 2)"), vs), false);
		assertEquals(Predicates.caseEqual(tokenizer.equalParenthesis("(EQUAL 1 1)"), vs), true);
		assertEquals(Predicates.caseEqual(tokenizer.equalParenthesis("(equal 1 2)"), vs), false);
		assertEquals(Predicates.caseEqual(tokenizer.equalParenthesis("(equal 1 1)"), vs), true);
		assertEquals(Predicates.caseEqual(tokenizer.equalParenthesis("(= 1 2)"), vs), false);
		assertEquals(Predicates.caseEqual(tokenizer.equalParenthesis("(= 2 2)"), vs), true);
		assertEquals(Predicates.caseEqual(tokenizer.equalParenthesis("(EQUAL value 2)"), vs), false);
		assertEquals(Predicates.caseEqual(tokenizer.equalParenthesis("(EQUAL 1 value)"), vs), false);
		assertEquals(Predicates.caseEqual(tokenizer.equalParenthesis("(EQUAL 8293 value)"), vs), true);
		assertEquals(Predicates.caseEqual(tokenizer.equalParenthesis("(= value valor)"), vs), false);
		assertEquals(Predicates.caseEqual(tokenizer.equalParenthesis("(= value value)"), vs), true);
		assertEquals(Predicates.caseEqual(tokenizer.equalParenthesis("(= \"jfkdsl\" 2)"), vs), false);
		assertEquals(Predicates.caseEqual(tokenizer.equalParenthesis("(= \"jfkdsl\"  \"jfkdsl\")"), vs), true);
		assertEquals(Predicates.caseEqual(tokenizer.equalParenthesis("(= (+ 1 2)  (+ 1 2) )"), vs), true);
		assertEquals(Predicates.caseEqual(tokenizer.equalParenthesis("(= (+ 2 2)  (+ 1 2) )"), vs), false);
		assertEquals(Predicates.caseEqual(tokenizer.equalParenthesis("(= (+ 2 2)  (+ 1 2)"), vs), null);
		assertEquals(Predicates.caseEqual(tokenizer.equalParenthesis("(= 1 2 3 )"), vs), null);
		assertEquals(Predicates.caseEqual(tokenizer.equalParenthesis("(= (1) 2 3 )"), vs), null);
		assertEquals(Predicates.caseEqual(tokenizer.equalParenthesis("(lista 2 3 )"), vs), null);
		
		
		assertEquals(Predicates.caseQuote(tokenizer.equalParenthesis("(quote (+ 1 2) )")).get(0), "(");
		assertEquals(Predicates.caseQuote(tokenizer.equalParenthesis("(quote (+ 1 2) 2 fd )")).get(5), "2");
		assertEquals(Predicates.caseQuote(tokenizer.equalParenthesis("(quote (+ 1 2 )")), null);
		System.out.print("Ingrese un quote valido con string o comillas: ");
		assertEquals(Predicates.caseQuote(tokenizer.equalParenthesis(scann.nextLine())).get(1), "kfjlds");//Por ejemplo (QUOTE "1 2 fdjksl" kfjlds)
		assertEquals(Predicates.evaluateList(tokenizer.equalParenthesis("(list 1 2 value )"), vs).get(2), vs.getVariableStorage().get("value"));
		assertEquals(Predicates.evaluateList(tokenizer.equalParenthesis("(list  )"), vs) , null);
		assertEquals(Predicates.evaluateList(tokenizer.equalParenthesis("(list (+ 1 2) 3 4 value )"), vs).get(0), "3.0");
		assertEquals(Predicates.evaluateList(tokenizer.equalParenthesis("(list  (+ 100 value) 0 1 )"), vs).get(0) , "8393.0"  );
		assertEquals(Predicates.evaluateList(tokenizer.equalParenthesis("(list  0 (< 100 value) 1 )"), vs).get(1), "true");
		assertEquals(Predicates.evaluateList(tokenizer.equalParenthesis("(list  (> 100 value) 0 1 )"), vs).get(0), "false");
		assertEquals(Predicates.evaluateList(tokenizer.equalParenthesis("(list  (= 100 100) 0 1 )"), vs).get(0), "true");
		assertEquals(Predicates.evaluateList(tokenizer.equalParenthesis("(list  (= 100 value) 0 1 )"), vs).get(0), "false");
		assertEquals(Predicates.evaluateList(tokenizer.equalParenthesis("(list  (+ valor value) 0 1 )"), vs), null );
		assertEquals(Predicates.evaluateList(tokenizer.equalParenthesis("(list  (+ 1 value) jksimmons 1 )"), vs), null );
		assertEquals(Predicates.evaluateList(tokenizer.equalParenthesis("(list (+1 value )"), vs), null);
		System.out.print("Ingrese un lista valida con comillas: "); //por ejemplo (list (^ 23 (+ 2 3 )) "hola"  )
		assertEquals(Predicates.evaluateList(tokenizer.equalParenthesis(scann.nextLine()), vs).get(1), " hola");
		assertEquals(Predicates.evaluateAtom(tokenizer.equalParenthesis("(atom 1)"), vs).get(0), "1");
		assertEquals(Predicates.evaluateAtom(tokenizer.equalParenthesis("(atom value)"), vs).get(0), "8293");
		assertEquals(Predicates.evaluateAtom(tokenizer.equalParenthesis("(atom )"), vs), null);
		assertEquals(Predicates.evaluateAtom(tokenizer.equalParenthesis("(atom (+ 1 2))"), vs).get(0), "3.0");
		assertEquals(Predicates.evaluateAtom(tokenizer.equalParenthesis("(atom (= 1 value))"), vs).getFirst(), "false");
		assertEquals(Predicates.evaluateAtom(tokenizer.equalParenthesis("(atom (= 1 1))"), vs).getFirst(), "true");
		assertEquals(Predicates.evaluateAtom(tokenizer.equalParenthesis("(atom (> 1 1))"), vs).getFirst(), "false");
		assertEquals(Predicates.evaluateAtom(tokenizer.equalParenthesis("(atom (> 2 1))"), vs).getLast(), "true");
		assertEquals(Predicates.evaluateAtom(tokenizer.equalParenthesis("(atom (< 1 1))"), vs).getLast(), "false");
		assertEquals(Predicates.evaluateAtom(tokenizer.equalParenthesis("(atom (< -1 1))"), vs).getFirst(), "true");
		System.out.print("Ingrese un atom que sea valido con comillas: "); // por ejemplo (atom "fjdkls")
		System.out.print("Atom: " + Predicates.evaluateAtom(tokenizer.equalParenthesis(scann.nextLine()), vs) + "\n");
		System.out.print("Ingrese un atom que no sea valido con comillas: "); // por ejemplo (atom "fjdkls)
		System.out.print("Atom: " + Predicates.evaluateAtom(tokenizer.equalParenthesis(scann.nextLine()), vs) + "\n");
		
		scann.close();
	}



}

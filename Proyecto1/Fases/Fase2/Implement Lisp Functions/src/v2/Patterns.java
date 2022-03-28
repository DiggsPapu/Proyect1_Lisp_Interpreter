package v2;
/**


 * 
 * @author Windows 10
 *Esta clase es para almacenar todos los patrones de string 
 *Este metodo se utiliza para almacenar las palabras y simbolos protegidos.
 */ 



public class Patterns {
	protected static final String LETTER = "[a-zA-Z]";
	protected static final String LITERAL = "[a-zA-Z0-9]+";
	protected static final String VALID_NAME = "[a-zA-Z][a-zA-Z0-9]*";
	protected static final String WHIESPACE = "[\\s]+";
	protected static final String NUMERIC_ATOM = "[\\+\\-]?[\\d]+[\\.\\d]*";
	protected static final String OPERATIONS =  "[\\+\\-\\*\\^\\/\\<\\>\\=]?";
	protected static final String SYMBOL = "[().]";
	protected static final String QUOTATION = "[\"]";
    	protected static final String CONS = "CONS";
	protected static final String ATOM = "ATOM";
	protected static final String LIST = "LIST";
	protected static final String SETQ = "SETQ";
	protected static final String QUOTE = "QUOTE";
	protected static final String DEFUN = "(?i)(\\W|^)(defun|DEFUN)(\\W|$)";
	protected static final String COND = "COND";
	protected static final String EQUAL = "EQUAL";
	protected static final String OPARENTHESIS = "[(].";
	protected static final String CPARENTHESIS = "[)].";
	protected static final String LOGICAL = "[\\<\\>\\=]?";
	protected static final String ARITHMETIC = "[\\+\\-\\*\\/\\^]?";
	
	
}

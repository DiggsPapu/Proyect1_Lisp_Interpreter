package function;


/**
 * 
 * @author Diego Andres Alonzo Medinilla
 *
 * @param <N> Generics
 */
public class node<N> {
	private N value;
	node<N> next;
	/***
	 * Es el constructor cuando no hay valores
	 */
	public node(N valor){
		this.value= valor;
		System.out.print("a");
		next=null;
	}
	/**
	 * Regresa el valor del nodo
	 * @return
	 */
	public N getValue() {
		return value;
	}
	/**
	 * Determinas el valor del nodo
	 * @param value
	 */
	public void setValue(N value) {
		this.value = value;
	}
	/**
	 * Regresa el valor de la referencia
	 * @return
	 */
	public node<N> getNext() {
		return next;
	}
	/**
	 * Determinas el valor de la referencia
	 * @param next
	 */
	public void setNext(node<N> next) {
		this.next = next;
	}
	
}
package function;

public interface IStack<N> {
	public void push(N value);
	N pop();
	int count();
	N peek();
	boolean isEmpty();
}
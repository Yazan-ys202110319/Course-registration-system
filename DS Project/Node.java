import java.io.Serializable;

public class Node <E> implements Serializable{
	private E data;
	private Node<E> next;
	public Node(E d, Node<E> n)
	{
	data=d;
	next=n;
	}
	public E getData() { return data; }
	public Node<E> getNext(){ return next; }
	public void setNext(Node<E> n) { next=n;}

}

import java.io.Serializable;

public class SinglyLinkedList <E> implements Serializable{
	private Node<E> head=null;
	private Node<E> tail=null;
	private int size=0;
	public SinglyLinkedList() { }
	public int size() { return size;}
	public boolean isEmpty() {return size==0;}
	public E first()
	{
	if (isEmpty()) return null;
		return head.getData();
	}
	public E last()
	{
		if (isEmpty()) return null;
			return head.getData();
	}
	public void addFirst(E e)
	{
	head=new Node<>(e,head);
	if(size==0)
		tail=head;
	size++;

	}
	public void addLast(E e)
	{
	Node<E> newest=new Node<>(e,null);
	if(isEmpty())
		head=newest;
	else
		tail.setNext(newest);

	tail=newest;
	size++;
	}

	public E removeFirst()
	{
		if(isEmpty()) return null;
		E answer=head.getData();
		head=head.getNext();
		size--;
		if (size==0)
			tail=null;
		return answer;
	}
	public E removeLast()
	{
	if(isEmpty()) return null;
	E answer=tail.getData();
	if (head==tail)
		head=tail=null;
	else
	{
	    Node<E> tmp=head;
	    while (tmp.getNext()!=tail)
	    	tmp=tmp.getNext();
	 tmp.setNext(null);
	 tail=tmp;
	}
	size--;
	return answer;
	}
	public void display() {
		for (Node<E> tmp=head ; tmp!=null ; tmp=tmp.getNext())
			System.out.println(tmp.getData());
	}
}
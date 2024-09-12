import java.io.Serializable;

public class Entry<E> implements Serializable{
    
    int key;
	E data;

	public Entry(int k, E d){

		key=k;
		data=d;
		
	}

	public void diplay(){

		 System.out.print(key+":");
		 System.out.println(data);
	}

}
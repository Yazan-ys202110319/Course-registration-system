import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

public class LinkedQueueHashTable <E> implements Serializable{

    private Entry[] hashArray;    
	private Entry  defunct;
    
	public LinkedQueueHashTable(int size){

		hashArray = new Entry[size]; // Create a table with specific size.
		defunct = new Entry(-1,null); 
	}

	public void displayTable(){

		for(int j = 0; j<hashArray.length; j++)
			if(hashArray[j] != null)
				hashArray[j].diplay();
			else
				System.out.println("** ");
	}

	public boolean isFull(){
		int count = 0;
		for (int i = 0; i < hashArray.length; i++) {
			if (hashArray[i] != null && hashArray[i] != defunct) {
				count++;
			}
		}
		return count == hashArray.length;
	}

	// This method use the CRN to create slot.
	public int hashFunc(int CRN){

		return CRN % hashArray.length;   

	}

	public void insert(int k, E d){ // k = crn

		if (!isFull()){

			int hashVal = hashFunc(k);  

			while(hashArray[hashVal] != null && hashArray[hashVal]!= defunct){
				++hashVal;                 
				hashVal %= hashArray.length;      
			}
			hashArray[hashVal] = new Entry(k,d);    
		}
	}
	
	public E delete(int k){

		int hashVal = hashFunc(k);  
		int start = hashVal;
		boolean checkAll=false;
		while(hashArray[hashVal] != null && hashArray[hashVal]!= defunct && !checkAll){                      
			if(hashArray[hashVal].key == k){
				E temp = (E)hashArray[hashVal].data; 
				hashArray[hashVal] = defunct;      
				return temp;                        
			}
			++hashVal;                 
			hashVal %= hashArray.length;      
			if (hashVal == start) 
				checkAll = true;
		}
		return null;                  
	}  
	
	public E find(int k){

		int hashVal = hashFunc(k);  
		int start = hashVal;
		boolean checkAll = false;
		while(hashArray[hashVal] != null && !checkAll){

			if(hashArray[hashVal].key == k)
				return (E)hashArray[hashVal].data;   
			++hashVal;                 
			hashVal %= hashArray.length;      
			if (hashVal == start) 
				checkAll = true;
		}
		return null;                  
	}

	public void addCourse(Course c){

		// Check the course CRN
		Course checkCourse = Search(c.getCRN());

		if(checkCourse == null){
			insert(c.getCRN(), (E) c);
			System.out.println("A course has been inserted");
			return;
		}

		else{
			System.out.println("Course with CRN " + c.getCRN() + " already exists!");
		}
		
	}

	public Course Search(int k){

		// Because find method return type generic we have to convert it to be of type course first before returning. 

		E getCourse = find(k); 

		if(getCourse != null){ // The course is available.
			return (Course) getCourse;
		}

		else{ // Course not found.
			return null;
		}
		
	}

	public void addStudent(int k, Student s){
		
		Course course = Search(k);
		if (course == null){
			throw new IllegalStateException("No course found with CRN: " + k);
		}

		LinkedList<Student> enrolled = course.getStudentsInCourse();
		Queue<Student> waitingList = course.getStudentsInWating();

		if (enrolled.size() >= course.getCapacity()){
			waitingList.enqueue(s);
			System.out.println("Course is full. Student added to waiting list.");
		} else {
			enrolled.addLast(s);
			System.out.println("Student successfully added to the course.");
		}
	}

	public void raiseCapacity(int c, int r){

	    // Step 1: Search for the course with the provided CRN
	    Course course = (Course) find(c);

	    if (course != null) {
	        // Step 2: Update course capacity
	        int oldCapacity = course.getCapacity();
	        int newCapacity = oldCapacity + r;
	        course.setCapacity(newCapacity);

			System.out.println("The course capcity has been rasied to " + newCapacity);
	        
	        // Step 3: Move students from waiting list to enrolled list based on the new capacity
	        LinkedQueue<Student> waitingList = course.getStudentsInWating();
	        LinkedList<Student> enrolled = course.getStudentsInCourse();
	        
	        while (!waitingList.isEmpty() && enrolled.size() < newCapacity) {
	            enrolled.addLast(waitingList.dequeue());
	        }


	    } else {
	        // Step 4: If course not found, throw an exception
	        throw new IllegalArgumentException("Course with CRN " + c + " not found.");
		}
	}

	public void dropStudent(int i, int c) {

		Course course = Search(c);
		if (course == null) {
			throw new IllegalStateException("Course with CRN " + c + " not found.");
		}
	
		LinkedList<Student> enrolled = course.getStudentsInCourse();
		Queue<Student> waitingList = course.getStudentsInWating();
		boolean found = false;
		int indexToRemove = -1;
	
		// Manually traversing the list to find the student
		for (int index = 0; index < enrolled.size(); index++) {
			if (enrolled.get(index).getId() == i) {
				indexToRemove = index;
				found = true;
				break;
			}
		}
	
		if (!found) {
			throw new IllegalStateException("Student with ID " + i + " not found in course CRN " + c);
		} else {
			// Remove the student once the index is found
			enrolled.remove(indexToRemove);
			System.out.println("Student removed from the course.");
			// Check if there's a waiting list and move the next student to enrolled list
			if (!waitingList.isEmpty()) {
				enrolled.addLast(waitingList.dequeue());
				System.out.println("Student from waiting list added to the course.");
	 		}
		}
	}

	public Course[] studentWaiting(int i){

		ArrayList<Course> studentWatiring = new ArrayList<>();

		for(Entry e : hashArray){
			if(e != null && e != defunct){
				Course c = (Course) e.data;

				if(c != null && isStudentWatiting(c, i) == true){
					studentWatiring.add(c);
				}

			}
		}

		Course[] arryCourse = new Course[studentWatiring.size()];
		return studentWatiring.toArray(arryCourse);

	}

	private boolean isStudentWatiting(Course c, int id){

		Queue<Student> waitingQueue = c.getStudentsInWating();

		while (!waitingQueue.isEmpty()) {
			if(waitingQueue.first().getId() == id){
				return true;
			}	
			waitingQueue.dequeue();

		}

		return false;
	}

	public Course[] studentEnrolled(int i){

		ArrayList<Course> studentEnrolled = new ArrayList<>();

		for(Entry e : hashArray){
			if(e != null && e != defunct){
				Course c = (Course) e.data;

				if(c != null && isStudentEnrolled(c, i) == true){
					studentEnrolled.add(c);
				}

			}
		}

		Course[] arrayCourse = new Course[studentEnrolled.size()];
		return studentEnrolled.toArray(arrayCourse);

	}

	private boolean isStudentEnrolled(Course c, int id){

		LinkedList<Student> enrolledList = c.getStudentsInCourse();

		for(Student s : enrolledList){
			if(s.getId() == id){
				return true;
			}
		}
		return false;
	}

}
import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class Test implements Serializable{
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		LinkedQueueHashTable hashTable = new LinkedQueueHashTable(10); // Initialize the hash table, it will
        // have 10 courses.
		String fileName = "HashTable.ser";
	
		try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))){
		    hashTable = (LinkedQueueHashTable) inputStream.readObject();
		    System.out.println("LinkedQueueHashTable object loaded from " + fileName);
		}catch (IOException | ClassNotFoundException e) {

		}

		int choice;
		do {
			System.out.println("\nEnter your choice: ");
			System.out.println("1- Add a new course. ");
			System.out.println("2- Display students in a course. ");
			System.out.println("3- Add a student to a course. ");
			System.out.println("4- Drop a student from a course. ");
			System.out.println("5- Raise course's capacity. ");
			System.out.println("6- Display student's status. ");
			System.out.println("7- Exit");
			choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			switch (choice) {
			case 1:

				if(hashTable.isFull() == true){
					System.out.println("Array is full!");
					break;
				}

				else{
					//check it is not course with same crn
					// hashTable.addCourse(course);
					System.out.println("Enter course CRN: ");
					int crn = scanner.nextInt();
					scanner.nextLine(); // Consume newline
					System.out.println("Enter course name: ");
					String name = scanner.nextLine();
					System.out.println("Enter course capacity: ");
					int capacity = scanner.nextInt();
					Course course = new Course(crn, name, capacity);

					hashTable.addCourse(course);
					// hashTable.displayTable();
				}

				break;

			case 2:

				// Implement logic of display list of student that enrolled and another list of waiting list
				System.out.println("Enter course CRN: ");
				int courseCRN = scanner.nextInt();

				Course getCourse = hashTable.Search(courseCRN);

				LinkedList<Student> enrolled = getCourse.getStudentsInCourse();

				if(enrolled.isEmpty()){
					System.out.println("The enrolled list for the course is empty!");	
				}

				else{

					System.out.println("Students in Enrolled list: ");

					for(Student s : enrolled){
						System.out.println(s.toString()); // To print the informations about the student.
					}
				}

				Queue<Student> waitingList = getCourse.getStudentsInWating();

				// need to print the students informations in the wiating list.

				if(waitingList.isEmpty()){
					System.out.println("The waitiing list for the course is empty!");
				}

				else{

					System.out.println("Studnents in Watiting list: ");

					for(int i = 0; i < waitingList.size(); i++){

						Student tmp = waitingList.dequeue();
						System.out.println(tmp.toString());
						waitingList.enqueue(tmp);

					}
				}

				break;

			case 3:
				// Implement logic to add a student to a course
				System.out.println("Enter course CRN: ");
				int addStudent = scanner.nextInt();
				System.out.println("Enter student ID: ");
				int sID = scanner.nextInt();

				scanner.nextLine();

				System.out.println("Enter student name: ");
				
				String sName = scanner.nextLine();
				
				Student student = new Student(sID, sName);

				hashTable.addStudent(addStudent, student);

				break;
			case 4:
			
				// Implement logic to drop a student from a course

				System.out.println("Enter course CRN: ");
				int getcrn = scanner.nextInt();

				System.out.println("Enter student ID: ");
				int stduentID = scanner.nextInt();

				hashTable.dropStudent(stduentID, getcrn);

				break;
			case 5:
				System.out.println("Enter course CRN:");
		        int CRN = scanner.nextInt();
		        System.out.println("Enter course raise Capacity:");
		        int r = scanner.nextInt();
				hashTable.raiseCapacity(CRN,r);
				
				break;
			case 6:
				// Implement logic to display student's status



				System.out.println("Enter student ID: ");
				int sid = scanner.nextInt();

				Course[] coursesEnrolled = hashTable.studentEnrolled(sid);


				if(coursesEnrolled.length == 0){
					System.out.println("Enrolled courses for student with ID : " + sid + " = 0");
				}
				 
				else{
					System.out.println("Enrolled students for student with ID " + sid + " : ");
					for (Course course1 : coursesEnrolled) {
						System.out.println("Course Name: " + course1.getName());
					}
				}


				Course[] coursesWaiting = hashTable.studentWaiting(sid);

				if(coursesWaiting.length == 0){
					System.out.println("Waiting list courses for student with ID : " + sid + " = 0");
				}
				 
				else{
					System.out.println("waiting students for student with ID " + sid + " : ");
				    for (Course course1 : coursesWaiting) {
				        System.out.println("Course Name: " + course1.getName());
				    }
				}

				break;
			case 7:
				try {
					FileOutputStream fileOut = new FileOutputStream("HashTable.ser");
					ObjectOutputStream out = new ObjectOutputStream(fileOut);
					out.writeObject(hashTable);
					out.close();
					fileOut.close();
					System.out.println("LinkedQueueHashTable object saved to HashTable.ser");
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			default:
				System.out.println("Invalid choice. Please enter a number between 1 and 7.");
			}
		} while (choice != 7);

		scanner.close();
	}

}
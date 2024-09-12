import java.io.Serializable;
import java.util.LinkedList;

public class Course implements Serializable{
    
    // course id
    private int CRN;
    private String name;
    //  maximum number of students who can register in this course
    private int capacity;

    // holds the students who are registered in this course
    private LinkedList<Student> studentsInCourse;

    // holds the students who are in waiting list
    private LinkedQueue<Student> studentsInWating;

    

    public Course(int cRN, String name, int capacity) {
        CRN = cRN;
        this.name = name;
        this.capacity = capacity;
        this.studentsInCourse = new LinkedList<>();
        this.studentsInWating = new LinkedQueue<>();
    }

    public int getCRN() {
        return CRN;
    }

    public void setCRN(int cRN) {
        CRN = cRN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public LinkedList<Student> getStudentsInCourse() {
        return studentsInCourse;
    }

    public void setStudentsInCourse(LinkedList<Student> studentsInCourse) {
        this.studentsInCourse = studentsInCourse;
    }

    public LinkedQueue<Student> getStudentsInWating() {
        return studentsInWating;
    }

    public void setStudentsInWating(LinkedQueue<Student> studentsInWating) {
        this.studentsInWating = studentsInWating;
    }

	@Override
	public String toString() {
		return "Course [CRN=" + CRN + ", name=" + name + ", capacity=" + capacity + ", studentsInCourse="
				+ studentsInCourse + ", studentsInWating=" + studentsInWating + "]";
	}

}
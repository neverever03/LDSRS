package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Section {
	private int sectionNo;
	private String dayOfWeek;
	private String timeOfDay;
	private String room;
	private int seatingCapacity;
	private Course representedCourse;
	private ScheduleOfClasses offeredIn;
	private Professor instructor;
	private Professor professor;//教师

	private ArrayList<Student> enrolledStudents;//注册学生HashMap存储学生对象引用
	private HashMap<Student, TranscriptEntity> assignedGrades; 
	public Section() {
		super();
	}
	public Section(int sectionNo, String dayOfWeek, String timeOfDay, String room, int capacity, Course course,
			Professor professor) {
		super();
		this.sectionNo = sectionNo;
		this.dayOfWeek = dayOfWeek;
		this.timeOfDay = timeOfDay;
		this.room = room;
		this. seatingCapacity = capacity;
		this. representedCourse = course;
		this.professor = professor;
		this.enrolledStudents = new ArrayList<Student>();
	}
/*	public Section(int sNo, char day, String time, Course course,
		       String room, int capacity) {
		setSectionNo(sNo);
		setDayOfWeek(day);
		setTimeOfDay(time);
		setRepresentedCourse(course);
		setRoom(room);
		setSeatingCapacity(capacity);
		setInstructor(null);
		enrolledStudents = new HashMap<String, Student>();
		assignedGrades = new HashMap<Student, TranscriptEntity>();
	}*/



	public Section(int i, char c, String string, Course c2, String string2, int j) {
		// TODO Auto-generated constructor stub
	}
	public HashMap<Student, TranscriptEntity> getAssignedGrades() {
		return assignedGrades;
	}

	public void setAssignedGrades(HashMap<Student, TranscriptEntity> assignedGrades) {
		this.assignedGrades = assignedGrades;
	}

	public void setSectionNo(int no) {
		sectionNo = no;
	}
	
	public int getSectionNo() {
		return sectionNo;
	}
	

		
	public String getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	public void setTimeOfDay(String time) {
		timeOfDay = time;
	}
	
	public String getTimeOfDay() {
		return timeOfDay;
	}

	public void setInstructor(Professor prof) {
		instructor = prof;
	}
	
	public Professor getInstructor() {
		return instructor;
	}
	
	public void setRepresentedCourse(Course c) {
		representedCourse = c;
	}
	
	public Course getRepresentedCourse() {
		return representedCourse;
	}		

	public void setRoom(String r) {
		room = r;
	}

	public String getRoom() {
		return room;
	}

	public void setSeatingCapacity(int c) {
		seatingCapacity = c;
	}

	public int getSeatingCapacity() {
		return seatingCapacity;
	}

	public void setOfferedIn(ScheduleOfClasses soc) {
		offeredIn = soc;
	}

	public ScheduleOfClasses getOfferedIn() {
		return offeredIn;
	}	


	public String toString() {
		return getRepresentedCourse().getCourseNo() + " - " +
		       getSectionNo() + " - " +
		       getDayOfWeek() + " - " +
		       getTimeOfDay();
	}



	public String getFullSectionNo() {
		return getRepresentedCourse().getCourseNo() + 
		       " - " + getSectionNo();
	}
	public ArrayList<Student> getEnrolledStudents() {
		return enrolledStudents;
	}
	public void setEnrolledStudents(ArrayList<Student> enrolledStudents) {
		this.enrolledStudents = enrolledStudents;
	}

	/*public EnrollmentStatus enroll(Student s) {

		
		ArrayList<TranscriptEntity> transcript = s.getTranscript();

		if (s.isCurrentlyEnrolledInSimilar(this) || 
		    transcript.verifyCompletion(this.getRepresentedCourse())) {
			return EnrollmentStatus.prevEnroll;
		}

		Course c = this.getRepresentedCourse();
		if (c.hasPrerequisites()) {
			for (Course pre : c.getPrerequisites()) {

				if (!transcript.verifyCompletion(pre)) {
					return EnrollmentStatus.prereq;
				}
			}
		}

		if (!this.confirmSeatAvailability()) {
			return EnrollmentStatus.secFull;
		}
enrolledStudents.put(s.getSsn(), s);
		s.addSection(this);
		return EnrollmentStatus.success;
	}
*/
		
	private boolean confirmSeatAvailability() {
		if (enrolledStudents.size() < getSeatingCapacity()) return true;
		else return false;
	}

	public boolean drop(Student s) {

		if (!s.isEnrolledIn(this)) return false;
		else {

			enrolledStudents.remove(s.getSsn());


			s.dropSection(this);
			return true;
		}
	}

	public int getTotalEnrollment() {
		return enrolledStudents.size();
	}	

	/*public void display() {
		System.out.println("Section Information:");
		System.out.println("\tSemester:  " + getOfferedIn().getSemester());
		System.out.println("\tCourse No.:  " + 
				   getRepresentedCourse().getCourseNo());
		System.out.println("\tSection No:  " + getSectionNo());
		System.out.println("\tOffered:  " + getDayOfWeek() + 
				   " at " + getTimeOfDay());
		System.out.println("\tIn Room:  " + getRoom());
		if (getInstructor() != null) 
			System.out.println("\tProfessor:  " + 
				   getInstructor().getName());
		displayStudentRoster();
	}
	*/
/*	public void displayStudentRoster() {
		System.out.print("\tTotal of " + getTotalEnrollment() + 
				   " students enrolled");


		if (getTotalEnrollment() == 0) System.out.println(".");
		else System.out.println(", as follows:");

		for (Student s : enrolledStudents.values()) {
			System.out.println("\t\t" + s.getName());
		}
	}
*/

	public String getGrade(Student s) {
		String grade = null;

		TranscriptEntity te = assignedGrades.get(s);
		if (te != null) {
			grade = te.getGrade(); 
		}


		return grade;
	}

	public boolean postGrade(Student s, String grade) {

		if (!TranscriptEntity.validateGrade(grade)) return false;


		if (assignedGrades.get(s) != null) return false;


		TranscriptEntity te = new TranscriptEntity(s, grade, this);


		assignedGrades.put(s, te);

		return true;
	}
	
	public boolean isSectionOf(Course c) {
		if (c == representedCourse) return true;
		else return false;
	}

	public Course getCourse() {
		return representedCourse;
	}
	public void setCourse(Course course) {
		this.representedCourse = course;
	}


	
	
}

package model;

import java.util.ArrayList;
import java.util.Collection;

public class Student extends Person {
	//------------
	// Attributes.
	//------------

	private String major;
	private String degree;

	private ArrayList<Section> attends; 
	private ArrayList<Course> planOfStudy;//学生课表
	private ArrayList<TranscriptEntity> transcript;//成绩单

		public Student(String degree, String major, ArrayList<TranscriptEntity> transcript, ArrayList<Section> attends,String password,ArrayList<Course> planOfStudy) {
			super();
			this.degree = degree;
			this.major = major;
			
	
			attends = new ArrayList<Section>();
			planOfStudy=new ArrayList<Course>();
			transcript = new ArrayList<TranscriptEntity>();
		}

		public Student(String ssn, String name,String degree,String major) {
			super(ssn, name);
			this.degree=degree;
			this.major=major;
			// TODO Auto-generated constructor stub
		}	

	public void setMajor(String major) {
		this.major = major;
	}

	public String getMajor() {
		return major;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getDegree() {
		return degree;
	}
	public ArrayList<TranscriptEntity> getTranscript() {
		return transcript;
	}
	public void setTranscript(ArrayList<TranscriptEntity> transcript) {
		this.transcript = transcript;
	}

	public void display() {
		super.display();
		System.out.println("Student-Specific Information:");
		System.out.println("\tMajor:  " + this.getMajor());
		System.out.println("\tDegree:  " + this.getDegree());
		this.displayCourseSchedule();


		System.out.println();
	}	
	

	public String toString() {
		return this.getName() + " (" + this.getSsn() + ") [" + this.getDegree() +
		       " - " + this.getMajor() + "]";
	}

	public void displayCourseSchedule() {
		System.out.println("Course Schedule for " + this.getName());

		for (Section s : attends) {

            
                    if (s.getGrade(this) == null) {
			System.out.println("\tCourse No.:  " + 
				s.getRepresentedCourse().getCourseNo());
			System.out.println("\tSection No.:  " + 
				s.getSectionNo());
			System.out.println("\tCourse Name:  " + 
				s.getRepresentedCourse().getCourseName());
			System.out.println("\tMeeting Day and Time Held:  "  +
				s.getDayOfWeek() + " - " +
				s.getTimeOfDay());
			System.out.println("\tRoom Location:  "  +
				s.getRoom());
			System.out.println("\tProfessor's Name:  " +
				s.getInstructor().getName());
			System.out.println("\t-----");
		    }
		}
	}
	
	public void addSection(Section s) {
		attends.add(s);
	}
	
	public void dropSection(Section s) {
		attends.remove(s);
	}
	
	public boolean isEnrolledIn(Section s) {
		if (attends.contains(s)) return true;
		else return false;
	}
		
	public boolean isCurrentlyEnrolledInSimilar(Section s1) {
		boolean foundMatch = false;

		Course c1 = s1.getRepresentedCourse();

		for (Section s2 : attends) {
			Course c2 = s2.getRepresentedCourse();
			if (c1.getCourseNo().equals(c2.getCourseNo())) {
			
				if (s2.getGrade(this) == null) {

					foundMatch = true;
					break;
				}
			}
		}

		return foundMatch;
	}
	
	

	public Collection<Section> getEnrolledSections() {
		return attends;
	}
	public ArrayList<Section> getAttends() {
		return attends;
	}


	public void setAttends(ArrayList<Section> attends) {
		this.attends = attends;
	}


	public void setPlanOfStudy(ArrayList<Course> planOfStudy) {
		this.planOfStudy = planOfStudy;
	}


	public ArrayList<Course> getPlanOfStudy() {
		return planOfStudy;
	}



	
}

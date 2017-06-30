package model;

import java.util.HashMap;

public class ScheduleOfClasses {

	private String semester;


	private HashMap<String, Section> sectionsOffered; 

	public ScheduleOfClasses(String semester) {
		setSemester(semester);

		sectionsOffered = new HashMap<String, Section>();
	}


	public ScheduleOfClasses(String semester2, HashMap<String, Section> findBySemester) {
			}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getSemester() {
		return semester;
	}

	public HashMap<String, Section> getSectionsOffered() {
		return sectionsOffered;
	}


	/*public void display() {
		System.out.println("Schedule of Classes for " + getSemester());
		System.out.println();

		for (Section s : sectionsOffered.values()) {
			s.display();
			System.out.println();
		}
	}*/

	public void addSection(Section s) {
		// We formulate a key by concatenating the course no.
		// and section no., separated by a hyphen.

		String key = s.getRepresentedCourse().getCourseNo() + 
			     " - " + s.getSectionNo();
		sectionsOffered.put(key, s);

		// Bidirectionally link the ScheduleOfClasses back to the Section.

		s.setOfferedIn(this);
	}

	// The full section number is a concatenation of the
	// course no. and section no., separated by a hyphen;
	// e.g., "ART101 - 1".

	public Section findSection(String fullSectionNo) {
		return sectionsOffered.get(fullSectionNo);
	}

	public boolean isEmpty() {
		if (sectionsOffered.size() == 0) return true;
		else return false;
	}
}

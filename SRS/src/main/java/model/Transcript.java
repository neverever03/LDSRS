package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Transcript {

	private ArrayList<TranscriptEntity> transcriptEntries; 
	private Student studentOwner;


	private HashMap<String,ArrayList<TranscriptEntity>> map;
	public Transcript() {
		super();
		map=new HashMap<String,ArrayList<TranscriptEntity>>();
	}

	public void setStudentOwner(Student s) {
		studentOwner = s;
	}

	public Student getStudentOwner() {
		return studentOwner;
	}


	public boolean verifyCompletion(Course c) {
		boolean outcome = false;

		for (TranscriptEntity te : transcriptEntries) {
			Section s = te.getSection();

			if (s.isSectionOf(c)) {

			    if (TranscriptEntity.passingGrade(te.getGrade())) {
				outcome = true;

				break;
			    }
			}
		}

		return outcome;
	}

	public ArrayList<TranscriptEntity> addTranscriptEntry(TranscriptEntity te) {
		transcriptEntries.add(te);
		return transcriptEntries;
	}

	public List<TranscriptEntity> display() {
		System.out.println("Transcript for:  " +
				   getStudentOwner().toString());

		if (transcriptEntries.size() == 0) {
			System.out.println("\t(no entries)");
		}
		else for (TranscriptEntity te : transcriptEntries) {
			Section sec = te.getSection();

			Course c = sec.getRepresentedCourse();

			ScheduleOfClasses soc = sec.getOfferedIn();

			System.out.println("\tSemester:        " +
					   soc.getSemester());
			System.out.println("\tCourse No.:      " +
					   c.getCourseNo());
			System.out.println("\tCredits:         " +
					   c.getCredits());
			System.out.println("\tGrade Received:  " +
					   te.getGrade());
			System.out.println("\t-----");
		}
		return transcriptEntries;
	}

	
}

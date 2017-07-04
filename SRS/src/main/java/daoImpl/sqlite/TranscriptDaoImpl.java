package daoImpl.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import dao.TranscriptDao;
import model.Course;
import model.ISpecification;
import model.IfPlanSpecification;
import model.Section;
import model.Student;
import model.Transcript;
import model.TranscriptEntity;
import model.User;
import util.DBUtil;

public class TranscriptDaoImpl implements TranscriptDao {
	private Transcript transcript;
	@Override
	public HashMap<String, TranscriptEntity> findAll() {
		Connection Conn = DBUtil.getSqliteConnection();
		HashMap<String, Student> students = new PersonDaoImpl().findAllStudents();
		HashMap<String, Section> sections = new SectionDaoImpl().findAll();
		HashMap<String, TranscriptEntity> transcripts = new HashMap<String, TranscriptEntity>();
		String sql = "select * from Transcript";
		try{
			PreparedStatement pstmt = Conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String str = rs.getString(2);
				Student student = students.get(rs.getString(1));
				Section section = sections.get(str.substring(str.indexOf("-")+2,str.length()));
				TranscriptEntity transcriptEntry = new TranscriptEntity(student, rs.getString(3), section);
				transcripts.put(section.getFullSectionNo(), transcriptEntry);
			}
			if(rs != null){
				rs.close();
				pstmt.close();
				Conn.close();
			} 
		}catch (SQLException e) {
				
			e.printStackTrace();
		}		
		return transcripts;
	}
	

	@Override
	public HashMap<String, TranscriptEntity> getTranscriptBySsn(User user) {
		Connection Conn = DBUtil.getSqliteConnection();
		HashMap<String, Student> students = new PersonDaoImpl().findAllStudents();
		HashMap<String, Section> sections = new SectionDaoImpl().findAll();
		HashMap<String, TranscriptEntity> transcripts = new HashMap<String, TranscriptEntity>();
		String sql = "select * from Transcript where StudentSsn=?";
		try{
			PreparedStatement pstmt = Conn.prepareStatement(sql);
			pstmt.setString(1, user.getSsn());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String str = rs.getString(2);
				Student student = students.get(rs.getString(1));
				Section section = sections.get(str.substring(str.indexOf("-")+2,str.length()));
				TranscriptEntity transcriptEntry = new TranscriptEntity(student, rs.getString(3), section);
				transcripts.put(section.getFullSectionNo(), transcriptEntry);
			}
			if(rs != null){
				rs.close();
				pstmt.close();
				Conn.close();
			} 
		}catch (SQLException e) {
			
		e.printStackTrace();
	}		
	return transcripts;
	}
	

	@Override
	public HashMap<String, TranscriptEntity> findTranscriptByStudent(User user) {
		HashMap<String, TranscriptEntity> studentTranscripts = new HashMap<String, TranscriptEntity>();
		HashMap<String, TranscriptEntity> transcripts = new TranscriptDaoImpl().findAll();
		for (Entry<String,TranscriptEntity> t : transcripts.entrySet()) {
			String str = t.getKey();
			String sectionNo = str.substring(str.indexOf("-")+2,str.length());
			Section section = new SectionDaoImpl().findAll().get(sectionNo);
			TranscriptEntity transcriptEntry = t.getValue();
			Student student = transcriptEntry.getStudent();
			student.addSection(section);
			if (user.getSsn().equals(student.getSsn())) {
				studentTranscripts.put(transcriptEntry.getSection().getFullSectionNo(), transcriptEntry);
			}
		}
		return studentTranscripts;
	}

	//选课
	@Override
	public void addTranscript(TranscriptEntity transcriptentry) {
		Connection Conn = DBUtil.getSqliteConnection();
		Student student = transcriptentry.getStudent();
		Section section = transcriptentry.getSection();
		ISpecification ifPlanspec = new IfPlanSpecification();
		ISpecification ifSelectspec = new IfPlanSpecification();
		ISpecification ifPrevPassspec = new IfPlanSpecification();	
     String  result1=ifPlanspec.validate(student, section);
     String  result2=ifSelectspec.validate(student, section);
     String  result3=ifPrevPassspec.validate(student, section);
       if(result1==null&&  result2==null&&result3==null){
    	   
    	   
   		String sql = "INSERT INTO Transcript(studentSsn,fullSectionNo,grade) VALUES(?,?,?)";
   		PreparedStatement stmt = null;
   		try {
   			stmt = Conn.prepareStatement(sql);
   			stmt.setString(1, student.getSsn());
   			stmt.setString(3, transcriptentry.getGrade());
   			stmt.setString(2, section.getFullSectionNo());
   			stmt.executeUpdate();
   			stmt.close();
   			Conn.close();
   		} catch (SQLException e) {
   			e.printStackTrace();
   		}
   	}
       }

		
	

	@Override
	public HashMap<String, TranscriptEntity> getBysection(Section se) {
		Connection Conn = DBUtil.getSqliteConnection();
		HashMap<String, Section> sections = new SectionDaoImpl().findAll();
		HashMap<String, Student> students = new PersonDaoImpl().findAllStudents();
		HashMap<String, TranscriptEntity> transcripts = new HashMap<String, TranscriptEntity>();
		String sql = "select * from Transcript where fullSectionNo=?";
		try{
			PreparedStatement pstmt = Conn.prepareStatement(sql);
			pstmt.setString(1, se.getFullSectionNo());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String str = rs.getString(2);
				Student student = students.get(rs.getString(1));
				Section section = sections.get(str.substring(str.indexOf("-")+2,str.length()));
				TranscriptEntity transcriptEntry = new TranscriptEntity(student, rs.getString(3), section);
				transcripts.put(section.getFullSectionNo(), transcriptEntry);
			}
			if(rs != null){
				rs.close();
				pstmt.close();
				Conn.close();
			} 
		}catch (SQLException e) {
			
		e.printStackTrace();
	}		
	return transcripts;
	}

	@Override
	public void deleteTranscript(String fullSectionNo) {
		Connection Conn = DBUtil.getSqliteConnection();
		String sql = "DELETE FROM Transcript WHERE name=? and sectionID=?  ";
		PreparedStatement stmt = null;
		try {
			stmt = Conn.prepareStatement(sql);
			stmt.setString(1, fullSectionNo);
			stmt.executeUpdate();
			stmt.close();
			Conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	

	@Override
	public void updateTranscript(String fullSectionNo, String ssn ,String grade) {
		Connection Conn = DBUtil.getSqliteConnection();
		String sql = "update Transcript set grade=? where  fullSectionNo=? StudentSsn=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = Conn.prepareStatement(sql);
			pstmt.setString(1, ssn);
			pstmt.setString(2, fullSectionNo);
			pstmt.setString(3, grade);
			pstmt.executeUpdate();
			pstmt.close();
			Conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}


	@Override
	public List<TranscriptEntity> getTranscript() {
		// TODO Auto-generated method stub
		return null;
	}


	
	

	

}

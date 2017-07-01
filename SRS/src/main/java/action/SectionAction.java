package action;

import java.util.HashMap;
import java.util.Map.Entry;

import com.opensymphony.xwork2.ActionSupport;

import dao.CourseDao;
import dao.DaoFactory;
import dao.PersonDao;
import dao.SectionDao;
import dao.TranscriptDao;
import daoImpl.sqlite.SectionDaoImpl;
import model.Course;
import model.EnrollmentStatus;
import model.Professor;
import model.Section;
import model.Student;
import model.TranscriptEntity;
import model.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.CourseService;
import service.PersonService;
import service.SectionService;



public class SectionAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JSONObject jsonObject = new JSONObject();
	private JSONArray jsonArray = new JSONArray();
	private String sectionNo;
	private String dayOfWeek;
	private String timeOfDay;
	private String room;
	private int seatingCapacity;
	private String professorSsn;
	private String courseNo;
	private String semester;
	private String ssn;

	public String findAllSection(){
		SectionService sectionService = new SectionService();
		HashMap<String,Section> sections = sectionService.findAll();
		for(Entry<String,Section> s : sections.entrySet()){
			JSONObject jo = new JSONObject();
			jo.put("sectionNo", s.getValue().getSectionNo());
			jo.put("dayOfWeek", s.getValue().getDayOfWeek());
			jo.put("timeOfDay", s.getValue().getTimeOfDay());
			jo.put("room", s.getValue().getRoom());
			jo.put("seatingCapacity", s.getValue().getSeatingCapacity());
			jo.put("professor", s.getValue().getInstructor().getName());
			jo.put("course", s.getValue().getRepresentedCourse().getCourseName());
			jsonArray.add(jo);
		}
		return "jsonArray";
	}

	public String addSection(){
		jsonObject.put("status", "ok");
		PersonService personService = new PersonService();
		CourseService courseService = new CourseService();
		HashMap<String, Professor> professors = personService.findAllProfessors();
		Professor professor = professors.get(professorSsn);
		HashMap<String, Course> courses = courseService.findAll();
		Course course = courses.get(courseNo);
		Section section =new Section(Integer.parseInt(sectionNo), dayOfWeek, timeOfDay  ,room ,seatingCapacity, course,professor);
	
		SectionDao sectionDao =  DaoFactory.createSectionDao();
		sectionDao.addSection(section,professor,semester);
		return "jsonObject";
	}

	/*public String addTranscript(){
		jsonObject.put("status", "ok");
		SectionDao sectionDao =  DaoFactory.createSectionDao();
		PersonDao personDao =  DaoFactory.createPersonDao();
		TranscriptDao transcriptDao = DaoFactory.createTranscriptDao();
		User user = new User();
		user.setSsn(ssn);
		HashMap<String,Section> sections = sectionDao.findAll();
	    Section section = sections.get(sectionNo);
	    Student student = personDao.findAllStudents().get(ssn);
	    EnrollmentStatus status = section.enroll(student);
	   
	   if(status.value().equals("Enrollment successful!")){
		   TranscriptEntity transcriptEntry = new TranscriptEntity(student, null, section);
		   transcriptDao.addTranscript(transcriptEntry);
		   SectionDao sectionDaoImpl = new SectionDaoImpl();
		   sectionDaoImpl.updateSection(section);
		   System.out.println("选课成功了！");
	   }else{
		   System.out.println(status.value());
	   }
	   return "jsonObject";
	}*/

	public String getBySsn(){
		TranscriptDao transcriptDao = DaoFactory.createTranscriptDao();
		User user = new User();
		user.setSsn(ssn);
		HashMap<String, TranscriptEntity> transcripts = transcriptDao.getTranscriptBySsn(user);
		for(Entry<String,TranscriptEntity> c : transcripts.entrySet()){
			JSONObject jo = new JSONObject();
			jo.put("sectionNo", c.getValue().getSection().getSectionNo());
			jo.put("dayOfWeek", c.getValue().getSection().getDayOfWeek());
			jo.put("timeOfDay", c.getValue().getSection().getTimeOfDay());
			jo.put("room", c.getValue().getSection().getRoom());
			jo.put("professor", c.getValue().getSection().getInstructor().getName());
			jo.put("course", c.getValue().getSection().getRepresentedCourse().getCourseName());
			jo.put("grade", c.getValue().getGrade());
			jsonArray.add(jo);
		}
		return "jsonArray";
	}
	

	public String getProfessorSection(){
		SectionDao sectionDao =  DaoFactory.createSectionDao();
		User user = new User();
		user.setSsn(ssn);
		HashMap<String, Section> sections = sectionDao.findByProfessor(user);
		for(Entry<String,Section> s : sections.entrySet()){
			JSONObject jo = new JSONObject();
			jo.put("sectionNo", s.getValue().getSectionNo());
			jo.put("dayOfWeek", s.getValue().getDayOfWeek());
			jo.put("timeOfDay", s.getValue().getTimeOfDay());
			jo.put("room", s.getValue().getRoom());
			jo.put("seatingCapacity", s.getValue().getSeatingCapacity());
			jo.put("course", s.getValue().getRepresentedCourse().getCourseName());
			jsonArray.add(jo);
		}
		return "jsonArray";
	}
	

	public String getDetail(){
		TranscriptDao transcriptDao = DaoFactory.createTranscriptDao();
		SectionDao sectionDao =  DaoFactory.createSectionDao();
		Section section = sectionDao.findAll().get(sectionNo);
		HashMap<String, TranscriptEntity> transcripts = transcriptDao.getBysection(section);
		for(Entry<String,TranscriptEntity> c : transcripts.entrySet()){
			JSONObject jo = new JSONObject();
			jo.put("name", c.getValue().getStudent().getName());
			jo.put("grade", c.getValue().getGrade());
			jsonArray.add(jo);
		}
		return "jsonArray";
	}
	
	
	

	public JSONObject getJsonObject() {
		return jsonObject;
	}
	public void setJsonObject(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}
	public JSONArray getJsonArray() {
		return jsonArray;
	}
	public void setJsonArray(JSONArray jsonArray) {
		this.jsonArray = jsonArray;
	}
	
	public String getSectionNo() {
		return sectionNo;
	}
	public void setSectionNo(String sectionNo) {
		this.sectionNo = sectionNo;
	}
	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public String getTimeOfDay() {
		return timeOfDay;
	}

	public void setTimeOfDay(String timeOfDay) {
		this.timeOfDay = timeOfDay;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public int getSeatingCapacity() {
		return seatingCapacity;
	}

	public void setSeatingCapacity(int seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}

	public String getProfessorSsn() {
		return professorSsn;
	}

	public void setProfessorSsn(String professorSsn) {
		this.professorSsn = professorSsn;
	}

	public String getCourseNo() {
		return courseNo;
	}

	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	
}

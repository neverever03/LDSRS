package dao;

import java.util.HashMap;
import model.Professor;
import model.Section;
import model.User;


public interface SectionDao {
	//查询所有可选课程
	public HashMap<String, Section> findAll();
	//根据学期查询可选课程
	public HashMap<String,Section> findBySemester(String semester);
	//添加可选课程
	void addSection(Section section,Professor professor,String semester);
	//删除可选课程
	void deleteSection(String FullSectionNo);
	//更新可选课程
	void updateSection(Section section);
	//查询教师教的课程
	public HashMap<String, Section> findByProfessor(User user);
	public void updateSection(String courseNo, String sectionNo, String week, String room, String seat, String time,
			String ssn);

	 
}

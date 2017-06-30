package dao;

import java.util.List;

import model.Course;
import model.Student;

public interface PlanOfStudyDao {
	
	//通过查询所有的planOfStudy
	List<Course> findByStudent(Student student);
	

}

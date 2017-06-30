package dao;

import java.util.HashMap;

import model.Course;

public interface CourseDao {
	
	public Course getByCourseNo(String CourseNo);
	//查询所有课程
	public HashMap<String, Course> findAll();
	//添加课程
	void addCourse(Course course);
	//修改课程
	void updateCourse(Course course);
	//删除课程
	void deleteCourse(Course course);
}

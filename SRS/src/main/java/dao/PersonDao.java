package dao;

import java.util.HashMap;
import java.util.List;

import model.Professor;
import model.Student;


public interface PersonDao {
	//查询所有教师
	public HashMap<String, Professor> findAllProfessors();
	//查询所有学生
	public HashMap<String, Student> findAllStudents();
	//添加教师
	void addProfessor(String type,Professor professor);
	//根据教师信息查询
	List<Professor> searchProfessor(Professor professor);
	//更新教师
	void updateProfessor(Professor professor);
	//删除教师
	void deleteProfessor(Professor professor);

}

package dao;

import java.util.HashMap;
import java.util.List;

import model.Section;
import model.TranscriptEntity;
import model.User;


public interface TranscriptDao {
	//查询所有选课
	public HashMap<String, TranscriptEntity> findAll();
	//查询所有学生选课
	public HashMap<String, TranscriptEntity> findTranscriptByStudent(User user);
	//选课
	public void addTranscript(TranscriptEntity transcriptentry);
	//删除
	public void deleteTranscript(String fullSectionNo);
	//输入
	public void updateTranscript(String fullSectionNo, String ssn , String grade);
	//根据学号查询已选课程
	HashMap<String, TranscriptEntity> getTranscriptBySsn(User user);
	//根据课程号查询
	HashMap<String, TranscriptEntity> getBysection(Section se);
	public List<TranscriptEntity> getTranscript();
}

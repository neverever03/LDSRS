package service;

import java.util.HashMap;

import dao.DaoFactory;
import dao.TranscriptDao;
import model.TranscriptEntity;
import model.User;

public class TranscriptService {
	private TranscriptDao dao = DaoFactory.createTranscriptDao();
	
	public HashMap<String, TranscriptEntity> findTranscriptByStudent(User user){
		HashMap<String,TranscriptEntity> transcripts = dao.findTranscriptByStudent(user);
		return transcripts;
	}
}

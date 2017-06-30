package service;

import java.util.List;

import dao.DaoFactory;
import dao.UserDao;
import model.Person;
import model.User;

public class UserService {

	private UserDao dao = DaoFactory.createUserDao();
	public boolean getUser(User user) {
		List<User> users = dao.findAllUser();
		for(User u:users){
			if(u.validatePassword(user.getPassword(),user.getType())){
				return true;
			}
		}
		return false;
		
	}
	public User login(String username,String password) {
		return dao.login(username, password);
	}
	public Person getPerson(String ssn, int type) {
		return dao.getPerson(ssn, type);
	}
	public int addCourseToPerson(String ssn, String sectionno) {
		return dao.addCourseToPerson(ssn, sectionno);
	}
}

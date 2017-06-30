package daoImpl.mock;



import java.util.List;

import dao.UserDao;
import model.Person;
import model.User;

public class UserDaoImpl implements UserDao{



	@Override
	public List<User> findAllUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person getPerson(String ssn, int type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addCourseToPerson(String ssn, String sectionno) {
		// TODO Auto-generated method stub
		return 0;
	}

}

package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import model.Person;
import model.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.UserService;


public class LoginAction extends ActionSupport {
	
	private static final long serialVersionUID = -475753802199201594L;

	private User user;
	private Person person;
	private String ssn;
	private String password;
	private String type;
	private JSONObject jsonObject = new JSONObject();
	private JSONArray jsonArray = new JSONArray();
	public String login(){
		UserService userService = new UserService();
		User u = new User();
		u.setSsn(ssn);
		u.setPassword(password);
		u.setType(type);
		if(userService.getUser(u)==true){
			jsonObject.put("ssn", ssn);
			jsonObject.put("status", "ok");
		}
		return "jsonObject";
	}
	


	public String logout(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.invalidate();
		return SUCCESS;
	}
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	
}

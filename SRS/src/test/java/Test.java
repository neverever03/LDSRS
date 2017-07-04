import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.runner.RunWith;

import model.Course;
import model.Student;
import service.PlanOfStudyService;



public class Test {

	private PlanOfStudyService planOfStudyService;
	
	@org.junit.Test
	public void testPlanOfStudy(){
		//String ssn, String name,String degree,String major
		Student lidan=new Student("09143641"," lidan", "senior", "ec");
			
		List<Course> test = planOfStudyService.findByStudent(lidan);
		
		System.out.println(Arrays.toString(test.toArray()));
		
	}
	
}

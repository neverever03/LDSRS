package model;


public class IfPlanSpecification implements ISpecification<Section> {
	@Override
	public String validate(Student student, Section section) {
		String result=null;
		boolean ifPlan=false;//培养计划是否包含该门课程
		
		for(Course course:student.getPlanOfStudy()){
			if(course.getCourseNo().equals(section.getCourse().getCourseNo())){
				ifPlan=true;
				break;
			}
		}

		if(ifPlan==false){
			result="您的课程计划中不包含这门课程！";
			return result;
		}else if(section.getSeatingCapacity()<=section.getEnrolledStudents().size()){
			result="人数已满！";
			return result;
		}
		return result;
	}
}
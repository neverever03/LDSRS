package model;


public class HasEnrolledBeforeAndNotEndYetSpecification implements ISpecification<Section> {
	@Override
	public String validate(Student student, Section section) {
		String result=null;
		boolean ifPlan=false;//培养计划是否包含该门课程
		boolean ifSelect=false;//是否已选该门课程
		boolean ifPrevCan=false;//这门课是否有先修课程以及是否选过先修课程
		for(Course course:student.getPlanOfStudy()){
			if(course.getCourseNo().equals(section.getCourse().getCourseNo())){
				ifPlan=true;
				break;
			}
		}
		if(ifPlan==true){
			if(student.getAttends()==null){
				
			}else{
				for(Section s:student.getAttends()){
					if(s.getCourse().getCourseNo().equals(section.getCourse().getCourseNo())){
						ifSelect=true;
						break;
					}
				}
			}
		}
		if(ifPlan==true&&ifSelect==false){
			if(section.getCourse().getPrerequisites()!=null){
				for(Course c:section.getCourse().getPrerequisites()){
					ifPrevCan=false;
					if(student.getTranscript()==null){
						
					}else{
						for(TranscriptEntity t:student.getTranscript()){
							if(t.getSection().getCourse().getNumber().equals(c.getCourseNo())&&t.getGrade().startsWith("A") ||t.getGrade().startsWith("B")){
								ifPrevCan=true;
							}
						}
					}
					if(ifPrevCan==false){
						result="先修课程不达标！";
						break;
					}
				}
			}
		}
		if(ifPlan==false){
			result="您的课程计划中不包含这门课程！";
			return result;
		}else if(section.getSeatingCapacity()<=section.getEnrolledStudents().size()){
			result="人数已满！";
			return result;
		}else if(ifSelect==true){
			result="您已经选过这门课程!";
			return result;
		}
		return result;
	}
}
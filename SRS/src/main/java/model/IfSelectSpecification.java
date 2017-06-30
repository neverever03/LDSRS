package model;


public class IfSelectSpecification implements ISpecification<Section> {
	@Override
	public String validate(Student student, Section section) {
		String result=null;
		
		boolean ifSelect=false;//是否已选该门课程


	
			if(student.getAttends()==null){
				
			}else{
				for(Section s:student.getAttends()){
					if(s.getCourse().getCourseNo().equals(section.getCourse().getCourseNo())){
						ifSelect=true;
						break;
					}
				}
			}
		
		if(ifSelect==false){
			if(section.getCourse().getPrerequisites()!=null){
				for(Course c:section.getCourse().getPrerequisites()){
					ifSelect=false;
					if(student.getTranscript()==null){
						
					}else{
						for(TranscriptEntity t:student.getTranscript()){
							if(t.getSection().getCourse().getNumber().equals(c.getCourseNo())&&t.getGrade().startsWith("A") ||t.getGrade().startsWith("B")){
								ifSelect=true;
							}
						}
					}
					if(ifSelect==false){
						result="先修课程不达标！";
						break;
					}
				}
			}
		}
		if(section.getSeatingCapacity()<=section.getEnrolledStudents().size()){
			result="人数已满！";
			return result;
		}else if(ifSelect==true){
			result="您已经选过这门课程!";
			return result;
		}
		return result;
	}
}
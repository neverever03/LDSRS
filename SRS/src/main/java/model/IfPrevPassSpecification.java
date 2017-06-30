package model;


public class IfPrevPassSpecification implements ISpecification<Section> {
	@Override
	public String validate(Student student, Section section) {
		String result=null;

		boolean ifPrevPass=false;//这门课是否有先修课程以及是否选过先修课程


			if(section.getCourse().getPrerequisites()!=null){
				for(Course c:section.getCourse().getPrerequisites()){
					ifPrevPass=false;
					if(student.getTranscript()==null){
						
					}else{
						for(TranscriptEntity t:student.getTranscript()){
							if(t.getSection().getCourse().getNumber().equals(c.getCourseNo())&&t.getGrade().startsWith("A") ||t.getGrade().startsWith("B")){
								ifPrevPass=true;
							}
						}
					}
					if(ifPrevPass==false){
						result="先修课程不达标！";
						break;
					}
				}
			}
			return result;
	}
}


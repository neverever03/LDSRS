package model;

public interface ISpecification<T> {
	//public boolean IsSatisfiedBy(T entity);
	public String validate(Student student, Section section);
}

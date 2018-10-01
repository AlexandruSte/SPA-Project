package SPA;

public class Project {
	private String name;
	private int capacity;
	private String teacher;
	
	//CONSTRUCTORS
	public Project() {
		
	}
	
	public Project(Project p) {
		this.name = p.getName();
		this.capacity = p.getCapacity();
	}
	
	//SETTER
	public void setName(String name) {
		this.name=name;
	}
	
	public void setCapacity(int capacity) {
		this.capacity=capacity;
	}
	
	public void setTeacher(String teacher) {
		this.teacher=teacher;
	}
	
	//GETTERS
	public String getName() {
		return name;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public String getTeacher() {
		return teacher;
	}
	
	//CAPACITY WORK
	public void studentAssigned() {
		capacity--;
	}
}

package SPA;
import java.util.*; 

public class Teacher extends Person{
	private ArrayList<Student> preferences = null;
	public ArrayList<Project> projects = null;
	
	//Constructors
	public Teacher(String name, String email) {
		projects = new ArrayList<Project>();
		preferences = new ArrayList<Student>();
		if(email.contains("@")==true) {
			this.email=email;
			this.name=name;
		}
		else {
			this.email=name;
			this.name=email;
		}
	}
	
	public Teacher(String name) {
		projects = new ArrayList<Project>();
		preferences = new ArrayList<Student>();
		this.name=name;
	}
	
	public Teacher() {
		projects = new ArrayList<Project>();
		preferences = new ArrayList<Student>();
	}
	
	public Teacher(Teacher t) {
		projects = new ArrayList<Project>();
		preferences = new ArrayList<Student>();
		this.name = t.getName();
		this.preferences = t.getPreferences();
		this.projects = t.getProjects();
	}
	
	//SETTERS
	public void setPreferences(Student... args) {
		for(Student arg : args) {
			preferences.add(arg);
		}
	}
	
	//GETTERS
	public int count() {
		return this.projects.size();
	}
	
	//Methods with Projects
	public ArrayList<Project> getProjects(){
		return projects;
	}
	
	public Project createProject(String name, int capacity) {
		var proj = new Project();
		proj.setCapacity(capacity);
		proj.setName(name);
		proj.setTeacher(this.getName());
		projects.add(proj);
		return proj;
	}
	
	public void removeProject(Project p) {
		projects.remove(p);
	}
	
	public void addProject(Project p) {
		projects.add(p);
	}
	
	//Methods with Students
	public ArrayList<Student> getPreferences(){
		return preferences;
	}
	
	public void removeStudent(Student student) {
		preferences.remove(student);
	}
	
	public boolean containsStudentAsPref(Student student) {
		if(preferences.contains(student)==true)
			return true;
		return false;
	}
	
	public void addStudent(Student s) {
		preferences.add(s);
	}
}
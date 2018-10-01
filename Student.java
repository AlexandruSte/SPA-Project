package SPA;
import java.util.*; 

public class Student extends Person {
	private ArrayList<Project> preferences = null;
	private boolean assigned=false;
	
	//Constructors
	public Student(String name, String email) {
		preferences = new ArrayList<Project>();
		if(email.contains("@")==true) {
			this.email=email;
			this.name=name;
		}
		else {
			this.email=name;
			this.name=email;
		}
	}
	
	public Student(String name) {
		preferences = new ArrayList<Project>();
		this.name=name;
	}
	
	public Student() {
		preferences = new ArrayList<Project>();
	}
	
	//SETTERS
	public void setPreferences(Project... args) {
		for(Project arg : args) {
			preferences.add(arg);
		}
	}
	
	public void setAssignment(boolean value) {
		assigned=value;
	}
	
	public void addPreference(Project p) {
		preferences.add(p);
	}
	
	//GETTERS
	
	public boolean isAssigned() {
		return assigned;
	}
	
	public ArrayList<Project> getPreferences(){
		return preferences;
	}
	
	public int nrOfPreferences() {
		return preferences.size();
	}
	
	//Working with List
	public void removePreference(Project p) {
		preferences.remove(p);
	}
}
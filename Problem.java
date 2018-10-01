package SPA;
import java.util.*; 

public class Problem {
	private ArrayList<Student> students = null;
	private ArrayList<Teacher> teachers = null;

	//CONSTRUCTORS
	public Problem() {
		students = new ArrayList<Student>();
		teachers = new ArrayList<Teacher>();
		//finalAssignment = new HashMap<Project, Student>();
	}
	
	//SETTER
	public void setStudents(Student... args) {
		for(var arg : args) {
			students.add(arg);
		}
	}
	
	public void setTeachers(Teacher... args) {
		for(var arg : args) {
			teachers.add(arg);
		}
	}
	
	public void setStudentsList(ArrayList<Student> list) {
		students=new ArrayList<Student>(list);
	}
	
	public void setTeachersList(ArrayList<Teacher> list) {
		teachers=new ArrayList<Teacher>(list);
	}
	
	//GETTERS
	public ArrayList<Student> getStudentList(){
		return students;
	}
	
	public ArrayList<Teacher> getTeacherList(){
		return teachers;
	}
	
	//Working with students
	public Student findStudent(String name) {
		Student p = new Student();
		for(var stud : students) {
			if(stud.getName().equals(name)==true) {
				return stud;
			}
		}
		return p;
	}
	
	public void swapStudents(int i, int j) {
		Collections.swap(students, i, j);
	}
	
	public void removeStudent(Student s) {
		students.remove(s);
	}
	
	public void addStudent(Student s) {
		students.add(s);
	}
	
	//Working with projects
	public Project findProject(String name) {
	Project p = new Project();
	int ok=0;
	for(var teach : teachers) {
		var list = teach.getProjects();
		for(var proj : list) {
			String s = proj.getName();
			if(s.equals(name)==true) {
				p= new Project(proj);
				ok=1;
				break;
			}
		}
		if(ok==1)
			break;
	}
	return p;
}
	
	//Working with teachers
	public Teacher getTeacherWithProjectP(Project p) {
		Teacher teacher = new Teacher();
		int ok=0;
		for(var teach : teachers) {
			for(var proj : teach.getProjects()) {
				if(proj.getName().equals(p.getName())) {
					teacher = new Teacher(teach);
					ok=1;
					break;
				}
			}
			if(ok==1)
				break;
		}
		return teacher;
	}
	
	public void addTeacher(Teacher t) {
		teachers.add(t);
	}
	
	//Display the actual problem and all the data to it
	public String showProblem() {
		String s="";
		if(students.size()==1) {
			s += "One student,";
		}
		else {
			s += students.size() + " students, ";
		}
		if(teachers.size()==1) {
			s += "one teacher, ";
		}
		else {
			s += teachers.size() + " teachers, ";
		}
		int k=1;
		for(var teacher : teachers) {
			s+= "teacher number " + k + " having " + teacher.count();
			if(teacher.count()==1) {
				s+= " project, ";
			}
			else {
				s+= " projects, ";
			}
			k++;
		}
		s+="with capacities : ";
		String capacity;
		for(var teacher : teachers) {
			for(var proj : teacher.projects) {
				capacity="" + proj.getCapacity();
				s+=" c(" + proj.getName() + ") = " + capacity + ", ";
			}
		}
		s+="\n";
		for(int j=0; j<students.size(); j++){
			s+="Student " + students.get(j).getName() + " has preferences : ";
			for(int i=0; i<students.get(j).getPreferences().size(); i++) {
				if(i==students.get(j).getPreferences().size()-1) {
					s+="" + students.get(j).getPreferences().get(i).getName() + ".";
				}
				else {
					s+="" + students.get(j).getPreferences().get(i).getName() + ", ";
				}
			}
			s+="\n";
		}
		for(int j=0; j<teachers.size(); j++){
			s+="Teacher " + teachers.get(j).getName() + " has preferences : ";
			for(int i=0; i<teachers.get(j).getPreferences().size(); i++) {
				if(i==teachers.get(j).getPreferences().size()-1) {
					s+="" + teachers.get(j).getPreferences().get(i).getName() + ".";
				}
				else {
					s+="" + teachers.get(j).getPreferences().get(i).getName() + ", ";
				}
			}
			s+="\n";
		}
		return s;
	}
	
	public String showProjects() {
		String s = " ";
		for(int j=0; j<teachers.size(); j++) {
			for(int i=0; i<teachers.get(j).getProjects().size(); i++) {
				if(i==teachers.get(j).getProjects().size()-1 && j==teachers.size()-1) {
					s+=teachers.get(j).getProjects().get(i).getName() + ".";
				}
				else{
					s+=teachers.get(j).getProjects().get(i).getName() + ", ";
				}
			}
		}
		return s;
	}
	
	public String showStudents() {
		String s = " ";
		for(int i=0; i<students.size(); i++) {
			if(i==students.size()-1) {
				s+=students.get(i).getName() + ".";
			}
			else {
				s+=students.get(i).getName() + ", ";
			}
		}
		return s;
	}
}

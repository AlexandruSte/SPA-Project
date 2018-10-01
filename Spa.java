import java.util.*;
import SPA.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Spa {
	public static void removeProjectPFromStudents(Problem problem, Project p){
		for(var student : problem.getStudentList()) {
			if(student.getPreferences().contains(p) == true) {
				student.removePreference(p);
			}
		}
	}
	
	public static void removeStudentSFromTeachers(Problem problem, Student student) {
		for(var teacher : problem.getTeacherList()) {
			boolean x = teacher.getPreferences().contains(student);
			if(x==true) {
				teacher.removeStudent(student);
			}
		}
	}
	
	public static void sortStudents(Problem problem){
		ArrayList<Student> studentsList = new ArrayList<Student>(problem.getStudentList());
		for(int i=0; i<studentsList.size()-1; i++) {
			for(int j=i+1; j<studentsList.size(); j++) {
				if(studentsList.get(i).nrOfPreferences()>studentsList.get(j).nrOfPreferences()) {
					problem.swapStudents(i, j);
				}
			}
		}
	}
		
	public static boolean isNumber(String s, Scanner sc) {
		int nr=0;
		try {
			 if(s.equals("") || s.equals(null)) {	
				 System.out.println("It has to be a number!");
				 return false;
			 }
			 nr = Integer.parseInt(s);
			 if(nr==0) {
			     System.out.println("Zero is not an option.");
			     return false;
			 }
		 	 else if(nr<0) {
				 System.out.println("Negative numbers are not accepted.");
				 return false;
		 	 }
		}catch(NumberFormatException e) {
			System.out.println("It has to be a number!");
		    return false;
		}
		return true;
	}
	
	public static void readFromKeyboard(Scanner sc, Problem problem) throws InterruptedException, IOException {
		System.out.println("How many students do you want to be there ?");
		String input = "";
		int nrOfStuds, nrOfTeachs, nrOfProjs, nrProjCapacity, nrStudentPref, nrTeacherPref;
		while(true) {
			input = sc.nextLine();
			if(isNumber(input, sc)==true) {
				nrOfStuds=Integer.parseInt(input);
				break;
			}
		}
		for(int i=0; i<nrOfStuds; i++) {
			System.out.println("Write the name of the student number " + (i+1));
			Student s = new Student();
			String name = "";
			while(true) {
				int ok=1;
				name = sc.nextLine();
				for(Student ss:problem.getStudentList()) {
					if(ss.getName().equals(name)==true) {
						ok=0;
						System.out.println("This student already exists.");
					}
				}
				if(ok==1) {
					break;
				}
			}
			s.setName(name);
			problem.addStudent(s);
		}
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		System.out.println("How many teachers would you like there to be?");
		while(true) {
			input = sc.nextLine();
			if(isNumber(input, sc)==true) {
				nrOfTeachs=Integer.parseInt(input);
				break;
			}
		}
		for(int i=0; i<nrOfTeachs; i++) {
			System.out.println("Write the name of the teacher number " + (i+1));
			Teacher t = new Teacher();
			String name = "";
			while(true) {
				int ok=1;
				name = sc.nextLine();
				for(Teacher tt:problem.getTeacherList()) {
					if(tt.getName().equals(name)==true) {
						ok=0;
						System.out.println("This teacher already exists.");
					}
				}
				if(ok==1) {
					break;
				}
			}
			t.setName(name);
			problem.addTeacher(t);
		}
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		System.out.println("Now let's add some projects to those teachers.");
		for(int i=0; i<nrOfTeachs; i++) {
			System.out.println("How many projects should the teacher " + (i+1) + " have?");
			while(true) {
				input = sc.nextLine();
				if(isNumber(input, sc)==true) {
					nrOfProjs=Integer.parseInt(input);
					break;
				}
			}
			for(int j=0; j<nrOfProjs; j++) {
				Project p = new Project();
				System.out.println("Write the name of the project:");
				String name = "";
				while(true) {
					int ok=1;
					name = sc.nextLine();
					for(Project pp:problem.getTeacherList().get(i).getProjects()) {
						if(pp.getName().equals(name)==true) {
							ok=0;
							System.out.println("This project already exists.");
						}
					}
					if(ok==1) {
						break;
					}
				}
				p.setName(name);
				System.out.println("Write the number of people that can work on the project:");
				while(true) {
					input = sc.nextLine();
					if(isNumber(input, sc)==true) {
						nrProjCapacity=Integer.parseInt(input);
						break;
					}
				}
				p.setCapacity(nrProjCapacity);
				Teacher t = problem.getTeacherList().get(i);
				t.addProject(p);
			}
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		}
		System.out.println("Let's see what those students prefer.");
		for(int i=0;i<nrOfStuds;i++) {
			Student student = problem.getStudentList().get(i);
			System.out.println("How many projects would student " + (i+1) + " like to take on?");
			while(true) {
				input = sc.nextLine();
				if(isNumber(input, sc)==true) {
					nrStudentPref=Integer.parseInt(input);
					break;
				}
			}
			System.out.println("The available projects are : " + problem.showProjects());
			System.out.println("Write the name of the projects : ");
			for(int j=0; j<nrStudentPref; j++) {
				String name = "";
				while(true) {
					int ok=1;
					name = sc.nextLine();
					for(Project pp:problem.getStudentList().get(i).getPreferences()) {
						if(pp.getName().equals(name)==true) {
							ok=0;
							System.out.println("He already took that project.");
						}
					}
					if(ok==1) {
						break;
					}
				}
				Project p = new Project();
				p=problem.findProject(name);
				student.addPreference(p);
			}
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		}
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		System.out.println("Finally, let's add teachers preferences!");
		for(int i=0; i<nrOfTeachs; i++) {
			System.out.println("How many students would teacher " + (i+1) + " like to take on?");
			while(true) {
				input = sc.nextLine();
				if(isNumber(input, sc)==true) {
					nrTeacherPref=Integer.parseInt(input);
					break;
				}
			}
			System.out.println("The available students are : " + problem.showStudents());
			System.out.println("Write the name of the students : ");
			Teacher teacher = problem.getTeacherList().get(i);
			for(int j=0; j<nrTeacherPref; j++) {
				String name = "";
				while(true) {
					int ok=1;
					name = sc.nextLine();
					for(Student ss:problem.getTeacherList().get(i).getPreferences()) {
						if(ss.getName().equals(name)==true) {
							ok=0;
							System.out.println("He already took that student.");
						}
					}
					if(ok==1) {
						break;
					}
				}
				Student s = new Student();
				s=problem.findStudent(name);
				teacher.addStudent(s);
			}
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		}
		System.out.println(problem.showProblem());
	}
	
	public static void readMyInput(Problem problem) {
		Student s1 = new Student("S1", "s1@info.uaic.ro");
		Student s2 = new Student("S2", "s2@info.uaic.ro");
		Student s3 = new Student("S3", "s3@info.uaic.ro");
	    Student s4 = new Student("S4", "s4@info.uaic.ro");
   	    Student s5 = new Student("S5", "s5@info.uaic.ro");
	    Student s6 = new Student("S6", "s6@info.uaic.ro");
		Student s7 = new Student("S7", "s7@info.uaic.ro");
	   
 	    Teacher t1 = new Teacher("T1", "t1@info.uaic.ro");
		Teacher t2 = new Teacher("T2", "t2@info.uaic.ro");
		Teacher t3 = new Teacher("T3", "t3@info.uaic.ro");
		   
		Project p1 = t1.createProject("P1", 2);
		Project p2 = t1.createProject("P2", 1);
		Project p3 = t1.createProject("P3", 1);
		Project p4 = t2.createProject("P4", 1);
		Project p5 = t2.createProject("P5", 1);
		Project p6 = t2.createProject("P6", 1);
		Project p7 = t3.createProject("P7", 1);
		Project p8 = t3.createProject("P8", 1);
		   
		s1.setPreferences(p1, p7);
		s2.setPreferences(p1, p2, p3, p4, p5, p6);
		s3.setPreferences(p2, p1, p4);
		s4.setPreferences(p7);
		s5.setPreferences(p1, p2, p3, p4);
		s6.setPreferences(p2, p3, p4, p5, p6);
		s7.setPreferences(p5, p3, p8);

		t1.setPreferences(s7, s4, s1, s3, s2, s5, s6); 
		t2.setPreferences(s3, s2, s6, s7, s5); 
		t3.setPreferences(s1, s7, s4); 
		   
	    problem.setStudents(s1, s2, s3, s4, s5, s6, s7);
	    problem.setTeachers(t1, t2, t3);
	    System.out.println(problem.showProblem());
	}
	
	public static void readFromFile(Problem problem, Scanner sc, File file) {
		String input = "";
		int nrOfStuds=0, nrOfTeachs=0, nrOfProjs=0, nrProjCapacity=0, nrStudentPref=0, nrTeacherPref=0;
		int ok=1;
		if(sc.hasNextLine()==false)
			System.out.println("Not enough information provided. (Students nr) ");
		else {
			input = sc.nextLine();
			try {
				nrOfStuds=Integer.parseInt(input);
			}catch(NumberFormatException e){
				ok=0;
				System.out.println("There should have been a variable for students number.");
			}
			for(int i=0; i<nrOfStuds; i++) {
			Student s = new Student();
				String name = "";
				if(sc.hasNextLine()==false)
					ok=0;
				else {
					try {
						name = sc.nextLine();
						int a = Integer.parseInt(name);
						ok=0;
						System.out.println("Student's name --- can't be made out of digits.");
					}catch(NumberFormatException e){
						s.setName(name);
						problem.addStudent(s);
					}
				}
				if(ok==0) {
					System.out.println("Not enough information provided. (Students name) ");
					break;
				}
			}
			if(ok==1) {
				if(sc.hasNextLine()==false)
					System.out.println("Not enough information provided. (Teachers nr)");
				else {
					input = sc.nextLine();
					try {
						nrOfTeachs=Integer.parseInt(input);
					}catch(NumberFormatException e){
						ok=0;
						System.out.println("There should have been a variable for teachers number.");
					}
					if(ok==1) {
						for(int i=0; i<nrOfTeachs; i++) {
							Teacher t = new Teacher();
							String name = "";
							if(sc.hasNextLine()==false)
								ok=0;
							else {
								try {
									name = sc.nextLine();
									int a = Integer.parseInt(name);
									ok=0;
									System.out.println("Teacher's name --- can't be made out of digits.");
								}catch(NumberFormatException e){
									t.setName(name);
									problem.addTeacher(t);
								}
							}
							if(ok==0) {
								System.out.println("Not enough information provided. (Teachers name)");
								break;
							}
						}
					}
					if(ok==1) {
						for(int i=0; i<nrOfTeachs; i++) {
							if(sc.hasNextLine()==false) {
								System.out.println("Not enough information provided. (Teachers projs)");
								ok=0;
							}
							else{
								input = sc.nextLine();
								try {
									nrOfProjs=Integer.parseInt(input);
								}catch(NumberFormatException e){
									ok=0;
									System.out.println("There should have been a variable for the number of "
											+ "projects the teacher should have.");
								}
								if(ok==1) {
									for(int j=0; j<nrOfProjs; j++) {
										Project p = new Project();
										String name = "";
										if(sc.hasNextLine()==false)
											ok=0;
										else{
											try {
												name = sc.nextLine();
												int a = Integer.parseInt(name);
												ok=0;
												System.out.println("Porject name can't be made out of digits.");
											}catch(NumberFormatException e){
												p.setName(name);
											}
											if(sc.hasNextLine()==false) {
												System.out.println("Not enough information provided."
														+ "(Proj capacity)");
												ok=0;
											}
											else {
												input = sc.nextLine();
												try {
													nrProjCapacity=Integer.parseInt(input);
												}catch(NumberFormatException e){
													ok=0;
													System.out.println("There should have been a variable for "
															+ "the number of students that cand attend the project.");
												}
												p.setCapacity(nrProjCapacity);
												Teacher t = problem.getTeacherList().get(i);
												t.addProject(p);
											}
										}
										if(ok==0) {
											System.out.println("Not enough information provided.");
											break;
										}
									}
								}
							}
							if(ok==0) {
								break;
							}
						}
						if(ok==1) {
							for(int i=0;i<nrOfStuds;i++) {
								Student student = problem.getStudentList().get(i);
								if(sc.hasNextLine()==false) {
									System.out.println("Not enough information provided. (Stud pref)");
									ok=0;
								}
								else {
									input = sc.nextLine();
									try {
										nrStudentPref=Integer.parseInt(input);
									}catch(NumberFormatException e){
										ok=0;
										System.out.println("There should have been a variable for the projects a "
												+ "student wants to take.");
									}
									if(ok==1) {
										for(int j=0; j<nrStudentPref; j++) {
											String name = "";
											if(sc.hasNextLine()==false) {
												System.out.println("Not enough information provided. "
														+ "(Numele proiectelor studentilor)");
												ok=0;
											}
											else {
												try {
													name = sc.nextLine();
													int a = Integer.parseInt(name);
													ok=0;
													System.out.println("Project name can't be made out "
															+ "of digits.");
												}catch(NumberFormatException e){
													Project p = new Project();
													p=problem.findProject(name);
													student.addPreference(p);
												}
											}
											if(ok==0)
												break;
										}
									}
								}
								if(ok==0) {
									break;
								}
							}
							if(ok==1) {
								for(int i=0; i<nrOfTeachs; i++) {
									if(sc.hasNextLine()==false) {
										System.out.println("Not enough information provided. (Teach pref)");
										ok=0;
									}
									else {
										input = sc.nextLine();
										try {
											nrTeacherPref=Integer.parseInt(input);
										}catch(NumberFormatException e){
											ok=0;
											System.out.println("There should have been a variable for the number "
													+ "of students a teacher want to take on.");
										}
										if(ok==1) {
											Teacher teacher = problem.getTeacherList().get(i);
											for(int j=0; j<nrTeacherPref; j++) {
												String name = "";
												if(sc.hasNextLine()==false) {
													System.out.println("Not enough information provided. "
															+ "(Students name for teachers pref)");
													ok=0;
												}
												else {
													try {
														name = sc.nextLine();
														int a = Integer.parseInt(name);
														ok=0;
														System.out.println("Student name can't be made "
																+ "out of digits.");
													}catch(NumberFormatException e){
														Student s = new Student();
														s=problem.findStudent(name);
														teacher.addStudent(s);
													}
												}
												if(ok==0) {
													break;
												}
											}
										}
									}
									if(ok==0) {
										break;
									}
								}
							}
						}
					}
				}
			}
		}
		if(ok==1)
			System.out.println(problem.showProblem());
	}
	
	public static boolean fileApprove(String fileName, File file, Scanner sc) {
		if(fileName.equals("")==true || fileName.equals(null)==true) {
			System.out.println("You have to provide a name.");
		}
		else if(fileName.length()<5) {
			System.out.println("It has to be at least 5 characters long.");
		}
		else if(fileName.substring(fileName.length()-4).equals(".txt")) {
			file = new File(fileName);
			try {
				sc = new Scanner(file);
				sc.close();
				return true;
			}
			catch(FileNotFoundException e) {
				System.out.println("There isn't such a file!");
			}
		}
		else if(!(fileName.substring(fileName.length()-4).equals(".txt"))){
			System.out.println(fileName.substring(fileName.length()-4));
			System.out.println("It has to be a text file. Let's try again.");
			System.out.print("Tell us again the path of the file: ");
		}
		sc.close();
		return false;
	}
	
	public static String getGameDone(Problem problem) {
		String response="";
	    Student student;
	    int count = problem.getStudentList().size();
	       
	    for(int i=0;i<count;i++) {
	    	sortStudents(problem);

	        boolean studentHasProblems = false;
	           
	    	student = problem.getStudentList().get(0);
	    	   
	    	while(student.isAssigned()==false) {
	        	if(student.getPreferences().size()==0) {
	        		System.out.println("The students couldn't be assigned.");
	        		studentHasProblems = true;
	        		break;
	        	}
	        	   
	        	Project project = student.getPreferences().get(0);
	        	Teacher teacher = problem.getTeacherWithProjectP(project);
	        	   
	        	if(teacher.containsStudentAsPref(student)==false) {
	        		student.removePreference(project);
	        	}
	        	else {
	        		response+=("Student " + student.getName() + " has been assigned to project " + 
	            			project.getName() + " thought by teacher " + teacher.getName() + ". \n");
	            	project.studentAssigned();
	            	student.setAssignment(true);
	            	   
	            	if(project.getCapacity()==0) {
	            		removeProjectPFromStudents(problem, project);
	            		teacher.removeProject(project);
	            		removeStudentSFromTeachers(problem, student);
	            	}
	        		problem.removeStudent(student);
	        	}
	    	}
	    	   
	    	if(studentHasProblems == true) {
	    		response = ("Student " + student.getName() + " couldn't be assigned to any project.");
	    		break;
	    	}
	    }
	    return response;
	}
	
   public static void main(String []args) throws IOException, InterruptedException  {
	   Problem problem = new Problem();
	   new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	   System.out.println("Write 1 in order to introduce data yourself, 2 to read a file from your PC or "
	   		+ "write any other word in order to let us get the input.");
	   Scanner sc = new Scanner(System.in);
	   int userOption = sc.nextInt();
	   if(userOption==1) {
		   readFromKeyboard(sc, problem);
		   sc.close();
	   }
	   else if(userOption==2){
		   System.out.print("Ok, so now provide us the path of the file: ");
		   File file = new File("");
		   sc.nextLine();
		   while(true) {
			   String fileName = sc.nextLine();
			   if(fileApprove(fileName,file,sc)==true) {
				   sc = new Scanner(new File(fileName));
				   break;
			   }
		   }
		   new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		   readFromFile(problem,sc,file);
		   sc.close();
		   //C:\Users\alexs\Desktop\citire.txt
	   }
	   else{
		   readMyInput(problem);
	   }
	   sc.close();
       System.out.println(getGameDone(problem));
   }
}
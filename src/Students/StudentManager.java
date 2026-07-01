package Students;

import java.util.ArrayList;

public class StudentManager {
    private ArrayList<Student> students;

    public StudentManager() {
        students = new ArrayList<>();
    }

    public boolean addStudent(String name) {
        if (name == null || name.trim().isEmpty()) return false;
        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name.trim())) {
                return false; // duplicate
            }
        }
        students.add(new Student(name.trim()));
        return true;
    }

    public Student findStudent(String name) {
        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name.trim())) {
                return s;
            }
        }
        return null;
    }

    public boolean addGradeToStudent(String name, double grade) {
        Student s = findStudent(name);
        if (s == null) return false;
        s.addGrade(grade);
        return true;
    }

    public ArrayList<Student> getAllStudents() {
        return students;
    }

    public boolean isEmpty() {
        return students.isEmpty();
    }
}

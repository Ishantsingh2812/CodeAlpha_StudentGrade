package Students;

import java.util.ArrayList;

public class Student {
    private String name;
    private ArrayList<Double> grades;

    public Student(String name) {
        this.name = name;
        this.grades = new ArrayList<>();
    }

    public void addGrade(double grade) {
        grades.add(grade);
    }

    public double getAverage() {
        if (grades.isEmpty()) return 0.0;
        double sum = 0;
        for (double g : grades) sum += g;
        return sum / grades.size();
    }

    public double getHighest() {
        if (grades.isEmpty()) return 0.0;
        double max = grades.get(0);
        for (double g : grades) if (g > max) max = g;
        return max;
    }

    public double getLowest() {
        if (grades.isEmpty()) return 0.0;
        double min = grades.get(0);
        for (double g : grades) if (g < min) min = g;
        return min;
    }

    public String getName() { return name; }
    public int getNumberOfGrades() { return grades.size(); }
    public ArrayList<Double> getGrades() { return grades; }

    @Override
    public String toString() {
        return String.format("%-15s | Grades: %-20s | Avg: %6.2f | High: %6.2f | Low: %6.2f",
                name, grades.toString(), getAverage(), getHighest(), getLowest());
    }
}
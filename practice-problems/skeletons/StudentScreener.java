import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class StudentScreener {
  public static void main(String[] args) throws Exception {
    int totalStudents = 7;
    int totalMarks = 1000;
    float requiredPercentile = 50f;

    int[] marks = {800, 300, 750, 760, 680, 790, 640};
    String[] students = { "Kartik", "Devang", "Pari", "Ketan", "Sheetal", "Darshana", "Mohan" };
    String[] examResults = {"Passed", "Failed", "Passed", "Failed", "Passed", "Passed", "Passed"};

    String eligiblStudents = getEligibleStudents(totalStudents, students, marks, examResults, totalMarks, requiredPercentile);
    System.out.println(eligiblStudents);
  }

  public static String getEligibleStudents(int totalStudents, String[] students, int[] marks, String[] examResults, int totalMarks, float requiredPercentile) throws Exception {
        List<Student> ans = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    sb.append("\"");
    //storing marks, students and examResults in form of object so that operations can be performed easily
    for(int i = 0; i < totalStudents; i++) {
      Student std = new Student(marks[i],students[i],examResults[i]);
      ans.add(std);
    }
    //sorted list based on marks
    List<Student> sorted = ans.stream().sorted(new StudentMarksComparator()).toList();
    for(int i = 0; i < sorted.size(); i++) {
      //setting percentile of each student
      sorted.get(i).setPtile(percentile(totalStudents,i+1));
      //checking the required conditions and adding required result
      if(sorted.get(i).ptile >= requiredPercentile && sorted.get(i).getExamResult().equalsIgnoreCase("Passed")) {
        if(sb.length() > 2) sb.append(",");
        sb.append(sorted.get(i).getName());
      }
    }
    //adding last double quote for final result
    sb.append("\"");
    return sb.toString();
  }
  //for calculating percentile
  static float percentile(int n, int i) {
    return ((float) n -  (float) i)/ (float) n * 100;
  }
  static class Student {
    private int marks;
    private String name;
    private String examResult;
    private float ptile;

    Student(int marks, String name, String result) {
      this.marks=marks;
      this.name=name;
      this.examResult=result;
    }
    Student(){}
    public int getMarks() {
      return marks;
    }

    public String getName() {
      return name;
    }
    public String getExamResult() {
      return examResult;
    }
    public float getPtile(){
      return ptile;
    }
    public void setPtile(float n) {
      this.ptile=n;
    }
  }
  //comparator to sort students based on their marks
  static class StudentMarksComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
      return s2.getMarks() - s1.getMarks();
    }
  }
}

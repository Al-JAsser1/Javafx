package Students;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class StudentHandler {
    public static StudentHandler instance;
    private List<Student> students;
    public static StudentHandler getInstance(){
        if(instance==null)
            instance = new StudentHandler();
        return instance;
    }
    private StudentHandler(){
        students = new ArrayList<>();
        students.add(new Student("123","Marwa","EDUC",87.9));
        students.add(new Student("111","Ahmed","CS",93.2));
        students.add(new Student("252","Salem","IT",88.2));
        students.add(new Student("265","Basem","CS",91.0));
        students.add(new Student("365","Omar","CS",91.0));
        students.add(new Student("352","Tamer","IT",88.5));
    }
    public List<Student> getStudents() {
        return students;
    }
/**
 * sort students according to their names
 * */
    public void sortStudents(){
        students =
                students.stream()
                .sorted(Comparator.comparing(Student::getName,String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
    }
    /**
     * sort students according to their grades
     * */
    public Map<String,Double> studentsSortedGrades(){
        Map<String,Double> map = new TreeMap<>();
        Comparator<Double> compareGrades = (grade1,grade2)-> -grade1.compareTo(grade2);
               students.stream()
                        .sorted((student1,student2)->compareGrades.reversed().compare(student1.getGrade(),student2.getGrade()))
                       .forEach(student -> map.put(student.getName(),student.getGrade()));
                return map;
    }
    /**
     * sort students according to their grades with range
     * */
    public Map<String,Double> studentsMapWithRange(String range) {
     String ranges[]= range.split("-");
      double min = Double.parseDouble(ranges[0]);
      double max = Double.parseDouble(ranges[1]);

        Map<String, Double> map = new HashMap<>();
        students.stream()
                .filter(student -> student.getGrade()>=min&&student.getGrade()<max)
                .sorted((s1,s2)-> (int) (-s1.getGrade()-s2.getGrade()))
                .forEach( student ->
                        map.put(student.getName(),student.getGrade()));
        return map;
    }
    //get average
    public double getGradeAvg(){
        DecimalFormat format = new DecimalFormat("###.##");
        if(!students.isEmpty()){
        double avg = students.stream()
                .mapToDouble(student -> student.getGrade())
                .average()
                .getAsDouble();
               return  Double.parseDouble(format.format(avg));
        }
               return 0;

    }
    //group by criteria, returns map
    public Map<Object, List<Student>> groupBy(String criteria){
        criteria = criteria.toLowerCase(Locale.ROOT);
        switch (criteria){
            case "major":
                return
                        students.stream()
                                .collect(Collectors.groupingBy(Student::getMajor));
            case "grade":
                return
                        students.stream()
                                .collect(Collectors.groupingBy(Student::getGrade));
            default:
                return null;

        }

    }
}

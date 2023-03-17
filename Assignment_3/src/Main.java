import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
/**
 * Link to videos:
//https://drive.google.com/file/d/1Pe-8pwk25wgOMZVJ51Mc8mnKkEHgRmNS/view?usp=sharing
 *
 * */
public class Main {
    public static void main(String[] args) {
//     1)
//     1.1
             IntConsumer printValue =
                        (value)-> System.out.printf("%d",value);
                printValue.accept(5);
        System.out.println();
//      1.2
              Function<String, String> upperCase =
                                          String::toUpperCase;
              System.out.println(upperCase.apply("test text"));
//      1.3
        Supplier<String> greet = ()-> "Welcome to lambdas!";
        System.out.println(greet.get());
//      1.4
        Function<Integer,Double> getCube = (value)-> Math.pow(value,3);
                System.out.println(getCube.apply(5));
//      2)

            Map<String, Long> occs = new HashMap<>();
            try {
                occs =  Files.lines(Paths.get("src/data.txt"))
                                    .flatMap(line -> line.chars().mapToObj(i->(char)i))
                                    .collect(
                                            Collectors.groupingBy(v->v.toString(),Collectors.counting()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(occs);

//       listEmployees
//                .stream()
//                .filter(e-> e.getSalary() >=800 && e.getSalary() <1200)
//                .map(e-> new Employee
//                        (e.getId(), e.getName(), e.getDepartment(), getSalary()*1.02))
//                .collect(Collectors.
//                        groupingBy(Employee::getDepartment,
//                                Collectors.counting()))
//                .forEach((d, c) -> System.out.println("Dept: "+ d + ", Count: " + c));
//      3)so what happened here is the following:
//        /* .stream() => make stream for the list of employees
//         * .filter()=> filters the employees and returns only those who's salary is between 800 and 1200
//         * .map() => create new Employee objects with values of filtered employees from filter
//         *       and  increasing their salaries by 20%
//         * .collect=> creates new collection with employees returned by map()
//         *   and group results by the number of employees of each department
//         *
//         * */


    }
}


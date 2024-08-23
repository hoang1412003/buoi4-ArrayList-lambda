import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ArrayList<String> colors = new ArrayList<>();
        colors.add("Red");
        colors.add("Green");
        colors.add("Blue");

        // truy cập phần tử tại vị trí chỉ định
        System.out.println(colors.get(1));

        // thay đổi phần tử tại vị trí chỉ định
        colors.set(1, "Yellow");

        // xóa phần tu theo giá trị
        colors.remove("Red");
        System.out.println(colors);

        // xóa phần tử theo chỉ số
        colors.remove(1);
        System.out.println(colors);

        for(String color : colors) {
            System.out.println(color);
        }
        // System.out.println(color);
        // tạo một mảng
        String[] colorsArray = {"Red", "Green", "Blue"};

        // tạo arrayList từ mảng
        ArrayList<String> colorsList = new ArrayList<>(Arrays.asList(colorsArray));
        // sắp xếp mảng: cách thường
        Collections.sort(colorsList);
        //sử dụng biểu thức lambda để sắp xếp danh sách theo thứ tự tăng dần
        Collections.sort(colorsList, (s1,s2) -> s1.compareTo(s2));
        System.out.println(colorsList); // output: [Red,Green, Blue]

        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(3, 7));
        points.add(new Point(3, 6));
        points.add(new Point(5, 2));

        Comparator<Point> x_y_compare = new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                return Integer.compare(p1.getX(), p2.getX());
            }
//            public int compare(Point p1, Point p2) {
//                int tmp = Integer.compare(p1.getX(), p2.getX());
//                if(tmp == 0) {
//                    return Integer.compare(p1.getY(), p2.getY());
//                }
//                return tmp;
//            }
//            public int compare(Point p1, Point p2) {
//                return Integer.compare(p1.getX() + p1.getY(), p2.getX() + p2.getY());
//            }
        };
        Collections.sort(points, x_y_compare);
        for (Point p : points) {
            System.out.println(p);
        }

        System.out.println("######################");
        ArrayList<Point> points1 = new ArrayList<>();
        points1.add(new Point(3, 5));
        points1.add(new Point(2, 6));
        points1.add(new Point(5, 2));
        points1.add(new Point(6, 0));

        System.out.println("######################");
        Predicate<Point> sumPoint = point -> point.getX() + point.getY() > 7;
        Predicate<Point> sumPoint2 = point -> {
             if(point.getX() > 5) {
                 return point.getX() + point.getY() > 7;
             } else  {
                 return point.getX() + point.getY() > 6;
             }
        };
        for (Point point : points1) {
            if (sumPoint2.test(point)) {
                System.out.println(point.toString());
            }
        }
        Function<Point, Integer> sumPoint3 = p -> p.getY() + p.getX();
        Function<Point, Integer> sumPoint4 = p -> {
            return p.getX() > p.getY() ? p.getY() - p.getX() : p.getY() + p.getX();
        };
        for(Point point : points1) {
            System.out.println(sumPoint4.apply(point));
        }
        Consumer<Point> printMessage = p -> {
            if(p.getX() > p.getY()) {
                System.out.println(p.getY() - p.getX());
            }
            else {
                System.out.println(p.getY() + p.getX());
            }
        };
        System.out.println("################");
        points1.forEach(printMessage);
        System.out.println("################");
        for (Point point : points1) {
            printMessage.accept(point);
        }

        System.out.println("#############");
        points1.stream()
                .filter(p -> p.getX() > 3)
                .forEach(System.out::println);
        System.out.println("################");
        for(Point a:points1) {
            if(a.getX()>3) {
                System.out.println(a.toString());
            }
        }
        System.out.println("##############");
        Predicate<Point> pre_x = point -> point.getX() > 3;
        for (Point point : points1) {
            if(pre_x.test(point)) {
                System.out.println(point.toString());
            }
        }
        System.out.println("############");
        Consumer<Point> consum_x = p-> {
          if(p.getX() > 3) {
              System.out.println(p.toString());
          }
        };
        points1.forEach(consum_x);

        System.out.println("###########");
        List<String> names = Arrays.asList("John", "Alice", "Bob", "Anna");

        // lọc các tên bắt đầu bằng 'A'
        names.stream()
                .filter(name -> name.startsWith("A"))
                .forEach(System.out::println); //Output: Alice, Anna
//        names.stream()
//                .filter(name -> name.startsWith("A"))
//                .forEach(item -> System.out.println(item)); //Output: Alice, Anna

        List<String> filteredNames = names.stream()
                .filter(name -> name.startsWith("A"))
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());

        System.out.println(filteredNames); // output: [ALICE, ANNA]

        // Ánh xạ các tên thành chữ hoa
        names.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println); // output: JOHN, ALICE, BOB

        names.stream()
                .sorted()
                .forEach(System.out::println); // Alice, Bob, John

        // Sắp xếp theo thứ tự  giảm dần
        names.stream()
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println); // Output: john, Bob, Alice

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        //tính tổng
        int sum = numbers.stream()
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("sum: "+ sum); // output: Sum: 15

        // stream<Integer> => int
        // Tính trung bình
        double average = numbers.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0);
        System.out.println("Average: "+ average); // output Average: 3.0

        //Tìm giá trị lớn nhất
        OptionalInt max = numbers.stream()
                .mapToInt(Integer::intValue)
                .max();
        System.out.println("Max: "+ (max.isPresent() ? max.getAsInt() : "Not present")); // output: max: 5

        // Tìm giá trị nhỏ nhất
        OptionalInt min = numbers.stream()
                .mapToInt(Integer::intValue)
                .min();
        System.out.println("Min: "+(min.isPresent() ? min.getAsInt() : "Not present")); // output: Min: 1

        // Sử dụng reduce để tính tổng
        int sum1 = numbers.stream()
                .reduce(0, Integer::sum);
        System.out.println("Sum using reduce: "+ sum1); // ouput: Sum using reduce: 15

        // sử dụng reduce để tính tích
        int product = numbers.stream()
                .reduce(1, (kq, item) -> kq * item);
        System.out.println("product using reduce: " + product); // output: Product using reduce: 120

        List<List<String>> listOfLists = Arrays.asList(
                Arrays.asList("a", "b", "c"),
                Arrays.asList("d", "e", "f"),
                Arrays.asList("g", "h", "i")
        );

        // sử dụng flatMap để kết hợp các danh sách con thành một danh sách
        List<String> combinedList = listOfLists.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        System.out.println(combinedList); // Output: [a, b, c, d, e, f, g, h, i]

        //

        double harmonicSum = numbers.stream()
                .mapToDouble(number -> 1.0 / number)
                .sum();

        System.out.println("Harmonic sum: " + harmonicSum);

        List<Point> points2 = new ArrayList<>();
        points2.add(new Point(7, 2));
        points2.add(new Point(3, 4));
        points2.add(new Point(2, 6));
        points2.add(new Point(7, 1));

        // 7+2+3+4+2+6+7+1
        int totalSum = points2.stream()
                .flatMapToInt(point -> Arrays.stream(new int[]{point.getX(),point.getY()}))
                .sum();

        int totalSum2 = points2.stream()
                .mapToInt(point -> point.getX()+point.getY())
                .sum();

        int totalSum3 = points2.stream()
                .reduce(0, (acc, p) -> acc +p.getX() + p.getY(), Integer::sum);

        int totalSum4 = points2.stream()
                .map(p -> p.getX() + p.getY()) // chuyển đôi từng Point thành tổng x+y
                .reduce(0, (acc, value) -> acc + value); // tính tổng cac giá trị
        // Tính tổng các hoành đô
        int sumX = points2.stream()
                .mapToInt(Point::getX) // chuyển đổi thành IntStream dựa trn giá trị x
                .sum(); // tính tổng

        //Tính tổng các hoành độ >5
        int sumX2 = points2.stream()
                .filter(p -> p.getX()>5)
                .mapToInt(Point::getX) // Chuyển dổi thành InStream dựa trên giá trị x
                .sum();

        // tính tổng toàn bộ x^2+1-y đói với các hoành độ >5
        int sumX3 = points2.stream()
                .filter(p -> p.getX()>5)
                .mapToInt(p->{
                    return p.getX()*p.getX()+1-p.getY();
                    }
                ) // chuyển đổi thành IntStream dựa trên giá trị x
                .sum();

        // In rea từng kết quả x^2+1-y đối với x>4
        points2.stream()
                .filter(p -> p.getX() > 4)
                .mapToInt(p-> {
                            return p.getX() * p.getX() + 1 - p.getY();
                        }
                ) // chuyển dổi thnahf IntStream dựa trên giá trị x
                .forEach(res -> System.out.println(res));
        // kq = 48, kq =49

        // tính tích toàn bộ x^2+1-y đối với các hoành độ >1
        long total5 = points2.stream()
                .filter(p->p.getX()>1)
                .mapToLong(p->{
                    return p.getX()*p.getX()+1-p.getY();
                })// chuyển đổi thành IntStream dựa trên giá trị x
                .reduce(1L, (kq, item)->kq+item);
        System.out.println("total5 = "+ total5);

        // tính tích các giá trị của 1 / (x^2 + y) cho tat cả các Point
        double toalProduct = points2.stream()
                .mapToDouble(point -> {
                    int x = point.getX();
                    int y = point.getY();
                    double tmp = x * x + y; // Tính x ^ 2 + y
                    return 1.0/tmp;
                })
                .reduce(1.0, (res, item)-> res * item); //tính toàn bộ các giá trị

        // In kết quả
        System.out.println("Tích toàn bộ các giá trị của 1 / (x^2 + y): "+ toalProduct);

        // in ra danh sách x+y>7
        points2.stream()
                .filter(p->(p.getX()+p.getY()>7))
                .forEach(System.out::println);

        System.out.println("####################");
        // in ra danh sách X>5 và y>1
        points2.stream()
                .filter(p->(p.getX()>5 && p.getY()>1))
                .forEach(System.out::println);
        //In kết quả
        System.out.println("Tổng tất cả các giả trị: " + totalSum);
        System.out.println("Tổng tất cả các giả trị cách2: " + totalSum2);
        System.out.println("Tổng tất cả các giả trị cách3: " + totalSum3);
        System.out.println("Tổng tất cả các giả trị cách4: " + totalSum4);
        System.out.println("Tổng tất cả các giá trị x: "+ sumX);
        System.out.println("Tổng tất cả các giá trị x2: "+ sumX2);


    }
}

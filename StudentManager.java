import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

class Student {
    int id;
    String name;
    double marks;

    public Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public String getRank() {
        if (marks < 5.0) return "Fail";
        if (marks < 6.5) return "Medium";
        if (marks < 7.5) return "Good";
        if (marks < 9.0) return "Very Good";
        return "Excellent";
    }
}

public class StudentManager {
    private ArrayList<Student> students;

    public StudentManager() {
        students = new ArrayList<>();
    }

    public void addStudent(int id, String name, double marks) {
        for (Student s : students) {
            if (s.id == id) {
                System.out.println("ID đã tồn tại. Vui lòng sử dụng ID khác.");
                return;
            }
        }
        students.add(new Student(id, name, marks));
        System.out.println("Sinh viên đã được thêm thành công!");
    }

    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("Không có sinh viên nào để hiển thị.");
            return;
        }
        for (Student s : students) {
            System.out.println("ID: " + s.id + ", Tên: " + s.name + ", Điểm: " + s.marks + ", Xếp hạng: " + s.getRank());
        }
    }

    public void sortStudents() {
        students.sort(Comparator.comparingDouble(s -> s.marks));
    }


    public void editStudent(int id, String name, double marks) {
        for (Student s : students) {
            if (s.id == id) {
                s.name = name;
                s.marks = marks;
                System.out.println("Thông tin sinh viên đã được cập nhật thành công!");
                return;
            }
        }
        System.out.println("Không tìm thấy sinh viên với ID: " + id);
    }

    public void deleteStudent(int id) {
        if (students.removeIf(s -> s.id == id)) {
            System.out.println("Sinh viên đã được xóa thành công!");
        } else {
            System.out.println("Không tìm thấy sinh viên với ID: " + id);
        }
    }

    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nChọn một tùy chọn:");
            System.out.println("1. Thêm sinh viên");
            System.out.println("2. Sửa sinh viên");
            System.out.println("3. Xóa sinh viên");
            System.out.println("4. Hiển thị sinh viên");
            System.out.println("5. Sắp xếp sinh viên theo điểm");
            System.out.println("6. Thoát");
            System.out.print("Nhập lựa chọn của bạn: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Nhập ID Sinh viên: ");
                        int id = Integer.parseInt(scanner.nextLine());

                        System.out.print("Nhập Tên Sinh viên: ");
                        String name = scanner.nextLine();

                        System.out.print("Nhập Điểm: ");
                        double marks = Double.parseDouble(scanner.nextLine());

                        if (marks < 0 || marks > 10) {
                            System.out.println("Điểm phải nằm trong khoảng từ 0 đến 10.");
                            break;
                        }

                        manager.addStudent(id, name, marks);
                        break;

                    case 2:
                        System.out.print("Nhập ID Sinh viên cần sửa: ");
                        int editId = Integer.parseInt(scanner.nextLine());

                        System.out.print("Nhập Tên mới: ");
                        String newName = scanner.nextLine();

                        System.out.print("Nhập Điểm mới: ");
                            double newMarks = Double.parseDouble(scanner.nextLine());

                        if (newMarks < 0 || newMarks > 10) {
                            System.out.println("Điểm mới phải nằm trong khoảng từ 0 đến 10.");
                            break;
                        }

                        manager.editStudent(editId, newName, newMarks);
                        break;

                    case 3:
                        System.out.print("Nhập ID Sinh viên cần xóa: ");
                        int deleteId = Integer.parseInt(scanner.nextLine());
                        manager.deleteStudent(deleteId);
                        break;

                    case 4:
                        System.out.println("Danh sách Sinh viên:");
                        manager.displayStudents();
                        break;

                    case 5:
                        System.out.println("Danh sách Sinh viên đã được sắp xếp theo điểm.");
                        manager.sortStudents();

                        break;

                    case 6:
                        System.out.println("Thoát chương trình.");
                        scanner.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Lựa chọn không hợp lệ, vui lòng thử lại.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Đầu vào không hợp lệ. Vui lòng nhập lại.");
            }
        }
    }
}

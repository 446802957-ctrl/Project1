import java.util.Scanner;
public class Project1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String name = "";
        String kkuId = "";
        int numCourses = 0;
        int[] marks = new int[5];

        while (true) {

            System.out.println("Menu:");
            System.out.println("1. Enter Student Details");
            System.out.println("2. Enter Marks");
            System.out.println("3. Display Marks Info.");
            System.out.println("4. Display Student Details");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = input.nextInt();
            input.nextLine(); 

            if (choice == 1) {
                System.out.print("Enter Student Name: ");
                name = toTitleCase(input.nextLine().trim());

                while (true) {
                    System.out.print("Enter ID Number: ");
                    kkuId = input.nextLine().trim();
                    if (kkuId.matches("^4\\d{8}$")) break;
                    System.out.println("Invalid KKU ID! Must start with 4 and be 9 digits.");
                }

                while (true) {
                    System.out.print("How many courses last semester? ");
                    numCourses = input.nextInt();
                    if (numCourses >= 1 && numCourses <= 5) break;
                    System.out.println("Number of courses must be between 1 and 5.");
                }
                System.out.println();
            } 
            else if (choice == 2) {
                System.out.print("\nEnter Marks for " + numCourses + " subjects:\n");
                for (int i = 0; i < numCourses; i++) {
                    while (true) {
                        int mark = input.nextInt();
                        if (mark >= 0 && mark <= 100) {
                            marks[i] = mark;
                            break;
                        }
                        System.out.println("Invalid mark! Enter between 0 and 100.");
                    }
                }
                System.out.println();
                displayMarks(marks, numCourses, false, false);
            } 
            else if (choice == 3) {
                displayMarks(marks, numCourses, false, false);
            } 
            else if (choice == 4) {
                System.out.println("Name: " + name);
                System.out.println("KKU ID: " + kkuId);
                displayMarks(marks, numCourses, true, true);
            } 
            else if (choice == 5) {
                break;
            }
        }
        input.close();
    }

    public static void displayMarks(int[] marks, int numCourses, boolean withHeader, boolean avgBeside) {
        int total = 0;
        int max = marks[0], min = marks[0];
        for (int i = 0; i < numCourses; i++) {
            total += marks[i];
            if (marks[i] > max) max = marks[i];
            if (marks[i] < min) min = marks[i];
        }
        double avg = (double) total / numCourses;

        if (withHeader) {
            if (avgBeside) {
                String header = String.format("Course\tMark\tP/F\tAvg: %.1f", avg);
                System.out.println(header);
                System.out.println("=".repeat(header.length())); // underline full header
            } else {
                String header = "Course\tMark\tP/F";
                System.out.println(header);
                System.out.println("=".repeat(header.length())); // underline just P/F header
            }
        }

        for (int i = 0; i < numCourses; i++) {
            String status = (marks[i] >= 60) ? "Pass" : "Fail";
            String extra = "";
            if (marks[i] == max) extra = "Max";
            if (marks[i] == min) extra = "Min";
            System.out.println((i + 1) + "\t" + marks[i] + "\t" + status + "\t" + extra);
        }

        if (!avgBeside) {
            System.out.printf("Avg: %.1f\n\n", avg);
        } else {
            System.out.println();
        }
    }

    public static String toTitleCase(String str) {
        String[] words = str.split(" ");
        StringBuilder title = new StringBuilder();
        for (String word : words) {
            if (word.length() > 0) {
                title.append(Character.toUpperCase(word.charAt(0)))
                     .append(word.substring(1).toLowerCase())
                     .append(" ");
            }
        }
        return title.toString().trim();
    }
}

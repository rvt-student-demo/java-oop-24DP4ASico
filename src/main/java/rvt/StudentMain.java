package rvt;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentMain{

private static ArrayList<Student> students = new ArrayList<>();
private static Scanner sc = new Scanner(System.in);

public static void main(String[] args) {
int izvele = -1;

while (izvele != 0) {
System.out.println("\n=== RVT Studentu reģistrācijas sistēma ===");
System.out.println("1. Pievienot studentu");
System.out.println("2. Parādīt visus studentus");
System.out.println("3. Meklēt studentu pēc ID");
System.out.println("4. Dzēst studentu");
System.out.println("0. Iziet");
System.out.print("Izvēle: ");

izvele = sc.nextInt();
sc.nextLine(); // notīra bufferi

if (izvele == 1) {
pievienotStudentu();
} else if (izvele == 2) {
paraditStudentus();
} else if (izvele == 3) {
mekletStudentu();
} else if (izvele == 4) {
dzestStudentu();
} else if (izvele == 0) {
System.out.println("Programma beidz darbu.");
} else {
System.out.println("Nepareiza izvēle!");
}
}
}

private static void pievienotStudentu() {
System.out.print("ID: ");
int id = sc.nextInt();
sc.nextLine();

System.out.print("Vārds: ");
String name = sc.nextLine();

System.out.print("Uzvārds: ");
String surname = sc.nextLine();

System.out.print("Grupa: ");
String group = sc.nextLine();

students.add(new Student(id, name, surname, group));
System.out.println("Students pievienots!");
}

private static void paraditStudentus() {
if (students.isEmpty()) {
System.out.println("Nav reģistrētu studentu.");
return;
}

for (Student s : students) {
System.out.println(s);
}
}

private static void mekletStudentu() {
System.out.print("Ievadi studenta ID: ");
int id = sc.nextInt();

for (Student s : students) {
if (s.getId() == id) {
System.out.println("Atrasts: " + s);
return;
}
}

System.out.println("Students nav atrasts.");
}

private static void dzestStudentu() {
System.out.print("Ievadi ID dzēšanai: ");
int id = sc.nextInt();

for (Student s : students) {
if (s.getId() == id) {
students.remove(s);
System.out.println("Students dzēsts.");
return;
}
}

System.out.println("Students nav atrasts.");
}
}
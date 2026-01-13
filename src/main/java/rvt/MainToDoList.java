package rvt;

import java.util.Scanner;

public class MainToDoList {
    public static void main(String[] args) {
        ToDoList list = new ToDoList();
        Scanner scanner = new Scanner(System.in);
        UserInteface ui = new UserInteface(list, scanner);
        ui.start();
    }
    
}

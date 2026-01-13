package rvt;
import java.util.ArrayList;

public class ToDoList {
   private ArrayList<String> list;
    public ToDoList() {
         this.list = new ArrayList<>();
    }
    public void add(String task) {
        this.list.add(task);
    }

    public void print(){
        for (String job: list){
            System.out.println(this.list.indexOf(job)+ 1 + ":" + job);    
        }
    }

    public void remove(int number){
        this.list.remove(number - 1);
    }

      
}
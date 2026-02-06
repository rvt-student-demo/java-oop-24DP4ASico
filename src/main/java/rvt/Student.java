package rvt;

public class Student{
    private int id;
    private String name;
    private String surname;
    private String group;

    public Student( int id, String name, String surname, String group){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.group = group;
    }



    public int getId(){
        return id;
    }

    public String getName(){
       return name;
    }

    public String getSurname(){
     return surname;
    }

    public String getGroup(){
        return group;
    }

    @Override
    public String toString(){
     return "ID:" + id + ", Vārds:" + name + ", Uzvārds:" + surname + ", Grupa:" + group;
    }
}
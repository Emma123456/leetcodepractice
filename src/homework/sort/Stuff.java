package homework.sort;

public class Stuff implements CountSortAble<Stuff>{
    private String name;
    private int age;

    public Stuff(String name,int age) {
        this.name = name;
        this.age = age;
    }

    public int getValue(){
        return age;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

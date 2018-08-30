package personal.nfl.java.demo.asm;

public class Person {

    private String name ;
    private String sex ;
    private int age ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void sayHello(){
        System.out.println("Hello");
    }
}

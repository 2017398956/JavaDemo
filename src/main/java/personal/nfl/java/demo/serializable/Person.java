package personal.nfl.java.demo.serializable;

import java.io.Serializable;

public class Person implements Serializable {

    public static final int serialVersionUID = 1 ;

    private int age;
    private String name;
    private String sex;
    private String nullString ;

    public Person(){
        System.out.println("Person 的无参构造方法");
    }

    public Person(String nullString){
        if (nullString == null){
            nullString = "" ;
        }
        System.out.println("Person 的有参构造方法");
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

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

    @Override
    public int hashCode() {
        System.out.println("Person#hashCode");
        return super.hashCode();
    }

    @Override
    public String toString() {
        System.out.println("Person#toString");
        return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        System.out.println("Person#equals");
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        System.out.println("Person#clone");
        return super.clone();
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Person#finalize");
        super.finalize();
    }
}

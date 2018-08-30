package personal.nfl.java.demo.asm;

import personal.nfl.java.demo.asm.account.Account;

public class AsmDemo {

    public static void main(String[] args) {
        Person person = new Person();
        person.setName("NFL");
        System.out.println(person.getName());
        Account account = new Account();
        account.operation();
    }
}

package personal.nfl.java.demo.asm;

import org.objectweb.asm.Type;
import org.objectweb.asm.TypeReference;
import personal.nfl.java.demo.asm.account.Account;

import java.util.*;

import static java.util.Collections.emptyList;

public class AsmDemo {

    public static void main(String[] args) {
        Person person = new Person();
//        person.setName("NFL");
//        System.out.println(person.getName());
//        Account account = new Account();
//        account.operation();
        System.out.println(Type.getDescriptor(String.class));
        List<Person> test = new ArrayList<>() ;
        System.out.println(
                Type.getMethodDescriptor(
                        Type.getType(test.getClass())
                )
        );
        System.out.println(
                Type.getMethodDescriptor(
                        Type.getType(List.class)
                )
        );
    }

    public static <T> Class<List<T>> getClazz(){
        return (Class<List<T>>) Collections.<T>emptyList().getClass() ;
    }
}

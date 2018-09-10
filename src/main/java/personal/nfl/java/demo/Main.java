package personal.nfl.java.demo;

import java.util.Iterator;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Set set = System.getProperties().keySet() ;
        if(!set.isEmpty()){
            Iterator iterator = set.iterator() ;
            Object key = null ;
            while (iterator.hasNext()){
                key = iterator.next() ;
                System.out.println(key + " : " + System.getProperties().get(key));
            }
        }

    }
}

package personal.nfl.java.demo.serializable;

import personal.nfl.java.demo.util.FileUtil;

import java.io.*;

public class SerializableTest {

    private static File serializableFile = new File(FileUtil.getMyTempDir() + "serializableFile.txt");

    public static void main(String[] args) throws Exception {
        if (!serializableFile.exists()) {
            serializableFile.getParentFile().mkdirs();
            serializableFile.createNewFile();
        }
//        serializePerson();
//        Person p = deserializePerson();
//        System.out.println(p.getName() + ";" + p.getAge());

//        serializeHistory();
        FundSearchContentHistory fundSearchContentHistory = deserializeHistory() ;
        fundSearchContentHistory.getAll().add("all6") ;
        System.out.println(fundSearchContentHistory.getAll().getValues());
    }

    private static void serializePerson() throws FileNotFoundException, IOException {
        Person person = new Person();
        person.setName("测试实例");
        person.setAge(25);
        person.setSex("male");

        ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(serializableFile));
        oo.writeObject(person);
        System.out.println("序列化成功");
        oo.close();
    }

    private static Person deserializePerson() throws IOException, Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(serializableFile));
        Person person = (Person) ois.readObject();
        System.out.println("反序列化成功");
        return person;
    }

    private static void serializeHistory() throws FileNotFoundException, IOException {
        FundSearchContentHistory fundSearchContentHistory = new FundSearchContentHistory();
        for(int i = 0 ; i < 6 ; i++){
            fundSearchContentHistory.getAll().add("all" + i) ;
        }
        ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(serializableFile));
        oo.writeObject(fundSearchContentHistory);
        System.out.println("序列化成功");
        oo.close();
    }

    private static FundSearchContentHistory deserializeHistory() throws IOException, Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(serializableFile));
        FundSearchContentHistory fundSearchContentHistory = (FundSearchContentHistory) ois.readObject();
        System.out.println("反序列化成功");
        return fundSearchContentHistory;
    }

}

package zigou.com.recycleviewtest.modle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 子桂 on 2018/11/22.
 */

public class Person {
    private String name;
    private int age;
    private String habits;

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

    public String getHabits() {
        return habits;
    }

    public void setHabits(String habits) {
        this.habits = habits;
    }

    public Person(String name, int age, String habits){
        this.name = name;
        this.age = age;
        this.habits = habits;
    }

    //模拟50个数据模型
    public static List<Person> getPersons(){
        ArrayList listPerson = new ArrayList<Person>();
        for (int i=0;i<50;i++){
            Person person = new Person("样式1",i,i+"*********");
            listPerson.add(person);
        }
        return listPerson;
    }
}

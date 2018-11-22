package zigou.com.recycleviewtest.modle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 子桂 on 2018/11/22.
 */

public class Animal {
    private String name;
    private String des;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Animal(String name, String des){
        this.name = name;
        this.des = des;
    }

    public static List<Animal> getAnimals(){
        List<Animal> animalList = new ArrayList<>();
        for (int i=0;i<20;i++){
            Animal anim = new Animal("样式2",i+"************");
            animalList.add(anim);
        }
        return animalList;
    }
}

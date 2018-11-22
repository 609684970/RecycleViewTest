package zigou.com.recycleviewtest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import zigou.com.recycleviewtest.R;
import zigou.com.recycleviewtest.modle.Animal;
import zigou.com.recycleviewtest.modle.Person;

public class VariousStyleActivity extends AppCompatActivity {
    //用来管理子RecyclerView
    private RecyclerView rv_various;

    //Person数据
    private ArrayList<Person> personslist;
    //Animal 数据
    private ArrayList<Animal> animalslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_various_style);

        //获取模拟的数据
        personslist = (ArrayList<Person>) Person.getPersons();
        animalslist = (ArrayList<Animal>) Animal.getAnimals();

        //绑定控件
        rv_various = (RecyclerView) findViewById(R.id.rv_various);
        //设置LaLayoutManager
        rv_various.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        //设置Adapter
        rv_various.setAdapter(new ApadterAll());
    }
    //配置 Person ViewHolder
    private class ViewHolderPerson extends RecyclerView.ViewHolder {
        private TextView tv_title;
        private RecyclerView rv_person_detail;
        public ViewHolderPerson(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_person_title);
            rv_person_detail = itemView.findViewById(R.id.rv_person);
        }

        public void bind(){
            tv_title.setText("人物");

            LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            //设置为水平方向的放置
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            //设置LayoutManger
            rv_person_detail.setLayoutManager(layoutManager);
            //adapter的初始化
            ApadterPerson apadter = new ApadterPerson();
            rv_person_detail.setAdapter(apadter);
        }
    }
    //配置 Animal ViewHolder
    private class ViewHolderAnimal extends RecyclerView.ViewHolder {
        private TextView tv_title;
        private RecyclerView rv_animal_details;
        public ViewHolderAnimal(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_animal_title);
            rv_animal_details = itemView.findViewById(R.id.rv_animal);
        }

        public void bind(){
            tv_title.setText("动物");
            LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            //设置为水平方向的放置
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            //设置LayoutManger
            rv_animal_details.setLayoutManager(layoutManager);
            //adapter的初始化
            ApadterAnimal adapter = new ApadterAnimal();
            rv_animal_details.setAdapter(adapter);
        }
    }

    //person 的详细 ViewHolder
    class ViewHolderPersonDetail extends RecyclerView.ViewHolder {

        private TextView tv_person;
        public ViewHolderPersonDetail(View itemView) {
            super(itemView);
            tv_person = itemView.findViewById(R.id.tv_name);
        }

        public void bind(Person person){
            tv_person.setText(person.getName());
        }
    }

    //Animal 的详细 ViewHolder
    class ViewHolderAnimalDetail extends RecyclerView.ViewHolder {
        private TextView tv_animal;
        public ViewHolderAnimalDetail(View itemView) {
            super(itemView);
            tv_animal = itemView.findViewById(R.id.tv_twostyle);
        }

        public void bind(Animal animal){
            tv_animal.setText(animal.getName());
        }
    }

    //添加总的数据适配器，按照一定的方式显示不同的RecyclerView
    class ApadterAll extends RecyclerView.Adapter<ViewHolder>{

        //根据不同的viewType 显示不同的RecyclerView
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //viewType == 0 就创建Person的ViewHolder
            if (viewType == 0){
                return new ViewHolderPerson(LayoutInflater.
                        from(getApplicationContext()).inflate(R.layout.item_rv_person,
                        parent,false));
            }else {
                //viewType == 1 就创建Animal的ViewHolder
                return new ViewHolderAnimal(LayoutInflater.
                        from(getApplicationContext()).inflate(R.layout.item_rv_animal,
                        parent,false));
            }
        }

        //根据创建出来的ViewHolder 显示绑定数据
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if (holder instanceof ViewHolderPerson){
                ((ViewHolderPerson) holder).bind();
            }else if (holder instanceof ViewHolderAnimal){
                ((ViewHolderAnimal) holder).bind();
            }
        }

        //告诉ListView我有多少种布局
        @Override
        public int getItemCount() {
            return 2;       //数字就是样式的个数
        }

        //方法告诉 我在第几个position展示哪种布局
        @Override
        public int getItemViewType(int position) {
            return position < 2 ? position : 2;
        }

    }

    class ApadterPerson extends RecyclerView.Adapter<ViewHolderPersonDetail>{
        @Override
        public ViewHolderPersonDetail onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getApplicationContext()).inflate(
                    R.layout.item_rv_onestyle,parent,false
            );
            ViewHolderPersonDetail holder = new ViewHolderPersonDetail(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolderPersonDetail holder, int position) {
            holder.bind(personslist.get(position));
        }

        @Override
        public int getItemCount() {
            return personslist.size();
        }
    }

    class ApadterAnimal extends RecyclerView.Adapter<ViewHolderAnimalDetail>{

        @Override
        public ViewHolderAnimalDetail onCreateViewHolder(ViewGroup parent, int viewType) {
           View view = LayoutInflater.from(getApplicationContext()).inflate(
                   R.layout.item_rv_twostyle,parent,false
           );
            ViewHolderAnimalDetail holder = new ViewHolderAnimalDetail(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolderAnimalDetail holder, int position) {
            holder.bind(animalslist.get(position));
        }

        @Override
        public int getItemCount() {
            return animalslist.size();
        }
    }
}

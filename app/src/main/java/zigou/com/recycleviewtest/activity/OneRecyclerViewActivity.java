package zigou.com.recycleviewtest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import zigou.com.recycleviewtest.R;
import zigou.com.recycleviewtest.modle.Person;

public class OneRecyclerViewActivity extends AppCompatActivity implements View.OnClickListener {
    private List<Person> mlistPerson;
    private RecyclerView mrv_one_style;
    private Button btOne,btVarious;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_recycler_view);
        //1.初始化数据、判断控件、这里我模拟出50个数据，利用Person 封装数据
        init();
        //2.将RecyclerView 设置layoutManager
        mrv_one_style.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        //3.设置Adapter
        mrv_one_style.setAdapter(new OneStyleAdapter(mlistPerson));
    }

    private void init() {
        btOne = (Button) findViewById(R.id.bt_one_style);
        btVarious = (Button)findViewById(R.id.bt_various_style);
        btOne.setOnClickListener(this);
        btVarious.setOnClickListener(this);
        mrv_one_style = (RecyclerView) findViewById(R.id.rv_one_style);
        mlistPerson = Person.getPersons();
    }

    //创建数据适配器OneStyleAdapter
    class OneStyleAdapter extends RecyclerView.Adapter<OneStyleViewHolder>{
        private List<Person> personList;
        public OneStyleAdapter(List<Person> listPerson){
            personList = listPerson;
        }

        //创建对应的ViewHolder，就会调用
        @Override
        public OneStyleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_rv_onestyle,parent,false);
            return new OneStyleViewHolder(view);
        }

        @Override
        public void onBindViewHolder(OneStyleViewHolder holder, int position) {
            holder.bind(personList.get(position));
        }

        //设置数据的长度
        @Override
        public int getItemCount() {
            return personList.size();
        }
    }

    //创建自己的ViewHolder,功能创建视图
    class OneStyleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private Person mPerson;
        private TextView name;
        private TextView age;
        private TextView habits;
        public OneStyleViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            age = itemView.findViewById(R.id.tv_age);
            habits = itemView.findViewById(R.id.tv_habits);
            itemView.setOnClickListener(this);
        }
        public void bind(Person person){
            mPerson = person;
            name.setText(mPerson.getName());
            age.setText(mPerson.getAge()+"");
            habits.setText(mPerson.getHabits());
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(getApplicationContext(),
                    mPerson.getName() +"cliked",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_one_style:
                //下次把它做成fragment 就可以相互切换了
                break;

            case R.id.bt_various_style:
                Intent intent = new Intent(OneRecyclerViewActivity.this,VariousStyleActivity.class);
                startActivity(intent);
                break;
        }
    }
}

package sg.edu.rp.c346.simpletodo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText ed;
    Button btAdd,btClear,btRemove;
    ListView lv;
    Spinner spn;
    ArrayAdapter aaDo;
    ArrayList<String> doList;
    String newDo;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed = findViewById(R.id.DISPLAY);
        btAdd = findViewById(R.id.buttonAdd);
        btClear = findViewById(R.id.buttonClear);
        btRemove = findViewById(R.id.buttonDelete);
        lv = findViewById(R.id.listView);
        spn = findViewById(R.id.spinner2);

        doList = new ArrayList<>();
        aaDo = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,doList);
        lv.setAdapter(aaDo);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newDo = ed.getText().toString();
                doList.add(newDo);
                aaDo.notifyDataSetChanged();
            }
        });


        btRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (doList.size() == 0){
                    Toast.makeText(MainActivity.this,"You don't have any task to remove",Toast.LENGTH_SHORT).show();
                }
                else {
                    newDo = ed.getText().toString();
                    boolean digitsOnly = TextUtils.isDigitsOnly(ed.getText());
                    if (digitsOnly){
                        pos = Integer.parseInt(ed.getText().toString());
                        if(pos>doList.size()){
                            Toast.makeText(MainActivity.this,"Wrong index Number",Toast.LENGTH_SHORT).show();
                        }else {
                            doList.remove(Integer.parseInt(ed.getText().toString())-1);
                            aaDo.notifyDataSetChanged();
                            return;
                        }


                    }
                    else {
                        Toast.makeText(MainActivity.this,"Please enter index number",Toast.LENGTH_SHORT).show();
                        return;

                    }



                }
            }
        });

        btClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doList.clear();
                aaDo.notifyDataSetChanged();
            }
        });

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        btAdd.setEnabled(true);
                        btRemove.setEnabled(false);
                        ed.setHint("Type in a new here");
                        break;

                    case 1:
                        btAdd.setEnabled(false);
                        btRemove.setEnabled(true);
                        ed.setHint("Type in the index of task to be remove");
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

}

package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> cities = new ArrayList<>();
    private ArrayAdapter<String> ad;

    private int pick = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // 你 activity_main.xml 那个

        ListView lv = findViewById(R.id.city_list);
        Button addBtn = findViewById(R.id.btn_add);
        Button delBtn = findViewById(R.id.btn_delete);
        Button okBtn  = findViewById(R.id.btn_confirm);
        EditText et   = findViewById(R.id.et_city);

        cities.add("Edmonton");
        cities.add("Montréal");

        ad = new ArrayAdapter<>(this, R.layout.content, cities);
        lv.setAdapter(ad);

        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lv.setOnItemClickListener((p, v, pos, id) -> {
            pick = pos;
            lv.setItemChecked(pos, true);
        });

        addBtn.setOnClickListener(v -> {
            et.setVisibility(View.VISIBLE);
            okBtn.setVisibility(View.VISIBLE);
            et.requestFocus();
        });

        okBtn.setOnClickListener(v -> {
            String s = et.getText().toString();
            if (s != null) s = s.trim();

            if (s == null || s.length() == 0) {
                Toast.makeText(this, "type something", Toast.LENGTH_SHORT).show();
                return;
            }

            cities.add(s);
            ad.notifyDataSetChanged();

            et.setText("");
            et.setVisibility(View.GONE);
            okBtn.setVisibility(View.GONE);
        });

        delBtn.setOnClickListener(v -> {
            if (pick < 0 || pick >= cities.size()) {
                Toast.makeText(this, "tap a city first", Toast.LENGTH_SHORT).show();
                return;
            }

            cities.remove(pick);
            ad.notifyDataSetChanged();

            lv.clearChoices();
            pick = -1;
        });
    }
}
